package at.htl.kursverwaltung.rest;

import at.htl.kursverwaltung.model.Teacher;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Path("teachers")
@Stateless
public class TeacherEndpoint extends AbstractEndpoint<Teacher> {
    public TeacherEndpoint(){
        super(Teacher.class, "select s from Teacher s");
    }
}

