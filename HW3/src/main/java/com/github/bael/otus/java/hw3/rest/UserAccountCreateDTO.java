package com.github.bael.otus.java.hw3.rest;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountCreateDTO {
    private String username;
    private String password;
}
