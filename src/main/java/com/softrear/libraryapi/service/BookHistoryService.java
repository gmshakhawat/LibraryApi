package com.softrear.libraryapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softrear.libraryapi.entity.Book;
import com.softrear.libraryapi.entity.BookHistory;
import com.softrear.libraryapi.entity.User;
import com.softrear.libraryapi.model.CommonResponse;
import com.softrear.libraryapi.repository.BookHistoryRepository;
import com.softrear.libraryapi.repository.BookRepository;
import com.softrear.libraryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookHistoryService {

    @Autowired
    BookHistoryRepository bookHistoryRepository;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;


    public CommonResponse getBookHistory( Pageable pageable) {
        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Book History List Found!");


            Page<BookHistory> bookList = bookHistoryRepository.findAll( pageable);
            for(BookHistory bookHistory:bookList){
                User user = bookHistory.getUser();
                bookHistory.setUser(user);
            }

            if(bookList.getContent().isEmpty()){
                throw new Exception("Data Not Found");
            }
            commonResponse.setContent(objectMapper.writeValueAsString(bookList));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Data not Found");
        }

        return commonResponse;
    }

    public CommonResponse getBookHistoryByStatus(String type,  Pageable pageable) {
        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Book History List Found!");


            boolean status=false;
            if(type.equals("return")){
                status=true;
            }


            Page<BookHistory> bookList = bookHistoryRepository.findByIsReturned(status, pageable);
            for(BookHistory bookHistory:bookList){
                User user = bookHistory.getUser();
                bookHistory.setUser(user);
            }

            if(bookList.getContent().isEmpty()){
                throw new Exception("Data Not Found");
            }
            commonResponse.setContent(objectMapper.writeValueAsString(bookList));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Data not Found");
        }

        return commonResponse;
    }


    public CommonResponse getBookHistoryByBookId(Long bookId, Pageable pageable) {
        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Book History List Found!");


            Page<BookHistory> bookList = bookHistoryRepository.findByBookId(bookId, pageable);
            if(bookList.getContent().isEmpty()){
                throw new Exception("Data Not Found");
            }
            commonResponse.setContent(objectMapper.writeValueAsString(bookList));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Data not Found");
        }

        return commonResponse;
    }

    public CommonResponse getBookHistoryByBooKAndStatus(Long userId, boolean status, Pageable pageable) {
        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Book History List Found!");


            Page<BookHistory> bookList = bookHistoryRepository.findByBookIdAndIsReturned(userId,status, pageable);
            if(bookList.getContent().isEmpty()){
                throw new Exception("Data Not Found");
            }
            commonResponse.setContent(objectMapper.writeValueAsString(bookList));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to get data");
        }

        return commonResponse;
    }


    public CommonResponse getBookHistoryByUserId(Long userId, Pageable pageable) {
        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Book History List Found!");


            Page<BookHistory> bookList = bookHistoryRepository.findByUserId(userId, pageable);
            if(bookList.getContent().isEmpty()){
                throw new Exception("Data Not Found");
            }
            commonResponse.setContent(objectMapper.writeValueAsString(bookList));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to get data");
        }

        return commonResponse;
    }



    public CommonResponse getBookHistoryByUserBookStatus(Long userId, boolean status, Pageable pageable) {
        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Book History List Found!");


            Page<BookHistory> bookList = bookHistoryRepository.findByUserIdAndIsReturned(userId,status, pageable);
            if(bookList.getContent().isEmpty()){
                throw new Exception("Data Not Found");
            }
            commonResponse.setContent(objectMapper.writeValueAsString(bookList));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to get data");
        }

        return commonResponse;
    }



    public CommonResponse borrowBook(Long bookId,Long userId,BookHistory bookHistory) {

        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {

            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Book borrowed Successfully!");


            Book book =bookRepository.findById(bookId).get();
            book.setAvailable(book.getAvailable()-1);
            User user= userRepository.findById(userId).get();

            bookHistory.setBook(book);
            bookHistory.setUser(user);
            bookHistory.setIsReturned(false);
            bookHistory.setCopyAfter(book.getAvailable());

            bookHistoryRepository.save(bookHistory);
            bookRepository.save(book);










//            if(!vendor.isPresent()){
//                throw new Exception("Data Not Found with this id "+id);
//            }
//            commonResponse.setContent(objectMapper.writeValueAsString(vendor.get()));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to borrow book with id "+bookId);
        }

        return commonResponse;
    }


    public CommonResponse returnBook(BookHistory bookHistory) {

        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {

            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Book Returned Successfully!");



//                Book book =bookRepository.findById(bookId).get();
                bookHistory.getBook().setAvailable(bookHistory.getBook().getAvailable()+1);
                bookHistory.setCopyAfter(bookHistory.getBook().getAvailable());
                bookHistory.setIsReturned(true);
                bookHistory.setUpdatedAt(new Date());
                bookHistoryRepository.save(bookHistory);
                bookRepository.save(bookHistory.getBook());



        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to return book with title "+bookHistory.getBook().getTitle());
        }

        return commonResponse;
    }



//    public CommonResponse searchBookByIdOrTitle(String value) {
//        CommonResponse commonResponse=new CommonResponse();
//        ObjectMapper objectMapper=new ObjectMapper();
//        try {
////        commonResponse.getContent="";
//            commonResponse.setHasError(false);
//            commonResponse.setDecentMessage("Success");
////
//            List<Book> books = bookRepository.findAllByTitle(value);
//            if(books.isEmpty()){
//                throw new Exception("Data Not Found with the type of "+value);
//            }
//
//            commonResponse.setContent(objectMapper.writeValueAsString(books));
//        }catch (Exception e){
//            e.printStackTrace();
//            commonResponse.setHasError(true);
//            commonResponse.setErrorDetails(e.getMessage());
//            commonResponse.setDecentMessage("Failed to get data");
//        }
//
//        return commonResponse;
//    }
//
//    public CommonResponse deleteBook(Long id) {
//
//        CommonResponse commonResponse=new CommonResponse();
//        ObjectMapper objectMapper=new ObjectMapper();
//        try {
////        commonResponse.getContent="";
//            commonResponse.setHasError(false);
//            commonResponse.setDecentMessage("Success");
//            bookRepository.deleteById(id);
////            commonResponse.setContent(objectMapper.writeValueAsString(vendorRepository.findVendorByType(type)));
//        }catch (Exception e){
//            e.printStackTrace();
//            commonResponse.setHasError(true);
//            commonResponse.setErrorDetails(e.getMessage());
//            commonResponse.setDecentMessage("Failed to delete data with id "+id);
//        }
//        return commonResponse;
//    }
//
//    public CommonResponse updateBook(Book book) {
//
//        CommonResponse commonResponse=new CommonResponse();
//        ObjectMapper objectMapper=new ObjectMapper();
//        try {
////        commonResponse.getContent="";
//            commonResponse.setHasError(false);
//            commonResponse.setDecentMessage("Success");
//
//            if(bookRepository.findById(book.getId()).isPresent()){
//                bookRepository.save(book);
//            }else {
//                throw new Exception("Data Not Found with the id of "+ book.getId());
//            }
//
////            commonResponse.setContent(objectMapper.writeValueAsString(vendorRepository.findVendorByType(type)));
//        }catch (Exception e){
//            e.printStackTrace();
//            commonResponse.setHasError(true);
//            commonResponse.setErrorDetails(e.getMessage());
//            commonResponse.setDecentMessage("Failed to update ");
//        }
//        return commonResponse;
//
//    }
}
