package com.github.bael.otus.java.hw3.rest;

import com.github.bael.otus.java.hw3.domain.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;

    @PostMapping("/api/users/")
    public void registerUser(UserAccountCreateDTO user) {
        if (userAccountService.hasUserAccountByUsername(user.getUsername())) {
            throw new RuntimeException("User account already exists");
        }
        userAccountService.createUserAccount(user.getUsername(), user.getPassword());
    }

}
