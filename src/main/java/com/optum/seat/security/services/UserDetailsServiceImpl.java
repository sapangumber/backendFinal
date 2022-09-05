package com.optum.seat.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optum.seat.models.User;
import com.optum.seat.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String empID) throws UsernameNotFoundException {
	    User user = userRepository.findByEmpID(empID)
	        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with Employee ID: " + empID));
	    return UserDetailsImpl.build(user);
	}

}
