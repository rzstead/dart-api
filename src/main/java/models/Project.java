package models;

import java.util.ArrayList;
import java.util.Date;

public class Project {
	public int id;
	public User artist;
	public String description;
	public ArrayList<ProjectItem> gallery;
	public ArrayList<Comment> comments;
	public int rating;
	public Date postDate;
}
