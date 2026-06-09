package com.project.user.controller;

import com.project.user.data.UserInfo;
import com.project.user.data.UserRepository;
import com.project.user.data.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.user.data.dto.RegisterRequest;
import com.project.user.data.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@NullMarked
@Slf4j
public class UserController {

    private final UserRepository userRepo;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId){
        UserInfo existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        UserResponse response = UserResponse.builder()
                .email(existingUser.getEmail())
                .password(existingUser.getPassword())
                .keycloakId("")
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        UserResponse response;

        if (userRepo.existsByEmail(request.getEmail())) {
            UserInfo existingUser = userRepo.findByEmail(request.getEmail());

            response = UserResponse.builder()
                    .email(existingUser.getEmail())
                    .password(existingUser.getPassword())
                    .keycloakId("")
                    .build();
        }

        UserInfo user = UserInfo.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(List.of(Role.USER))
                .build();
        UserInfo savedUser = userRepo.save(user);

        response = UserResponse.builder()
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .keycloakId("")
                .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId){
        log.info("Calling User Validation API for userId: {}", userId);

        return ResponseEntity.ok(userRepo.existsByKeycloakId(userId));
    }
}
