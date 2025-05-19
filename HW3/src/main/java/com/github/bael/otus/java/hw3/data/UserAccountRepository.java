package com.github.bael.otus.java.hw3.data;

import com.github.bael.otus.java.hw3.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    Optional<UserAccount> getUserAccountByUsername(String username);
}
