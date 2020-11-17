package com.sise.design_book.controller;

import com.sise.design_book.pojo.Book;
import com.sise.design_book.pojo.Student;
import com.sise.design_book.service.BookService;
import com.sise.design_book.service.StudentService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import java.net.URLEncoder;


@Controller
public class FileController {

    @Autowired
    private BookService bookService;


    private boolean checkFile(String fileName) {
        //设置允许上传是的图像文件类型
        String suffixList = "txt";
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")
                + 1, fileName.length());
        if (suffixList.contains(suffix.trim().toLowerCase())) {
            return true;
        }
        return false;
    }

    //上传
    @RequestMapping(value="/fileUpload", method = RequestMethod.POST)
    public String uploadFile(Book book, @RequestParam(value = "resume", required = false) MultipartFile ebooks,
                             HttpServletRequest request, HttpSession session, Model model) {
        System.out.println(book);
        // 定义数据库文件路径
        String ebooksPath = null;
        // 设置上传保存地址目录

        String dirPath = "D:\\IDEAproject\\design_book\\src\\main\\resources\\static\\upload\\";
        File filePath = new File(dirPath);
        // 判断目录是否存在
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        // 判断文件是否存在
        if (!ebooks.isEmpty() && ebooks.getSize() > 0) {
            // 获取原文件名
            String oldFileName = ebooks.getOriginalFilename();
            // 判断文件格式
            if (checkFile(oldFileName)) {
                String newFileName = book.getId() + ".txt";
                // 上传文件
                try {
                    // 数据库保存路径
                    ebooksPath = dirPath + newFileName;
                    ebooks.transferTo(new File(ebooksPath));
                } catch (IOException e) {
                    e.printStackTrace();
                    return "";
                }
            } else {
                return "";
            }
        }

        int checkBook = bookService.queryBookCount(book);
        if (checkBook == 0) {
            book.setEbooks(ebooksPath);
            model.addAttribute(book);
            int flag  = bookService.addBook(book);
            if(flag>0){
                return "redirect:/queryAllBooks";
            }
            return "";
        } else {
            book.setEbooks(ebooksPath);
            model.addAttribute(book);
            int flag = bookService.editBook(book);
            if(flag>0){
                return "redirect:/queryAllBooks";
            }
            return "redirect:/queryAllBooks";
        }
    }

    //下载
    @RequestMapping("/download")
    public ResponseEntity<byte[] > fileDownload(HttpServletRequest request, String filename) throws Exception{

        //文件下载路劲
        String dirPath = "D:\\IDEAproject\\design_book\\src\\main\\resources\\static\\upload\\";
        //创建文件对象;
        File file2=new File(dirPath+File.separator+filename+".txt");
        //文件名编码
        filename=this.getFilename(request,filename+".txt");
        //设置相应头
        HttpHeaders headers =new HttpHeaders();
        //浏览器以下载形式打开
        headers.setContentDispositionFormData("filename", filename);
        //流得形式下载返回得数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //用ResponseEntity对象封装返回下载数据
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2),headers, HttpStatus.OK);

    }

    private String getFilename(HttpServletRequest request, String filename) throws Exception{
        String[] IEBrowserKeyWords={"MSIE","Trident","Edge"};
        String userAgent=request.getHeader("User-Agent");
        for(String keyWord : IEBrowserKeyWords){
            if (userAgent.contains(keyWord)) {
                return URLEncoder.encode(filename, "UTF-8");
            }
        }
        return new String(filename.getBytes("UTF-8"),"ISO-8859-1");
    }


}
