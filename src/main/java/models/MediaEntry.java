package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MediaEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mediaId;
	@ManyToOne
	@JsonIgnore  
	private Project project;
	private String mediaLink;
	private String description;
	private boolean isVideo;
	
	public int getId() {
		return mediaId;
	}

	public void setId(int id) {
		this.mediaId = id;
	}

	public String getMediaLink() {
		return mediaLink;
	}

	public void setMediaLink(String mediaLink) {
		this.mediaLink = mediaLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isVideo() {
		return isVideo;
	}

	public void setVideo(boolean isVideo) {
		this.isVideo = isVideo;
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}
