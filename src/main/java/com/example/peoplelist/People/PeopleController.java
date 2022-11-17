package com.example.peoplelist.People;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class PeopleController {
    @Autowired
    private ModelMapper modelMapper;
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    public List<PeopleDto> getPeopleService() {
        return peopleService.getPeople().stream().map(people -> modelMapper.map(people, PeopleDto.class)).collect(Collectors.toList());
    }

    @GetMapping(path = "number")
    public String numberOfPeople() {
        return "number Of People: " + String.valueOf(peopleService.numberOfPeople());
    }


    @PostMapping
    public ResponseEntity<PeopleDto> addNewPeople(@RequestBody PeopleDto peopleDto) {
        People peopleRequest = modelMapper.map(peopleDto, People.class);
        People people = peopleService.addNewPeople(peopleRequest);
        PeopleDto peopleResponse = modelMapper.map(people, PeopleDto.class);
        return new ResponseEntity<PeopleDto>(peopleResponse, HttpStatus.CREATED);
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
        People peopleRequest = modelMapper.map(peopleDto, People.class);
        People people = peopleService.updatePeople(peopleId, peopleRequest);
        PeopleDto peopleResponse = modelMapper.map(people, PeopleDto.class);
        return ResponseEntity.ok().body(peopleResponse);
    }


}
