package com.example.fg_backend.config;

import java.io.IOException;
import java.rmi.ServerException;

import org.springframework.http.HttpLogging;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.fg_backend.repository.UserRepository;
import com.example.fg_backend.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class Jwtauthenticator extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserRepository userRepo;

    

    public Jwtauthenticator(JwtService jwtService, UserRepository userRepo) {
        this.jwtService = jwtService;
        this.userRepo = userRepo;
    }



    @Override 
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws IOException, ServletException{
        // filterChain.doFilter(request, response);

        String authHeader=request.getHeader("Authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }

        String token=authHeader.substring(7);

        if(!jwtService.isTokenValid(token))
        {
            filterChain.doFilter(request, response);
            return ;
        }

        // String email=jwtService.extractEmail(token);
    }
    
}
