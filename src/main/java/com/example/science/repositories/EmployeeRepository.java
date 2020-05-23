package com.example.science.repositories;

import com.example.science.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select distinct e from Employee e join fetch e.calendars c where e.department_id = ?1 and extract(month from day) = ?2")
    List<Employee> findByDepartmentIdAndMonth(Long depId, int month);

    @Query("select e from Employee e where e.department_id = ?1")
    List<Employee> findByDepartmentId(Long depId);

    @Query("select e from Employee e where e.lastName = ?1")
    Employee findOneByName(String name);

}
