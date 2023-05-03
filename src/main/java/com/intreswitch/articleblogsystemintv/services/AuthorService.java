package com.intreswitch.articleblogsystemintv.services;

import com.intreswitch.articleblogsystemintv.dto.UserRegistrationDTO;
import com.intreswitch.articleblogsystemintv.entities.Author;
import com.intreswitch.articleblogsystemintv.repository.AuthorRepository;
import com.intreswitch.articleblogsystemintv.security.model.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public boolean save(UserRegistrationDTO user) {
        if (user != null) {
            Author newAuthor = new Author();
            newAuthor.setName(user.getName());
            newAuthor.setEmail(user.getEmail());
            newAuthor.setPassword(PasswordUtil.getPasswordHash(user.getPassword()));
            authorRepository.save(newAuthor);
            return true;
        }
        return false;
    }

    public Author getAuthorByEmail(String email) {
        return authorRepository.findByEmail(email);
    }
}
