package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorrepos;

    @Override
    public List<Author> findAll()
    {
        List<Author> list = new ArrayList<>();
        authorrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
