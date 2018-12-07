package at.htl.kursverwaltung.rest;

import at.htl.kursverwaltung.model.Teacher;

import javax.annotation.processing.Generated;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Method;

@Path("teachers")
@Stateless
public class TeacherEndpoint {

    @PersistenceContext
    private EntityManager em;

    @GET
    public Response listTeachers(){
        TypedQuery<Teacher> teachers = em.createQuery("select t FROM Teacher t", Teacher.class);
        return Response.ok().entity(teachers.getResultList()).build();
    }

    @GET
    @Path("{id}")
    public Response getTeacher(@PathParam("id") long id){
        Teacher teacher = em.find(Teacher.class, id);
        if(teacher != null){
            return Response.ok().entity(teacher).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTeacher(@PathParam("id") long id){
        Teacher teacher = em.find(Teacher.class, id);
        if(teacher != null){
            em.remove(teacher);
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postTeacher(Teacher teacher){
        em.persist(teacher);
        return Response.ok().entity(teacher).build();
    }

}

