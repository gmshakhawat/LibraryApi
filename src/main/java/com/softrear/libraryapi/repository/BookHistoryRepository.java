package com.softrear.libraryapi.repository;

import com.softrear.libraryapi.entity.Book;
import com.softrear.libraryapi.entity.BookHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {

    Page<BookHistory> findByBookId(Long bookId, Pageable pageable);
    Page<BookHistory> findByUserId(Long userId, Pageable pageable);
    Page<BookHistory> findByIsReturned(boolean returned, Pageable pageable);

//    Page<BookHistory> findAllByBookContainingOr(Long userId, Pageable pageable);

    Page<BookHistory> findByUserIdAndIsReturned(Long userId, boolean returned, Pageable pageable);
    Page<BookHistory> findByBookIdAndIsReturned(Long bookId, boolean returned, Pageable pageable);

    //    Optional<BookHistory> findByIdAndBookId(Long id, Long bookId);

//    List<Book> findVendorByType(String type);
//    Vendor
}
