package dart;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import models.Project;
import models.MediaEntry;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectController {

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void A_testAddProject() throws IOException {
		Project project = createProject();
		String body = mapper.writeValueAsString(project);
		int statusCode = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(body).post("/project")
				.getStatusCode();
		assertEquals(200, statusCode);
		List<Project> projects = getProjects();
		assertTrue(projects.size() > 0);
		assertEquals(project.getTitle(), projects.get(projects.size() - 1).getTitle());
	}

	@Test
	public void B_testGetProjects() throws JsonParseException, JsonMappingException, IOException {
		List<Project> projects = getProjects();
		assertTrue(projects.size() > 0);
	}

	@Test
	public void C_testGetProject() throws JsonParseException, JsonMappingException, IOException {
		List<Project> projects = getProjects();
		assertTrue(projects.size() > 0);
		int projectId = projects.get(projects.size() - 1).getId();
		given().pathParams("id", projectId).when().get("/project/{id}").then().body("id", is(projectId));
	}

	@Test
	public void D_testUpdateProject() throws JsonParseException, JsonMappingException, IOException {
		List<Project> projects = getProjects();
		assertTrue(projects.size() > 0);
		Project project = projects.get(projects.size() - 1);
		project.setTitle("changed");
		String body = mapper.writeValueAsString(project);
		int statusCode = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(body).post("/project")
				.getStatusCode();
		assertEquals(200, statusCode);
		given().pathParams("id", project.getId()).when().get("/project/{id}").then().body("title", is("changed"));
	}

	@Test
	public void E_testAddMediaEntry() throws JsonParseException, JsonMappingException, IOException {
		List<Project> projects = getProjects();
		Project project = projects.get(projects.size() - 1);
		MediaEntry entry = createMediaEntry(project);
		given().pathParams("id", project.getId()).accept(ContentType.JSON).contentType(ContentType.JSON)
				.body(mapper.writeValueAsString(entry)).when().post("/project/{id}/media").then().assertThat()
				.statusCode(200);
		Project gottenProject = mapper.readValue(given().pathParams("id", project.getId()).get("/project/{id}").getBody().asString(), Project.class);
		assertTrue(gottenProject.getGallery().size() > 0);
 	}

	@Test
	public void F_testGetMediaEntrys() throws JsonParseException, JsonMappingException, IOException {
		List<Project> projects = getProjects();
		Project project = projects.get(projects.size() - 1);
		Project gottenProject = mapper.readValue(given().pathParams("id", project.getId()).get("/project/{id}").getBody().asString(), Project.class);
		assertTrue(gottenProject.getGallery().size() > 0);
	}
	
	@Test
	public void G_testRemoveProject() throws JsonParseException, JsonMappingException, IOException {
		List<Project> projects = getProjects();
		assertTrue(projects.size() > 0);
		int id = projects.get(projects.size() - 1).getId();
		given().pathParam("id", id).delete("/project/{id}");
		assertEquals("", given().pathParam("id", id).get("/project/{id}").getBody().asString());
	}

	private Project createProject() {
		Project project = new Project();
		project.setDescription("This is a test project.");
		project.setTitle(UUID.randomUUID().toString());
		project.setPostDate(new Date());
		return project;
	}

	private MediaEntry createMediaEntry(Project project) {
		MediaEntry entry = new MediaEntry();
		entry.setProject(project);
		//entry.setMediaLink("https://img-lumas-avensogmbh1.netdna-ssl.com/showimg_mcu26_full.jpg");
		entry.setVideo(false);
		entry.setDescription("Hehe");
		return entry;
	}

	private List<Project> getProjects() throws JsonParseException, JsonMappingException, IOException {
		List<Project> projects = mapper.readValue(given().get("project/").body().asString(),
				new TypeReference<List<Project>>() {
				});
		return projects;
	}

}
