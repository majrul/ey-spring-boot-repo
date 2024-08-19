package com.training;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

	@Value("${app.eureka.admin.username}")
	private String username;
	
	@Value("${app.eureka.admin.password}")
	private String password;
	
	@Bean
	public UserDetailsManager userDetailsService() {
		return new InMemoryUserDetailsManager(User.withUsername(username).password(password).roles("USER").build());
	}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.securityMatcher("/eureka/**")
				.httpBasic(withDefaults())
		        .authorizeHttpRequests(authConfig -> authConfig.requestMatchers("/eureka/**").authenticated().anyRequest().permitAll())				
				.build();
	}
}
