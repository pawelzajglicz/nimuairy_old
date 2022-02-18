package com.nimuairy;

import com.nimuairy.auth.dto.SimpleUserInfo;
import com.nimuairy.auth.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contacts")
public class ContactsController {

	ContactsServiceImpl contactsService;

	@GetMapping("/{userId}")
	public ResponseEntity<Set<SimpleUserInfo>> getUserContacts(@PathVariable Long userId) {

		return ResponseEntity.ok(contactsService.getUserContacts(userId));
	}
}
