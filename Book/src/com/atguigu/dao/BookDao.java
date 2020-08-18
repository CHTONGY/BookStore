package com.atguigu.dao;

import com.atguigu.bean.Book;

import java.util.List;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-22 9:47 上午
 */
public interface BookDao {
    // 增删改查

    /**
     * 添加图书
     * @param book
     * @return 返回-1表示失败
     */
    int addBook(Book book);

    /**
     * 通过id删除图书
     * @param id
     * @return 返回-1表示失败
     */
    int deleteBookById(Integer id);

    /**
     * 更新图书
     * @param book
     * @return 返回-1表示失败
     */
    int updateBook(Book book);

    /**
     * 根据id查询图书
     * @param id
     * @return 返回null表示查询失败
     */
    Book queryBookById(Integer id);

    /**
     * 查询已有的所有图书信息
     * @return 返回null表示查询失败
     */
    List<Book> queryAllBooks();

    /**
     * 查询总记录数
     * @return
     */
    Long queryForPageTotalCount();

    /**
     * 查询当前页数据
     * @return
     */
    List<Book> queryForItems(int pageNo, int pageSize);

    /**
     * 根据价格区间查询符合条件的记录数
     * @param min
     * @param max
     * @return
     */
    Long queryForPageTotalCountByPrice(int min, int max);

    /**
     * 根据价格区间查找当前页数据
     * @param pageNo
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    List<Book> queryForItemsByPrice(Integer pageNo, int pageSize, int min, int max);
}
