package com.sise.design_book.service;

import com.sise.design_book.mapper.BookMapper;
import com.sise.design_book.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public List<Book> queryAllBooks() {
        List<Book> books =bookMapper.queryAllBooks();
        return books;
    }

    public int deleteBookById(Book book) {
        int flag = bookMapper.deleteBookById(book);
        return flag;
    }

    public List<Book> queryByName(Book book) {
        List<Book> books=bookMapper.queryByName(book);
        return books;
    }

    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    public int queryBookCount(Book book) {
        int count = bookMapper.queryBookCount(book);
        return count;
    }

    public Book queryBookById(Book book) {
        Book thisBook = bookMapper.queryBookById(book);
        return thisBook;
    }

    public int editBook(Book book) {
        int flag = bookMapper.editBook(book);
        return flag;
    }

}
