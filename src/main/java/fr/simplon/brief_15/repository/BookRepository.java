package fr.simplon.brief_15.repository;

import fr.simplon.brief_15.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBorrowerId(Long studentId);
}