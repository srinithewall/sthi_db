package com.sthi.config;

import com.sthi.service.CustomUserDetailsService;

import jakarta.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	   @PostConstruct
	    public void debugCors() {
	        System.out.println(">>> SecurityConfig loaded (CORS bean should be active)");
	    }

	   @PostConstruct
	   public void testBcrypt() {
	       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	       String raw = "Sthirva@26";
	       String hash = "$2a$12$cIvjdImf5xkN2z23GCN1nOn1gzGrGQ2aL2uKsr9kG8MSzoFAgoFOu";

	       System.out.println("BCrypt matches = " + encoder.matches(raw, hash));
	   }

    // =========================
    // 🔐 SECURITY FILTER CHAIN
    // =========================
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF for APIs
            .csrf(AbstractHttpConfigurer::disable)

            // Enable CORS with custom config
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // Allow everything (you can restrict later)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // ⭐ allow preflight
                .anyRequest().permitAll()
            );

        return http.build();
    }

    // =========================
    // 👤 USER DETAILS SERVICE
    // =========================
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    // =========================
    // 🔑 PASSWORD ENCODER
    // =========================
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // =========================
    // 🔐 AUTH PROVIDER
    // =========================
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    // =========================
    // 🔐 AUTH MANAGER
    // =========================
    @Bean
    public AuthenticationManager authenticationManager(
            DaoAuthenticationProvider provider
    ) {
        return new ProviderManager(provider);
    }

    // =========================
    // 🌍 CORS CONFIGURATION
    // =========================
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // ⭐ VERY IMPORTANT: explicit origins (NO "*")
        configuration.setAllowedOriginPatterns(Arrays.asList(
        		"http://localhost:*",
        	//	"http://localhost:3005",  
        	 //   "http://localhost:5173",
        	 //   "http://localhost:8880",
        		"https://sthirvaa.com",

        	    "https://sthirvaa.pages.dev",
        	    "https://www.sthirvaa.com",
        	    "http://www.sthirvaa.com"
        	));


        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization",
            "Content-Type",
            "Accept"
        ));

        configuration.setExposedHeaders(Arrays.asList(
            "Authorization"
        ));

        // ⭐ Required if you send JWT / cookies
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
