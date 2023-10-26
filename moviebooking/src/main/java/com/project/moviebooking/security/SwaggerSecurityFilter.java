package com.project.moviebooking.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.moviebooking.constant.Constants;

import lombok.Setter;
import lombok.val;

/**
 *
 * @author Rohit
 */
@Component
public class SwaggerSecurityFilter extends OncePerRequestFilter {

    List<String> excludeUrlPatterns;
    
    @Setter
    List<String> includeUrlPatterns;

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    private static final Logger LOGGER = LogManager.getLogger(SwaggerSecurityFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String locale = request.getHeader(Constants.LOCALE_HEADER);

		if (!shouldNotFilter(request)) {
			// JWT Token is in the form "Basic base64-credentials". Decode and split.
			if (authHeader != null && authHeader.startsWith("Basic ")) {
				String base64Credentials = authHeader.substring(6);
				String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
				String[] split = credentials.split(":");
				String username = split[0];
				String password = split[1];

				if ("admin".equals(username) && "rohitshiv".equals(password)) {
					// The provided credentials are valid; proceed with the request.
					filterChain.doFilter(request, response);
				} else {
					// Invalid credentials; send a 401 Unauthorized response and request new
					// credentials.
					response.setHeader("WWW-Authenticate", "Basic realm=\"Secure Area\"");
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				}
			} else {
				// No or invalid Authorization header; send a 401 Unauthorized response and
				// request credentials.
				response.setHeader("WWW-Authenticate", "Basic realm=\"Secure Area\"");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} else {
			// No need to perform authentication; proceed with the request.
			filterChain.doFilter(request, response);
		}
	}

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    	if(includeUrlPatterns!=null) {
    		
        val include = includeUrlPatterns.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath()));

        if (include) {
            return false;
        }
        return true;
    }
		return true;
    }

}
