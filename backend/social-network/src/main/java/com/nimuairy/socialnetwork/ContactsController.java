package com.nimuairy.socialnetwork;

import com.nimuairy.socialnetwork.dto.ContactsInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/contacts")
public class ContactsController {

	ContactsServiceImpl contactsService;

	@GetMapping("/{userId}")
	public ResponseEntity<List<ContactsInfo>> getUserContacts(@PathVariable Long userId) {
		log.info("Getting contacts for user with id: {}", userId);
		return ResponseEntity.ok(contactsService.getUserContacts(userId));
	}
}
