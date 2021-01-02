package bookapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {

    @Autowired
    private BookService bookservice;

    @RequestMapping("/")
    public String index(){
        return "Hello World Spring Boot";
    }

    @GetMapping("/books")
    public List getAllBooks() {
        return bookservice.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable String bookId){
        return bookservice.getBook(bookId);
    }

    @PostMapping(
            value="/addBook", consumes = "application/json", produces = "application/json")
    public void addBook(@RequestBody Book book)
    {
        bookservice.addBook(book);
    }

    @PutMapping("/books/{bookId}")
    public void updateBook(@PathVariable String bookId, @RequestBody Book book){
        bookservice.updateBook(bookId, book);
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable String bookId){
        bookservice.deleteBook(bookId);
    }
}
