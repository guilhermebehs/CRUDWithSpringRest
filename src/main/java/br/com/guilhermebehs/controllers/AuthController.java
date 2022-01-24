package br.com.guilhermebehs.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.guilhermebehs.data.vos.AccountCredentialsVO;
import br.com.guilhermebehs.jwt.JwtTokenProvider;
import br.com.guilhermebehs.repositories.UserRepository;

@Tag(name= "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	UserRepository userRepository;
	
	@Operation(summary="Authenticate a user by credentials")
	@PostMapping(value = "/signin", produces = {"application/json", "application/xml"})
	@ResponseStatus(code=HttpStatus.CREATED)
    public ResponseEntity<Map<Object, Object>> create(@RequestBody() AccountCredentialsVO data){
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			var user = userRepository.findByUsername(username);
			var token = "";
			if(user == null)
				throw new UsernameNotFoundException("Username "+username+" not found");
				 
			token = tokenProvider.createToken(username, user.getRoles());	
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
			
		}
		catch(AuthenticationException e) {
		   throw new BadCredentialsException("Invalid username/password");	
		}
	}
}
