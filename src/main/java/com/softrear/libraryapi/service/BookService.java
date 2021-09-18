package com.softrear.libraryapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softrear.libraryapi.entity.Book;
import com.softrear.libraryapi.model.CommonResponse;
import com.softrear.libraryapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public CommonResponse getBooks(Pageable pageable) {
        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
//        commonResponse.getContent="";
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Vendor List Found!");
            Page<Book> bookList = bookRepository.findAll(pageable);
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

    public CommonResponse getBook(Long id) {

        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
//        commonResponse.getContent="";
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Book Found!");
            Optional<Book> vendor= bookRepository.findById(id);
            if(!vendor.isPresent()){
                throw new Exception("Data Not Found with this id "+id);
            }
            commonResponse.setContent(objectMapper.writeValueAsString(vendor.get()));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to get data with id "+id);
        }

        return commonResponse;
    }

    public CommonResponse saveBook(Book book) {


        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
//        commonResponse.getContent="";
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Success");

            bookRepository.save(book);
//            commonResponse.setContent(objectMapper.writeValueAsString(vendorRepository.findVendorByType(type)));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to add "+ book.getTitle());
        }
        return commonResponse;
    }


    public CommonResponse searchBookByIdOrTitle(String value, Pageable pageable) {
        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
//        commonResponse.getContent="";
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Success");
//
            Page<Book> books = bookRepository.findAllByTitleContaining(value,pageable);
            if(books.getContent().isEmpty()){
                throw new Exception("Data Not Found with the type of "+value);
            }

            commonResponse.setContent(objectMapper.writeValueAsString(books.getContent()));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to get data");
        }

        return commonResponse;
    }

    public CommonResponse deleteBook(Long id) {

        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
//        commonResponse.getContent="";
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Success");
            bookRepository.deleteById(id);
//            commonResponse.setContent(objectMapper.writeValueAsString(vendorRepository.findVendorByType(type)));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to delete data with id "+id);
        }
        return commonResponse;
    }

    public CommonResponse updateBook(Book book) {

        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
//        commonResponse.getContent="";
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Success");

            if(bookRepository.findById(book.getId()).isPresent()){
                bookRepository.save(book);
            }else {
                throw new Exception("Data Not Found with the id of "+ book.getId());
            }

//            commonResponse.setContent(objectMapper.writeValueAsString(vendorRepository.findVendorByType(type)));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to update ");
        }
        return commonResponse;

    }
}
