package lv.nixx.poc.rest.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		auth.inMemoryAuthentication()
				.passwordEncoder(encoder)
				.withUser("quest").password(encoder.encode("quest_pass")).roles("USER")
				.and()
				.withUser("nixx").password(encoder.encode("nixx_pass")).roles("ADMIN");

		SecurityContextHolder.clearContext();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.httpBasic().and().authorizeRequests().
		 antMatchers(HttpMethod.GET, "/person").permitAll().
		 antMatchers(HttpMethod.GET, "/swagger-resources").permitAll().
		 antMatchers(HttpMethod.POST, "/person/**").hasRole("ADMIN").
		 antMatchers(HttpMethod.PUT, "/person/**").hasRole("ADMIN").
		 antMatchers(HttpMethod.DELETE, "/person/**").hasRole("ADMIN").
		 antMatchers(HttpMethod.GET, "/person/**").hasRole("ADMIN").
		 and().csrf().disable();
	}
	
	
	
}
