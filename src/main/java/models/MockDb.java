package models;

import java.util.ArrayList;

public class MockDb implements IDatabaseAccess {
	
	private ArrayList<User> users = new ArrayList<User>();

	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Project getProject(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<Project> getProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean addProject(Project project, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean deleteProject(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ProjectItem getProjectItem(int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<ProjectItem> getProjectItems(int projId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean addProjectItem(ProjectItem item, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean deleteProjectItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Comment getComment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<Comment> getComments(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean addComment(Comment comment, int projId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean removeComment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Challenge getChallenge(int challengeId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<Submission> getChallenges() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean addChallenge(Challenge challenge) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean removeChallenge(int challengeId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Submission getSubmission() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<Submission> getSubmission(int submissionId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean addSubmission(Submission submission, int challengeId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean removeSubmission(int submissionId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean authenticateUser(User auth) {
		User user = users.stream().filter(x -> x.username.equals(auth.username) && x.hashedPass.equals(auth.hashedPass)).findFirst().orElse(null);
		return user != null;
	}

	
	public Boolean updateUser(User user) {
		User foundUser = findUser(user.id);
		if(foundUser != null) {
			foundUser = user;
		}
		return foundUser != null;
	}
	
	private User findUser(int id) {
		return users.stream().filter(x -> x.id == id).findFirst().orElse(null);
	}

}
