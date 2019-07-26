package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "section")
public class Section extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sectionid;

    private String name;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private List<Book> books = new ArrayList<>();


    public Section() {
    }

    public Section(String name) {
        this.name = name;
    }

    public long getSectionid() {
        return sectionid;
    }

    public void setSectionid(long sectionid) {
        this.sectionid = sectionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
