package com.carlease.restapi.filter;

import com.carlease.restapi.service.CustomUserDetailsService;
import com.carlease.restapi.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private JwtService jwtUtil;

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws ServletException, IOException {
    final String authenticationScheme = "Bearer ";
    final String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader == null || !authorizationHeader.startsWith(authenticationScheme)) {
      chain.doFilter(request, response);
      return;
    }

    try {
      final String jwt = authorizationHeader.substring(authenticationScheme.length());
      final String username = jwtUtil.extractUsername(jwt);
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

        if (jwtUtil.validateToken(jwt, userDetails)) {
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
          usernamePasswordAuthenticationToken
              .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
      }
      chain.doFilter(request, response);
    } catch (Exception e) {
      //TODO
    }
  }
}