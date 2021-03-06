package com.example.SpringAss3APJ2.config;

import com.example.SpringAss3APJ2.repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
        security.authorizeRequests()
                .antMatchers("/", "/registration").permitAll()
                .antMatchers("/profile").hasAnyRole("USER", "ADMIN")
                .antMatchers("/history/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/payments").hasAnyRole("USER", "ADMIN")
                .antMatchers("/transfers-boot").hasAnyRole("USER", "ADMIN")
                .antMatchers("/profile").hasAuthority("read")
                .antMatchers("/arrivals").hasAuthority("edit")
            .and()
                //.formLogin().permitAll()
                .formLogin().loginPage("/login").permitAll()
            .and()
                .logout().permitAll()
            .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}