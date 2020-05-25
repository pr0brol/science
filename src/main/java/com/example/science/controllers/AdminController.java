package com.example.science.controllers;

import com.example.science.entities.Calendar;
import com.example.science.entities.Department;
import com.example.science.entities.Employee;
import com.example.science.entities.Position;
import com.example.science.enums.Days;
import com.example.science.enums.Mark;
import com.example.science.enums.Months;
import com.example.science.services.CalendarService;
import com.example.science.services.DepartmentService;
import com.example.science.services.EmployeeService;
import com.example.science.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private PositionService positionService;

    private Mark marks;
    private Months months;
    private Days days;

    //-----------------------------------------------------------------------------------------
    // Добавление записи в календарь

    @GetMapping("/add/mark")
    public String showTabelPage(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "edit_tabel";
    }

    @GetMapping("/add/mark/department/{id}")
    public String showTabelPageWithDepartmentId(Model model, @PathVariable Long id) {
        List<Department> departments = departmentService.findAll();
        Optional<Department> department = departmentService.findById(id);
        List<Employee> employees = employeeService.findByDepartment(department.get().getId());
        model.addAttribute("departments", departments);
        model.addAttribute("employees", employees);
        return "edit_tabel";
    }

    @GetMapping("/add/mark/employee/{id}")
    public String showTabelPageWithEmployeeId(Model model, @PathVariable Long id) {
        Employee employee = employeeService.findById(id).get();
        model.addAttribute("employee", employee);
        model.addAttribute("marks", marks);
        System.out.println(marks);
        return "employee_calendar";
    }

    @PostMapping("/calendar_success")
    public String pageAfterAddStatus(Model model,
                                     @RequestParam(name = "id") String id,
                                     @RequestParam(name = "calendar") String calendar,
                                     @RequestParam(name = "status") String status) {
        Long ID = Long.parseLong(id);
        Date date = Date.valueOf(calendar);
        Employee employee = employeeService.findById(ID).get();
        calendarService.addStatus(new Calendar(ID, employee, date, status));
        return "success";
    }

    //-----------------------------------------------------------------------------------------
    // Редактирование департаментов

    @GetMapping("/edit/department")
    public String showDepartmentsPage(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping("/edit/department/{id}")
    public String showDepartmentsEditPage(Model model, @PathVariable Long id) {
        Department department = departmentService.findById(id).get();
        model.addAttribute("department", department);
        return "edit_department";
    }

    @GetMapping("/delete/department/{id}")
    public String showDepartmentDeletePage(Model model, @PathVariable Long id) {
        departmentService.delete(id);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping("/add/department")
    public String pageForAddDepartment() {
        return "add_department";
    }

    @PostMapping("/department_add")
    public String pageAfterAddDepartment(@RequestParam(name = "title") String title) {
        departmentService.save(new Department(title));
        return "success";
    }

    @PostMapping("/department_success")
    public String pageAfterUpdateDepartment(Model model,
                                            @RequestParam(name = "id") String id,
                                            @RequestParam(name = "title") String title) {
        Long ID = Long.parseLong(id);
        Department department = departmentService.findById(ID).get();
        department.setTitle(title);
        departmentService.save(department);
        return "success";
    }

    //-----------------------------------------------------------------------------------------
    // Редактирование пользователей


    @GetMapping("/employee")
    public String showEmployeePage(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "departments_employee";
    }

    @GetMapping("/employee/department/{id}")
    public String showEmployeePageWithDepartment(Model model, @PathVariable Long id) {
        List<Department> departments = departmentService.findAll();
        List<Employee> employees = employeeService.findByDepartment(id);
        model.addAttribute("departments", departments);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/edit/employee/{id}")
    public String showEmployeePageForEdit(Model model, @PathVariable Long id) {
        Employee employee = employeeService.findById(id).get();
        List<Position> positions = positionService.findAll();
        List<Department> departments = departmentService.findAll();
        model.addAttribute("employee", employee);
        model.addAttribute("positions", positions);
        model.addAttribute("departments", departments);
        return "edit_employee";
    }

    @PostMapping("/edit_success")
    public String pageAfterUpdateEmployee(@ModelAttribute(name = "employee") Employee employee) {
        employeeService.save(employee);
        return "success";
    }

    @GetMapping("/add/employee")
    public String addEmployeePage(Model model) {
        List<Position> positions = positionService.findAll();
        List<Department> departments = departmentService.findAll();
        model.addAttribute("positions", positions);
        model.addAttribute("departments", departments);
        return "add_employee";
    }

    @PostMapping("/add_success")
    public String pageAfterAddEmployee(@ModelAttribute(name = "employee") Employee employee) {
        employeeService.add(employee);
        return "success";
    }

    @GetMapping("/delete/employee/{id}")
    public String deleteEmployeePage(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "success";
    }
}
