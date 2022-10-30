package com.alpctr.springboot.simplebugtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpctr.springboot.simplebugtracker.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
}
