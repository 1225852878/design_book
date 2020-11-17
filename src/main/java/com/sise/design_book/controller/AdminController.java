package com.sise.design_book.controller;

import com.sise.design_book.pojo.Book;
import com.sise.design_book.pojo.Student;
import com.sise.design_book.service.AdminService;
import com.sise.design_book.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;

    @RequestMapping("/login")
    public String logins(){
        return "login";
    }

    @RequestMapping(value = "/doLogin")
    public String login(String sno, String pwd, String role, Model model, HttpSession session) {
        //角色为“admin”,管理员
        if(role.equals("t")){
            //在模型数据中添加教师登陆成功的提示信息
            session.setAttribute("USER_SESSION",sno);
            //在模型数据中添加教师的工号
            if (adminService.login(sno, pwd)) {

                model.addAttribute("adminno",sno);
                return "redirect:/queryAllBooks";
            }else{
                //在模型数据中添加教师登陆失败的提示信息
                model.addAttribute("msg", "工号或密码错误，请重新登录！");
                //返回到登陆页面
                return "login";
            }
        }else{
            session.setAttribute("USER_SESSION",sno);
            //角色为“Student”，普通用户
            if(studentService.login(sno, pwd)){
                //在模型数据中添加学生登陆成功的提示信息
                model.addAttribute("msg","学生登录成功");
                model.addAttribute("stuno",sno);
                //跳转到userInfo.jsp
                return "redirect:/queryAllBook";
            }else{
                //在模型数据中添加学生登陆失败的提示信息
                model.addAttribute("msg", "学号或密码错误，请重新登录！");
                //返回到登陆页面
                return "login";
            }
        }
    }

    //退出
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        // 清除Session
        session.invalidate();
        // 重定向到登录页面的跳转方法
        return "login";
    }


}
