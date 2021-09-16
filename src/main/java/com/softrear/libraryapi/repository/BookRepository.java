package com.softrear.libraryapi.repository;

import com.softrear.libraryapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByTitle(String title);
//    Vendor
}
