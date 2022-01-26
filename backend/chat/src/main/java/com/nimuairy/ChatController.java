package com.nimuairy;

import com.nimuairy.ticket.TicketResponse;
import com.nimuairy.ticket.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

	final TicketServiceImpl ticketService;

	@GetMapping("/ticket")
	public ResponseEntity<TicketResponse> getChatTicket() {

		return ResponseEntity.ok(ticketService.generateTicket());
	}
}
