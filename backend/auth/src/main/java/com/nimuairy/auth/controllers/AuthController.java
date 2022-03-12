package com.nimuairy.auth.controllers;

import com.nimuairy.auth.exception.EmailTakenException;
import com.nimuairy.auth.exception.ErrorMessage;
import com.nimuairy.auth.exception.UsernameTakenException;
import com.nimuairy.auth.models.User;
import com.nimuairy.auth.payload.request.LoginRequest;
import com.nimuairy.auth.payload.request.CreateUserRequest;
import com.nimuairy.auth.payload.request.SignupRequest;
import com.nimuairy.auth.payload.request.TokenRefreshRequest;
import com.nimuairy.auth.payload.response.JwtResponse;
import com.nimuairy.auth.payload.response.TokenRefreshResponse;
import com.nimuairy.auth.repository.RoleRepository;
import com.nimuairy.auth.repository.UserRepository;
import com.nimuairy.auth.security.jwt.JwtUtils;
import com.nimuairy.auth.security.services.RefreshTokenService;
import com.nimuairy.auth.security.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {

	final AuthenticationManager authenticationManager;
	final JwtUtils jwtUtils;
	final PasswordEncoder encoder;
	final RefreshTokenService refreshTokenService;
	final RoleRepository roleRepository;
	final UserRepository userRepository;
	final UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		System.out.println("+++++++++++++++++++ signin");

		return ResponseEntity.ok(userService.loginUser(loginRequest));
	}

	@ApiOperation(value = "Register user", notes = "Endpoint for creating new users")
	@PostMapping("/signup")
	public ResponseEntity<User> registerUser(@ApiParam(value = "signup request") @Valid @RequestBody SignupRequest signUpRequest) {

		System.out.println("+++++++++++++++++++ signup");

		return ResponseEntity.ok(userService.registerUser(signUpRequest));
	}

	@PostMapping("/create-user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<User> createUser(@ApiParam(value = "signup request") @Valid @RequestBody CreateUserRequest createUserRequest) {

		return ResponseEntity.ok(userService.createUser(createUserRequest));
	}


	@PostMapping("/refreshtoken")
	public ResponseEntity<TokenRefreshResponse> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {

		return ResponseEntity.ok(refreshTokenService.refreshToken(request));
	}

	@ExceptionHandler({EmailTakenException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage emailTakenError(EmailTakenException exc, WebRequest request) {

		log.info("There was attempt to register at taken email address: {}" + exc.getEmail());
		return new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				exc.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler({UsernameTakenException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage usernameTakenError(UsernameTakenException exc, WebRequest request) {

		log.info("There was attempt to register at taken username: {}" + exc.getUsername());
		return new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				exc.getMessage(),
				request.getDescription(false));
	}
}
