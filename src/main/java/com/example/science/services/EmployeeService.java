package com.example.science.services;

import com.example.science.entities.Employee;
import com.example.science.entities.Role;
import com.example.science.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<Employee> findByDepartmentAndMonth(Long id, int month){
        return employeeRepository.findByDepartmentIdAndMonth(id, month);
    }

    public List<Employee> findByDepartment(Long id){
        return employeeRepository.findByDepartmentId(id);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findOneByName(name);
        if (employee == null) {
            throw new UsernameNotFoundException("invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(employee.getLastName(), employee.getPassword(), mapRolesToAuthorities(employee.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public List<Employee> findAll(){
        return (List<Employee>) employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id){
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee add(Employee employee){
        String password = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(password);
        return save(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

}
