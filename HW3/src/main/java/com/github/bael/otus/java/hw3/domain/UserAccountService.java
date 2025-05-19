package com.github.bael.otus.java.hw3.domain;

import com.github.bael.otus.java.hw3.data.UserAccountRepository;
import com.github.bael.otus.java.hw3.entity.UserAccount;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccount getUserAccountByUsername(String username) {
        return userAccountRepository.getUserAccountByUsername(username).orElse(null);
    }

    @Transactional
    public UserAccount createUserAccount(String username, String password) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        return userAccountRepository.save(userAccount);
    }



    @Transactional
    @Cacheable("userExistsByName")
    public Boolean hasUserAccountByUsername(String username) {
        return getUserAccountByUsername(username) != null;
    }
}
