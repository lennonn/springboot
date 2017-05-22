package com.zl.dao;

import com.zl.entity.Student;

import java.util.List;

/**
 * Created by Administrator on 2017/5/6.
 */
public interface StudentDao {
    List<Student> getStudents();

    void insertStudents(Student student);
}
