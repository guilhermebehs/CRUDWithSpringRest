package br.com.guilhermebehs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.guilhermebehs.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
    UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userRepository.findByUsername(username);
		if(user != null)
		   return user;
		
		throw new UsernameNotFoundException("Username "+username+" not found");
	}
	
	

}
