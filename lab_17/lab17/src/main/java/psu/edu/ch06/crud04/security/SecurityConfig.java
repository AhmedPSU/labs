package psu.edu.ch06.crud04.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			// CSRF is not needed: no sessions/cookies, credentials are sent with every request
			.csrf(csrf -> csrf.disable())
			// every API endpoint requires authentication
			.authorizeHttpRequests(requests -> requests
					.anyRequest().authenticated())
			// HTTP Basic authentication (Authorization: Basic base64(username:password))
			.httpBasic(Customizer.withDefaults())
			// stateless: the server keeps no session between requests
			.sessionManagement(session -> session
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

	// passwords in the users table are stored as BCrypt hashes
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
