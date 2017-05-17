package Controlador;

import Modelo.Relation.Relation;
import Modelo.Relation.RelationManager;
import Modelo.User.UserManager;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.core.GenericEntity;


public class RelationService {
    private RelationManager manager;

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
