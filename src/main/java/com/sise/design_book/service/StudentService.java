package com.sise.design_book.service;

import com.sise.design_book.mapper.StudentMapper;
import com.sise.design_book.pojo.Book;
import com.sise.design_book.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public boolean login(String stuno, String stupwd) {
        Student stu=studentMapper.login(stuno, stupwd);
        if (stu!=null){
            return true;
        }else
            return false;
    }

    public int register(Student stu) {
        return studentMapper.register(stu);
    }

    public List<Student> queryAllStudent() {
        List<Student> students =studentMapper.queryAllStudent();
        return students;
    }

    public int deleteStudentById(Student student) {
        int flag = studentMapper.deleteStudentById(student);
        return flag;
    }

    public List<Student> queryStudentByName(Student student) {
        List<Student> students=studentMapper.queryStudentByName(student);
        return students;
    }
}
