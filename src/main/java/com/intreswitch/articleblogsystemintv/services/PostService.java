package com.intreswitch.articleblogsystemintv.services;

import com.intreswitch.articleblogsystemintv.dto.PostDTO;
import com.intreswitch.articleblogsystemintv.entities.Author;
import com.intreswitch.articleblogsystemintv.entities.Post;
import com.intreswitch.articleblogsystemintv.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PostService {
    @Autowired
    private PostRepository repository;

    public boolean savePost(PostDTO postDTO, Author author) {
        Post post = new Post();
        if (postDTO != null && author != null) {
            if (postDTO.getId() != null && postDTO.getId() != 0) {
                post.setPostId(postDTO.getId());
            }
            post.setAuthor(author);
            post.setTitle(postDTO.getTitle());
            post.setContent(postDTO.getContent());
            repository.save(post);
            return true;
        }
        return false;
    }


    public boolean deletePost(Integer postId, Author author) {
        if (repository.existsById(postId)) {
            repository.deletePostByAuthorAndAndPostId(author, postId);
            return true;
        } else {
            return false;
        }
    }

    public List<Post> getAllPost() {
        return (List<Post>) repository.findAll();
    }

    public List<Post> getAllPostForAuthor(Author author) {
        return repository.findByAuthor(author);
    }

}
