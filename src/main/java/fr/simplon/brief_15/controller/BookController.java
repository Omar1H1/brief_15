package fr.simplon.brief_15.controller;

import fr.simplon.brief_15.models.Book;
import fr.simplon.brief_15.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;

    public BookController (BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createBook (@RequestBody Book book) {
        bookService.createBook(book);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PostMapping("/book/{bookId}/borrow/{studentId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @PathVariable Long studentId) {
        try {
            Book borrowedBook = bookService.borrowBook(bookId, studentId);
            return new ResponseEntity<>(borrowedBook, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),null, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/book/{bookId}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId) {
        try {
            Book returnedBook = bookService.returnBook(bookId);
            return new ResponseEntity<>(returnedBook, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
