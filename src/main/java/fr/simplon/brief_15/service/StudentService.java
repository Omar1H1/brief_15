package fr.simplon.brief_15.service;

import fr.simplon.brief_15.models.Book;
import fr.simplon.brief_15.models.Student;
import fr.simplon.brief_15.repository.BookRepository;
import fr.simplon.brief_15.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;

    public StudentService(StudentRepository studentRepository, BookRepository bookRepository) {
        this.studentRepository = studentRepository;
        this.bookRepository = bookRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Book> getBorrowedBooks(Long studentId) {
        return bookRepository.findByBorrowerId(studentId);
    }
}