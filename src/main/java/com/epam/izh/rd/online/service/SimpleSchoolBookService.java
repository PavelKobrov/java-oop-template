package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;

public class SimpleSchoolBookService implements BookService {

    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;


    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }


    @Override
    public boolean save(Book book) {
        SchoolBook school = (SchoolBook) book;
        if(authorService.findByFullName(school.getAuthorName(), school.getAuthorLastName()) == null) return false;
        return schoolBookBookRepository.save((SchoolBook) book);
    }

    @Override
    public Book[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return schoolBookBookRepository.getNumberOfBooksByName(name);
    }

    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {

        Author authorByBookName = schoolBookBookRepository.findAuthorByBookName(name);
        if(authorByBookName== null) return null;
        return authorService.findByFullName(authorByBookName.getName(), authorByBookName.getLastName());
    }
}
