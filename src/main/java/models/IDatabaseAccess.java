package models;

import java.util.ArrayList;

public interface IDatabaseAccess {
	
	public User getUser(int id);
	public ArrayList<User> getUsers();
	public Boolean registerUser(User user);
	public Boolean deleteUser(int id);
	public Boolean updateUser(User user);
	
	public Project getProject(int id);
	public ArrayList<Project> getProjects();
	public Boolean addProject(Project project, int userId);
	public Boolean deleteProject(int id);
	
	public ProjectItem getProjectItem(int itemId);
	public ArrayList<ProjectItem> getProjectItems(int projId);
	public Boolean addProjectItem(ProjectItem item, int userId);
	public Boolean deleteProjectItem(int id);
	
	public Comment getComment(int id);
	public ArrayList<Comment> getComments(int id);
	public Boolean addComment(Comment comment, int projId);
	public Boolean removeComment(int id);
	
	public Challenge getChallenge(int challengeId);
	public ArrayList<Submission> getChallenges();
	public Boolean addChallenge(Challenge challenge);
	public Boolean removeChallenge(int challengeId);
	
	public Submission getSubmission();
	public ArrayList<Submission> getSubmission(int submissionId);
	public Boolean addSubmission(Submission submission, int challengeId);
	public Boolean removeSubmission(int submissionId);

	public Boolean authenticateUser(User user);
	
}
