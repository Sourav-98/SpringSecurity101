package com.tutorials.SpringSecurity101.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    protected final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("testuser").password(this.passwordEncoder.encode("test@123")).roles("user").and()
//                .withUser("testadmin").password(this.passwordEncoder.encode("admin@123")).roles("admin");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/", "css/*", "js/*").permitAll().and().httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().username("user1").password(this.passwordEncoder.encode("user1@123")).roles("USER").build();
        UserDetails admin1 = User.builder().username("admin1").password(this.passwordEncoder.encode("admin@12345")).roles("ADMIN").build();
//        List<UserDetails> userDetailsList = new ArrayList<>();
//        userDetailsList.add(user1);
//        userDetailsList.add(admin1);
        return new InMemoryUserDetailsManager(
                user1, admin1
//                userDetailsList
//                User.builder()
//                    .username("user1").password(this.passwordEncoder.encode("user1@123")).roles("USER")
//                    .username("user2").password(this.passwordEncoder.encode("user2@123")).roles("USER")
//                    .username("admin1").password(this.passwordEncoder.encode("admin@12345")).roles("ADMIN")
//                    .build()
        );
    }

    //    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return new BCryptPasswordEncoder(10);
//    }
}
