package Controlador;

import Modelo.Eetakemon;
import Modelo.Usuario;
import Modelo.Track;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;



@Path("/json")
public class JSONservice {

    protected Controlador c;

    public JSONservice() {
        c = Controlador.getControlador();
        c.anadirATabla(new Eetakemon("Aleix",1));
        c.anadirATabla(new Eetakemon("Guillem",2));
        c.anadirATabla(new Eetakemon("MIkel",3));
    }

    @GET
    @Path("/got/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Eetakemon getEetakemonId(@PathParam("id") int id) {
        Eetakemon e = (Eetakemon) c.buscarPorId(id);
        System.out.println(e.toString());
        return e;
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Eetakemon getEetakemon() {

        Eetakemon track = new Eetakemon("luis", 3);
        return track;

    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Eetakemon getEetakemon2() {

        Eetakemon track = new Eetakemon("juan", 8);
        return track;

    }

    @POST
    @Path("/newEetakemon")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Eetakemon eetakemon) {
        c.anadirATabla(eetakemon);
        return Response.status(201).entity("Eetakemon añadido: ").build();
    }
}
