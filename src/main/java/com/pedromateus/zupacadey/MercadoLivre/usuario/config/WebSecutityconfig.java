package com.pedromateus.zupacadey.MercadoLivre.usuario.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecutityconfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    public static final String [] PUBLIC = {"/users/**","/h2-console/**"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //H2
        if(Arrays.asList(env.getActiveProfiles()).contains("test"))
            http.headers().frameOptions().disable();

        http.authorizeRequests().antMatchers(HttpMethod.POST,PUBLIC).permitAll().anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
    }
}
