package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.Comment;
import repos.CommentJpaRepository;
@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentJpaRepository commentRepo;

	@RequestMapping(method = RequestMethod.GET)
	public List<Comment> getComments() {
		return commentRepo.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Comment getComment(@PathVariable int id) {
		return commentRepo.findById(id).orElse(null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateComment(@RequestBody Comment comment) {
		Comment existing = commentRepo.findById(comment.getId()).orElse(null);
		if(existing != null) {
			existing.setText(comment.getText());
			commentRepo.saveAndFlush(existing);
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeComment(@PathVariable int id) {
		commentRepo.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addComment(@RequestBody Comment comment) {
		commentRepo.saveAndFlush(comment);
	}
}
