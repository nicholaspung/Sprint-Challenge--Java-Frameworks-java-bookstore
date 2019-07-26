package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    private String title;
    private String ISBN;
    private int copy;

    @ManyToMany(mappedBy = "books")
    @JsonIgnoreProperties("books")
    private List<Author> authors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "sectionid")
    @JsonIgnoreProperties("books")
    private Section section;

    public Book() {
    }

    public Book(String title, String ISBN, int copy, List<Author> authors, Section section) {
        this.title = title;
        this.ISBN = ISBN;
        this.copy = copy;
        this.authors = authors;
        this.section = section;
    }

    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public String getBooktitle() {
        return title;
    }

    public void setBooktitle(String booktitle) {
        this.title = booktitle;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }
}
