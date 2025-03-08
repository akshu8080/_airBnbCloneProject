package com.codingshuttle.airBnb.airBnbCloneProject.controller;

import com.codingshuttle.airBnb.airBnbCloneProject.DTO.LoginDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.LoginResponseDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.SignUpRequestDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.UserDto;
import com.codingshuttle.airBnb.airBnbCloneProject.security.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        return new ResponseEntity<>(authService.signUp(signUpRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto>login(@RequestBody LoginDto loginDto, HttpServletResponse response, HttpServletRequest request){
        String[] tokens = authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken",tokens[1]);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDto(tokens[0]));



    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request) {

        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)

                .orElseThrow(()->new AuthenticationServiceException("Refresh token not found inside the cookies"));

        String accessToken = authService.refreshToken(refreshToken);
        return  ResponseEntity.ok(new LoginResponseDto(accessToken));
    }


}
