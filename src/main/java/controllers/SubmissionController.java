package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.Submission;
import models.User;
import repos.SubmissionJpaRepository;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

	@Autowired
	private SubmissionJpaRepository submissionRepo;

	@Transactional
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Submission getSubmission(@PathVariable int id) {
		return submissionRepo.findById(id).orElse(null);
	}
	
	@Transactional
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeSubmission(@PathVariable int id) {
		submissionRepo.deleteById(id);
	}
}
