package com.xiexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
    @RequestMapping("/studentList") // /page/studentList
    public String studentList() {
        return "studentlist";
    }

    @RequestMapping("/studentsList") // /page/studentsList
    public String studentsList() {
        return "studentslist";
    }

    @RequestMapping("/login") // /page/login
    public String login() {
        return "login";
    }

    @RequestMapping("/reg") // /page/reg
    public String reg() {
        return "reg";
    }


}
