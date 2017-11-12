package com.example.bookservice;

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
    public void configureGlobal1(AuthenticationManagerBuilder auth) throws Exception {
        //try in memory auth with no users to support the case that this will allow for users that are logged in to go anywhere
        auth.inMemoryAuthentication();
    }
	
	@Override
	protected void configure(HttpSecurity http){
		
		try {
			http.httpBasic().disable().authorizeRequests()
				.antMatchers("/books").permitAll()
				.antMatchers("/books/*").hasAnyRole("USER", "ADMIN")
					.anyRequest().authenticated()
					.and()
					.csrf()
						.disable();
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
}
