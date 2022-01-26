package com.nimuairy.ticket;

import com.nimuairy.auth.models.User;
import com.nimuairy.auth.security.services.UserService;
import com.nimuairy.cache.CacheRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class TicketServiceImpl {

	UserService userService;
	CacheRepository cacheRepository;

	public TicketResponse generateTicket() {

		User user = userService.currentUser();
		String ticket = UUID.randomUUID().toString();
		cacheRepository.putTicket(ticket, user.getId());

		return new TicketResponse(ticket);
	}
}
