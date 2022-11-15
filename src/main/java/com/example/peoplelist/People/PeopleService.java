package com.example.peoplelist.People;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PeopleService {
    private PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<People> getPeople() {
//        return List.of(new People(1L,"Hamidreza","Karimi",20));
        return peopleRepository.findAll();
    }

    public People addNewPeople(People people) {
        Optional<People> peopleOptional = peopleRepository.findPeopleById(people.getId());
        if (peopleOptional.isPresent()) {
            System.out.println("People taken!");
        }
        return peopleRepository.save(people);

    }

    public void deletePeople(Long peopleId) {
        Optional<People> deletepeople = peopleRepository.findPeopleById(peopleId);
        if (!deletepeople.isPresent()) {
            System.out.println("people deleted!");
        }
        peopleRepository.deleteById(peopleId);

    }

    @Transactional
    public People updatePeople(Long peopleId,
                               String firstName,
                               String lastName,
                               Integer age) {
        People people = peopleRepository.findById(peopleId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + peopleId + " does not exist!"
                ));
        System.out.println(firstName != null);
        System.out.println(firstName.length() > 0);
        System.out.println(!Objects.equals(people.getFirstName(), firstName));
        if (firstName != null && firstName.length() > 0 && !Objects.equals(people.getFirstName(), firstName)) {
            people.setFirstName(firstName);
            System.out.println(people);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(people.getLastName(), lastName)) {
            people.setLastName(lastName);
            System.out.println(people);
        }
        if (age != 0 && !Objects.equals(people.getAge(), age)) {
            people.setAge(age);
            System.out.println(people);
        }
        System.out.println(people);

        return peopleRepository.save(people);
    }
}
