package com.project.moviebooking.security;

import lombok.Setter;
import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.moviebooking.constant.Constants;
import com.project.moviebooking.constant.LocaleEnum;
import com.project.moviebooking.security.model.CustomUserDetail;
import com.project.moviebooking.security.util.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author bhavesh
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Setter
    List<String> excludeUrlPatterns;

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    private static final Logger LOGGER = LogManager.getLogger(JwtRequestFilter.class);

    @Autowired
    private CustomUserDetailService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        final String locale = request.getHeader(Constants.LOCALE_HEADER);

        String username = null;
        String jwtToken = null;
        try {
            if (!shouldNotFilter(request)) {

                // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
                if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                    jwtToken = requestTokenHeader.substring(7);
                    try {
                        username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                } else {
                    logger.warn("JWT Token does not begin with Bearer String");
                }

                //Once we get the token validate it.
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    CustomUserDetail userDetails = (CustomUserDetail) customUserDetailsService.loadUserByUsername(username);
                    // if token is valid configure Spring Security to manually set authentication
                    if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                        userDetails.setLocale(StringUtils.hasText(locale) ? LocaleEnum.valueOf(locale.toUpperCase())
                                : Constants.DEFAULT_LOCALE);

                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // After setting the Authentication in the context, we specify
                        // that the current user is authenticated. So it passes the Spring Security Configurations successfully.
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        val exclude = excludeUrlPatterns.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath()));

        if (exclude) {
            return true;
        }

        return false;
    }

}
