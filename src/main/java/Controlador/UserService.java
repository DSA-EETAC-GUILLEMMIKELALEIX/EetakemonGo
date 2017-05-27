package Controlador;

import Modelo.Exceptions.NotSuchPrivilegeException;
import Modelo.Exceptions.UnauthorizedException;
import Modelo.Security.AuthenticationManager;
import Modelo.Security.Verification;
import Modelo.User.User;
import Modelo.User.UserManager;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;


@Path("/User")
@Singleton
public class UserService {
    private UserManager manager;

    public UserService() {
        manager=new UserManager();
    }


    //registrar user
    @POST
    @Path("/Register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        int code;
        /*code = manager.register(user);
        if (code==0) {
            return Response.status(Response.Status.CREATED).entity("User registered").build();//201
        } else if (code==1){
            return Response.status(Response.Status.ACCEPTED).entity("User already exists").build();///202
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad request").build();//400
        }*/
        return Response.status(Response.Status.CREATED).entity("User registered").build();//201
    }

    //logearse
    @POST
    @Path("/Login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Login(User usuario) {
        int code;
        code=manager.login(usuario);
        if (code==0) {
            String token = new AuthenticationManager().getToken(usuario);
            return Response.status(Response.Status.OK).entity(token).build();//200
        }
        else{
            return Response.status(Response.Status.UNAUTHORIZED).entity("Incorrect user").build();//401
        }
    }

    //modificar user
    @POST
    @Path("/{id}")
    public Response modifyUser(@Context HttpHeaders header,@PathParam("id") int id, User user) {
        Boolean a=false;
        try {
            a = manager.updateUser(header, id, user);
            if (a) {
                return Response.status(Response.Status.OK).entity("Usuario modificado: ").build();//200
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error modifying").build();//500
            }
        }catch(UnauthorizedException ex){
        return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401

        }catch(NotSuchPrivilegeException ex){
            return Response.status(Response.Status.FORBIDDEN).entity("Forbidden").build();//403
        }
    }

    //Obtener usuario por id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserId(@Context HttpHeaders header,@PathParam("id") int id) {
        Verification v= new Verification();
        User u;
        try {
            u = manager.getUserById(header, id);
            if (u.getNombre() != null) {
                return Response.status(Response.Status.OK).entity(u).build();//200
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No user found").build();//204
            }
        }catch (UnauthorizedException ex){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401
        }catch(NotSuchPrivilegeException ex){
            return Response.status(Response.Status.FORBIDDEN).entity("Forbidden").build();//403
        }
    }

    //borrar usuario
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@Context HttpHeaders header, @PathParam("id") int id) {
        Verification v= new Verification();
        try {
            manager.deleteUser(header, id);
            return Response.status(Response.Status.OK).entity("User deleted").build();//200

        }catch(UnauthorizedException ex){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401

        }catch(NotSuchPrivilegeException ex){
            return Response.status(Response.Status.FORBIDDEN).entity("Forbidden").build();//403
        }
    }

    //Lista de usuarios
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListUsers(@Context HttpHeaders header) {
        Verification v= new Verification();
        try {
            List<User> list;
            list = manager.listAllUsers(header);

            if (!list.isEmpty()) {
                GenericEntity<List<User>> entity;
                entity = new GenericEntity<List<User>>(list) {
                };
                return Response.status(Response.Status.OK).entity(entity).build();//200
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No users found: ").build();//204
            }
        }catch(UnauthorizedException ex){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401

        }catch(NotSuchPrivilegeException ex){
            return Response.status(Response.Status.FORBIDDEN).entity("Forbidden").build();//403
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
            return Response.status(Response.Status.OK).entity("E-mail sended").build(); //200
        else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reseting password").build();//500
        }
    }

    ///////////////////////////////////////////////////////////////////

    //logearse
    @POST
    @Path("/LoginApp")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response LoginApp(User usuario) {
        int code;
        code=manager.login(usuario);
        if (code==0) {
            System.out.println(usuario.getId());
            return Response.status(201).entity(usuario).build();
        }
        else{
            return Response.status(202).entity("Usuario incorrecto: ").build();
        }
    }
    @POST
    @Path("/RegisterApp")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerApp(User user) {
        int code;
        code = manager.register(user);
        if (code==0) {
            return Response.status(201).entity(user).build();
        } else if (code==1){
            return Response.status(202).entity("Usuario ya utilizado: ").build();
        }else{
            return Response.status(203).entity("Error al registrarse: ").build();
        }
    }

}
