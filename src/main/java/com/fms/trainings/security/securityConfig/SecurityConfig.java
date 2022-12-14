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
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
 * cr??e une class (securityConfig) qui h??rite de la classe
 * "WebSecurityConfigurerAdapter" de sring secutity 2- On active spring
 * security avec le annotation @Configuration et @EnableWebSecurity pour
 * d??sactiver la security de spring par d??faut et utiliser une s??curit??
 * cibl??e, ici UserDetails 3- On charge deux methodes de
 * "WebSecurityConfigurerAdapter"
 * <p>
 * 3-1- configure(AuthenticationManagerBuilder auth): pour
 * l'authentification et le choix de l'authentification - Pour le choix
 * de l'authentification, on choisit UsersDetails en faisant appel ??
 * "userDetailsService" . - userDetailsService nous fournit une m??thode
 * (loadUserByUsername(String username)) qui r??cup??re les donn??es du
 * formulaire d'authentification de l'utilisateur - Avec les donn??es
 * r??cuper??es (userName et password), on fait appel ?? la base de donn??e
 * users (JPA) et chercher le userName correspondant - Si le userName
 * existe, on charge le User (return new User(uuser.getUserName(),
 * uuser.getPassword(), uuser.getActive(), true, true, true,
 * grantedAuthorities);) de spring boot security avec les donn??e des
 * (password, roles, active...) l'utilisateur charg?? depuis la bd afin
 * qu'il puisse verifier l'authenticit?? et le droit d'acc??s de
 * l'utilisateur
 * <p>
 * 3-2- protected void configure(HttpSecurity http): pour la gestion de
 * la securit?? des requette http - Le formulaire de login ?? utiliser -
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
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests().antMatchers( HttpMethod.GET,"/api/category/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/training/**").permitAll();

//        http.formLogin().loginPage("/login");
//       http.authorizeHttpRequests().antMatchers( HttpMethod.POST,"/auth/**").hasRole("ADMIN");
////        http.authorizeHttpRequests().antMatchers( HttpMethod.GET,"/auth/**").hasRole("USER");
//        //http.authorizeHttpRequests().antMatchers("/myCustomers/**/**").authenticated();
//        http.authorizeHttpRequests().antMatchers("/api/login").permitAll();
//
//        http.authorizeRequests(
//                request -> request.antMatchers(HttpMethod.GET,"/api/category/**").permitAll()
//                        .anyRequest().authenticated()
//        );

       // http.authorizeHttpRequests().anyRequest().authenticated();
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class).csrf().disable()
                .authorizeRequests().antMatchers("/**/api/login",
                        "/**/api/training/**",
                        "/**/category/**").permitAll()
                .anyRequest().authenticated();
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

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
    }
}
