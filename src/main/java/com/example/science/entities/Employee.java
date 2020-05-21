package com.example.science.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String birthday;

    @Column
    private int age;

    @Column
    private boolean remote_work;

    @Column
    private String address;

    @Column(insertable = false, updatable = false)
    private Long position_id;

    @OneToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(insertable = false, updatable = false)
    private Long department_id;

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "employee")
    private List<Calendar> calendars;

}
