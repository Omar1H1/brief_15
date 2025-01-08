package fr.simplon.brief_15.service;

import fr.simplon.brief_15.models.Book;
import fr.simplon.brief_15.models.Student;
import fr.simplon.brief_15.repository.BookRepository;
import fr.simplon.brief_15.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public BookService(BookRepository bookRepository, StudentRepository studentRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book borrowBook(Long bookId, Long studentId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (book.isAvailable()) {
                Optional<Student> optionalStudent = studentRepository.findById(studentId);
                if (optionalStudent.isPresent()) {
                    book.setAvailable(false);
                    book.setBorrower(optionalStudent.get());
                    return bookRepository.save(book);
                } else {
                    throw new RuntimeException("Student not found");
                }
            } else {
                throw new RuntimeException("Book is not available for borrowing");
            }
        } else {
            throw new RuntimeException("Book not found");
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}