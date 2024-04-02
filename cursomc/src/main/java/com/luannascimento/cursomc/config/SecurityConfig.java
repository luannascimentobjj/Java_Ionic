package com.luannascimento.cursomc.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String[] PUBLIC_MATCHERS = {

			"/pedidos/**"};
	
	private static final String[] PUBLIC_MATCHERS_GET = {

			"/produtos/**", "/categorias/**", "/clientes/**" };
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated()).httpBasic(withDefaults()).sessionManagement((sessionManagement) ->
        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.cors(withDefaults()).csrf((csrf) -> csrf.disable());
		return http.build();
        
    
	}

    @Bean
     WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).requestMatchers(PUBLIC_MATCHERS);
    }
	
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	
    	CorsConfiguration configuration = new CorsConfiguration();
    	configuration.applyPermitDefaultValues();
    	UrlBasedCorsConfigurationSource  source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", configuration);
    	
    	return source;
    }
    
    
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
    	
    	return new BCryptPasswordEncoder();
    	
    }
	
}
