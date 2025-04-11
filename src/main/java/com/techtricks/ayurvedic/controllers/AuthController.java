package com.techtricks.ayurvedic.controllers;
import com.techtricks.ayurvedic.config.JwtUtil;
import com.techtricks.ayurvedic.dto.LoginRequest;
import com.techtricks.ayurvedic.models.Admin;
import com.techtricks.ayurvedic.models.BaseUser;
import com.techtricks.ayurvedic.models.User;
import com.techtricks.ayurvedic.services.AdminService;
import com.techtricks.ayurvedic.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final UserService userService;


    private final AdminService adminService;



    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, AdminService adminService,JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.adminService = adminService;

        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest)  {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<? extends BaseUser> authenticatedUser = authenticateUser(email, password);

        return authenticatedUser.map(user -> {
            String token = jwtUtil.generateToken(user.getEmail());
            String role = user.getRole().toString();
            String userId = user.getId().toString();

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", role);
            response.put("userId", userId);

            return ResponseEntity.ok(response);
        }).orElseGet(() -> ResponseEntity.status(401).body(Map.of("error", "Invalid username or password")));
    }

    private Optional<? extends BaseUser> authenticateUser(String email, String password) {
        Optional<User> user = userService.authenticate(email, password);
        if (user.isPresent()) return user;

        Optional<Admin> admin = adminService.authenticate(email, password);
        if (admin.isPresent()) return admin;
        return Optional.empty();
    }
    
}