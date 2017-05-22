package com.zl.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Administrator on 2017/5/4.
 */
@Document(collection = "student")
public class Student {
    String id;
    String name;

    public Student(){

    }
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(Student student){
        this(student.getId(),student.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
