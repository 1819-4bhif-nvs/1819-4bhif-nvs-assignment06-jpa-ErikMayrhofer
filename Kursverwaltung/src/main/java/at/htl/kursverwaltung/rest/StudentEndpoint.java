package at.htl.kursverwaltung.rest;

import at.htl.kursverwaltung.model.Student;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Path("students")
@Stateless
public class StudentEndpoint extends AbstractEndpoint<Student>{
    public StudentEndpoint(){
        super(Student.class, "select s from Student s");
    }
}
