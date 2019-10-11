package com.codegym.cms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("123456").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/blog/home/**","/blog/info/**").hasRole("USER")
                .and()
                .authorizeRequests().antMatchers("/blog/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .and().formLogin().successHandler(customSuccessHandler)
                .usernameParameter("ssoId").passwordParameter("password")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/Access_Denied")

                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

    }
}