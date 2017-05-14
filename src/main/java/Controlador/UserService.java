package Controlador;

import Modelo.TrippleDes;
import Modelo.User.User;
import Modelo.User.UserManager;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.core.GenericEntity;

@Path("/User")
@Singleton
public class UserService {
    private TrippleDes td;
    private UserManager manager;

    public UserService() {
        manager=new UserManager();
    }


    //registrar user
    @POST
    @Path("/Register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        Boolean a;
        a = manager.register(user);
        if (!a) {
            return Response.status(201).entity("Usuario añadido: ").build();
        } else {
            return Response.status(202).entity("Usuario ya utilizado: ").build();

        }
    }

    //logearse
    @POST
    @Path("/Login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Login(User usuario) {
        usuario=manager.login(usuario);
        if (usuario!=null) {
            System.out.println(usuario.getId());
            return Response.status(201).entity(usuario).build();
        }
        else{
            return Response.status(202).entity("Usuario incorrecto: ").build();
        }
    }

    //modificar user
    @POST
    @Path("/{id}")
    public Response modifyUser(@PathParam("id") int id, User user) {
        Boolean a=false;
        a=manager.updateUser(id,user);
        if (a) {
            return Response.status(201).entity("Usuario modificado: ").build();
        } else {
            return Response.status(202).entity("No se ha podido modificar: ").build();
        }
    }

    //Obtener usuario por id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserId(@PathParam("id") int id) {
        User u;
        u=manager.getUserById(id);
        if (u.getNombre()!=null) {
            return Response.status(201).entity(u).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
    }

    //borrar usuario
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id) {
        User u;
        u=manager.deleteUser(id);
        if (u.getNombre()!= null)
            return Response.status(201).entity("Usuario eliminado").build();
        else{
            return Response.status(202).entity("No se ha podido eliminar").build();
        }
    }

    //Lista de usuarios
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListUsers() {
        List<User> list;
        list = manager.listAllUsers();

        if (!list.isEmpty()) {
            GenericEntity< List <User> > entity;
            entity  = new GenericEntity< List<User> >( list ) { };
            return Response.status(201).entity(entity).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
    }

    //Recuperar contraseña
    @POST
    @Path("/Password")
    @Produces(MediaType.APPLICATION_JSON)
    public Response restorePassword(User usuario){
        boolean a;
        a=manager.resetPassword(usuario);
        if (a)
            return Response.status(201).entity("E-mail enviado").build();
        else{
            return Response.status(202).entity("No se ha podido recuperar contraseña").build();
        }
    }
}
