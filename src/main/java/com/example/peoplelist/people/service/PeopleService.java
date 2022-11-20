package com.example.peoplelist.people.service;

import com.example.peoplelist.people.model.People;
import com.example.peoplelist.people.model.PeopleDto;
import com.example.peoplelist.people.model.StatsDto;
import com.example.peoplelist.people.repository.PeopleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PeopleService {
    private PeopleRepository peopleRepository;
    private final ModelMapper modelMapper;

    public PeopleService(PeopleRepository peopleRepository, ModelMapper modelMapper) {
        this.peopleRepository = peopleRepository;
        this.modelMapper = modelMapper;
    }

    public List<People> getPeople() {
//        return List.of(new People(1L,"Hamidreza","Karimi",20));
        return peopleRepository.findAll();
    }


    public PeopleDto addNewPeople(PeopleDto peopleDto) {
        People peopleRequest = modelMapper.map(peopleDto, People.class);
        People people = peopleRepository.save(peopleRequest);
        Optional<People> peopleOptional = peopleRepository.findPeopleById(peopleRequest.getId());
        if (peopleOptional.isPresent()) {
            System.out.println("People taken!");
        }
        PeopleDto peopleResponse = modelMapper.map(people, PeopleDto.class);
        return peopleResponse;

    }

    public void deletePeople(Long peopleId) {
        Optional<People> deletepeople = peopleRepository.findPeopleById(peopleId);
        if (!deletepeople.isPresent()) {
            System.out.println("people deleted!");
        }
        peopleRepository.deleteById(peopleId);

    }

    @Transactional
    public PeopleDto updatePeople(Long peopleId, PeopleDto peopleDto) {
        People people = peopleRepository.findById(peopleId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + peopleId + " does not exist!"
                ));
        People peopleREquest = modelMapper.map(peopleDto, People.class);

        if (peopleREquest.getFirstName() != null && peopleREquest.getFirstName().length() > 0 && !Objects.equals(people.getFirstName(), peopleREquest.getFirstName())) {
            people.setFirstName(peopleREquest.getFirstName());

        }
        if (peopleREquest.getLastName() != null && peopleREquest.getLastName().length() > 0 && !Objects.equals(people.getLastName(), peopleREquest.getLastName())) {
            people.setLastName(peopleREquest.getLastName());

        }
        if (peopleREquest.getAge() != 0 && !Objects.equals(people.getAge(), peopleREquest.getAge())) {
            people.setAge(peopleREquest.getAge());

        }
//        People updatePeople = peopleRepository.save(peopleREquest);


        PeopleDto peopleResponse = modelMapper.map(people, PeopleDto.class);


        return peopleResponse;
    }

    public StatsDto countAndAverage() {
        return peopleRepository.getStats();
    }

    public Page<People> pagination(String title, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<People> pagePeoples;
        if (title == null) {
            pagePeoples = peopleRepository.findAll(paging);
        } else {
            pagePeoples = peopleRepository.findByTitle(title, paging);
        }
        return pagePeoples;
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
//    public Long averagePeopleAges(){
//        List<People> people = peopleRepository.findAll();
//        Long number = 0L;
//        for (int i = 0; i < people.size(); i++) {
//            number +=people.get(i).getAge();
//        }
//        Long average = number/numberOfPeople();
//        return average;
}

