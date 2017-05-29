package Controlador;

import Modelo.Exceptions.NotSuchPrivilegeException;
import Modelo.Exceptions.UnauthorizedException;
import Modelo.Relation.Captured;
import Modelo.Relation.Relation;
import Modelo.Relation.RelationManager;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.core.HttpHeaders;
import java.util.ArrayList;
import javax.ws.rs.core.GenericEntity;

@Path("/Relation")
@Singleton
public class RelationService {
    private RelationManager manager;

    public RelationService() {
        manager=new RelationManager();
    }

//aaa

    //añadir eetakemon capturado
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newRelation(@Context HttpHeaders header, Relation relation) {
        Boolean a;
        try {
            a = manager.addRelation(relation, header);

            if (!a) {
                return Response.status(Response.Status.CREATED).entity("Nuevo Eetakemon capturado: ").build();
            } else {
                return Response.status(Response.Status.OK).entity("Nivel aumentado: ").build();
            }
        } catch (UnauthorizedException ex) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401

        }
    }
    //Obtener relacion por id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRelationId(@Context HttpHeaders header, @PathParam("id") int id) {
        Captured c = new Captured();
        try {
            c = manager.getRelationById(id, header);
            if (c.getIdEetakemon() != -1) {
                return Response.status(Response.Status.OK).entity(c).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("Error al obtener relación: ").build();
            }
        }catch (UnauthorizedException ex) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401

        }
    }

    //borrar eetakemon de un usuario
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delRelation(@Context HttpHeaders header, @PathParam("id") int id) {
        Relation r = new Relation();
        try {
            r = manager.deleteRelation(id, header);
            if (r.getIdUser() != -1)
                return Response.status(Response.Status.OK).entity("Relación eliminada").build();
            else {
                return Response.status(Response.Status.ACCEPTED).entity("No se ha podido eliminar").build();
            }
        }catch (UnauthorizedException ex) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401

        }

    }

    //Lista de Tus eetac-emons //ARREGLAR
    @GET
    @Path("/Captured/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarCapturados(@Context HttpHeaders header, @PathParam("id") int id) {
        List<Captured> list;
        try {
            list = manager.getCaptured(id, header);
            if (!list.isEmpty()) {
                GenericEntity<List<Captured>> entity;
                entity = new GenericEntity<List<Captured>>(list) {
                };
                return Response.status(Response.Status.OK).entity(entity).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No hay eetakemons capturados ").build();
            }
        }catch (UnauthorizedException ex) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401

            }

    }

    //Lista todos los capturados de todos los usuarios
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarRelacion(@Context HttpHeaders header) {
        List<Relation> list;
        try {
            list = manager.listAllRelation(header);
            if (!list.isEmpty()) {
                GenericEntity<List<Relation>> entity;
                entity = new GenericEntity<List<Relation>>(list){};
                return Response.status(201).entity(entity).build();
            } else {
                return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
            }
        }catch(UnauthorizedException ex){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401

        }catch(NotSuchPrivilegeException ex){
            return Response.status(Response.Status.FORBIDDEN).entity("Forbidden").build();//403
        }
    }
}
