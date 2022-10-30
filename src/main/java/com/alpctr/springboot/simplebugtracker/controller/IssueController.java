package com.alpctr.springboot.simplebugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpctr.springboot.simplebugtracker.exception.ResourceNotFoundException;
import com.alpctr.springboot.simplebugtracker.model.Comment;
import com.alpctr.springboot.simplebugtracker.model.Issue;
import com.alpctr.springboot.simplebugtracker.repository.CommentRepository;
import com.alpctr.springboot.simplebugtracker.repository.IssueRepository;

@RestController
@RequestMapping("/api/v1")
public class IssueController {
	
	@Autowired
	private IssueRepository issueRepository;
	
	
	@PostMapping("/issues")
	public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
		issueRepository.save(issue);
		return ResponseEntity.ok().body(issue);
		
	}
	
	@PutMapping("/issues/comment/{id}")
	public void addComment(@PathVariable(name = "id") Long id, @RequestBody Comment comment) throws ResourceNotFoundException {
		Issue issue = issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue not found for this id: " + id));
		issue.getComments().add(comment);
		issueRepository.save(issue);
	}
	
	@GetMapping("/issues")
	public List<Issue> getIssues() {
		return issueRepository.findAll();
	}
	
	@GetMapping("/issues/{id}")
	public ResponseEntity<Issue> getIssueById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Issue issue = issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue not found for this id: " + id));
		return ResponseEntity.ok().body(issue);
	}
	
	@PutMapping("/issues/{id}")
	public ResponseEntity<Issue> updateIssueById(@PathVariable(value = "id") Long id, @RequestBody Issue issue) throws ResourceNotFoundException {
		Issue issueDetails = issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue not found for this id: " + id));
		issueRepository.deleteById(id);
		issueDetails.setSubject(issue.getSubject());
		issueDetails.setDescription(issue.getDescription());
		issueDetails.setPriority(issue.getPriority());
		issueDetails.setAsignee(issue.getAsignee());
		issueDetails.setMilestone(issue.getMilestone());
		issueDetails.setCategory(issue.getCategory());
		issueDetails.setComments(issue.getComments());
		
		issueRepository.save(issueDetails);
		
		return ResponseEntity.ok().body(issueDetails);
	}
	
	@DeleteMapping("/issues/{id}")
	public ResponseEntity<Issue> deleteIssue(@PathVariable (value = "id") Long id) {
		issueRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
