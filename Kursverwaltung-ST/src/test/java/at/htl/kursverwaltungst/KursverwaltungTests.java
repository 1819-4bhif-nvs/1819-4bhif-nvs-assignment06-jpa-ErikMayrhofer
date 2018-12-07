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
    public void t01_addAndRemoveTeacher(){
        JsonObjectBuilder t = Json.createObjectBuilder();
        t.add("firstName", "test");
        t.add("lastName", "test");
        t.add("teacherNumber", "TEST321");
        JsonObject obj = t.build();

        Response resp = this.tut.path("teachers").request(MediaType.APPLICATION_JSON).post(Entity.json(obj.toString()));
        assertThat(resp.getStatus(), is(200));
        JsonObject inserted = resp.readEntity(JsonObject.class);
        assertThat(inserted, is(notNullValue()));
        int id = inserted.getInt("id");

        JsonObjectBuilder exBuilder = Json.createObjectBuilder(obj);
        exBuilder.add("id",id);

        Response get = this.tut.path("teachers/"+id).request(MediaType.APPLICATION_JSON).get();

        JsonObject expected = exBuilder.build();
        JsonObject actual = get.readEntity(JsonObject.class);

        assertThat(actual, equalTo(expected));

        Response del = this.tut.path("teachers/"+id).request(MediaType.APPLICATION_JSON).delete();
        assertThat(del.getStatus(), is(200));

        Response getDel = this.tut.path("teachers/"+id).request(MediaType.APPLICATION_JSON).get();
        assertThat(getDel.getStatus(), is(404));
    }

    @Test
    public void t02_addAndRemoveStudent(){
        JsonObjectBuilder t = Json.createObjectBuilder();
        t.add("firstName", "test");
        t.add("lastName", "test");
        t.add("matNumber", "TEST321");
        JsonObject obj = t.build();

        Response resp = this.tut.path("students").request(MediaType.APPLICATION_JSON).post(Entity.json(obj.toString()));
        assertThat(resp.getStatus(), is(200));
        JsonObject inserted = resp.readEntity(JsonObject.class);
        assertThat(inserted, is(notNullValue()));
        int id = inserted.getInt("id");

        JsonObjectBuilder exBuilder = Json.createObjectBuilder(obj);
        exBuilder.add("id",id);

        Response get = this.tut.path("students/"+id).request(MediaType.APPLICATION_JSON).get();

        JsonObject expected = exBuilder.build();
        JsonObject actual = get.readEntity(JsonObject.class);

        assertThat(actual, equalTo(expected));

        Response del = this.tut.path("students/"+id).request(MediaType.APPLICATION_JSON).delete();
        assertThat(del.getStatus(), is(200));

        Response getDel = this.tut.path("students/"+id).request(MediaType.APPLICATION_JSON).get();
        assertThat(getDel.getStatus(), is(404));
    }

}
