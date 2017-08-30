package com.mee.entity;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OpeningHours {
    private LocalTime from;
    private LocalTime to;
}
