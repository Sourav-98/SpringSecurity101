package com.tutorials.SpringSecurity101.securityconfig;

import com.tutorials.SpringSecurity101.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    protected final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/**", "css/*", "js/*").permitAll()
                .and().formLogin();
    }

    // In Memory User Details Service
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails user1 = User.builder().username("user1").password(this.passwordEncoder.encode("user1@123")).roles("USER").build();
//        UserDetails admin1 = User.builder().username("admin1").password(this.passwordEncoder.encode("admin@12345")).roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(
//                user1, admin1
//        );
//    }
}
