package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.Comment;
@RestController
@RequestMapping("/comment")
public class CommentController {
	
	private List<Comment> comments = new ArrayList<Comment>();

	@RequestMapping(method = RequestMethod.GET)
	public List<Comment> getComments() {
		return comments;
		//return userRepo.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Comment getComment(@PathVariable int id) {
		return findComment(id);
		//return userRepo.findById(id).orElse(null);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateComment(@RequestBody Comment comment) {
		Comment existing = findComment(comment.getId());
		//User existing = userRepo.findById(user.getId()).orElse(null);
		if(existing != null) {
			existing.setText(comment.getText());
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void removeComment(@PathVariable int id) {
		Comment existing = findComment(id);
		if(existing != null) {
			comments.remove(existing);
		}
		//userRepo.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addComment(@RequestBody Comment comment) {
		comments.add(comment);
	}
	
	private Comment findComment(int id) {
		return comments.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}
}
