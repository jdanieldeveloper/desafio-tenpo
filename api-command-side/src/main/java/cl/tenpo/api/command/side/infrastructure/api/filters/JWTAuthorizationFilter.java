package cl.tenpo.api.command.side.infrastructure.api.filters;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static cl.tenpo.api.command.side.infrastructure.util.UtilJwtToken.extractUsername;
import static cl.tenpo.api.command.side.infrastructure.util.UtilJwtToken.resolveToken;
import static cl.tenpo.api.command.side.infrastructure.util.UtilJwtToken.validateToken;

@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private UserDetailsService userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String token = resolveToken(request);
        if (Objects.nonNull(token)) {
            //
            String userName = extractUsername(token);
            // get user details
            final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            boolean isValid = validateToken(token, userDetails);
            // check
            if (isValid) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else {
                chain.doFilter(request, response);
            }
        }
        chain.doFilter(request, response);
    }
}
