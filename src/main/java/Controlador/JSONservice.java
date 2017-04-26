package Controlador;

import Modelo.Eetakemon;
import Modelo.Usuario;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
        Boolean a;
        a=usuario.validarRegistro(usuario.getNombre());
        if (a){
            usuario.crear();
            return Response.status(201).entity("Usuario añadido: ").build();
        }
        else{
            return Response.status(202).entity("Usuario ya utilizado: ").build();
        }
    }

    @POST
    @Path("/Login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Login(Usuario usuario) {
        Boolean a;
        System.out.println(usuario);
        String n,c;
        n=usuario.getNombre();
        c=usuario.getContrasena();
        a=usuario.login(n,c);
        if (a) {
            return Response.status(201).entity("Usuario identificado: ").build();
        }
        else{
            return Response.status(202).entity("Usuario incorrecto: ").build();
        }
    }

    @POST
    @Path("/User/{id}")
    public Response modificarUsuario(@PathParam("id") int id, Usuario usuario) {
        Boolean a=false;
        a=usuario.modificar(id, usuario);//Se tiene que llamar a la funcion modificar
        if (a) {
            return Response.status(201).entity("Usuario modificado: ").build();
        }
        else{
            return Response.status(202).entity("No se ha podido modifucar: ").build();
        }
    }

    @GET
    @Path("/User/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioId(@PathParam("id") int id) {
       Usuario u = new Usuario();
       u.buscarPorId(id);
        System.out.println(u.toString());
        if (u.getNombre()!=null) {
            return Response.status(201).entity(u).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
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
    @GET
    @Path("/UserList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarUsuarios() {
        List<Usuario> u = new ArrayList<Usuario>();
        u = Collections.list(ListarUsuarios())
        if (u!=null) {
            return Response.status(201).entity(u).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
    }
}
