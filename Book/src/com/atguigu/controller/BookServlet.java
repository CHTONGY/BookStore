package com.atguigu.controller;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-22 10:47 上午
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求的参数，封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo += 1;
        // 2. 调用bookService保存到数据库
        bookService.addBook(book);
        // 3. 重定向回book_manager页面
//        req.getRequestDispatcher("").forward(req,resp);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取图书编号
        String sid = req.getParameter("id");
        // 2. 调用bookService删除
        Integer id = WebUtils.parseInt(sid, 0);
        bookService.deleteBookById(id);
        // 3. 重定向回book_manager页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求参数，封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        book.setId(WebUtils.parseInt(req.getParameter("id"), 0));
        // 2. 调用BookService的updateBook修改图书
        bookService.updateBook(book);
        // 3. 重定向
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求的图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 2. 调用bookService查询图书
        Book book = bookService.queryBookById(id);
        // 3. 将图书保存到request域中
        req.setAttribute("book", book);
        // 4. 将请求转发到pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 通过bookService查询全部图书
        List<Book> books = bookService.queryAllBooks();
        // 2. 把全部图书保存在request域中
        req.setAttribute("books",books);
        // 3. 请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求参数pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        // 2. 调用bookService获取Page对象
        Page<Book> page = bookService.queryPage(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        // 3. 保存到request域中
        req.setAttribute("page",page);
        // 4. 请求转发回/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
