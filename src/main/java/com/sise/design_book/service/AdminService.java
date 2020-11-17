package com.sise.design_book.service;

import com.sise.design_book.mapper.AdminMapper;
import com.sise.design_book.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public boolean login(String adminno, String adminpwd){
        Admin t=adminMapper.login(adminno, adminpwd);
        if (t!=null){
            return true;
        }else
            return false;
    }


}
