package com.zzz.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name",unique = true)
    private String name;

    @Column(nullable = false)
    private String author;

    private String icon;

    private double price;

    private Date time;

    public Book() {
    }


    public Book(String name, String author, String icon, double price, Date time) {
        this.name = name;
        this.author = author;
        this.icon = icon;
        this.price = price;
        this.time = time;
    }

    public Book(int id, String name, String author, String icon, double price, Date time) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.icon = icon;
        this.price = price;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
