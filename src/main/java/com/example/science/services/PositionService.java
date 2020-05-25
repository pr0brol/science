package com.example.science.services;

import com.example.science.entities.Position;
import com.example.science.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public Position findByName(String position){
        return positionRepository.findOneByTitle(position);
    }

    public Position findById(Long id){
        return positionRepository.findById(id).get();
    }

    public List<Position> findAll(){
        return (List<Position>) positionRepository.findAll();
    }
}
