package org.project.entities;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Person {
    private int id;
    private String name;
    private double salary;
    private boolean isManager;

    @Override
    public String toString() {
        return id + ", " + name + ", " + BigDecimal.valueOf(salary).setScale(2, RoundingMode.HALF_UP);
    }
}
