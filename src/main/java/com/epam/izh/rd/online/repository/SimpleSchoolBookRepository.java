package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.service.AuthorService;
import com.epam.izh.rd.online.service.SimpleAuthorService;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    SchoolBook[] schoolBooks = new SchoolBook[0];


    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] findBooks = new SchoolBook[0];
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                findBooks = Arrays.copyOf(findBooks, findBooks.length + 1);
                findBooks[findBooks.length - 1] = schoolBook;
            }
        }
        return findBooks;
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        int count = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean removeByName(String name) {
        int count = 0;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                if (schoolBooks.length - 1 - i >= 0)
                    System.arraycopy(schoolBooks, i + 1, schoolBooks, i, schoolBooks.length - 1 - i);
                count++;
            }
        }
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - count);
        return count > 0;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }

    @Override
    public Author findAuthorByBookName(String name) {
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                //return authorService.findByFullName(schoolBooks[i].getAuthorName(), schoolBooks[i].getAuthorLastName());
                Author author = new Author();
                author.setName(schoolBook.getAuthorName());
                author.setLastName(schoolBook.getAuthorLastName());
                return author;
            }
        }
        return null;
    }
}
