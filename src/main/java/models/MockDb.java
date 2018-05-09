package models;

import java.util.ArrayList;

public class MockDb implements IDatabaseAccess {
	
	private ArrayList<User> users = new ArrayList<>();

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getProject(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Project> getProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addProject(Project project, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteProject(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectItem getProjectItem(int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ProjectItem> getProjectItems(int projId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addProjectItem(ProjectItem item, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteProjectItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment getComment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Comment> getComments(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addComment(Comment comment, int projId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeComment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Challenge getChallenge(int challengeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Submission> getChallenges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addChallenge(Challenge challenge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeChallenge(int challengeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Submission getSubmission() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Submission> getSubmission(int submissionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addSubmission(Submission submission, int challengeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeSubmission(int submissionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean authenticateUser(User auth) {
		User user = users.stream().filter(x -> x.username.equals(auth.username) && x.hashedPass.equals(auth.hashedPass)).findFirst().orElse(null);
		return user != null;
	}

	@Override
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
