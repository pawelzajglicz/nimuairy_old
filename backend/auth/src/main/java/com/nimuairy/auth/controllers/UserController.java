package com.nimuairy.auth.controllers;

import com.nimuairy.auth.dto.SimpleUserInfo;
import com.nimuairy.auth.security.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

	private final UserService userService;

	@GetMapping("/{userIds}")
	private ResponseEntity<List<SimpleUserInfo>> getUsers(@PathVariable List<Long> userIds,
														  @RequestHeader(name = "User-Id") Long userId) {

		log.info("User with id {} is getting users with ids: {}", userId, userIds);
		List<SimpleUserInfo> users = userService.getUsers(userIds);
		log.info("User with id {} successfully get users with ids: {}", userId, userIds);

		return ResponseEntity.ok(users);
	}
}
