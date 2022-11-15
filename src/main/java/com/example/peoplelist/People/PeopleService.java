package com.example.peoplelist.People;

import org.springframework.stereotype.Service;

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

        if (firstName != null && firstName.length() > 0 && !Objects.equals(people.getFirstName(), firstName)) {
            people.setFirstName(firstName);

        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(people.getLastName(), lastName)) {
            people.setLastName(lastName);

        }
        if (age != 0 && !Objects.equals(people.getAge(), age)) {
            people.setAge(age);

        }


        return peopleRepository.save(people);
    }

    public Long numberOfPeople() {
//        return peopleRepository.count();
        return peopleRepository.numberOfPeople();
    }

//    public long averagePeopleAges() {
//
//        Long average = Long.valueOf(0);
//        for (Long i = Long.valueOf(1); i <=numberOfPeople() ; i++) {
//            Long finalI = i;
//            People people = peopleRepository.findById(i)
//                    .orElseThrow(() -> new IllegalStateException(
//                            "student with id does not exist!"
//                    ));
//            System.out.println(
//                    people.getAge()
//            );
//            average+=people.getAge();
//        }
//        System.out.println(average);
//        return average/numberOfPeople();
//    }
    public Long averagePeopleAges(){
//        List<People> people = peopleRepository.findAll();
//        Long number = 0L;
//        for (int i = 0; i < people.size(); i++) {
//            number +=people.get(i).getAge();
//        }
//        Long average = number/numberOfPeople();
//        return average;
        return peopleRepository.averagePeopleAges();
    }
}
