package com.example.bookstore.Controller;


import com.example.bookstore.Entity.Book;
import com.example.bookstore.Exceptions.BookNotFoundException;
import com.example.bookstore.Model.BookRequest;
import com.example.bookstore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add-book")
    public ResponseEntity<?> addBook(@RequestBody BookRequest bookRequest) {
        Book data = bookService.addBook(bookRequest);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/gte-all-books")
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok().body(books);

    }

    @GetMapping("/get-by/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) {
        Book book = bookService.getBookByTitle(title);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/get-book-by/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/by/{author}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String author) {
        List<Book> book = bookService.getBookByAuthor(author);
        return ResponseEntity.ok().body(book);

    }

    @DeleteExchange("/delete-by/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        try {
            Book updatedBook = bookService.updateBook(id, bookRequest);
            return ResponseEntity.ok(updatedBook);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An internal server error occurred: " + e.getMessage());
        }
    }

}
