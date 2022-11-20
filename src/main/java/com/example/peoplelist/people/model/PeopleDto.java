package com.example.peoplelist.people.model;

import lombok.Data;

@Data
public class PeopleDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
