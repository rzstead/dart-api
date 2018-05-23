package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Project {
	@Id
	@Column(name="project_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private User user;
	private String description;
	@OneToMany(mappedBy="project")
	private List<MediaEntry> gallery = new ArrayList<>();
	@OneToMany(mappedBy="project")
	private List<Comment> comments = new ArrayList<>();
	private int rating;
	private Date postDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUserId() {
		return user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MediaEntry> getGallery() {
		return gallery;
	}

	public void setGallery(List<MediaEntry> gallery) {
		this.gallery = gallery;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}
