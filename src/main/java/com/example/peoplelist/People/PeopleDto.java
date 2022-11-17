package com.example.peoplelist.People;

import lombok.Data;

@Data
public class PeopleDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
