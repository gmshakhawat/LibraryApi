package com.softrear.libraryapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softrear.libraryapi.entity.Book;
import com.softrear.libraryapi.entity.User;
import com.softrear.libraryapi.model.CommonResponse;
import com.softrear.libraryapi.repository.BookRepository;
import com.softrear.libraryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public CommonResponse getUsers() {
        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
//        commonResponse.getContent="";
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("User List Found!");
            List<User> userList = userRepository.findAll();
//            if(!vendorList.()){
//                throw new Exception("Data Not Found");
//            }
            commonResponse.setContent(objectMapper.writeValueAsString(userList));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to get data");
        }

        return commonResponse;
    }

    public CommonResponse getUser(Long id) {

        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
//        commonResponse.getContent="";
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Vendor Found!");
            Optional<User> user= userRepository.findById(id);
            if(!user.isPresent()){
                throw new Exception("Data Not Found with this id "+id);
            }
            commonResponse.setContent(objectMapper.writeValueAsString(user.get()));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to get data with id "+id);
        }

        return commonResponse;
    }

    public CommonResponse saveUser(User user) {


        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
//        commonResponse.getContent="";
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Success");

            userRepository.save(user);
//            commonResponse.setContent(objectMapper.writeValueAsString(vendorRepository.findVendorByType(type)));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to add "+ user.getName());
        }
        return commonResponse;
    }


    public CommonResponse searchUserByName(String name) {
        CommonResponse commonResponse=new CommonResponse();
        ObjectMapper objectMapper=new ObjectMapper();
        try {
//        commonResponse.getContent="";
            commonResponse.setHasError(false);
            commonResponse.setDecentMessage("Success");
//
            List<User> users = userRepository.findAllByName(name);
            if(users.isEmpty()){
                throw new Exception("Data Not Found with the type of "+name);
            }

            commonResponse.setContent(objectMapper.writeValueAsString(name));
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setHasError(true);
            commonResponse.setErrorDetails(e.getMessage());
            commonResponse.setDecentMessage("Failed to get data");
        }

        return commonResponse;
    }

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
