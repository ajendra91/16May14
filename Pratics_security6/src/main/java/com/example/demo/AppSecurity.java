package com.example.demo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter{
	
	 static final long TOKEN_LIFETIME = 604_800_000;
	 static final String TOKEN_PREFIX = "Bearer ";
	 static final String TOKEN_SECRET = "ThisIsOurSecretKeyToSignOurTokens";
	
	@Resource
	public UserDetailsService uds;
	
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
	
	 @Bean
	 public TokenAuthenticationFilter tokenAuthenticationFilter() {
	    return new TokenAuthenticationFilter();
	 }
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		auth.userDetailsService(uds).passwordEncoder(noop());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/").permitAll();
		
		//http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//http.exceptionHandling().authenticationEntryPoint(new RESTAuthenticationEntryPoint());
		
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		//ise lagane par automatic mylogin page nahi aayega
		
		
		http.authorizeRequests().anyRequest()
		.authenticated().and().formLogin().loginPage("/mylogin")
		.loginProcessingUrl("/log").usernameParameter("name")
		.passwordParameter("pass").defaultSuccessUrl("/").permitAll();
		
		http.logout().logoutUrl("/mylogout").logoutSuccessUrl("/").permitAll();
		
		http.formLogin().successHandler(authenticationSuccessHandler);
		
		http.addFilter(new TokenBasedAuthenticationFilter(authenticationManager()))
        .addFilter(new TokenBasedAuthorizationFilter(authenticationManager()));
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
	
	@Bean
	public NoOpPasswordEncoder noop() {
		return (NoOpPasswordEncoder)NoOpPasswordEncoder.getInstance();
	}
	
	

}
