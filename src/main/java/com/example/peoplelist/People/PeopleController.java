package com.example.peoplelist.People;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PeopleController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public List<People> getPeopleService() {
        return peopleService.getPeople();
    }
    @GetMapping(path = "number")
    public String numberOfPeople(){
        return "number Of People: " +String.valueOf(peopleService.numberOfPeople());
    }
    @GetMapping(path = "average")
    public String averagePeopleAges(){
        return "average of people ages: " + String.valueOf(peopleService.averagePeopleAges());
    }

    @PostMapping
    public People addNewPeople(@RequestBody People people) {
        return peopleService.addNewPeople(people);
    }

    @DeleteMapping(path = "{peopleId}")
    public void deletePeople(@PathVariable("peopleId") Long peopleId) {
        peopleService.deletePeople(peopleId);
    }

    @PutMapping(path = "{peopleId}")
    public People updatePeople(
            @PathVariable("peopleId") Long peopleId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Integer age
    ) {
        return peopleService.updatePeople(peopleId, firstName, lastName, age);
    }


}
