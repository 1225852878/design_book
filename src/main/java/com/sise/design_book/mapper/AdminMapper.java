package com.sise.design_book.mapper;

import com.sise.design_book.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

    public Admin login(@Param("adminno") String adminno, @Param("adminpwd") String adminpwd);
}
