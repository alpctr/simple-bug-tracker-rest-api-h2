package com.alpctr.springboot.simplebugtracker.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "issues")
public class Issue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "subject")
	private String subject;
	@Column(name = "description")
	private String description;
	@Column(name = "priority")
	private String priority;
	@Column(name = "asignee")
	private String asignee;
	@Column(name = "milestone")
	private String milestone;
	@Column(name = "category")
	private String category;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "c_fid", referencedColumnName = "id")
	private List<Comment> comments = new ArrayList<Comment>();
	
	public Issue(long id, String subject, String description, String priority, String asignee, String milestone, String category,
			List<Comment> comments) {
		super();
		this.id = id;
		this.subject = subject;
		this.description = description;
		this.priority = priority;
		this.asignee = asignee;
		this.milestone = milestone;
		this.category = category;
		this.comments = comments;
	}

	public Issue() {
		super();
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAsignee() {
		return asignee;
	}

	public void setAsignee(String asignee) {
		this.asignee = asignee;
	}

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	
}
