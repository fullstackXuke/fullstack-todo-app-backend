package com.example.backend.controller;

      import com.example.demo.util.JwtUtil;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.security.authentication.AuthenticationManager;
      import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
      import org.springframework.security.core.Authentication;
      import org.springframework.security.core.context.SecurityContextHolder;
      import org.springframework.web.bind.annotation.*;

      @RestController
      @RequestMapping("/api/auth")
      public class AuthController {

          @Autowired
          private AuthenticationManager authenticationManager;

          @Autowired
          private JwtUtil jwtUtil;

          @PostMapping("/login")
          public String login(@RequestBody LoginRequest loginRequest) {
              Authentication authentication = authenticationManager.authenticate(
                      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
              );
              SecurityContextHolder.getContext().setAuthentication(authentication);
              return jwtUtil.generateToken(loginRequest.getUsername());
          }
      }
