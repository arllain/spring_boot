package com.arllain.UserRegistrationSystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.arllain.UserRegistrationSystem.service.UserInfoDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration_Database extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserInfoDetailsService userInfoDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
			authenticationManagerBuilder.userDetailsService(userInfoDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests().antMatchers("/api/user/**").authenticated()
		.and().authorizeRequests().antMatchers("/h2-console/**")
		.authenticated().and().httpBasic()
		.realmName("Sistema de Registro de Usuario").and().csrf().disable();		
	}
	
	
}
