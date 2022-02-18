package com.nimuairy.ticket;

import com.nimuairy.auth.models.User;
import com.nimuairy.auth.security.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

	TicketRepository ticketRepository;
	UserService userService;

	@Override
	public TicketResponse generateTicket() {

		User user = userService.currentUser();
		String ticketId = UUID.randomUUID().toString();
		ticketRepository.save(new Ticket(ticketId, user.getId()));

		return new TicketResponse(ticketId);
	}

	@Override
	public Optional<Long> getUserIdByAccessToken(String token) {
		return ticketRepository.findById(token).map(Ticket::getUserId);
	}
}
