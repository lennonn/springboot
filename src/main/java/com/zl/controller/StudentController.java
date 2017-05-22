package com.zl.controller;

import com.zl.entity.Student;
import com.zl.service.StudentService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * Created by Administrator on 2017/5/4.
 */
@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public Collection<Student> getStudents(){
        return studentService.getStudents();
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudents(@RequestBody String student){
        //JSONObject object =JSONObject.fromObject(student);
        //Student s=(Student)JSONObject.toBean(object,Student.class);
        //studentService.insertStudents(s);
    }
}
