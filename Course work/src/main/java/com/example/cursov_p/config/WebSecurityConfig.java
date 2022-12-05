package com.example.cursov_p.config;

import com.example.cursov_p.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception { http.csrf().disable()
        .authorizeRequests().and()
        .formLogin()
            .usernameParameter("name")
            .passwordParameter("password")
            .loginProcessingUrl("/login")
            .permitAll()
            .and()
        .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .permitAll(); }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    { auth.authenticationProvider(authenticationProvider()); }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        val provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults()
    { return new GrantedAuthorityDefaults(""); }
}
