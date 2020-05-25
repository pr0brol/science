package com.example.science.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Data
@NoArgsConstructor
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(insertable = false, updatable = false)
    private Long employee_id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @Column
    private Date day;

    @Column
    private String status;

    public Calendar(Long employee_id, Date day, String status) {
        this.employee_id = employee_id;
        this.day = day;
        this.status = status;
    }

    public Calendar(Long employee_id, Employee employee, Date day, String status) {
        this.employee_id = employee_id;
        this.employee = employee;
        this.day = day;
        this.status = status;
    }
}
