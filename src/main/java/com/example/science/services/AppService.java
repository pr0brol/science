package com.example.science.services;

import com.example.science.entities.Department;
import com.example.science.entities.Employee;
import com.example.science.enums.Days;
import com.example.science.enums.Months;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class AppService {

    private final Months months = new Months();
    private final Days days = new Days();
    private Long depId = 1L;
    private boolean isMonth;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    public void showSimplePage(Model model){
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        addDateAttribute(model);
    }

    public void showPageWithDepartment(Model model, Long id){
        depId = id;
        isMonth = false;
        List<Department> departments = departmentService.findAll();
        Optional<Department> department = departmentService.findById(id);
        List<Employee> employees = employeeService.findByDepartment(department.get().getId());
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("ismonth", isMonth);
        addDateAttribute(model);
    }

    public void showPageWithMonth(Model model, String m){
        int month = months.getMonths().indexOf(m);
        month++;
        isMonth = true;
        List<Department> departments = departmentService.findAll();
        Optional<Department> department = departmentService.findById(depId);
        List<Employee> employees = employeeService.findByDepartmentAndMonth(department.get().getId(), month);
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("ismonth", isMonth);
        addDateAttribute(model);
    }

    public void addDateAttribute(Model model){
        model.addAttribute("months", months.getMonths());
        model.addAttribute("days", days.getDays());
    }
}
