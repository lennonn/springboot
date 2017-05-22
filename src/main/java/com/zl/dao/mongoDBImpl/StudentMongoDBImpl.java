package com.zl.dao.mongoDBImpl;

import com.zl.dao.StudentDao;
import com.zl.entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/6.
 */
@Repository
@Qualifier("mongoDBStudent")
public class StudentMongoDBImpl implements StudentDao {
    //@Resource
   //private MongoTemplate mongoTemplate;
    @Override
    public List<Student> getStudents() {
       // return mongoTemplate.findAll(Student.class);
        return null;
    }

    @Override
    public void insertStudents(Student student) {

    }
}
