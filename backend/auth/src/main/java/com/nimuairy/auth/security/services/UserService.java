package com.nimuairy.auth.security.services;

import com.nimuairy.auth.dto.SimpleUserInfo;
import com.nimuairy.auth.models.User;
import com.nimuairy.auth.payload.request.CreateUserRequest;
import com.nimuairy.auth.payload.request.LoginRequest;
import com.nimuairy.auth.payload.request.SignupRequest;
import com.nimuairy.auth.payload.response.JwtResponse;

import java.util.List;

public interface UserService {

	User createUser(CreateUserRequest createUserRequest);

	JwtResponse loginUser(LoginRequest loginRequest);

	User registerUser(SignupRequest signUpRequest);

	User currentUser();

	List<SimpleUserInfo> getUsers(List<Long> userIds);
}
