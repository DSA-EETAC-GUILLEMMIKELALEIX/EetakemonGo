package Controlador;

import Modelo.Relation.Relation;
import Modelo.Relation.RelationManager;
import Modelo.User.UserManager;
import Modelo.Relation.Captured;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.core.GenericEntity;

@Path("/Relation")
@Singleton
public class RelationService {
    private RelationManager manager;

    public RelationService() {
        manager=new RelationManager();
    }



    //añadir relacion
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newEetakemon(Relation relation) {
        Boolean a;
        a=manager.addRelation(relation);
        if (!a) {
            return Response.status(201).entity("Relación añadida: ").build();
        }
        else{
            return Response.status(202).entity("Nivel aumentado: ").build();
        }
    }
    //Obtener relacion por id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEetakemonId(@PathParam("id") int id) {
        Relation r = new Relation(); //aaaa
        r=manager.getRelationById(id);
        if (r.getIdUser()!=-1) {
            return Response.status(201).entity(r).build();
        }
        else{
            return Response.status(202).entity("Error al obtener relación: ").build();
        }
    }

    //borrar eetakemon
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delEetakemon(@PathParam("id") int id) {
        Relation r = new Relation();
        r=manager.deleteRelation(id);
        if (r.getIdUser()!=-1)
            return Response.status(201).entity("Relación eliminada").build();
        else{
            return Response.status(202).entity("No se ha podido eliminar").build();
        }
    }

    //Lista de Tus eetac-emons
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarRelacion() {
        List<Relation> list;
        list=manager.listAllRelation();
        if (!list.isEmpty()) {
            GenericEntity< List <Relation> > entity;
            entity  = new GenericEntity< List< Relation > >( list ) { };
            return Response.status(201).entity(entity).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
    }
}
