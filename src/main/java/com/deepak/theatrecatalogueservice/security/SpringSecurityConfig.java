package com.deepak.theatrecatalogueservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER")
				.and().withUser("admin").password("{noop}root").roles("USER","ADMIN");

	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()      
                .antMatchers(HttpMethod.GET, "/theatres").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST, "/theatres/**").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST, "/bookseats").permitAll() 
                .antMatchers(HttpMethod.POST, "/addTheatre").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/addShow").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/updateTheatre").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/deleteTheatre").hasAnyRole("ADMIN")
                .anyRequest().authenticated().and()
                .csrf().disable()
                .formLogin().disable();;
    }
}
