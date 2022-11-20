package com.example.peoplelist.people.repository;

import com.example.peoplelist.people.model.People;
import com.example.peoplelist.people.model.StatsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

    @Query("SELECT p FROM peoples p")
    Page<People> findByTitle(String title, Pageable pageable);

    @Query("SELECT p FROM peoples p WHERE p.id=?1")
    Optional<People> findPeopleById(Long id);

    @Query("select new com.example.peoplelist.people.model.StatsDto(count(p), avg(p.age)) from peoples p")
    StatsDto getStats();
//    @Query("SELECT COUNT(firstName),AVG(age) FROM People ")
//    Long numberOfPeople();
//    @Query("SELECT new People (id,firstName) from People ")
//    Optional<People> averageAndNuber();


}
