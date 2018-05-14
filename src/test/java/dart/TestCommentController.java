package dart;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import controllers.CommentController;
import models.Comment;
import models.Submission;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestCommentController {

	public static CommentController commentController = new CommentController();
	@Test
	void testAddAComment() {
		Comment comment = new Comment();
		comment.setId(1);
		comment.setPostDate(new Date());
		comment.setText("Ew");
		commentController.addComment(comment);
		assertEquals(1, commentController.getComments().size());
	}
	
	@Test
	void testGetTheComments() {
		assert(commentController.getComments().size() != 0);
	}

	@Test
	void testGetAComment() {
		Comment gotten = commentController.getComment(1);
		assertNotNull(gotten);
		assertEquals(1, gotten.getId());
	}
	
	@Test
	void testUpdateComment() {
		Comment edit = commentController.getComment(1);
		edit.setText("Changed");
		commentController.updateComment(edit);
		Comment edited = commentController.getComment(1);
		assertEquals("Changed", edited.getText());
	}

	@Test
	void testRemoveTheComment() throws FileNotFoundException {
		commentController.removeComment(1);
		assertEquals(0, commentController.getComments().size());
	}

}
