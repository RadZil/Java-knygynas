package com.kitm.darbas1.Models;

import java.io.Reader;
import java.io.StringReader;

public class BookTaken {
    private Book book;
    private Reader reader;

    public BookTaken(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

}
