package com.zzz.book.controller;

import com.zzz.book.model.Book;
import com.zzz.book.model.ResultModel;
import com.zzz.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * spring-data-jpa 使用
 * <p>
 * <p>
 * http://www.ityouknow.com/springboot/2016/08/20/springboot(%E4%BA%94)-spring-data-jpa%E7%9A%84%E4%BD%BF%E7%94%A8.html
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class BookController {

    private final static Random RANDOM = new Random();

    @Autowired
    private BookRepository mBookRepository;

    /**
     * 新增书籍信息
     *
     * @return
     */
    @PostMapping("/book")
    public Book save(Book book) {
        book.setTime(new Date());
        book.setIcon("icon_" + RANDOM.nextInt(9) + ".png");
        return mBookRepository.save(book);
    }

    /**
     * 修改
     *
     * @param id
     * @return
     */
    @DeleteMapping("/book/{id}")
    public ResultModel delete(@PathVariable("id") int id) {
        ResultModel model = new ResultModel();
        int res = -1;
        try {
            mBookRepository.delete(id);
            res = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.code = res == 1 ? 1 : 0;
        model.msg = res == 1 ? "success" : "failure";
        return model;
    }

    /**
     * 删除
     * 调用 save 方法 没有id新增 有id就修改
     *
     * @param book
     * @return
     */
    @PutMapping("/book")
    public Book update(Book book) {
        return mBookRepository.save(book);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") int id) {
        return mBookRepository.findOne(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("/book")
    public List<Book> getBooks() {
        return mBookRepository.findAll();
    }

    @GetMapping("/book/price/{price}")
    public List<Book> findBookByPrice(@PathVariable("price") double price) {
        return mBookRepository.findPrice(price);
    }
}
