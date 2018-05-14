package dart;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import controllers.MediaEntryController;
import models.MediaEntry;

class TestMediaEntryController {

	public static MediaEntryController mediaEntryController = new MediaEntryController();
	
	@Test
	void testGetTheMediaEntrys() {
		assert(mediaEntryController.getMediaEntrys().size() != 0);
	}

	@Test
	void testGetAMediaEntry() {
		MediaEntry gotten = mediaEntryController.getMediaEntry(1);
		assertNotNull(gotten);
		assertEquals(1, gotten.getId());
	}
	
	@Test
	void testUpdateMediaEntry() {
		MediaEntry edit = mediaEntryController.getMediaEntry(1);
		edit.setDescription("Changed");
		mediaEntryController.updateMediaEntry(edit);
		MediaEntry edited = mediaEntryController.getMediaEntry(1);
		assertEquals("Changed", edited.getDescription());
	}

	@Test
	void testRemoveTheMediaEntry() throws FileNotFoundException {
		mediaEntryController.removeMediaEntry(1);
		assertEquals(0, mediaEntryController.getMediaEntrys().size());
	}

}
