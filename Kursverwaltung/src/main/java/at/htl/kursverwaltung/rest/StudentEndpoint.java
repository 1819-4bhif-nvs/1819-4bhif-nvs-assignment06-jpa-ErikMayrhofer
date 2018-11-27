package at.htl.kursverwaltung.rest;

import at.htl.kursverwaltung.core.InitBean;
import at.htl.kursverwaltung.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("students")
public class StudentEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    public List<Student> listStudents(){
        TypedQuery<Student> query = em.createNamedQuery("Student.findAll", Student.class);
        return query.getResultList();
    }

    @POST
    public void addStudent(Student student){
        em.persist(student);
    }

    @GET
    @Path("{id}")
    public Student findStudent(@PathParam("id") long id){
        return em.createNamedQuery("Student.findById", Student.class).setParameter("ID", id).getSingleResult();
    }
}
