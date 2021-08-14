package com.hackaton.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.dao.UserDAO;
import com.hackaton.model.Level;
import com.hackaton.model.User;
import com.hackaton.model.UserLevel;
import com.hackaton.repository.LevelRepo;
import com.hackaton.repository.UserRepo;
import com.hackaton.request.LoginForm;
import com.hackaton.request.SignUpForm;
import com.hackaton.response.LoginResponse;
import com.hackaton.response.Response;
import com.hackaton.service.AccountService;
import com.hackaton.service.UserService;
import com.hackaton.util.JwtUtil;

//Get Post Put Delete

//M- model  V- view  C - controller
@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class LoginController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	LevelRepo levelRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User user = (User) authentication.getPrincipal();

		String jwt = jwtUtil.generateToken(authentication);
		
		UserDAO userDAO = userService.getUserDAO(user);
		
		return ResponseEntity.ok(new LoginResponse(userDAO,jwt));
	}
	
	
	
	
	
	@PostMapping("/register")
	public ResponseEntity<Response> registerUser(@Valid @RequestBody SignUpForm signUpRequest){
		Response response = new Response();
		if(userRepo.existsByUsername(signUpRequest.getUsername())){
			response.setMessage("Username is Taken");
			response.setSuccess(false);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		if(userRepo.existsByEmail(signUpRequest.getEmail())){
			response.setMessage("The Email is Taken");
			response.setSuccess(false);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		if(userRepo.existsByEmail(signUpRequest.getPhone())){
			response.setMessage("The Phone is already registered");
			response.setSuccess(false);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		
		
		User user = new User(signUpRequest.getFirstName(),
				signUpRequest.getLastName(),
				signUpRequest.getUsername(),
				signUpRequest.getDob(),
		signUpRequest.getEmail(),
		signUpRequest.getPassword(),
		signUpRequest.getGender(),
		signUpRequest.getReligion(),
		signUpRequest.getMother_tongue(),
		signUpRequest.getCountry(),
		signUpRequest.getState(),
		signUpRequest.getCity());
		
				

		Set<UserLevel> userRoles = new HashSet<>();
		Set<String> strRoles = signUpRequest.getLevel();
		strRoles.forEach(roleName -> {
			Level level = levelRepo.findByName(roleName).orElseThrow(() -> new RuntimeException("User Role not found."));
			userRoles.add(new UserLevel(user, level));
		});
		user.setUserLevels(userRoles);
		user.setAccount(accountService.createAccount());
		userRepo.save(user);
		response.setMessage("User Registered Successfully!");
		response.setSuccess(true);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	


}
