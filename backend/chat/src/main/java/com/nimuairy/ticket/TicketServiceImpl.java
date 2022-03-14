package com.nimuairy.ticket;

import com.nimuairy.jwt.JWTStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

	TicketRepository ticketRepository;
	JWTStore jwtStore;

	@Override
	public TicketResponse generateTicket() {

		String ticketId = UUID.randomUUID().toString();
		ticketRepository.save(new Ticket(ticketId, Long.valueOf(jwtStore.loadUserId())));

		return new TicketResponse(ticketId);
	}

	@Override
	public Optional<Long> getUserIdByAccessToken(String token) {
		return ticketRepository.findById(token).map(Ticket::getUserId);
	}
}
