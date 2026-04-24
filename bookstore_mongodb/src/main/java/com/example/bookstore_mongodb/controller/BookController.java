package com.example.bookstore_mongodb.controller;

import com.example.bookstore_mongodb.entity.Book;
import com.example.bookstore_mongodb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    // -------------------------- HW -------------------------
    // DELETE endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {

        Optional<Book> bookOpt = bookService.getBookById(id);

        if (bookOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(Map.of("error", "Book not found"));
        }

        bookService.deleteBookById(id);

        return ResponseEntity.ok(Map.of("message", "Book deleted successfully"));
    }
}
