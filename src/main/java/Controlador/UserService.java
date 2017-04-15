package Controlador;

import Modelo.Usuario;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by Aleix on 15/04/2017.
 */

@Path("/user")
public class UserService {

    @Path("/register")
    @POST
    @Produces({ MediaType.APPLICATION_JSON})
    public Response UsuarioNuevo(Usuario user)throws Exception{

       // return WebFunctions.getInstance().newUser(user);

    }

    @Path("/login")
    @POST
    @Produces({ MediaType.APPLICATION_JSON})
    public Response LogInUsuario(Usuario user)throws Exception{

       // return  WebFunctions.getInstance().logUser(user);
    }


}
