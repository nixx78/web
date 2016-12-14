package lv.nixx.poc.rest.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().
				withUser("quest").password("quest_pass").roles("USER").and().//
				withUser("nixx").password("nixx_pass").roles("USER", "ADMIN");
		
		SecurityContextHolder.clearContext();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.httpBasic().and().authorizeRequests().//
		 antMatchers(HttpMethod.GET, "/person").permitAll().
		 //		 antMatchers(HttpMethod.POST, "/person/**").hasRole("ADMIN").
//		 antMatchers(HttpMethod.PUT, "/person/**").hasRole("ADMIN").
//		 antMatchers(HttpMethod.DELETE, "/person/**").hasRole("ADMIN").
//		 antMatchers(HttpMethod.GET, "/person/**").hasRole("USER").
		 and().csrf().disable();
	}
}
