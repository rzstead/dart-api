package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.MediaEntry;

@RestController
@RequestMapping("/media-entry")
public class MediaEntryController{
	private List<MediaEntry> mediaEntries = new ArrayList<MediaEntry>();

	@RequestMapping(method = RequestMethod.GET)
	public List<MediaEntry> getMediaEntrys() {
		return mediaEntries;
		//return userRepo.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public MediaEntry getMediaEntry(@PathVariable int id) {
		return findMediaEntry(id);
		//return userRepo.findById(id).orElse(null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateMediaEntry(@RequestBody MediaEntry mediaEntry) {
		MediaEntry existing = findMediaEntry(mediaEntry.getId());
		//MediaEntry existing = userRepo.findById(user.getId()).orElse(null);
		if(existing != null) {
			existing.setDescription(mediaEntry.getDescription());
			existing.setMediaLink(mediaEntry.getMediaLink());
			existing.setRating(mediaEntry.getRating());
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeMediaEntry(@PathVariable int id) {
		MediaEntry existing = findMediaEntry(id);
		if(existing != null) {
			mediaEntries.remove(existing);
		}
		//userRepo.deleteById(id);
	}
	
	private MediaEntry findMediaEntry(int id) {
		return mediaEntries.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}
}
