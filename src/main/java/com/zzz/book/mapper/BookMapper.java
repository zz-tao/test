package com.zzz.book.mapper;

import com.zzz.book.model.Book;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public interface BookMapper {

    @SelectProvider(type = SqlProvider.class, method = "getBooks")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "time", column = "time"),
            @Result(property = "author", column = "author"),
    })
    List<Book> getBooks();


    @SelectProvider(type = SqlProvider.class, method = "getBook")
    Book getBook( int id);

    @Delete("delete from book where id=#{id}")
    int delete(int id);

    @Update("update book set name=#{name},price=#{price} where id=#{id}")
    int update(Book book);

    @Insert("insert into(name,price,author,time,icon) values(#{name},#{price},#{author},#{time},#{icon})")
    int save(Book book);


    class SqlProvider {
        public String getBooks() {
            return "select * from book";
        }

        public String getBook() {

            return new SQL() {
                {
                    SELECT("*");
                    FROM("book");
                    WHERE("id=#{id}");
                }
            }.toString();

            // return "select * from book where id=#{id}";
        }
    }
}
