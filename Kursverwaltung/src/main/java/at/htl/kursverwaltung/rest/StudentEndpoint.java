package at.htl.kursverwaltung.rest;

import at.htl.kursverwaltung.model.Student;
import at.htl.kursverwaltung.model.Teacher;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("students")
@Stateless
public class StudentEndpoint {

    @PersistenceContext
    private EntityManager em;

    @GET
    public Response listStudents(){
        TypedQuery<Student> student = em.createQuery("select s FROM Student s", Student.class);
        return Response.ok().entity(student.getResultList()).build();
    }

    @GET
    @Path("{id}")
    public Response getStudent(@PathParam("id") long id){
        Student student = em.find(Student.class, id);
        if(student != null){
            return Response.ok().entity(student).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent(@PathParam("id") long id){
        Student student = em.find(Student.class, id);
        if(student != null){
            em.remove(student);
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postTeacher(Student student){
        em.persist(student);
        return Response.ok().entity(student).build();
    }
}
