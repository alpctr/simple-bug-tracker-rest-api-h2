package com.alpctr.springboot.simplebugtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alpctr.springboot.simplebugtracker.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findByIssue(Long issue);
	
	
	@Modifying
	@Query("delete from Comment b where b.issue=:issue")
	void removeByIssue(@Param("issue") Long issue);
}
