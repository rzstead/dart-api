package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	public String hashedPass;
	public String username;
	public String description;
	public String avatarLink;
	public String backgroundLink;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHashedPass() {
		return hashedPass;
	}
	public void setHashedPass(String hashedPass) {
		this.hashedPass = hashedPass;
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
}
