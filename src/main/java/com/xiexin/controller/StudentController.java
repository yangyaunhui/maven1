package com.xiexin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiexin.bean.Student;
import com.xiexin.bean.StudentExample;
import com.xiexin.respcode.Result;
import com.xiexin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    //全查 注意:不用map 公司中都用一个类,好几个类组成的类叫做
    //统一响应类,每个公司都不一样
    @RequestMapping("/selectAll") // /student/selectAll
    public Result selectAll(){
        List<Student> students = studentService.selectByExample(null);//select * from student
        Result result = new Result(students);
        return result;
    }

    //分页查询(带参数)
    @RequestMapping("/selectPageAll") // /student/selectPageAll
    public Result selectPageAll(Integer studentSex, String studentName,@RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                                @RequestParam(value = "limit",defaultValue = "10",required = true)Integer pageSize) {
        System.out.println("studentSex = " + studentSex);
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();

        //使用pagehelper分页
        PageHelper.startPage(page, pageSize);

        if (null != studentName&& !"".equals(studentName)) {
            criteria.andStudentNameEqualTo(studentName);
        }

        if (null != studentSex&& !"".equals(studentSex)) {
            criteria.andStudentSexEqualTo(studentSex);
            }

        List<Student> students = studentService.selectByExample(example);//select * from student
        PageInfo pageInfo = new PageInfo(students);
        Result result = new Result(pageInfo);
        return result;
    }
}
