package com.example.blog.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.Auth.RefreshToken.RefreshTokenRequest;
import com.example.blog.Auth.Config.CustomUserDetailsService;
import com.example.blog.Auth.Config.JwtService;
import com.example.blog.Auth.Login.LoginRequest;
import com.example.blog.Auth.Login.LoginResponse;
import com.example.blog.Auth.RefreshToken.RefreshTokenResponse;
import com.example.blog.Auth.Register.RegisterUserRequest;
import com.example.blog.Auth.Register.RegisterUserResponse;
import com.example.blog.User.User;
import com.example.blog.User.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(userDetails);

        return new LoginResponse(
                "You are login Successfully",
                accessToken);
    }

    @PostMapping("/refresh")
    public RefreshTokenResponse refresh(@RequestBody RefreshTokenResponse request) {
        String email = jwtService.extractUsername(request.token());
        UserDetails userdDetails = customUserDetailsService.loadUserByUsername(email);

        if (!jwtService.isRefreshToken(request.token())) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        String token = jwtService.generateAccessToken(userdDetails);

        return new RefreshTokenResponse(
                "Refreshed Token Was Success",
                token);
    }

    @PostMapping("/register")
    public RegisterUserResponse register(@RequestBody @Valid RegisterUserRequest request) {
        User user = userService.store(request);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new RegisterUserResponse(
                "The User Was Create Successfuly",
                accessToken,
                refreshToken);
    }
}
