package com.sise.design_book.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.design_book.pojo.Book;
import com.sise.design_book.pojo.Student;
import com.sise.design_book.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/toregister")
    public String toaddBook(Student student) {
        return "register";
    }

    /*private final static String UPLOAD_PATH = "static/images/";
    String dirPath = "src/main/resources/" + UPLOAD_PATH;*/

    private boolean checkimgFile(String fileName) {
        //设置允许上传是的图像文件类型
        String suffixList = "jpg";
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")
                + 1, fileName.length());
        if (suffixList.contains(suffix.trim().toLowerCase())) {
            return true;
        }
        return false;
    }


    @RequestMapping(value="/fileUpload2", method = RequestMethod.POST)
    public String uploadFile2(Student student, @RequestParam(value = "picture", required = false) MultipartFile photo,
                              HttpServletRequest request,Model model) {
        // 定义数据库文件路径
        String photoPath = null;
        String newFileName = null;
        // 设置上传保存地址目录
        String dirPath2 = "D:\\IDEAproject\\design_book\\src\\main\\resources\\static\\images\\";
        File filePath2 = new File(dirPath2);
        // 判断目录是否存在
        if (!filePath2.exists()) {
            filePath2.mkdir();
        }
        // 判断文件是否存在
        if (!photo.isEmpty() && photo.getSize() > 0) {
            // 获取原文件名
            String oldFileName = photo.getOriginalFilename();
            // 判断文件格式
            if (checkimgFile(oldFileName)) {
             newFileName = student.getStuno() + ".jpg";
                // 上传文件
                try {
                    // 数据库保存路径
                    photoPath = dirPath2 + newFileName;
                    photo.transferTo(new File(photoPath));
                } catch (IOException e) {
                    e.printStackTrace();
                    return "redirect:/queryAllBooks";
                }
            } else {
                return "";
            }
        }
        student.setPhoto(newFileName);
        model.addAttribute(student);
        int flag  = studentService.register(student);
        if(flag>0){
            return "redirect:/login";
        }
        return "";

    }

    //所有图书
    @RequestMapping("/queryAllStudent")
    public String queryAllStudent(@RequestParam(defaultValue = "1") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum,3);
        List<Student> students=studentService.queryAllStudent();
        PageInfo<Student> pageInfo=new PageInfo<>(students);
        model.addAttribute("pageInfos",pageInfo);
        return "adminInfoStu";
    }

    //查找
    @RequestMapping("/queryStudentByName")
    public String queryStudentByName(Student student,Model model,@RequestParam(defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<Student> students=studentService.queryStudentByName(student);
        PageInfo<Student> pageInfos=new PageInfo<>(students);
        /*request.setAttribute("books",bookList);*/
        model.addAttribute("pageInfos",pageInfos);
        return "adminInfoStu";
    }

    //删除
    @RequestMapping("/deleteStudentById")
    public String deleteStudent(Student student){
        int flag = studentService.deleteStudentById(student);
        if(flag>0){
            return "redirect:/queryAllStudent";
        }
        return "";
    }
}
