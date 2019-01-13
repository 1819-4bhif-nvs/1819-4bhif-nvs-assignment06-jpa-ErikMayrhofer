package at.htl.kursverwaltung.rest;

import at.htl.kursverwaltung.model.Course;

import javax.ejb.Stateless;
import javax.ws.rs.Path;


@Stateless
@Path("courses")
public class CourseEndpoint extends AbstractEndpoint<Course> {
    public CourseEndpoint(){
        super(Course.class);
    }
}
