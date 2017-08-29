package com.mee.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Order {
    @Id
    private String id;
    private String companyId;
    private String companyName;
    private String userId;
    private String fullName;
    private LocalTime workHouseFrom;
    private LocalTime workHoursTo;
    private LocalDate workDay;
}
