package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import models.Challenge;

public class ChallengeController {
	private List<Challenge> challenges = new ArrayList<Challenge>();

	@RequestMapping(method = RequestMethod.GET)
	public List<Challenge> getChallenges() {
		return challenges;
		//return userRepo.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Challenge getChallenge(@PathVariable int id) {
		return findChallenge(id);
		//return userRepo.findById(id).orElse(null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateChallenge(@RequestBody Challenge challenge) {
		Challenge existing = findChallenge(challenge.getId());
		//User existing = userRepo.findById(user.getId()).orElse(null);
		if(existing != null) {
			existing.setDescription(challenge.getDescription());
			existing.setStartDate(challenge.getStartDate());
			existing.setEndDate(challenge.getEndDate());
			existing.setTitle(challenge.getTitle());
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeChallenge(@PathVariable int id) {
		Challenge existing = findChallenge(id);
		if(existing != null) {
			challenges.remove(existing);
		}
		//userRepo.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addChallenge(@RequestBody Challenge challenge) {
		challenges.add(challenge);
	}
	
	private Challenge findChallenge(int id) {
		return challenges.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}
}
