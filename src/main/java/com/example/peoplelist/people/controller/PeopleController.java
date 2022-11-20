package com.example.peoplelist.people.controller;

import com.example.peoplelist.people.model.People;
import com.example.peoplelist.people.model.PeopleDto;
import com.example.peoplelist.people.model.StatsDto;
import com.example.peoplelist.people.service.PeopleService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class PeopleController {

    private final ModelMapper modelMapper;
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService,
                            ModelMapper modelMapper
    ) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    public List<PeopleDto> getPeopleService() {
        return peopleService.getPeople().stream().map(people -> modelMapper.map(people, PeopleDto.class)).collect(Collectors.toList());
    }

    @GetMapping(path = "people")
    public Page<People> getPeoplePage(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return peopleService.pagination(title, page, size);
    }

    @GetMapping(path = "count&average")
    public StatsDto numberOfPeople() {
        return peopleService.countAndAverage();
    }


    @PostMapping
    public ResponseEntity<PeopleDto> addNewPeople(@RequestBody PeopleDto peopleDto) {

        return new ResponseEntity<PeopleDto>(peopleService.addNewPeople(peopleDto), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{peopleId}")
    public void deletePeople(@PathVariable("peopleId") Long peopleId) {
        peopleService.deletePeople(peopleId);
    }

    @PutMapping(path = "{peopleId}")
    public ResponseEntity<PeopleDto> updatePeople(
            @PathVariable("peopleId") Long peopleId,
            @RequestBody PeopleDto peopleDto
    ) {

        return ResponseEntity.ok().body(peopleService.updatePeople(peopleId, peopleDto));
    }


}
