package com.uni.libreria.configurations;



import com.uni.libreria.support.authentication.JwtAuthenticationConverter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.persistence.PersistenceContext;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/check/simple").permitAll()
                .antMatchers("/artisti/**").permitAll()
                .antMatchers("/attori/**").permitAll()
                .antMatchers("/autori/**").permitAll()
                .antMatchers("/reparti/**").permitAll()
                .antMatchers("/registi/**").permitAll()
                .antMatchers("/prodotti/**").permitAll()
                .antMatchers("/libri/**").permitAll()
                .antMatchers("/film/**").permitAll()
                .antMatchers("/editori/**").permitAll()
                .antMatchers("/clienti/**").hasAuthority("user")
                .antMatchers("/cd/**").permitAll()
                .antMatchers("/ordini/**").permitAll()
                .antMatchers("/carrelli/**").permitAll()
                .antMatchers("/prodottiInCarrello/**").permitAll()
                .anyRequest().authenticated().and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(new JwtAuthenticationConverter());

    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("OPTIONS");
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }


}
