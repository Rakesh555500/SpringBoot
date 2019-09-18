package com.sogeti.api.filmland.controller;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.api.filmland.config.JwtTokenUtil;
import com.sogeti.api.filmland.exception.InvalidCredentialsException;
import com.sogeti.api.filmland.exception.UserDisabledException;
import com.sogeti.api.filmland.exception.UserExistsException;
import com.sogeti.api.filmland.model.ApiResponse;
import com.sogeti.api.filmland.model.LoginResponse;
import com.sogeti.api.filmland.model.UserInfo;
import com.sogeti.api.filmland.model.UserInfoDto;
import com.sogeti.api.filmland.service.FilmLandUserService;

@RestController
@CrossOrigin
public class UserServiceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private FilmLandUserService userDetailsService;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody UserInfoDto userInfoDto)
			throws UserDisabledException, InvalidCredentialsException {
		authenticate(userInfoDto.getUsername(), userInfoDto.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userInfoDto.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		LoginResponse apiResponse = new LoginResponse(token);
		apiResponse.setStatus("Login Successfull");
		apiResponse.setMessage(userInfoDto.getUsername() + " logged in successfully");
		return ResponseEntity.ok(apiResponse);
	}

	private void authenticate(String username, String password)
			throws UserDisabledException, InvalidCredentialsException {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			LOGGER.error("{} is disabled", username, e);
			throw new UserDisabledException(username + " is disabled");
		} catch (BadCredentialsException e) {
			LOGGER.error("Credentials entered are invalid", e);
			throw new InvalidCredentialsException("Credentials entered are invalid");
		}
	}

	@PostMapping(value = "/registerUser")
	public ResponseEntity<ApiResponse> addNewUser(@RequestBody UserInfoDto userInfoDto) throws UserExistsException {
		UserInfo userAdded = userDetailsService.registerNewUser(convertToEntity(userInfoDto));
		ApiResponse apiResponse = new ApiResponse();
		if (userAdded == null) {
			apiResponse.setStatus("User registration failed");
			apiResponse.setMessage("User " + userInfoDto.getUsername() + "failed to register");
		} else {
			apiResponse.setStatus("User registration successfull");
			apiResponse.setMessage("User " + userInfoDto.getUsername() + "register successfully");
		}
		return ResponseEntity.ok(apiResponse);
	}

	private UserInfo convertToEntity(UserInfoDto userInfoDto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(userInfoDto, UserInfo.class);
	}
}
