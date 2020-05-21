package com.example.science.services;

import com.example.science.entities.Department;
import com.example.science.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department save(Department department){
        return departmentRepository.save(department);
    }

    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    public List<Department> findAll() {return (List<Department>) departmentRepository.findAll();}

    public void delete(Long id) {departmentRepository.deleteById(id);}
}
