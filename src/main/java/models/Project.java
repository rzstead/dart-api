package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projectId;
	@ManyToOne
	private User user;
	private String title;
	private String description;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<MediaEntry> mediaEntries = new ArrayList<>();
	private Date postDate;

	public int getId() {
		return projectId;
	}

	public void setId(int id) {
		this.projectId = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MediaEntry> getGallery() {
		return mediaEntries;
	}

	public void setGallery(List<MediaEntry> mediaEntries) {
		this.mediaEntries = mediaEntries;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void addMedia(MediaEntry entry) {
		mediaEntries.add(entry);
		entry.setProject(this);
	}
	
	public void removeMediaEntry(MediaEntry entry) {
		mediaEntries.remove(entry);
		entry.setProject(null);
	}
}
