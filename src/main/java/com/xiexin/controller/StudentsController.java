package com.xiexin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiexin.bean.*;
import com.xiexin.respcode.Result;
import com.xiexin.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentsService studentsService;
    //全查 注意:不用map 公司中都用一个类,好几个类组成的类叫做
    //统一响应类,每个公司都不一样
    @RequestMapping("/selectAll") // /students/selectAll
    public Result selectAll(){
        List<Students> students = studentsService.selectByExample(null);//select * from student
        Result result = new Result(students);
        return result;
    }

    //分页查询(带参数)
    @RequestMapping("/selectPageAll") // /students/selectPageAll
    public Result selectPageAll(Integer studentSex, String studentName,@RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                                @RequestParam(value = "limit",defaultValue = "10",required = true)Integer pageSize) {
        System.out.println("studentSex = " + studentSex);
        StudentsExample example = new StudentsExample();
        StudentsExample.Criteria criteria = example.createCriteria();

        //使用pagehelper分页
        PageHelper.startPage(page, pageSize);

        if (null != studentName&& !"".equals(studentName)) {
            criteria.andStudentNameEqualTo(studentName);
        }

        if (null != studentSex&& !"".equals(studentSex)) {
            criteria.andStudentSexEqualTo(studentSex);
            }

        List<Students> students = studentsService.selectByExample(example);//select * from student
        PageInfo pageInfo = new PageInfo(students);
        Result result = new Result(pageInfo);
        return result;
    }

    //修改
    @RequestMapping("/updateOne") // /students/updateOne
    public Result updateOne(@RequestBody Students students){
        System.out.println("students = " + students);
        int i = studentsService.updateByPrimaryKeySelective(students);
        if (i == 1) {
            return new Result();
        }else{
            return new Result(40001,"修改失败");
        }
    }

    //增
    // 后端订单增加 -- 针对layui的 针对前端传 json序列化的
    @RequestMapping("/insert") // /students/insert
    public  Result  insert(@RequestBody StudentsDTO students){ // orders 对象传参, 规则: 前端属性要和后台的属性一致!!!
        System.out.println("students = " + students);
        int i =  studentsService.insertSelectiveDTO(students);
        if(i==1){
            return new Result();
        }else{
            return new Result(50002,"添加失败");
        }
    }

    //删除
    @RequestMapping("/deleteOne") // /students/deleteOne
    public Result deleteOne(@RequestParam(value = "studentId",required = false) Integer studentId){
        int i = studentsService.deleteByPrimaryKey(studentId);
        if(i==1){
            return new Result(0,"删除成功");
        }else{
            return new Result("40001","删除失败");
        }
    }

}
