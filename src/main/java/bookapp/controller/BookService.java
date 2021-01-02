package bookapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List getAllBooks(){
        List books = new ArrayList();
        bookRepository.findAll().forEach(books::add);
        return books;
    };

    public Book getBook(String Id){
        return bookRepository.findById(Integer.valueOf(Id)).orElseGet(Book::new);
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public void updateBook(String Id, Book book){
        bookRepository.save(book);
    }

    public void deleteBook(String Id){
        bookRepository.deleteById(Integer.valueOf(Id));
    }
}
