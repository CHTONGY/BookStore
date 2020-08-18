package com.atguigu.dao.impl;

import com.atguigu.bean.Book;
import com.atguigu.dao.BookDao;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-22 9:50 上午
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        String name = book.getName();
        String author = book.getAuthor();
        BigDecimal price = book.getPrice();
        Integer sales = book.getSales();
        Integer stock = book.getStock();
        String imgPath = book.getImgPath();
        return update(sql, name, author, price, sales, stock, imgPath);
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name=?, author=?, price=?, sales=?, stock=?, img_path=? where id=?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book where id=?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryAllBooks() {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Long queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        return (Long) queryForSingleValue(sql);
    }

    @Override
    public List<Book> queryForItems(int pageNo, int pageSize) {
        int begin = (pageNo - 1) * pageSize;
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book limit ?, ?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public Long queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        return (Long) queryForSingleValue(sql, min, max);
    }

    @Override
    public List<Book> queryForItemsByPrice(Integer pageNo, int pageSize, int min, int max) {
        int begin = (pageNo - 1) * pageSize;
        String sql = "select id, name, author, price, sales, stock, img_path imgPath " +
                "from t_book where price between ? and ? order by price limit ?, ?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }
}
