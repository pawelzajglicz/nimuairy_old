package com.nimuairy.cache;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CacheRepositoryImpl implements CacheRepository {

	TicketRepository ticketRepository;

	@Override
	public void putTicket(String token, Long userId) {
		ticketRepository.save(new Ticket(token, userId));
	}

	@Override
	public Optional<Long> getUserIdByAccessToken(String token) {
		return ticketRepository.findById(token).map(ticket -> ticket.getUserId());
	}
}