package com.softrear.libraryapi.controller;


import com.softrear.libraryapi.entity.Book;
import com.softrear.libraryapi.entity.BookHistory;
import com.softrear.libraryapi.exception.ResourceNotFoundException;
import com.softrear.libraryapi.model.CommonResponse;
import com.softrear.libraryapi.repository.BookHistoryRepository;
import com.softrear.libraryapi.repository.BookRepository;
import com.softrear.libraryapi.service.BookHistoryService;
import com.softrear.libraryapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1")
public class BookHistoryController {
    @Autowired
    BookHistoryService bookHistoryService;

    @Autowired
    BookHistoryRepository bookHistoryRepository;

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/books/histories")
    public CommonResponse getAllBookHistory(Pageable pageable) {
        return bookHistoryService.getBookHistory( pageable);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/books/{bookId}/histories")
    public CommonResponse getAllBookHistoryByBookId(@PathVariable (value = "bookId") Long bookId,
                                                    Pageable pageable) {
        return bookHistoryService.getBookHistoryByBookId(bookId, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{bookId}/histories")
    public CommonResponse getAllBookHistoryByUserId(@PathVariable (value = "bookId") Long bookId,
                                                    Pageable pageable) {
        return bookHistoryService.getBookHistoryByUserId(bookId, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/{bookId}/histories/{bookType}")
    public CommonResponse getBookAllHistoryByStatus(@PathVariable Long bookId,
                                                        @PathVariable String bookType,
                                                        Pageable pageable) {

        boolean status=false;
        if(bookType.toLowerCase(Locale.ROOT).equals("borrowed")){
            status=false;
        }else if(bookType.toLowerCase(Locale.ROOT).equals("returned")){
            status=true;
        }
        return bookHistoryService.getBookHistoryByBooKAndStatus(bookId,status, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}histories/{bookType}")
    public CommonResponse getUserAllBookHistoryByStatus(@PathVariable (value = "userId") Long userId,
                                                    @PathVariable String bookType,
                                                    Pageable pageable) {

        boolean status=false;
        if(bookType.toLowerCase(Locale.ROOT).equals("borrowed")){
            status=false;
        }else if(bookType.toLowerCase(Locale.ROOT).equals("returned")){
            status=true;
        }
        return bookHistoryService.getBookHistoryByUserBookStatus(userId,status, pageable);
    }


    @PostMapping("/books/{bookId}/{userId}/borrow")
    public CommonResponse borrowBook(@PathVariable (value = "bookId") Long bookId,
                                  @PathVariable Long userId,
                                  @RequestBody BookHistory bookHistory) {


        return bookHistoryService.borrowBook(bookId,userId,bookHistory);
    }

    @PutMapping("/books/return")
    public CommonResponse returnBook( @RequestBody BookHistory bookHistory) {
//        if(!bookRepository.existsById(bookHistory.getBook().getId())) {
//            throw new ResourceNotFoundException("BookId " + bookId + " not found");
//        }

        return bookHistoryService.returnBook(bookHistory);
    }







//        @RequestMapping(method = RequestMethod.GET, value = "/book/history")
//        public CommonResponse getBooks(){
//            return   bookService.getBooks();
//        }
////
//        @RequestMapping(method = RequestMethod.GET, value = "/books/{id}")
//        public CommonResponse getBook(@PathVariable Long id){
//            return bookService.getBook(id);
//        }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/books/search/{value}")
//    public CommonResponse searchBookByIdOrTitle(@PathVariable String value){
//        return bookService.searchBookByIdOrTitle(value);
//    }

//
//    @RequestMapping(method = RequestMethod.POST,value = "/books")
//        public void saveBook(@RequestBody Book book){
//            bookService.saveBook(book);
//        }
//
//    @RequestMapping(method = RequestMethod.DELETE,value = "/books/{id}")
//    public CommonResponse deleteBook(@PathVariable Long id){
//        return bookService.deleteBook(id);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT,value = "/books")
//    public CommonResponse updateBook(@RequestBody Book book){
//        return bookService.updateBook(book);
//    }
}
