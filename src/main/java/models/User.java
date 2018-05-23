package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

import dart.EntityJsonViews;

@Entity
public class User {
	
	@Id
	private String username;
	private String email;
	private String password;
	private String description;
	private String avatarLink;
	private String backgroundLink;

	public String getPassword() {
		return password;
	}

	public void setPassword(String hashedPass) {
		this.password = hashedPass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvatarLink() {
		return avatarLink;
	}

	public void setAvatarLink(String avatarLink) {
		this.avatarLink = avatarLink;
	}

	public String getBackgroundLink() {
		return backgroundLink;
	}

	public void setBackgroundLink(String backgroundLink) {
		this.backgroundLink = backgroundLink;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
