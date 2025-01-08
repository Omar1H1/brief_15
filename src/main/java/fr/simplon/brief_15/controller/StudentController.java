package fr.simplon.brief_15.controller;

import fr.simplon.brief_15.models.Book;
import fr.simplon.brief_15.models.Student;
import fr.simplon.brief_15.service.BookService;
import fr.simplon.brief_15.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private BookService bookService;

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/student/{id}/books")
    public ResponseEntity<List<Book>> getBorrowedBooks(@PathVariable Long id) {
        List<Book> borrowedBooks = studentService.getBorrowedBooks(id);
        return new ResponseEntity<>(borrowedBooks, HttpStatus.OK);
    }

}