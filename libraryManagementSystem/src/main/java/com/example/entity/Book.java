package com.example.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "bookId")
    private int bookId;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "price")
    private double price;
    @Column(name = "issueDate")
    private LocalDate issueDate;
    @Column(name = "dueDate")
    private LocalDate dueDate;
    @Column(name = "memberId")
    private Integer memberId;
    @Column(name = "isAvailable")
    private Boolean available;

    public Book() {

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Book(int bookId, String title, String author, double price, LocalDate issueDate, LocalDate dueDate, Integer memberId, Boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.memberId = memberId;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", memberId=" + memberId +
                ", available=" + available +
                '}';
    }
}
