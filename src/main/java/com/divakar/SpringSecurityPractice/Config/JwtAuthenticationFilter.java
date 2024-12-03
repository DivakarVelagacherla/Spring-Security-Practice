package com.divakar.SpringSecurityPractice.Config;

import ch.qos.logback.core.util.StringUtil;
import com.divakar.SpringSecurityPractice.Service.JwtUtil;
import com.divakar.SpringSecurityPractice.Service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String userEmail;
        if (authHeader == null && StringUtils.isEmpty(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }
        token = authHeader.substring(7);
        userEmail = jwtUtil.extractUserName(token);

        if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

            if (jwtUtil.validateToken(token, userDetails)) {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken newToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                securityContext.setAuthentication(newToken);
                SecurityContextHolder.setContext(securityContext);

            }
        }
        filterChain.doFilter(request,response);
    }
}
