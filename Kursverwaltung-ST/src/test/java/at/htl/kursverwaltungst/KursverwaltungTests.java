package at.htl.kursverwaltungst;

import org.glassfish.jersey.client.ClientResponse;
import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class KursverwaltungTests {

    private Client client;
    private WebTarget tut;

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.tut = client.target("http://localhost:8080/kursverwaltung/api/");
    }

    @Test
    public void t01_setuptestdata(){
        JsonObjectBuilder t = Json.createObjectBuilder();
        t.add("firstName", "test");
        t.add("lastName", "test");
        t.add("teacherNumber", "TEST321");
        JsonObject obj = t.build();

        postGetDeleteGet(obj, "teachers", new String[]{"/firstName", "/lastName", "/teacherNumber"});
    }

    @Test
    public void t01_addAndRemoveTeacher(){
        JsonObjectBuilder t = Json.createObjectBuilder();
        t.add("firstName", "test");
        t.add("lastName", "test");
        t.add("teacherNumber", "TEST321");
        JsonObject obj = t.build();

        postGetDeleteGet(obj, "teachers", new String[]{"/firstName", "/lastName", "/teacherNumber"});
    }

    @Test
    public void t02_addAndRemoveStudent(){
        JsonObjectBuilder t = Json.createObjectBuilder();
        t.add("firstName", "test");
        t.add("lastName", "test");
        t.add("matNumber", "TEST321");
        JsonObject obj = t.build();

        postGetDeleteGet(obj, "students", new String[]{"/firstName", "/lastName", "/matNumber"});
    }

    @Test
    public void t03_addAndRemoveCourse(){
        JsonObjectBuilder t = Json.createObjectBuilder();
        t.add("name", "test123");
        t.add("subject", Json.createObjectBuilder().add("id", 1));
        t.add("teacher", Json.createObjectBuilder().add("id", 4));
        JsonObject obj = t.build();

        postGetDeleteGet(obj, "courses", new String[]{"/name", "/subject/id", "/teacher/id"});
    }

    @Test
    public void t04_addAndRemoveEnrolment(){
        JsonObjectBuilder t = Json.createObjectBuilder();
        t.add("date", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        t.add("course", Json.createObjectBuilder().add("id", 8));
        t.add("student", Json.createObjectBuilder().add("id", 16));
        JsonObject obj = t.build();

        postGetDeleteGet(obj, "enrolments", new String[]{"/date", "/student/id", "/course/id"});
    }

    private void postGetDeleteGet(JsonObject toPost, String path, String[] compareVals){

        Response resp = this.tut.path(path).request(MediaType.APPLICATION_JSON).post(Entity.json(toPost.toString()));
        assertThat(resp.getStatus(), is(200));
        JsonObject inserted = resp.readEntity(JsonObject.class);
        assertThat(inserted, is(notNullValue()));
        int id = inserted.getInt("id");

        JsonObjectBuilder exBuilder = Json.createObjectBuilder(toPost);
        exBuilder.add("id",id);

        Response get = this.tut.path(path).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get();

        JsonObject expected = exBuilder.build();
        JsonObject actual = get.readEntity(JsonObject.class);

        for(String s:compareVals){
            assertThat(actual.getValue(s), equalTo(expected.getValue(s)));
        }

        Response del = this.tut.path(path).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).delete();
        assertThat(del.getStatus(), is(200));

        Response getDel = this.tut.path(path).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get();
        assertThat(getDel.getStatus(), is(404));
    }

}
