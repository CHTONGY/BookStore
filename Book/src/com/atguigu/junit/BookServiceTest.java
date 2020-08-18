package com.atguigu.junit;

import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-22 10:44 上午
 */
public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryAllBooks() {
    }

    @Test
    public void queryPage() {
        Page page = bookService.queryPage(1, 6);
        System.out.println(page);
    }
}