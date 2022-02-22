package com.nimuairy.ticket;

import java.util.Optional;

public interface TicketService {
	TicketResponse generateTicket();

	Optional<Long> getUserIdByAccessToken(String token);
}
