package com.intreswitch.articleblogsystemintv.security.jwt;

import java.util.Collection;

import com.intreswitch.articleblogsystemintv.entities.Author;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
public class JwtUserPrincipal implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Integer id;
	private final String username;
	private final String password;
	private final Author author;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;

	public JwtUserPrincipal(Integer id,String username, String password, Author user,
			Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;
		this.author = user;
		this.authorities = authorities;
		this.enabled = enabled;
	}

	@JsonIgnore
	public long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	
	public Author getAuthor() {
		return author;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		//make correction using the date before to check if the user has expired
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

}
