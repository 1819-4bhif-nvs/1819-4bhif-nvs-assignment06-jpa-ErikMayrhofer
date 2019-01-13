package at.htl.kursverwaltung.rest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

public class AbstractEndpoint<T> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> clazz;
    private final String listQuery;

    public AbstractEndpoint(Class<T> clazz, String listQueryName) {
        this.clazz = clazz;
        this.listQuery = Objects.requireNonNullElseGet(listQueryName, () -> clazz.getSimpleName() + ".findAll");
    }

    public AbstractEndpoint(Class<T> clazz){
        this(clazz, null);
    }

    @GET
    public Response list(){
        TypedQuery<T> student = em.createNamedQuery(listQuery, clazz);
        return Response.ok().entity(student.getResultList()).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") long id){
        T entity = em.find(clazz, id);
        if(entity != null){
            return Response.ok().entity(entity).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        T entity = em.find(clazz, id);
        if(entity != null){
            em.remove(entity);
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(T entity){
        try {
            em.persist(entity);
            em.flush();
            em.refresh(entity);
        }catch(PersistenceException e){
            return Response.status(400).build();
        }
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(T entity){
        entity = em.merge(entity);
        em.flush();
        em.refresh(entity);
        return Response.ok().entity(entity).build();
    }
}
