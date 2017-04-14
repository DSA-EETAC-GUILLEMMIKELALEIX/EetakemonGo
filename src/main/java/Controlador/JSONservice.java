package Controlador;

import Modelo.Eetakemon;
import Modelo.Usuario;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Aleix on 13/04/2017.
 */
@Path("/json")
public class JSONservice {

    protected List<Usuario> usuarios;
    protected List<Eetakemon> eetakemons;

    public JSONservice() {
        usuarios = new ArrayList<Usuario>();
        eetakemons = new ArrayList<Eetakemon>();
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return Response.status(201).entity("Track added in position "+usuarios.size()).build();
    }//h
}
