package com.fms.trainings.security.securityConfig;

import java.util.ArrayList;
import java.util.List;

import com.fms.trainings.security.JWTFilter.JwtAuthenticationFilter;
import com.fms.trainings.security.JWTFilter.JwtAuthorizationFilter;
import com.fms.trainings.security.entities.Uuser;
import com.fms.trainings.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

/**
 * @author groupe Caroline, Delmerie et Belkacem
 * <p>
 * ***************** Mise en place de spring security ********** 1- On
 * crée une class (securityConfig) qui hérite de la classe
 * "WebSecurityConfigurerAdapter" de sring secutity 2- On active spring
 * security avec le annotation @Configuration et @EnableWebSecurity pour
 * désactiver la security de spring par défaut et utiliser une sécurité
 * ciblée, ici UserDetails 3- On charge deux methodes de
 * "WebSecurityConfigurerAdapter"
 * <p>
 * 3-1- configure(AuthenticationManagerBuilder auth): pour
 * l'authentification et le choix de l'authentification - Pour le choix
 * de l'authentification, on choisit UsersDetails en faisant appel à
 * "userDetailsService" . - userDetailsService nous fournit une méthode
 * (loadUserByUsername(String username)) qui récupère les données du
 * formulaire d'authentification de l'utilisateur - Avec les données
 * récuperées (userName et password), on fait appel à la base de donnée
 * users (JPA) et chercher le userName correspondant - Si le userName
 * existe, on charge le User (return new User(uuser.getUserName(),
 * uuser.getPassword(), uuser.getActive(), true, true, true,
 * grantedAuthorities);) de spring boot security avec les donnée des
 * (password, roles, active...) l'utilisateur chargé depuis la bd afin
 * qu'il puisse verifier l'authenticité et le droit d'accès de
 * l'utilisateur
 * <p>
 * 3-2- protected void configure(HttpSecurity http): pour la gestion de
 * la securité des requette http - Le formulaire de login à utiliser -
 * la protection des routers selon les role utilisateurs - ....
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService authService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = passwordEncoder();
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                Uuser uuser = authService.findUuserByUserName(username);
                System.out.println(uuser.getUserName());
                List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
                uuser.getRoles().forEach(r -> {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + r.getRole());
                    grantedAuthorities.add(grantedAuthority);
                });
//                return new User(uuser.getUserName(), uuser.getPassword(), uuser.getActive(), true, true, true, grantedAuthorities);
                return new User(uuser.getUserName(), uuser.getPassword(),
                        grantedAuthorities);
            }
        }).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.formLogin();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.formLogin().loginPage("/login");

       http.authorizeHttpRequests().antMatchers( HttpMethod.POST,"/auth/**").hasRole("ADMIN");
//        http.authorizeHttpRequests().antMatchers( HttpMethod.GET,"/auth/**").hasRole("USER");
        //http.authorizeHttpRequests().antMatchers("/myCustomers/**/**").authenticated();
        http.authorizeHttpRequests().antMatchers("/api/login").permitAll();
        http.authorizeHttpRequests().antMatchers( "/api/category/**").permitAll();
        http.authorizeHttpRequests().antMatchers( "/api/training/**").permitAll();
        // http.exceptionHandling().accessDeniedPage("/accessDenied");
        //http.authorizeHttpRequests().anyRequest().authenticated();
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers()
                .addHeaderWriter(
                        new StaticHeadersWriter("Access-Control-Allow-Origin", "http://localhost:4200")
                       );


    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
