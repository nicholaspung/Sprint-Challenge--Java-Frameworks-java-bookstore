package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    private String lname;
    private String fname;

    @ManyToMany
    @JoinTable(name = "wrote", joinColumns = {@JoinColumn(name = "authorid")}, inverseJoinColumns = {@JoinColumn(name = "bookid")})
    @JsonIgnoreProperties("authors")
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String lastname, String firstname) {
        this.lname = lastname;
        this.fname = firstname;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getLastname() {
        return lname;
    }

    public void setLastname(String lastname) {
        this.lname = lastname;
    }

    public String getFirstname() {
        return fname;
    }

    public void setFirstname(String firstname) {
        this.fname = firstname;
    }
}
