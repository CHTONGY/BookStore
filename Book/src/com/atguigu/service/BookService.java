package com.atguigu.service;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;

import java.util.List;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-22 10:40 上午
 */
public interface BookService {
    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryAllBooks();

    Page<Book> queryPage(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
