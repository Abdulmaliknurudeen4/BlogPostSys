package com.intreswitch.articleblogsystemintv.repository;

import com.intreswitch.articleblogsystemintv.entities.Author;
import com.intreswitch.articleblogsystemintv.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Initializer implements CommandLineRunner {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        Author author = new Author();
        author.setName("AuthorONe");
        author.setEmail("authorone@gmail.com");
        author.setPassword("password");

        Author author1 = new Author();
        author.setName("AuthorTwo");
        author.setEmail("authortwo@gmail.com");
        author.setPassword("password");

        Author author2 = new Author();
        author.setName("AuthorThree");
        author.setEmail("authorthree@gmail.com");
        author.setPassword("password");

        authorRepository.saveAll(List.of(author1, author2, author));

        Post firstPost = new Post();
        firstPost.setTitle("FirstPost");
        firstPost.setContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        firstPost.setAuthor(author);
        postRepository.save(firstPost);

        Post secondPost = new Post();
        secondPost.setTitle("FirstPost");
        secondPost.setContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        secondPost.setAuthor(author2);
        postRepository.save(secondPost);

        Post thridPost = new Post();
        thridPost.setTitle("FirstPost");
        thridPost.setContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        thridPost.setAuthor(author2);
        postRepository.save(thridPost);


    }
}
