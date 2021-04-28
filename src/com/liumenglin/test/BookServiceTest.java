package com.liumenglin.test;

import com.liumenglin.pojo.Book;
import com.liumenglin.pojo.Page;
import com.liumenglin.service.BookService;
import com.liumenglin.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Package: com.liumenglin.test
 * Description: TODO
 * Author: menglin liu
 * Date: 2021/4/20 15:11
 * Version 1.0
 */
public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "理想国", "柏拉图", new BigDecimal(25), 1100, 65, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(23);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(21, "人生的智慧", "叔本华", new BigDecimal(99), 110000, 0, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for(Book queryBook : bookService.queryBooks()){
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
}