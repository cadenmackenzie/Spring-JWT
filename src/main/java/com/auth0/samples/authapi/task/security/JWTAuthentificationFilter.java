package com.auth0.samples.authapi.task.security;

import com.auth0.samples.authapi.task.model.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.samples.authapi.task.util.SecurityConstant.*;

/**
 * Created by Thomas Leruth on 11/9/17
 */

// the JWT maker, having attempt checking if creds are right then passing it to the JWT maker below via the
// the authenticate method from the manager
public class JWTAuthentificationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthentificationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	// check if the creds are good and pass the ball to the maker
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			AppUser creds = new ObjectMapper().readValue(req.getInputStream(), AppUser.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
							creds.getUsername(),
							creds.getPassword(),
							new ArrayList<>())
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// jwt builder, pretty simple with almost no payload could be added with .claim()
	@Override
	protected void successfulAuthentication
	(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth)
			throws IOException, ServletException {

		String token = Jwts.builder()
				.setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
}
