package com.softrear.libraryapi.controller;


import com.softrear.libraryapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    BookService bookService;

    @RequestMapping("/login")
    public String chekAuth(){
        return "Success";
    }
}
