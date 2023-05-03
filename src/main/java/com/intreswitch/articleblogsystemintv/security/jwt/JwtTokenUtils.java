package com.intreswitch.articleblogsystemintv.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class JwtTokenUtils implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_AUDIENCE = "audience";
	static final String CLAIM_KEY_CREATED = "created";

	// private static final Logger logger =
	// LoggerFactory.getLogger(JwtTokenUtils.class);

	public String getUsernameFromToken(String token) {
		String username = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			username = null;
		}

		return username;
	}

	private Claims getClaimsFromToken(String token) {
		// TODO Auto-generated method stub
		Claims claim = null;
		try {
			claim = Jwts.parser().setSigningKey(JwtProperties.SECRET).parseClaimsJws(token).getBody();

		} catch (Exception e) {
			claim = null;
			// TODO: handle exception
		}
		return claim;
	}

	public boolean validateToken(String token, UserDetails userDetails) {

		JwtUserPrincipal jwtUserPrincipal = (JwtUserPrincipal) userDetails;
		final String username = getUsernameFromToken(token);

		return (username.equals(jwtUserPrincipal.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Date getExpirationDateFromToken(String token) {
		// TODO Auto-generated method stub
		Date expiration = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			if (claims != null) {
				expiration = claims.getExpiration();
			} else {
				expiration = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			expiration = null;
		}
		return expiration;
	}

	public String generateToken(JwtUserPrincipal userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}

	private String generateToken(Map<String, Object> claims) {
		// TODO Auto-generated method stub
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, JwtProperties.SECRET).compact();
	}

	private Date generateExpirationDate() {
		// TODO Auto-generated method stub
		return new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME * 1000);
	}
}
