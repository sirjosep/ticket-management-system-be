package com.lawencon.ticketjosep.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.ticketjosep.dto.ErrorResDto;
import com.lawencon.ticketjosep.service.JwtService;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

	private JwtService jwtService;

	private List<RequestMatcher> matchers;

	AuthorizationFilter(JwtService jwtService, List<RequestMatcher> matchers) {
		this.jwtService = jwtService;
		this.matchers = matchers;
	} 

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final long count = matchers.stream().filter(m -> m.matches(request)).collect(Collectors.counting());
		if (count == 0) {
			final String header = request.getHeader("Authorization");
			if (header != null) {
				final String jwt = header.replaceFirst("Bearer ", "");
				try {					
					final Map<String, Object> map = jwtService.parseJwt(jwt);
					
					final Authentication auth = new UsernamePasswordAuthenticationToken(map, null);
					SecurityContextHolder.getContext().setAuthentication(auth);
					
				} catch (Exception e) {   
					e.printStackTrace();
					response.setStatus(401);
					
					final ErrorResDto<String> errorRes = new ErrorResDto<>();
					errorRes.setMessage("Token Expired");   
					response.getWriter().append(new ObjectMapper().writeValueAsString(errorRes));
					return;
				}
			} else {
				response.setStatus(401);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}

}