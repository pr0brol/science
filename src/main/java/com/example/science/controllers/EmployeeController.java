package com.example.science.controllers;

import com.example.science.entities.Department;
import com.example.science.entities.Employee;
import com.example.science.enums.Days;
import com.example.science.enums.Months;
import com.example.science.services.DepartmentService;
import com.example.science.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    private final Months months = new Months();
    private final Days days = new Days();
    private Long depId = 1L;
    private boolean isMonth;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index(Model model){
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("months", months.getMonths());
        model.addAttribute("days", days.getDays());
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login_page";
    }

    @GetMapping("/department/{id}")
    public String getDepartment(Model model, @PathVariable Long id){
        depId = id;
        isMonth = false;
        List<Department> departments = departmentService.findAll();
        Optional<Department> department = departmentService.findById(id);
        List<Employee> employees = employeeService.findByDepartment(department.get().getId());
        addInitAttribute(model, employees, departments);
        return "index";
    }

    @GetMapping("/month/{m}")
    public String getDepartmentWithMonth(Model model, @PathVariable String m){
        int month = months.getMonths().indexOf(m);
        month++;
        isMonth = true;
        List<Department> departments = departmentService.findAll();
        Optional<Department> department = departmentService.findById(depId);
        List<Employee> employees = employeeService.findByDepartmentAndMonth(department.get().getId(), month);
        addInitAttribute(model, employees, departments);
        return "index";
    }

    public void addInitAttribute(Model model, List<Employee> employees, List<Department> departments){
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("ismonth", isMonth);
        model.addAttribute("months", months.getMonths());
        model.addAttribute("days", days.getDays());
    }
}
