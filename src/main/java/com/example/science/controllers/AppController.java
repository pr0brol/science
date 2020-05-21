package com.example.science.controllers;

import com.example.science.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping("/")
    public String index(Model model){
        appService.showSimplePage(model);
        return "index";
    }

    @GetMapping("/dep/{id}")
    public String getDepartment(Model model, @PathVariable Long id){
        appService.showPageWithDepartment(model, id);
        return "index";
    }

    @GetMapping("/month/{m}")
    public String getDepartmentWithMonth(Model model, @PathVariable String m){
        appService.showPageWithMonth(model, m);
        return "index";
    }
}
