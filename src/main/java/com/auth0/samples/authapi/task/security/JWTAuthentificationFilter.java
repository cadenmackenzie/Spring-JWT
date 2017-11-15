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

/**
 * Nice typo on the class :D This class is the JWT maker
 */
public class JWTAuthentificationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthentificationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	// Read the value and pass the ball to the authentication manager

	/**
	 * Read the values given and check if they are right and return Authentication token (which can lead to the creation of
	 * a JWT token)
	 * @param req Getting the info from the http request
	 * @param res Getting the info from the http request
	 * @return Token for Authentication (if set Authenticated property is true then JWT builder will create it)
	 * @throws AuthenticationException Used when the given Object is invalid
	 */
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

	/**
	 * This method check the Authentication token and create the JWT token
	 * @param req Getting the info from the http request
	 * @param res Getting the info from the http request
	 * @param chain The chain of filters being invoked
	 * @param auth the authentication token
	 * @throws IOException I/O issue
	 * @throws ServletException Very general exception when the Servlet is having issues
	 */
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
