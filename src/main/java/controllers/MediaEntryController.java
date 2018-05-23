package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.MediaEntry;
import repos.MediaEntryJpaRepository;

@RestController
@RequestMapping("/media-entry")
public class MediaEntryController{
	
	@Autowired
	private MediaEntryJpaRepository mediaEntryRepo;

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public MediaEntry getMediaEntry(@PathVariable int id) {
		return mediaEntryRepo.findById(id).orElse(null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateMediaEntry(@RequestBody MediaEntry mediaEntry) {
		MediaEntry existing = mediaEntryRepo.findById(mediaEntry.getId()).orElse(null);
		if(existing != null) {
			existing.setDescription(mediaEntry.getDescription());
			existing.setMediaLink(mediaEntry.getMediaLink());
			mediaEntryRepo.saveAndFlush(existing);
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeMediaEntry(@PathVariable int id) {
		mediaEntryRepo.deleteById(id);
	}
}
