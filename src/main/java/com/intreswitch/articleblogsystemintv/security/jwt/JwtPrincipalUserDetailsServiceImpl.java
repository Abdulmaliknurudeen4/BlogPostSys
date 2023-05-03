package com.intreswitch.articleblogsystemintv.security.jwt;

import com.intreswitch.articleblogsystemintv.entities.Author;
import com.intreswitch.articleblogsystemintv.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtPrincipalUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	AuthorRepository authorRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Author author = authorRepository.findByEmail(username);
		if (author == null) {
			throw new UsernameNotFoundException(String.format("No User found with username '%s'.", username));
		} else {
			return JwtuserFactory.create(author);
		}

	}

}
