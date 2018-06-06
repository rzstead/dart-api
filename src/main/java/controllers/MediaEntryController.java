package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import models.Log;
import models.MediaEntry;
import models.S3Interaction;
import repos.LogJpaRepository;
import repos.MediaEntryJpaRepository;

@RestController
@RequestMapping("/media-entry")
public class MediaEntryController {

	@Autowired
	private MediaEntryJpaRepository mediaEntryRepo;
	@Autowired
	private LogJpaRepository logRepo;

	@Transactional
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public MediaEntry getMediaEntry(@PathVariable int id) {
		return mediaEntryRepo.findById(id).orElse(null);
	}

	@Transactional
	@RequestMapping(method = RequestMethod.PUT)
	public void updateMediaEntry(@RequestBody MediaEntry mediaEntry) {
		MediaEntry existing = mediaEntryRepo.findById(mediaEntry.getId()).orElse(null);
		if (existing != null) {
			existing.setDescription(mediaEntry.getDescription());
			existing.setMediaLink(mediaEntry.getMediaLink());
			mediaEntryRepo.saveAndFlush(existing);
			Log log = new Log();
			log.logInteraction("PUT", "MediaEntry", "Updated MediaEntry with new details");
			logRepo.saveAndFlush(log);
		}
	}
	
	@Transactional
	@RequestMapping(value = "/{id}/uploadMedia/{isVideo}", method = RequestMethod.POST)
	public void handleMediaUpload(@PathVariable int id, @PathVariable Boolean isVideo, @RequestPart("file") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			File convFile = new File(file.getOriginalFilename());
		    FileOutputStream fos = new FileOutputStream(convFile);
		    fos.write(file.getBytes());
		    fos.close();
			String fileLocation = S3Interaction.putMedia(convFile, file.getContentType());
			convFile.delete();
			MediaEntry entry = mediaEntryRepo.findById(id).orElse(null);
			entry.setMediaLink(fileLocation);
			entry.setVideo(isVideo);
			mediaEntryRepo.saveAndFlush(entry);
			Log log = new Log();
			log.logInteraction("POST", "MediaEntry", "Added image to S3");
			logRepo.saveAndFlush(log);
		}else {
			throw new IllegalArgumentException();
		}
	}
}
