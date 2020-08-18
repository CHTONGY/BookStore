package com.atguigu.junit;

import com.atguigu.bean.Book;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-22 10:08 上午
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "滚雪球", "巴菲特", new BigDecimal(78), 50, 30, null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(3);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "滚雪球", "巴菲特", new BigDecimal(82), 52, 28, null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(21);
        System.out.println(book);
    }

    @Test
    public void queryAllBooks() {
        List<Book> books = bookDao.queryAllBooks();
        books.forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount() {
        Long count = bookDao.queryForPageTotalCount();
        System.out.println(count);
    }

    @Test
    public void queryForItems() {
        List<Book> items = bookDao.queryForItems(1, 6);
        items.forEach(System.out::println);
    }


    @Test
    public void queryForPageTotalCountByPrice() {
        Long count = bookDao.queryForPageTotalCountByPrice(100, 200);
        System.out.println(count);
    }

    @Test
    public void queryForItemsByPrice() {
        List<Book> books = bookDao.queryForItemsByPrice(1, 4, 100, 200);
        books.forEach(System.out::println);
    }
}