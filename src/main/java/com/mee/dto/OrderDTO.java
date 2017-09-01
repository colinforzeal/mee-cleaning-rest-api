package com.mee.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String companyId;
    private String userId;
    private Date startsAt;
    private Date endsAt;
}
