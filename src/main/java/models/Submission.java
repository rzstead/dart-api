package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Submission {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int submissionId;
	@ManyToOne
	@JsonIgnore
	private Challenge challenge;
	@OneToOne
	@JoinColumn
	private Project project;
	
	public int getId() {
		return submissionId;
	}
	public void setId(int id) {
		this.submissionId = id;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Challenge getChallenge() {
		return challenge;
	}
	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}
}
