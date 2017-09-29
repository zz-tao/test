package com.zzz.book.controller;

import com.zzz.book.mapper.BookMapper;
import com.zzz.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class Book2Controller {

    @Autowired
    private BookMapper mBookMapper;

    @GetMapping("/book2")
    public List<Book> getBooks() {
        return mBookMapper.getBooks();
    }

    @GetMapping("/book2/{id}")
    public Book getBook(@PathVariable("id") int id) {
        return mBookMapper.getBook(id);
    }


}
