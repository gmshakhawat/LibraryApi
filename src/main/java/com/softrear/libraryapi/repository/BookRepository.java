package com.softrear.libraryapi.repository;

import com.softrear.libraryapi.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAllByTitleContaining(String title,  Pageable pageable);

//    Vendor
}
