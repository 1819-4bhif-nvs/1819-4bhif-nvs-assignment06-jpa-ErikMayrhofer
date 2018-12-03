package at.htl.kursverwaltung.rest;

import at.htl.kursverwaltung.core.InitBean;
import at.htl.kursverwaltung.model.Course;
import at.htl.kursverwaltung.model.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;


@Stateless
@Path("courses")
public class CourseEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    public Response listStudents(){
        TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class);
        List<Course> courses = query.getResultList();
        return Response.ok().entity(courses).build();
    }
}
