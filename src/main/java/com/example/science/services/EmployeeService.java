package com.example.science.services;

import com.example.science.entities.Employee;
import com.example.science.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findByDepartmentAndMonth(Long id, int month){
        return employeeRepository.findByDepartmentIdAndMonth(id, month);
    }

    public List<Employee> findByDepartment(Long id){
        return employeeRepository.findByDepartmentId(id);
    }

}
