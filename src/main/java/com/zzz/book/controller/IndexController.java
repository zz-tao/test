package com.zzz.book.controller;

import com.zzz.book.model.Book;
import com.zzz.book.model.ResultModel;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@EnableAutoConfiguration
@RestController
@CrossOrigin
@RequestMapping("/api/index")
public class IndexController {

    private final static String[] ICON = {"icon_0.png", "icon_1.png",
            "icon_2.png", "icon_3.png",
            "icon_4.png", "icon_5.png",
            "icon_6.png", "icon_7.png",
            "icon_8.png", "icon_9.png"};

    private final static Random RANDOM = new Random();

    private static List<Book> books = new ArrayList<>();

    static {
        Date time = new Date();
        for (int i = 0; i < 20; i++) {
            books.add(new Book(i, "冰与火之歌" + RANDOM.nextInt(9999),
                    "艾丽娅" + RANDOM.nextInt(5555),
                    ICON[RANDOM.nextInt(9)], 20.0 * RANDOM.nextInt(20), time));
        }
    }


    @RequestMapping("/index")
    public String index(String name) {
        return "hello:" + name;
    }


    @PostMapping("/login")
    public ResultModel login(String name, String pwd) {
        ResultModel<List<Book>> model = new ResultModel<>();
        int res = -1;
        if ((name.equals("admin") || name.equals("root")) &&
                pwd.equals("123123")) {
            res = 1;
        }
        model.code = res == 1 ? 200 : 500;
        model.msg = res == 1 ? "登录成功！" : "登录失败！";
        return model;
    }

    @GetMapping("/book")
    public ResultModel getBooks() {
        ResultModel<List<Book>> model = new ResultModel<>();
        model.code = 200;
        model.msg = "所有书籍信息";
        model.data = books;
        return model;
    }

    @GetMapping("/book/{position}")
    public ResultModel getBook(@PathVariable("position") int position) {
        ResultModel<Book> model = new ResultModel<>();
        int res = 0;
        try {
            model.data = books.get(position);
            res = 1;
        } catch (Exception e) {
            // e.printStackTrace();
        }

        model.code = res == 1 ? 200 : 500;
        model.msg = res == 1 ? "书籍信息！" : "未获取到书籍信息！";

        return model;
    }

    @PostMapping("/book")
    public ResultModel save(Book book) {
        ResultModel<Book> model = new ResultModel<>();
        book.setId(books.size());
        book.setTime(new Date());
        book.setIcon(ICON[RANDOM.nextInt(9)]);
        boolean b = books.add(book);

        model.code = b ? 200 : 500;
        model.msg = b ? "新增成功！" : "新增失败！";
        model.data = book;

        return model;
    }

    @DeleteMapping("/book/{position}")
    public ResultModel delete(@PathVariable("position") int position) {
        ResultModel<Book> model = new ResultModel<>();

        int res = 0;
        try {
            books.remove(position);
            res = 1;
        } catch (Exception e) {
            // e.printStackTrace();
        }

        model.code = res == 1 ? 200 : 500;
        model.msg = res == 1 ? "删除成功！" : "删除失败！";
        return model;
    }

    @PutMapping("/book")
    public ResultModel update(Book book) {
        ResultModel<Book> model = new ResultModel<>();
        int res = -1;
        try {
            int position = book.getId();
            Book b = books.get(position);
            b.setName(book.getName());
            b.setAuthor(book.getAuthor());
            b.setPrice(book.getPrice());
            b.setTime(new Date());
            res = 1;
        } catch (Exception e) {
            // e.printStackTrace();
        }

        model.code = res == 1 ? 200 : 500;
        model.msg = res == 1 ? "修改成功！" : "修改失败！";

        return model;
    }
}
