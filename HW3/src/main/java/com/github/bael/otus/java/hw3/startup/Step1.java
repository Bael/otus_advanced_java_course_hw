package com.github.bael.otus.java.hw3.startup;

import com.github.bael.otus.java.hw3.domain.UserAccountService;
import com.github.bael.otus.java.hw3.rest.UserAccountCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Step1 implements ApplicationListener<ContextRefreshedEvent> {
    private final UserAccountService userAccountService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        for (int i=0; i<1000_000; i++) {
            UserAccountCreateDTO user = new UserAccountCreateDTO("unique_user_name_something_good" + i, "password");
            if (i % 10000 == 0) {
                System.out.println("KB remains: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024);
                System.out.println("KB free: " + (double) (Runtime.getRuntime().freeMemory()) / 1024);
            }
            create(user);
        }
    }

    private void create(UserAccountCreateDTO user) {
        if (userAccountService.hasUserAccountByUsername(user.getUsername())) {
            throw new RuntimeException("User account already exists");
        }
        userAccountService.createUserAccount(user.getUsername(), user.getPassword());

    }
}
