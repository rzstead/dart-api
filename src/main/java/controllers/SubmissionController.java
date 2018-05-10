package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.Submission;
import models.User;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

	private List<Submission> submissions = new ArrayList<Submission>();

	@RequestMapping(method = RequestMethod.GET)
	public List<Submission> getSubmissions() {
		return submissions;
		//return userRepo.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Submission getSubmission(@PathVariable int id) {
		return findSubmission(id);
		//return userRepo.findById(id).orElse(null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateSubmission(@RequestBody Submission submission) {
		Submission existing = findSubmission(submission.getId());
		//User existing = userRepo.findById(user.getId()).orElse(null);
		if(existing != null) {
			existing.setProject(submission.getProject());
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeSubmission(@PathVariable int id) {
		Submission existing = findSubmission(id);
		if(existing != null) {
			submissions.remove(existing);
		}
		//userRepo.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addSubmission(@RequestBody Submission submission) {
		submissions.add(submission);
	}
	
	private Submission findSubmission(int id) {
		return submissions.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}
	
}
