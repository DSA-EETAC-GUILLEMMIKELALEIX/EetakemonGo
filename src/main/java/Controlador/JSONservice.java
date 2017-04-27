package Controlador;

import Modelo.Eetakemon;
import Modelo.Usuario;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/json")
@Singleton
public class JSONservice {

    protected DAO dao;
    public JSONservice() {
        dao=DAO.getEetakemonManagerClass();
    }

    //Obtener eetakemon por id
    @GET
    @Path("/Eetakemon/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEetakemonId(@PathParam("id") int id) {
        System.out.println("Id eetac-emon: " + id);
        Eetakemon e = new Eetakemon();
        e.select(id);
        if (e.getNombre()!=null) {
            return Response.status(201).entity(e).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
    }

    //añadir eetakemon
    @POST
    @Path("/Eetakemon")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newEetakemon(Eetakemon eetakemon) {
        Boolean a;
        a=eetakemon.checkExistent("nombre", eetakemon.getNombre());
        if (a) {
            eetakemon.insert();
            return Response.status(201).entity("Eetakemon añadido: ").build();
        }
        else{
            return Response.status(202).entity("Eetakemon ya existente: ").build();
        }
    }

    //añadir usuario
    @POST
    @Path("/User")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario usuario) {
        Boolean a;
        a = usuario.checkExistent("email",usuario.getEmail());
        if (a) {
            usuario.insert();
            return Response.status(201).entity("Usuario añadido: ").build();
        } else {
            return Response.status(202).entity("Usuario ya utilizado: ").build();

        }
    }

    //logearse
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

    //modificar usuario
    @POST
    @Path("/User/{id}")
    public Response modificarUsuario(@PathParam("id") int id, Usuario usuario) {
        Boolean a=false;
        usuario.setId(id);
        System.out.println(id);
        a = usuario.update();
        System.out.println(a);
        if (a) {
            return Response.status(201).entity("Usuario modificado: ").build();
        } else {
            return Response.status(202).entity("No se ha podido modifucar: ").build();
        }
    }

    //Obtener usuario
    @GET
    @Path("/User/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioId(@PathParam("id") int id) {
       Usuario u = new Usuario();
       u.select(id);
        System.out.println(u.toString());
        if (u.getNombre()!=null) {
            return Response.status(201).entity(u).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
    }

    //borrar eetakemon
    @DELETE
    @Path("/Eetakemon/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delEetakemon(@PathParam("id") int id) {
        Eetakemon e = new Eetakemon();
        e.select(id);
        e.delete();
        if (e.getNombre()!= null)
            return Response.status(201).entity("Eetakemon eliminado").build();
        else{
            return Response.status(202).entity("No se ha podido eliminar").build();
        }
    }

    //borrar usuario
    @DELETE
    @Path("/User/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delUser(@PathParam("id") int id) {
        Usuario u = new Usuario();
        u.select(id);
        u.delete();
        if (u.getNombre()!= null)
            return Response.status(201).entity("Usuario eliminado").build();
        else{
            return Response.status(202).entity("No se ha podido eliminar").build();
        }
    }

    //Lista de usuarios
    @GET
    @Path("/Users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarUsuarios() {
        List<Object> u = new ArrayList<>();
        //u = dao.findAll();
        if (!u.isEmpty()) {
            System.out.println(u);
            return Response.status(201).entity(u).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
    }

    //Lista de eetac-emons
    @GET
    @Path("/Eetakemons")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarEetakemons() {
        List<Eetakemon> u = new ArrayList<Eetakemon>();
        //u = dao.findAll();
        if (!u.isEmpty()) {
            return Response.status(201).entity(u).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
    }

}
