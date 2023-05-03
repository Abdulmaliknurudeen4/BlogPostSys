package com.intreswitch.articleblogsystemintv.controllers;

import com.intreswitch.articleblogsystemintv.dto.UserRegistrationDTO;
import com.intreswitch.articleblogsystemintv.services.AuthorService;
import com.intreswitch.articleblogsystemintv.security.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController()
@RequestMapping(value = "/AuthorController")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/registration")
    public ResponseEntity<Response> register(@RequestBody UserRegistrationDTO user) {
        boolean saveUser = authorService.save(user);
        if (saveUser) {
            return new ResponseEntity<Response>(new Response("user is saved successfully"), HttpStatus.CREATED);
        }
        return null;
    }
}
