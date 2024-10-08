package com.example.spring.controller;

import com.example.spring.Security.DematAccountService;
import com.example.spring.dto.request.LoginRequest;
import com.example.spring.dto.request.SignupRequest;
import com.example.spring.dto.response.ApiResponse;
import com.example.spring.dto.response.CommandResponse;
import com.example.spring.dto.response.CurrentUser;
import com.example.spring.dto.response.JwtResponse;
import com.example.spring.service.AuthService;
import com.example.spring.service.CurrentUserService;
import com.example.spring.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.time.Clock;
import java.time.Instant;

import static com.example.spring.Constants.Constants.SUCCESS;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Clock clock;
    private final AuthService authService;
    private final CurrentUserService currentUserService;
    private final UserService UserService;
    private final DematAccountService dematAccountService;


    /**
     * Authenticates users by their credentials
     *
     * @param request
     * @return JwtResponse
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> login(@Valid @RequestBody LoginRequest request) {
        final JwtResponse response = authService.login(request);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }

    /**
     * Registers users using their credentials and user info
     *
     * @param request
     * @return id of the registered user
     */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<CommandResponse>> signup(@Valid @RequestBody SignupRequest request) {
        final CommandResponse response = authService.signup(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }


    @GetMapping("/currentUser")
    public CurrentUser getCurrentUser() {
        return currentUserService.getCurrentUser();
    }


    @GetMapping("/current")
    public String getDematNumberForCurrentUser() {
        try{
            String demat = dematAccountService.getDematAccountByUsername(currentUserService.getCurrentUser().getUsername());
            return  demat;
        }catch (Exception e){
            return "please login";
        }
    }


}
