package com.mee.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Company {
    @Id
    private String id;

    private String name;
    private String address;
    private String email;
    private String password;
    private String photoUrl;

    // todo change type
    //private String openingHours;
    private List<DayOfWeek> openingDays;
    private OpeningHours openingHours;
    private List<Role> roles;
}
