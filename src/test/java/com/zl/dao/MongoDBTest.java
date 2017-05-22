package com.zl.dao;

import com.zl.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/5/7.
 */
@Component
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBTest {

    @Resource
    private MongoTemplate mongoTemplate;


    @Test
    public void run() throws Exception {
      //  insert();
        find();
    }

    private void insert() {
        Student user = new Student();
        user.setId("123456789");
        user.setName("张三sfdfd");
        mongoTemplate.insert(user);
    }

    private void find() {
        Student student = mongoTemplate.findOne(Query.query(Criteria.where("id").is("123456789")),
                Student.class);
        System.out.println(student.getName());
    }
}
