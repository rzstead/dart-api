package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import models.Log;
import models.MediaEntry;
import models.Project;
import models.S3Interaction;
import models.User;
import repos.LogJpaRepository;
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
	private LogJpaRepository logRepo;

	@Transactional
	@RequestMapping(method = RequestMethod.GET)
	public List<Project> getProjects() {
		return projectRepo.findAll();
	}

	@Transactional
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Project getProject(@PathVariable int id) {
		return projectRepo.findById(id).orElse(null);
	}
	
	@Transactional
	@RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
	public List<Project> getProjectsByUser(@PathVariable String username) {
		List<Project> notNull = projectRepo.findAll().stream().filter(x -> x.getUser() != null).collect(Collectors.toList());
		
		return notNull.stream().filter(x -> x.getUser().getUsername().equals(username)).collect(Collectors.toList());
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.PUT)
	public void updateProject(@RequestBody Project project) {
		Project existing = projectRepo.findById(project.getId()).orElse(null);
		if(existing != null) {
			existing.setDescription(project.getDescription());
			existing.setGallery(project.getGallery());
			Log log = new Log();
			log.logInteraction("PUT", "Project", "Updated project with new details");
			logRepo.saveAndFlush(log);
		}
	}

	@Transactional
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeProject(@PathVariable int id) {
		projectRepo.deleteById(id);
		Log log = new Log();
		log.logInteraction("DELETE", "Project", "Deleted project with id" + id);
		logRepo.saveAndFlush(log);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public Project addProject(@RequestBody Project project) {
		projectRepo.saveAndFlush(project);
		List<Project> projects = projectRepo.findAll();
		Log log = new Log();
		log.logInteraction("POST", "Project", "Added new project.");
		logRepo.saveAndFlush(log);
		return projects.get(projects.size() - 1);
	}
	
	@Transactional
	@RequestMapping(path="/{id}/media", method = RequestMethod.GET)
	public List<MediaEntry> getMediaEntries(@PathVariable int id) {
		return mediaRepo.findAll().stream().filter(x -> x.getProject().getId() == id).collect(Collectors.toList());
	}
	
	@Transactional
	@RequestMapping(path="/{id}/media", method = RequestMethod.POST)
	public MediaEntry addMediaEntry(@PathVariable int id, @RequestBody MediaEntry entry) {
		MediaEntry result = mediaRepo.saveAndFlush(entry);
		Project project = projectRepo.findById(id).orElse(null);
		if(project != null) {
			project.addMedia(entry);
			project = projectRepo.saveAndFlush(project);
			Log log = new Log();
			log.logInteraction("POST", "MediaEntry", "Added a new MediaEntry to Project " + id);
			logRepo.saveAndFlush(log);
			return result;
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	@Transactional	
	@RequestMapping(path="/{id}/media/{mediaId}", method = RequestMethod.DELETE)	
	public void removeMediaEntry(@PathVariable int id, @PathVariable int mediaId) {
		Project project = projectRepo.findById(id).orElse(null);
		MediaEntry entry = mediaRepo.findById(mediaId).orElse(null);
		if(project != null && entry != null) {
			S3Interaction.deleteMedia(entry.getMediaLink());
			project.removeMediaEntry(entry);
			projectRepo.saveAndFlush(project);
			Log log = new Log();
			log.logInteraction("DELETE", "MediaEntry", "Deleted MediaEntry with id " + mediaId + " from Project with id " + id);
			logRepo.saveAndFlush(log);
		}else {
			throw new IllegalArgumentException();
		}
	}
}
