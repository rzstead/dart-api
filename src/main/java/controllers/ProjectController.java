package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.MediaEntry;
import models.Project;
import repos.MediaEntryJpaRepository;
import repos.ProjectJpaRepository;
@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectJpaRepository projectRepo;
	@Autowired 
	private MediaEntryJpaRepository mediaRepo;

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
	
	@RequestMapping(path="/{id}/media", method = RequestMethod.GET)
	public List<MediaEntry> getMediaEntries(@PathVariable int id) {
		return mediaRepo.findAll().stream().filter(x -> x.getProject().getId() == id).collect(Collectors.toList());
	}
	
	@RequestMapping(path="/{id}/media", method = RequestMethod.POST)
	public void addMediaEntry(@PathVariable int id, @RequestBody MediaEntry entry) {
		mediaRepo.saveAndFlush(entry);
		Project project = projectRepo.findById(id).orElse(null);
		if(project != null) {
			project.getGallery().add(entry);
			projectRepo.saveAndFlush(project);
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	@RequestMapping(path="/{id}/media", method = RequestMethod.POST)
	public void removeMediaEntry(@PathVariable int id, @RequestBody MediaEntry entry) {
		Project project = projectRepo.findById(id).orElse(null);
		if(project != null) {
			project.removeMediaEntry(entry);
			projectRepo.saveAndFlush(project);
		}else {
			throw new IllegalArgumentException();
		}
	}
}
