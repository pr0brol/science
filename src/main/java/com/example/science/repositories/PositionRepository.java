package com.example.science.repositories;

import com.example.science.entities.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {

    @Query("select p from Position p where p.title = ?1")
    Position findOneByTitle(String title);
}
