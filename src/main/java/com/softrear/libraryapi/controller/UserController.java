package com.softrear.libraryapi.controller;


import com.softrear.libraryapi.entity.Book;
import com.softrear.libraryapi.entity.User;
import com.softrear.libraryapi.model.CommonResponse;
import com.softrear.libraryapi.service.BookService;
import com.softrear.libraryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService userService;


        @RequestMapping(method = RequestMethod.GET, value = "/users")
        public CommonResponse getUsers(){
            return   userService.getUsers();
        }
//
        @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
        public CommonResponse getUser(@PathVariable Long id){
            return userService.getUser(id);
        }

    @RequestMapping(method = RequestMethod.GET, value = "/users/search/{value}")
    public CommonResponse searchUserByIdOrTitle(@PathVariable String value){
        return userService.searchUserByName(value);
    }
//
//
    @RequestMapping(method = RequestMethod.POST,value = "/users")
        public CommonResponse saveUser(@RequestBody User user){
        return userService.saveUser(user);
        }
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
