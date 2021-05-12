package com.liumenglin.service;

import com.liumenglin.dao.impl.BaseDao;
import com.liumenglin.pojo.Book;
import com.liumenglin.pojo.Page;

import java.util.List;

/**
 * Package: com.liumenglin.service
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/4/20 15:04
 * Version 1.0
 */
public interface BookService {

    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    Book queryBookByName(String name);

    List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
