package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import models.MediaEntry;
import models.S3Interaction;
import repos.MediaEntryJpaRepository;

@RestController
@RequestMapping("/media-entry")
public class MediaEntryController {

	@Autowired
	private MediaEntryJpaRepository mediaEntryRepo;

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public MediaEntry getMediaEntry(@PathVariable int id) {
		return mediaEntryRepo.findById(id).orElse(null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateMediaEntry(@RequestBody MediaEntry mediaEntry) {
		MediaEntry existing = mediaEntryRepo.findById(mediaEntry.getId()).orElse(null);
		if (existing != null) {
			existing.setDescription(mediaEntry.getDescription());
			existing.setMediaLink(mediaEntry.getMediaLink());
			mediaEntryRepo.saveAndFlush(existing);
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeMediaEntry(@PathVariable int id) {
		mediaEntryRepo.deleteById(id);
	}

	@RequestMapping(value = "/{id}/uploadMedia/{isVideo}", method = RequestMethod.POST)
	public void handleMediaUpload(@PathVariable int id, @PathVariable Boolean isVideo, @RequestParam("file") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			File fileStream = null;
			file.transferTo(fileStream);
			String fileLocation = S3Interaction.putMedia(fileStream);
			MediaEntry entry = mediaEntryRepo.findById(id).orElse(null);
			entry.setMediaLink(fileLocation);
			entry.setVideo(isVideo);
			mediaEntryRepo.saveAndFlush(entry);
		}else {
			throw new IllegalArgumentException();
		}
	}
}
