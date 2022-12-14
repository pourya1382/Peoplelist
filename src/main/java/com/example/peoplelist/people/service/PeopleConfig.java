package com.example.peoplelist.people.service;

import com.example.peoplelist.people.model.People;
import com.example.peoplelist.people.repository.PeopleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PeopleConfig {
    @Bean
    CommandLineRunner commandLineRunner(PeopleRepository repository) {
        return args -> {
            People Hamidreza = new People(
                    "Hamidrez",
                    "Karimi",
                    20

            );
            People Arshia = new People(
                    "Arshia",
                    "Rashvand",
                    21

            );
            repository.saveAll(List.of(Arshia, Hamidreza));
        };
    }
}
