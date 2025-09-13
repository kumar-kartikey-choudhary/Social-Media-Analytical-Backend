package com.socialanalyzer.configuration;

import com.socialanalyzer.Util.JwtUtil;
import com.socialanalyzer.service.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailService detailService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailService detailService)
    {
        this.jwtUtil = jwtUtil;
        this.detailService = detailService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Inside @class JwtAuthenticationFilter @method doFilterInternal");
        String header = request.getHeader("Authorization");

        String username = null ;
        String token = null;

        if(header != null && header.startsWith("Bearer "))
        {
              token =  header.substring(7);
              try {
                  username = this.jwtUtil.extractUsername(token);
              } catch (UsernameNotFoundException e) {
                  throw new UsernameNotFoundException("USER_NOT_FOUND");
              }
        }
        else {
            log.warn("Authorization header is missing or does not start with Bearer");
        }

        if(username != null && SecurityContextHolder.getContext() == null)
        {
            try {
                UserDetails userDetails = detailService.loadUserByUsername(username);
                if(this.jwtUtil.validateToken(token, userDetails))
                {
                    UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.getAuthorities());
                    authenticated.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticated);
                }
                else
                {
                    log.error("Jwt validation failed !!!!");
                }
            } catch (UsernameNotFoundException e) {
                log.error("User not found for username: {}", username);
                filterChain.doFilter(request, response);
                return;
            }
        }

    }
}
