package com.nimuairy;

import com.nimuairy.dtos.LoadConversationDto;
import com.nimuairy.models.Message;
import com.nimuairy.ticket.TicketResponse;
import com.nimuairy.ticket.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

	final TicketServiceImpl ticketService;
	final ConversationServiceImpl conversationService;
	final MessageService messageService;

	@GetMapping("/tickets")
	public ResponseEntity<TicketResponse> getChatTicket() {

		return ResponseEntity.ok(ticketService.generateTicket());
	}

	@GetMapping("/conversations")
	public ResponseEntity<LoadConversationDto> getConversationWithUser(@RequestParam Long interlocutorId) {

		return ResponseEntity.ok(conversationService.loadConversationWithUser(interlocutorId));
	}

	@GetMapping("/conversations/{conversationId}/messages")
	public ResponseEntity<List<Message>> getConversationMessages(@PathVariable Long conversationId,
																 @RequestParam Long from,
																 @RequestParam Long to) {

		return ResponseEntity.ok(messageService.getMessages(conversationId, from, to));
	}
}
