package com.sise.design_book.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.design_book.pojo.Book;
import com.sise.design_book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/queryAllBooks")
    public String queryAllBooks(@RequestParam(defaultValue = "1") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum,4);
        List<Book> books = bookService.queryAllBooks();
        PageInfo<Book> pageInfo=new PageInfo<>(books);
        model.addAttribute("pageInfos",pageInfo);
        return "adminInfo";
    }

    //删除
    @RequestMapping("/deleteBookById")
    public String deleteBook(Book book){
         int flag = bookService.deleteBookById(book);
            if(flag>0){
                return "redirect:/queryAllBooks";
            }
            return "";
        }

    //模糊查找
    @RequestMapping("/queryByName")
    public String queryBookByName(Book book,Model model,@RequestParam(defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<Book> books=bookService.queryByName(book);
        PageInfo<Book> pageInfos=new PageInfo<>(books);
        model.addAttribute("pageInfos",pageInfos);
        return "adminInfo";
    }

    //批量删除
    @RequestMapping("/delete")
    public String delete(String[] ids) {
        Book book=new Book();
        if (ids != null) {
            for (String id : ids) {
                book.setId(String.valueOf(id));
                bookService.deleteBookById(book);
            }
        }
        return "redirect:/queryAllBooks";
    }

    //跳转到添加页面
    @RequestMapping("/toaddBook")
    public String toaddBook(Book book) {
        return "addBook";
    }


    //显示图书
    @RequestMapping("/queryBookById")
    public String queryBookById(Book book,HttpSession session,HttpServletRequest request,Model model){
        Book thisBook = bookService.queryBookById(book);
        model.addAttribute("thisBook", thisBook);
        return "editBook";
    }


    @RequestMapping("/queryAllBook")
    public String queryAllBook(@RequestParam(defaultValue = "1") Integer pageNum, Model model){
        PageHelper.startPage(pageNum,4);
        List<Book> book=bookService.queryAllBooks();
        PageInfo<Book> pageInfo=new PageInfo<>(book);
        model.addAttribute("pageInfos",pageInfo);
        return "userInfo";
    }

    //模糊查找2
    @RequestMapping("/stuqueryByName")
    public String stuqueryBookByName(Book book,@RequestParam(defaultValue = "1") Integer pageNum,Model model){
        PageHelper.startPage(pageNum,4);
        List<Book> books=bookService.queryByName(book);
        PageInfo<Book> pageInfos=new PageInfo<>(books);
        model.addAttribute("pageInfos",pageInfos);
        return "userInfo";
    }



}
