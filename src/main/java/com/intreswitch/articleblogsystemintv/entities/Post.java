package com.intreswitch.articleblogsystemintv.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "post")
@Entity
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
   @Column(name = "post_title", nullable = false, length = 255)
   private String title;
    @Column(name = "post_content", nullable = false, length = 20045)
    private String content;
    @ManyToOne
    @JsonBackReference
    private Author author;

    public Post() {

    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(postId, post.postId) && Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, author);
    }
}
