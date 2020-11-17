package com.sise.design_book.mapper;

import com.sise.design_book.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

	//查询所有
	public List<Book> queryAllBooks();

	//删除
	public int deleteBookById(Book book);

	//模糊查询
	public List<Book> queryByName(Book book);

	//添加图书
	public int addBook(Book book);

	public int queryBookCount(Book book);

	//显示图书
	public Book queryBookById(Book book);

	//修改图书
	public int editBook(Book book);
	

}
