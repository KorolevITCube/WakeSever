package com.korolev.wake.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private String login;
    private String password;
    private String email;
}
