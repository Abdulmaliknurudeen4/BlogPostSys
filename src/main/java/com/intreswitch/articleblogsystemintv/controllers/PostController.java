package com.intreswitch.articleblogsystemintv.controllers;

import com.intreswitch.articleblogsystemintv.dto.PostDTO;
import com.intreswitch.articleblogsystemintv.entities.Post;
import com.intreswitch.articleblogsystemintv.security.jwt.JwtUserPrincipal;
import com.intreswitch.articleblogsystemintv.security.model.Response;
import com.intreswitch.articleblogsystemintv.services.AuthorService;
import com.intreswitch.articleblogsystemintv.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin("*")
@RestController()
@RequestMapping(value = "/PostController")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;

    @PostMapping("/createPost")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<Response> createUser(@RequestBody PostDTO postDTO,
                                               @AuthenticationPrincipal JwtUserPrincipal principal) {

        Principal userDetails = SecurityContextHolder.getContext().getAuthentication();


        boolean result = postService.savePost(postDTO, principal.getAuthor());
        if (result) {
            return ResponseEntity.ok(new Response("Post Created Successfully."));
        }
        return ResponseEntity.ok(new Response("Post Creation Unsuccessful."));
    }

    @PostMapping("/updatePost")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<Response> updatePost(@RequestBody PostDTO postDTO,
                                               @AuthenticationPrincipal JwtUserPrincipal principal) {
        boolean result = postService.savePost(postDTO, principal.getAuthor());
        if (result) {
            return ResponseEntity.ok(new Response("Post Update Successfully."));
        }
        return ResponseEntity.ok(new Response("Post Update Unsuccessful."));
    }

    @GetMapping("/allPost")
    public ResponseEntity<List<Post>> listPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @DeleteMapping("/deletePost/{postId}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<Response> deletePost(@PathVariable("postId") Integer id,
                                               @AuthenticationPrincipal JwtUserPrincipal principal) {
        boolean result = postService.deletePost(id, principal.getAuthor());
        if (result) {
            return ResponseEntity.ok(new Response("Post Deletion Successful."));
        }
        return ResponseEntity.ok(new Response("Post Deletion Unsuccessful."));
    }

    @GetMapping("/getAllPostForMe")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<List<Post>> myPosts(@AuthenticationPrincipal JwtUserPrincipal principal) {
        List<Post> allPost = postService.getAllPostForAuthor(principal.getAuthor());
        if (allPost != null) {
            return ResponseEntity.ok(allPost);
        }
        return null;
    }
}
