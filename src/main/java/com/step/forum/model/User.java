package com.step.forum.model;

import lombok.Data;

@Data
public class User {

    private int id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String token;
    private int status;
    private Role role;

}
