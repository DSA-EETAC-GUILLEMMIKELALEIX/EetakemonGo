package Controlador;
import Modelo.Eetakemon.Eetakemon;
import Modelo.Eetakemon.EetakemonManager;
import Modelo.Relation.Relation;
import Modelo.User.User;
import Modelo.User.UserManager;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import Modelo.Relation.RelationManager;
import Modelo.Relation.Relation;

/**
 * Created by usuario on 16/05/2017.
 */
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
    public Response newEetakemon(Eetakemon eetakemon) {
        Boolean a;
        a=manager.addEetakemon(eetakemon);
        if (!a) {
            return Response.status(201).entity("Eetakemon añadido: ").build();
        }
        else{
            return Response.status(202).entity("Eetakemon ya existente: ").build();
        }
    }
    //Obtener relacion por id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEetakemonId(@PathParam("id") int id) {
        Eetakemon e = new Eetakemon();
        e=manager.getEetakemonById(id);
        if (e.getNombre()!=null) {
            return Response.status(201).entity(e).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
    }
}
