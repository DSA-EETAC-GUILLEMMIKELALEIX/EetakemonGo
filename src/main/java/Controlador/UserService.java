package Controlador;

import Modelo.User;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.core.GenericEntity;

@Path("/User")
@Singleton
public class UserService {
    protected DAO dao;
    private TrippleDes td;
    public UserService() {
        dao=DAO.getEetakemonManagerClass();
        try{
            td=new TrippleDes();
        }
        catch (Exception e){

        }
    }


    //registrar user
    @POST
    @Path("/Register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        Boolean a;
        a = user.checkExistent("email", user.getEmail());



        if (a) {
            String encriptedpass=td.encrypt(user.getContrasena());
            user.setContrasena(encriptedpass);
            user.insert();
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
        Boolean a;
        String e,c;
        e=usuario.getEmail();
        c=usuario.getContrasena();
        String encriptedpass=td.encrypt(c);
        a=usuario.login(e,encriptedpass);
        User u = new User();
        u.select(usuario.getEmail());
        if (a) {
            System.out.println(u.getId());
            return Response.status(201).entity(u).build();
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
        user.setId(id);
        System.out.println(id);
        a = user.update();
        System.out.println(a);
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
        User u = new User();
        u.select(id);
        System.out.println(u.toString());
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
        User u = new User();
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
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListUsers() {
        List<User> list;
        list = new User().findAll();
        GenericEntity< List <User> > entity;
        entity  = new GenericEntity< List<User> >( list ) { };
        if (!list.isEmpty()) {
            System.out.println("Lista a enviar" + entity);
            return Response.status(201).entity(entity).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
    }

    //Recuperar contraseña
    @POST
    @Path("/RecuperarUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response RecuperarlaContraseña(User usuario){
        boolean a;
        System.out.println("AAA:" + usuario);
        User u = new User();
        u.select(usuario.getEmail());
        a=u.Recuperar(u);
        if (a)
            return Response.status(201).entity("E-mail enviado").build();
        else{
            return Response.status(202).entity("No se ha podido recuperar contraseña").build();
        }
    }
}
