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
        System.out.println("Id eetac-emon: " + id);
        Eetakemon e = new Eetakemon();
        e.buscarPorId(id);
        System.out.println(e.toString());
        return e;
    }


    @POST
    @Path("/Eetakemon")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newEetakemon(Eetakemon eetakemon) {
       eetakemon.crear();
        return Response.status(201).entity("Eetakemon añadido: ").build();
    }

    @POST
    @Path("/User")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario usuario) {
        usuario.crear();
        return Response.status(201).entity("Usuario añadido: ").build();
    }

    @POST
    @Path("/Login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Login(Usuario usuario) {
        String a;
        System.out.println(usuario);
        String n,c;
        n=usuario.getNombre();
        c=usuario.getContrasena();
        usuario.login(n,c);
        return Response.status(201).entity("Usuario identificado: ").build();
    }

    @POST
    @Path("/User/{id}")
    public Response modificarUsuario(@PathParam("id") int id, Usuario usuario) {
        usuario.crear();//Se tiene que llamar a la funcion modificar
        Usuario u = new Usuario();
        System.out.println(u.getContrasena());
        return Response.status(201).entity("Usuario añadido: ").build();
    }

    @GET
    @Path("/User/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioId(@PathParam("id") int id) {
       Usuario u = new Usuario();
       u.buscarPorId(id); //poner select(id) o setId(id)
        System.out.println(u.toString());
        return u;
    }

    @DELETE
    @Path("/Eetakemon/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delEetakemon(@PathParam("id") int id) {
        Eetakemon e = new Eetakemon();
        e.buscarPorId(id);
        e.borrar();
        return Response.status(204).entity("Eetakemon eliminado").build();
    }

    @DELETE
    @Path("/User/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delUser(@PathParam("id") int id) {
        Usuario u = new Usuario();
        u.buscarPorId(id);
        u.borrar();
        return Response.status(204).entity("Usuario eliminado").build();
    }
}
