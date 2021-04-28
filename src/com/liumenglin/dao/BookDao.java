package com.liumenglin.dao;

import com.liumenglin.pojo.Book;

import java.util.List;

/**
 * Package: com.liumenglin.dao
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/4/20 14:13
 * Version 1.0
 */
public interface BookDao {

    int addBook(Book book);

    int deleteBookById(Integer id);

    int updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForItems(int begin, int pageSize);
}
