package com.intreswitch.articleblogsystemintv.repository;


import com.intreswitch.articleblogsystemintv.entities.Author;
import com.intreswitch.articleblogsystemintv.entities.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer>, PagingAndSortingRepository<Post, Integer> {
    List<Post> findByAuthor(Author author);

    @Modifying
    void deletePostByAuthorAndAndPostId(Author author, Integer postId);
}
