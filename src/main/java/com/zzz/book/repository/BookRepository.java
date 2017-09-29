package com.zzz.book.repository;

import com.zzz.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b.id,b.name,b.author,b.time,b.icon,b.price FROM Book b where b.price>:price")
    List<Book> findPrice(double price);
}
