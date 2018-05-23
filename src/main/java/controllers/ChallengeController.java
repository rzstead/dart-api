package controllers;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.Challenge;
import models.Submission;
import repos.ChallengeJpaRepository;
import repos.SubmissionJpaRepository;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
	
	@Autowired
	private ChallengeJpaRepository challengeRepo;
	@Autowired
	private SubmissionJpaRepository submissionRepo;

	@RequestMapping(method = RequestMethod.GET)
	public List<Challenge> getChallenges() {
		return challengeRepo.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Challenge getChallenge(@PathVariable int id) {
		return challengeRepo.findById(id).orElse(null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateChallenge(@RequestBody Challenge challenge) {
		Challenge existing = challengeRepo.findById(challenge.getId()).orElse(null);
		if(existing != null) {
			existing.setDescription(challenge.getDescription());
			existing.setStartDate(challenge.getStartDate());
			existing.setEndDate(challenge.getEndDate());
			existing.setTitle(challenge.getTitle());
			challengeRepo.saveAndFlush(existing);
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeChallenge(@PathVariable int id) throws FileNotFoundException {
		challengeRepo.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addChallenge(@RequestBody Challenge challenge) {
		challengeRepo.saveAndFlush(challenge);
	}
	
	@RequestMapping(path="/{id}/submission", method = RequestMethod.POST)
	public void addSubmission(@PathVariable int id, @RequestBody Submission submission) {
		submissionRepo.saveAndFlush(submission);
		Challenge challenge = challengeRepo.findById(id).orElse(null);
		if(challenge != null) {
			challenge.getSubmissions().add(submission);	
		}
	}
	
	@RequestMapping(path="/{id}/submission", method = RequestMethod.GET)
	public List<Submission> getSubmissions(@PathVariable int id) {
		return submissionRepo.findAll().stream().filter(x -> x.getChallenge().getId() == id).collect(Collectors.toList());
	}
}
