package com.mee.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Id shows step of time in days
 * So fixed intervals = 1 hours
 **/

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class TimeInterval implements Serializable, Comparable<TimeInterval> {
    private int idTimeInterval;
    boolean isFree;

    /**
     * compareTo should return < 0 if this is supposed to be
     * less than other, > 0 if this is supposed to be greater than
     * other and 0 if they are supposed to be equal
     * */
    @Override
    public int compareTo(TimeInterval other) {
        return this.idTimeInterval - other.idTimeInterval;
    }
}
