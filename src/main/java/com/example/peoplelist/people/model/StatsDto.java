package com.example.peoplelist.people.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class StatsDto {
    private Long count;
    private Double averageAge;

    public StatsDto(Long count, Double averageAge) {
        this.count = count;
        this.averageAge = averageAge;
    }
}
