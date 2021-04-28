package com.liumenglin.service.impl;

import com.liumenglin.dao.BookDao;
import com.liumenglin.dao.impl.BaseDao;
import com.liumenglin.dao.impl.BookDaoImpl;
import com.liumenglin.pojo.Book;
import com.liumenglin.pojo.Page;
import com.liumenglin.service.BookService;

import java.util.List;

/**
 * Package: com.liumenglin.service.impl
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/4/20 15:06
 * Version 1.0
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
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        // 设置当前页码
        page.setPageNo(pageNo);
        // 设置当前页显示数量
        page.setPageSize(pageSize);
        // 设置总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        // 设置总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        // 设置当前页数据
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForItems(begin, pageSize);
        page.setItems(items);
        return page;
    }
}
