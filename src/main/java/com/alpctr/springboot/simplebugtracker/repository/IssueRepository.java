package com.alpctr.springboot.simplebugtracker.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alpctr.springboot.simplebugtracker.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
	
	@Modifying
	@Transactional
	@Query("UPDATE Issue x SET x.description= :description WHERE x.id = :id")
	void updateDescripton(@Param("description") String description, Long id);
	
}


