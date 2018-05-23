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

import models.Comment;
import models.MediaEntry;
import models.Project;
import repos.CommentJpaRepository;
import repos.MediaEntryJpaRepository;
import repos.ProjectJpaRepository;
@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectJpaRepository projectRepo;
	@Autowired 
	private MediaEntryJpaRepository mediaRepo;
	@Autowired
	private CommentJpaRepository commentRepo;

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
	public List<MediaEntry> getMediaEntrys(@PathVariable int id) {
		return mediaRepo.findAll().stream().filter(x -> x.getProject().getId() == id).collect(Collectors.toList());
	}
	
	@RequestMapping(path="/{id}/media", method = RequestMethod.PUT)
	public void addMediaEntry(@PathVariable int id, @RequestBody MediaEntry entry) {
		mediaRepo.saveAndFlush(entry);
		Project project = projectRepo.findById(id).orElse(null);
		if(project != null) {
			project.getGallery().add(entry);
		}
	}
	
	@RequestMapping(path="/{id}/comment", method = RequestMethod.POST)
	public void addComment(@PathVariable int id, @RequestBody Comment comment) {
		commentRepo.saveAndFlush(comment);
		Project project = projectRepo.findById(id).orElse(null);
		if(project != null) {
			project.getComments().add(comment);	
		}
	}
	
	@RequestMapping(path="/{id}/comment", method = RequestMethod.GET)
	public List<Comment> getComments(@PathVariable int id) {
		return commentRepo.findAll().stream().filter(x -> x.getProject().getId() == id).collect(Collectors.toList());
	}
}
