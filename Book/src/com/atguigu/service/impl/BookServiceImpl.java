package com.atguigu.service.impl;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;

import java.util.List;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-22 10:42 上午
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryAllBooks() {
        return bookDao.queryAllBooks();
    }

    @Override
    public Page<Book> queryPage(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        Long pageTotalCount = bookDao.queryForPageTotalCount();
        int pageTotal = (int) ((pageTotalCount % pageSize == 0) ? pageTotalCount / pageSize : pageTotalCount / pageSize + 1);
        page.setPageNo(pageNo, pageTotal);

        List<Book> items = bookDao.queryForItems(page.getPageNo(), pageSize);
        page.setPageTotal(pageTotal);
        page.setPageTotalCount(pageTotalCount);
        page.setPageSize(pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();

        Long pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        int pageTotal = (int) ((pageTotalCount % pageSize == 0) ? pageTotalCount / pageSize : pageTotalCount / pageSize + 1);
        page.setPageNo(pageNo, pageTotal);

        List<Book> items = bookDao.queryForItemsByPrice(page.getPageNo(), pageSize, min, max);
        page.setPageTotal(pageTotal);
        page.setPageTotalCount(pageTotalCount);
        page.setPageSize(pageSize);
        page.setItems(items);
        return page;
    }
}
