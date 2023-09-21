/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Egg.news;

import com.Egg.news.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class seguridadWeb extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure (HttpSecurity http)throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/css/*","/js/*","/img/*","/**")
                .permitAll()
                .and().formLogin()
                .loginPage("/seguridad/login")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureForwardUrl("/seguridad/login")
                .defaultSuccessUrl("/seguridad/inicio")
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/seguridad/login")
                .permitAll()
                .and().csrf()
                .disable();
                
                        
    }
    @Autowired
    public UsuarioServicio usuarioServicio;
    
    @Autowired
    public void configuredGlobal(AuthenticationManagerBuilder aut) throws Exception{
        aut.userDetailsService(usuarioServicio)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    
}
