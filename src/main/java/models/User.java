package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	@ElementCollection
	private List<String> roles = new ArrayList<>();
	
	public void addRole(String role) {
		roles.add(role);
	}
	
	public void removeRole(String role) {
		roles.remove(role);
	}

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
