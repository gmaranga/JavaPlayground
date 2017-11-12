package com.example.ratingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth){
		
		try {
			auth.inMemoryAuthentication().withUser("user").password("password")
			.roles("USER").and().withUser("admin").password("admin")
			.roles("ADMIN");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	@Override
	protected void configure(HttpSecurity http){
		
		try {
			http.httpBasic().disable().authorizeRequests()
				.antMatchers("/ratings").permitAll()
				.antMatchers("/books/all").hasAnyRole("USER", "ADMIN")
					.anyRequest().authenticated().and().csrf().disable();
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
}
