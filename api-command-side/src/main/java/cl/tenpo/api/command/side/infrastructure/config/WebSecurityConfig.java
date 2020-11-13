package cl.tenpo.api.command.side.infrastructure.config;

import cl.tenpo.api.command.side.infrastructure.api.filters.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * Mantiene la configuracion de la aplicacion y los beans correspondientes.
 *
 * @author daniel.carvajal
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                // swagger
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                // this disables session creation on Spring Security
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService))
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
