package org.ntvru.rucast.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	    @Autowired
	    private UserDetailsService userDetailsService;
	 
	 
		
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			
			return new BCryptPasswordEncoder();
		}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()	
		.antMatchers("/rssfeed/**","/webjars/**", "/registration","/downloads/files/**").permitAll()
		.antMatchers("/").hasAnyRole("EDITOR")
		.antMatchers("/index","/upload/**").hasAnyRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.permitAll()
		.and()
		.rememberMe()
		.and()
		.csrf()
		.disable();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder
		.inMemoryAuthentication()
		.withUser("genesis").password("$2a$10$7CbNjdc1SIrrHKDO5VywVu69xUK3gYZvvOTpmOUhlAGe9IUxbJZs2").roles("EDITOR","ADMIN")
		.and()
		.withUser("lima").password("$2a$10$l4UtQTONoBqh2RErK0y6HOxeBzIj/5qghixv1x1me7jsJnoVoR57O").roles("EDITOR");
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
	}
	
	

}
