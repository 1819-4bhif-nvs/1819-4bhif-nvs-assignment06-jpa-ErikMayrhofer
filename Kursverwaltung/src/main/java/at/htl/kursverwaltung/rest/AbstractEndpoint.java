package at.htl.kursverwaltung.rest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AbstractEndpoint<T> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> clazz;
    String listQuery;

    public AbstractEndpoint(Class<T> clazz, String listQuery) {
        this.clazz = clazz;
        this.listQuery = listQuery;
    }

    @GET
    public Response list(){
        TypedQuery<T> student = em.createQuery(listQuery, clazz);
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
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(T entity){
        em.persist(entity);
        return Response.ok().entity(entity).build();
    }
}
