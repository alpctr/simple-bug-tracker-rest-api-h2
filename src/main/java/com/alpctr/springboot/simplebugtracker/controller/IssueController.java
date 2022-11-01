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
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	@PostMapping("/issues")
	public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
		issueRepository.save(issue);
		return ResponseEntity.ok().body(issue);
		
	}
	
	@PostMapping("/issues/comments/{id}")
	public void addComment(@PathVariable(name = "id") Long id, @RequestBody Comment comment) throws ResourceNotFoundException {
		Issue issue = issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue not found for this id: " + id));
		List<Comment> commentList = issue.getCommentList();
		commentList.add(comment);
		issue.setCommentList(commentList);
		issueRepository.save(issue);
	}
	
	@DeleteMapping("/issues/comments/{id}")
	public void deleteComment(@PathVariable(value = "id") Long id) {
		commentRepository.deleteById(id);
	}
	
	@GetMapping("/issues/comments")
	public List<Comment> getComments() {
		List<Comment> comments = commentRepository.findAll();
		return comments;
	}
	
	@GetMapping("/issues/comments/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found for this id: " + id));
		return ResponseEntity.ok().body(comment);
	}
	
	@PutMapping("/issues/comments/{id}")
	public ResponseEntity<Comment> updateCommentById(@PathVariable(value = "id") Long id, @RequestBody Comment comment) throws ResourceNotFoundException {
		Comment commentDetails = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found for this id: " + id));
		commentDetails.setText(comment.getText());
		commentRepository.save(commentDetails);
		return ResponseEntity.ok().body(commentDetails);
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
		issueRepository.updateDescripton(issue.getDescription(), id);
		Issue issueDetails = issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue not found for this id: " + id));
		
		return ResponseEntity.ok().body(issueDetails);
	}
	
	@DeleteMapping("/issues/{id}")
	public ResponseEntity<Issue> deleteIssue(@PathVariable (value = "id") Long id) {
		issueRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
