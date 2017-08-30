package com.mee.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String facebookId;
    private String password;
    private String photoUrl;
    private List<Role> roles;
}
