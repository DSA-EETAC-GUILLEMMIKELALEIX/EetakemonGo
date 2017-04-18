package Controlador;

import Modelo.Eetakemon;
import Modelo.Usuario;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/json")
@Singleton
public class JSONservice {


    public JSONservice() {
    }

    @GET
    @Path("/Eetakemon/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Eetakemon getEetakemonId(@PathParam("id") int id) {
        Eetakemon e = new Eetakemon();
        e.select(id);
        System.out.println(e.toString());
        return e;
    }


    @POST
    @Path("/Eetakemon")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newEetakemon(Eetakemon eetakemon) {
        eetakemon.setId(0);
       eetakemon.insert();
        return Response.status(201).entity("Eetakemon añadido: ").build();
    }

    @POST
    @Path("/User")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario usuario) {
        usuario.setId(0);
        usuario.insert();
        return Response.status(201).entity("Usuario añadido: ").build();
    }


    @GET
    @Path("/User/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioId(@PathParam("id") int id) {
       Usuario u = new Usuario();
       u.select(id);
        System.out.println(u.toString());
        return u;
    }

    @DELETE
    @Path("/Eetakemon/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delEetakemon(@PathParam("id") int id) {
        Eetakemon e = new Eetakemon();
        e.setId(id);
        e.delete();
        return Response.status(204).entity("Eetakemon eliminado").build();
    }

    @DELETE
    @Path("/User/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delUser(@PathParam("id") int id) {
        Usuario u = new Usuario();
        u.setId(id);
        u.delete();
        return Response.status(204).entity("Usuario eliminado").build();
    }
}
