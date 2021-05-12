package com.liumenglin.test;

import com.liumenglin.dao.BookDao;
import com.liumenglin.dao.impl.BookDaoImpl;
import com.liumenglin.pojo.Book;
import com.liumenglin.pojo.Page;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Package: com.liumenglin.test
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/4/20 14:25
 * Version 1.0
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "人生的智慧", "叔本华", new BigDecimal(99), 110000, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "爱你就像爱生命", "王小波", new BigDecimal(65), 11000, 50, null));
    }

    @Test
    public void queryBookById() {
        System.out.println( bookDao.queryBookById(19) );
    }

    @Test
    public void queryBookByName() {
        System.out.println( bookDao.queryBookByName("红楼梦") );
    }

    @Test
    public void queryBooks() {
        for(Book queryBook : bookDao.queryBooks()){
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForItems() {
        for(Book book : bookDao.queryForItems(8, Page.PAGE_SIZE)){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void queryForItemsByPrice() {
        for(Book book : bookDao.queryForItemsByPrice(0, Page.PAGE_SIZE, 10, 50)){
            System.out.println(book);
        }
    }
}