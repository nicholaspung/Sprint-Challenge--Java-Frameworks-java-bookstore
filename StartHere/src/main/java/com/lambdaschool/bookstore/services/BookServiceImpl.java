package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import com.lambdaschool.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookrepos;

    @Autowired
    private AuthorRepository authorrepos;

    @Override
    public List<Book> findAll()
    {
        List<Book> list = new ArrayList<>();
        bookrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book findBookById(long id)
    {
        return bookrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public Book update(Book newBook, long id) {
        Book currentBook = bookrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (newBook.getBooktitle() != null) {
            currentBook.setBooktitle(newBook.getBooktitle());
        }
        if (newBook.getCopy() != 0) {
            currentBook.setCopy(newBook.getCopy());
        }
        if (newBook.getISBN() != null) {
            currentBook.setISBN(newBook.getISBN());
        }
        return bookrepos.save(currentBook);
    }

    @Override
    public Book addAuthor(long bookid, long authorid) {
        Book b = bookrepos.findById(bookid).orElseThrow(() -> new ResourceNotFoundException(Long.toString(bookid)));
        Author a = authorrepos.findById(authorid).orElseThrow(() -> new ResourceNotFoundException(Long.toString(authorid)));

        List<Author> bookAuthors = new ArrayList<>();
        b.getAuthors().iterator().forEachRemaining(bookAuthors::add);
        bookAuthors.add(a);

        b.setAuthors(bookAuthors);
        return bookrepos.save(b);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        if (bookrepos.findById(id).isPresent())
        {
            bookrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }
}
