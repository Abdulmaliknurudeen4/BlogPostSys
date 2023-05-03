package com.intreswitch.articleblogsystemintv.security.jwt;

import com.intreswitch.articleblogsystemintv.entities.Author;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtuserFactory {
	public static JwtUserPrincipal create(Author author) {
		return new JwtUserPrincipal(author.getAuthorId(), author.getEmail(), author.getPassword(), author,
				maptoGrantedAuthorities(new ArrayList<>(List.of("ROLE_AUTHOR"))),
				true);
	}

	private static List<GrantedAuthority> maptoGrantedAuthorities(List<String> auths) {
		// TODO Auto-generated method stub
		return auths.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

}
