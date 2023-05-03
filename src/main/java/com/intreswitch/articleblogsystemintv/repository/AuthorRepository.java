package com.intreswitch.articleblogsystemintv.repository;


import com.intreswitch.articleblogsystemintv.entities.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer>, PagingAndSortingRepository<Author, Integer> {

    Author findByEmail(String email);
}
