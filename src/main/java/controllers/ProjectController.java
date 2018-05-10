package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.Project;
@RestController
@RequestMapping("/project")
public class ProjectController {
	private List<Project> projects = new ArrayList<Project>();

	@RequestMapping(method = RequestMethod.GET)
	public List<Project> getProjects() {
		return projects;
		//return userRepo.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Project getProject(@PathVariable int id) {
		return findProject(id);
		//return userRepo.findById(id).orElse(null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateProject(@RequestBody Project project) {
		Project existing = findProject(project.getId());
		//User existing = userRepo.findById(user.getId()).orElse(null);
		if(existing != null) {
			existing.setDescription(project.getDescription());
			existing.setGallery(project.getGallery());
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeProject(@PathVariable int id) {
		Project existing = findProject(id);
		if(existing != null) {
			projects.remove(existing);
		}
		//userRepo.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addProject(@RequestBody Project project) {
		projects.add(project);
	}
	
	private Project findProject(int id) {
		return projects.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}
}
