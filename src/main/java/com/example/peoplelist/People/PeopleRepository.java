package com.example.peoplelist.People;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.hibernate.hql.internal.antlr.HqlTokenTypes.AS;
@Repository
public interface PeopleRepository extends JpaRepository<People,Long> {
    @Query("SELECT s FROM People s WHERE s.id=?1")
    Optional<People> findPeopleById(Long id);

}
