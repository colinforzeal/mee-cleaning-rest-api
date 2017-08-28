package com.mee.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Company {
    @Id
    private String id;

    private String name;
    private String Address;
    private String email;
    private String password;
    private String photoUrl;

    // todo change type
    private String openingHours;
    private String openingDays;
}
