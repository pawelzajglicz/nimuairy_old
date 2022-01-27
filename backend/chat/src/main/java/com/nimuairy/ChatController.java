package com.nimuairy;

import com.nimuairy.dtos.LoadConversationDto;
import com.nimuairy.models.Conversation;
import com.nimuairy.ticket.TicketResponse;
import com.nimuairy.ticket.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

	final TicketServiceImpl ticketService;
	final ConversationServiceImpl conversationService;

	@GetMapping("/ticket")
	public ResponseEntity<TicketResponse> getChatTicket() {

		return ResponseEntity.ok(ticketService.generateTicket());
	}

	@GetMapping("/conversation")
	public ResponseEntity<LoadConversationDto> getConversationWithUser(@RequestParam Long interlocutorId) {

		return ResponseEntity.ok(conversationService.loadConversationWithUser(interlocutorId));
	}
}
