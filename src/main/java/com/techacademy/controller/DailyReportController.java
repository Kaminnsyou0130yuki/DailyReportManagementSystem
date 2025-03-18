package com.techacademy.controller;

import com.techacademy.entity.Employee;
import com.techacademy.repository.DailyReportRepository;
import com.techacademy.service.DailyReportService;
import com.techacademy.service.EmployeeService;
import com.techacademy.service.UserDetailService;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("reports")
public class DailyReportController {

    private final DailyReportService dailyReportService;
    private final EmployeeService employeeService;

    @Autowired
    DailyReportController(DailyReportService dailyReportService, EmployeeService employeeService, DailyReportRepository dailyReportRepository, UserDetailService userDetailService, EmployeeController employeeController) {
        this.dailyReportService = dailyReportService;
        this.employeeService = employeeService;

    }

//    日報一覧画面
    @GetMapping
    public String list(Model model) {

        model.addAttribute("listSize", dailyReportService.findAll().size());
        model.addAttribute("dairyReportList", dailyReportService.findAll());
        return "dailyReport/dailyReportList";

    }

    @GetMapping("/add")
    public String create(Principal principal, Model model) {
        String code = principal.getName();
        model.addAttribute("employee", employeeService.findByCode(code));
        return "dailyReport/dailyReportNew";
    }

    @PostMapping("/add")
    public String add(Model model) {
        return "redirect:/reports";
    }
}
