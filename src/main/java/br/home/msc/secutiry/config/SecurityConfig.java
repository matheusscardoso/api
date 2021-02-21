package br.home.msc.secutiry.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.home.msc.security.jwt.JwtAuthenticationFilter;
import br.home.msc.security.jwt.JwtAuthorizationFilter;
import br.home.msc.security.jwt.handler.UnauthorizedHandler;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private UnauthorizedHandler unauthorizedHandler;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	public final String URL_JWT = "/api/v1/login";

//	private final String[] URL_H2 = { "/console/**" };
	private final String[] URLS_PUBLIC = { "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		AuthenticationManager authManager = authenticationManager();	
		
		
		http.authorizeRequests()
		.antMatchers("/status").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/swagger-ui.html").permitAll()
        .antMatchers(HttpMethod.GET, "/api/v1/login").permitAll()
        .antMatchers(URLS_PUBLIC)
        .permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable()
        .addFilter(new JwtAuthenticationFilter(authManager))
        .addFilter(new JwtAuthorizationFilter(authManager, userDetailsService))
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler)
        .authenticationEntryPoint(unauthorizedHandler)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}

}
