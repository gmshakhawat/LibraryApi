package com.softrear.libraryapi.controller;


import com.softrear.libraryapi.entity.Book;
import com.softrear.libraryapi.model.CommonResponse;
import com.softrear.libraryapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    @Autowired
    BookService bookService;


        @RequestMapping(method = RequestMethod.GET, value = "/books")
        public CommonResponse getBooks(){
            return   bookService.getBooks();
        }
//
        @RequestMapping(method = RequestMethod.GET, value = "/books/{id}")
        public CommonResponse getBook(@PathVariable Long id){
            return bookService.getBook(id);
        }

    @RequestMapping(method = RequestMethod.GET, value = "/books/search/{value}")
    public CommonResponse searchBookByIdOrTitle(@PathVariable String value, Pageable pageable){
        return bookService.searchBookByIdOrTitle(value,pageable);
    }


    @RequestMapping(method = RequestMethod.POST,value = "/books")
        public CommonResponse saveBook(@RequestBody Book book){
            return bookService.saveBook(book);
        }

    @RequestMapping(method = RequestMethod.DELETE,value = "/books/{id}")
    public CommonResponse deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/books")
    public CommonResponse updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }
}
