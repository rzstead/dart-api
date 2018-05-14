package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.Project;
import repos.ProjectJpaRepository;
@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectJpaRepository projectRepo;

	@RequestMapping(method = RequestMethod.GET)
	public List<Project> getProjects() {
		return projectRepo.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Project getProject(@PathVariable int id) {
		return projectRepo.findById(id).orElse(null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateProject(@RequestBody Project project) {
		Project existing = projectRepo.findById(project.getId()).orElse(null);
		if(existing != null) {
			existing.setDescription(project.getDescription());
			existing.setGallery(project.getGallery());
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeProject(@PathVariable int id) {
		projectRepo.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addProject(@RequestBody Project project) {
		projectRepo.saveAndFlush(project);
	}
}
