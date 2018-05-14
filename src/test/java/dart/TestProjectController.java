package dart;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import controllers.ProjectController;
import models.Project;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestProjectController {

	public static ProjectController projectController = new ProjectController();
	@Test
	void testAddProject() {
		Project project = new Project();
		project.setId(1);
		project.setPostDate(new Date());
		project.setDescription("Ew");
		projectController.addProject(project);
		assertEquals(1, projectController.getProjects().size());
	}
	
	@Test
	void testGetProjects() {
		assert(projectController.getProjects().size() != 0);
	}

	@Test
	void testGetProject() {
		Project gotten = projectController.getProject(1);
		assertNotNull(gotten);
		assertEquals(1, gotten.getId());
	}
	
	@Test
	void testUpdateProject() {
		Project edit = projectController.getProject(1);
		edit.setDescription("Changed");
		projectController.updateProject(edit);
		Project edited = projectController.getProject(1);
		assertEquals("Changed", edited.getDescription());
	}

	@Test
	void testRemoveTheProject() throws FileNotFoundException {
		projectController.removeProject(1);
		assertEquals(0, projectController.getProjects().size());
	}

}
