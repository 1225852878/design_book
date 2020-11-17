package com.sise.design_book.mapper;

import com.sise.design_book.pojo.Book;
import com.sise.design_book.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {

    //登录
    public Student login(@Param("stuno") String stuno, @Param("stupwd") String stupwd);

    //注册
    public int register(Student stu);

    //查询所有
    public List<Student> queryAllStudent();

    //模糊查询
    public List<Student> queryStudentByName(Student student);

    //删除
    public int deleteStudentById(Student student);
}
