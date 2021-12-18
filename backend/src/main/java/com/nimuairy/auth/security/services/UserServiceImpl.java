package com.nimuairy.auth.security.services;

import com.nimuairy.auth.exception.EmailTakenException;
import com.nimuairy.auth.exception.UsernameTakenException;
import com.nimuairy.auth.models.ERole;
import com.nimuairy.auth.models.RefreshToken;
import com.nimuairy.auth.models.Role;
import com.nimuairy.auth.models.User;
import com.nimuairy.auth.payload.request.LoginRequest;
import com.nimuairy.auth.payload.request.SignupRequest;
import com.nimuairy.auth.payload.response.JwtResponse;
import com.nimuairy.auth.repository.RoleRepository;
import com.nimuairy.auth.repository.UserRepository;
import com.nimuairy.auth.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	final AuthenticationManager authenticationManager;
	final PasswordEncoder encoder;
	final JwtUtils jwtUtils;
	final RefreshTokenServiceImpl refreshTokenService;
	final RoleRepository roleRepository;
	final UserRepository userRepository;

	public JwtResponse loginUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String jwt = jwtUtils.generateJwtToken(userDetails);

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

		return new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
				userDetails.getUsername(), userDetails.getEmail(), roles);
	}

	public User registerUser(SignupRequest signUpRequest) {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new UsernameTakenException(signUpRequest.getUsername());
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new EmailTakenException(signUpRequest.getEmail());
		}

		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<Role> roles = getRoles(signUpRequest);
		user.setRoles(roles);
		userRepository.save(user);

		return user;
	}


	private Set<Role> getRoles(SignupRequest signUpRequest) {

		Set<String> stringRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (stringRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			stringRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "mod":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);

						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}
		return roles;
	}
}
