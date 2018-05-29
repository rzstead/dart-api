package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Challenge {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int challengeId;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	@OneToMany(orphanRemoval=true, cascade= CascadeType.REMOVE)
	private List<Submission> submissions = new ArrayList<Submission>();
	
	public int getId() {
		return challengeId;
	}
	public void setId(int id) {
		this.challengeId = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<Submission> getSubmissions() {
		return submissions;
	}
	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}
	public void addSubmission(Submission submission) {
		submissions.add(submission);
		submission.setChallenge(this);
	}
	public void removeSubmission(Submission submission) {
		submissions.remove(submission);
		submission.setChallenge(null);
	}
}
