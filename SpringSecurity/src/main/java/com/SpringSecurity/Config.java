package com.SpringSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {

	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin = User.withUsername("Sriram10").password(encoder.encode("sri10")).roles("Admin").build();
		UserDetails staff = User.withUsername("Raji").password(encoder.encode("raji16")).roles("Staff").build();
		return new InMemoryUserDetailsManager(admin, staff);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests().requestMatchers("/getBatter").hasAnyRole("Admin", "Staff").and()
				.authorizeHttpRequests().requestMatchers("/getBowler").hasRole("Staff").and().formLogin().and().build();
//		return http
//				.authorizeHttpRequests(
//						((authorize) -> authorize.requestMatchers("/login").permitAll().anyRequest().authenticated()))
//				.httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults()).build();

	}

//	@Bean
//	public AuthenticationManager authenticationManager(
//			UserDetailsService userDetailsService,
//			PasswordEncoder passwordEncoder) {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService);
//		authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//		ProviderManager providerManager = new ProviderManager(authenticationProvider);
//		providerManager.setEraseCredentialsAfterAuthentication(false);
//
//		return providerManager;
//	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
