package com.fms.trainings.security.JWTFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fms.trainings.security.GeneratedToken;
import com.fms.trainings.security.service.AuthService;
import com.fms.trainings.security.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("auth :"+username);
        System.out.println("auth :"+password);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);
        //System.out.println("authentication manger :"+authenticationManager.authenticate(usernamePasswordAuthenticationToken));
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        System.out.println("I'm inside successfulAuthentication");
        User user = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("mySecretKey1234");
        String jwtAccessToken = JWT.create()
                .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+3*60*1000))
                                .withIssuer(request.getRequestURI().toString())
                                        .withClaim("roles", user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
                                                .sign(algorithm);
        String jwtRefreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+20*60*1000))
                .withIssuer(request.getRequestURI().toString())
                .sign(algorithm);
        Map<String, String> tokenId = new HashMap<>();
        tokenId.put("access-token", jwtAccessToken);
        tokenId.put("refresh-token", jwtRefreshToken);
        System.out.println(jwtAccessToken);

        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), tokenId);
        response.setHeader("Authorization", jwtAccessToken);
    }
}
