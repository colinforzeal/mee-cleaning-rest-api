package com.mee.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class User {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String facebookId;
    private String password;
    private String photoUrl;
}
