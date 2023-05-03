package com.intreswitch.articleblogsystemintv.controllers;

import com.intreswitch.articleblogsystemintv.dto.PostDTO;
import com.intreswitch.articleblogsystemintv.entities.Author;
import com.intreswitch.articleblogsystemintv.security.model.Response;
import com.intreswitch.articleblogsystemintv.services.AuthorService;
import com.intreswitch.articleblogsystemintv.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin("*")
@RestController()
@RequestMapping(value = "/PostController")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;

    @PostMapping("/createPost")
    public ResponseEntity<Response> createUser(@RequestBody PostDTO postDTO, Principal principal) {
        Author author = authorService.getAuthorByEmail(principal.getName());
        boolean result = postService.savePost(postDTO, author);
        if (result) {
            return ResponseEntity.ok(new Response("Post Created Successfully."));
        }
        return ResponseEntity.ok(new Response("Post Creation Unsuccessful."));
    }

    @PostMapping("/updatePost")
    public ResponseEntity<Response> updatePost(@RequestBody PostDTO postDTO, Principal principal) {
        Author author = authorService.getAuthorByEmail(principal.getName());
        boolean result = postService.savePost(postDTO, author);
        if (result) {
            return ResponseEntity.ok(new Response("Post Update Successfully."));
        }
        return ResponseEntity.ok(new Response("Post Update Unsuccessful."));
    }
}
