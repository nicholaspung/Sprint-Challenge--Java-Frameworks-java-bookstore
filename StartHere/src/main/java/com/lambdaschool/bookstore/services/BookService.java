package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> findAll(Pageable pageable);

    Book findBookById(long id);

    Book update(Book book, long id);

    Book addAuthor(long bookid, long authorid);

    void delete(long id);
}
