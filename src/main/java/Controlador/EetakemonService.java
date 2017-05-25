package Controlador;

import Modelo.Eetakemon.Eetakemon;
import Modelo.Eetakemon.EetakemonManager;
import Modelo.Exceptions.NotSuchPrivilegeException;
import Modelo.Exceptions.UnauthorizedException;
import Modelo.Security.AuthenticationManager;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


@Path("/Eetakemon")
@Singleton
public class EetakemonService {
    private EetakemonManager manager;
    private AuthenticationManager authManager;

    public EetakemonService() {
        manager= new EetakemonManager();
        authManager= new AuthenticationManager();
    }

    //Obtener eetakemon por id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEetakemonId(@Context HttpHeaders header, @PathParam("id") int id) {
        Eetakemon e = new Eetakemon();
        try {
            e = manager.getEetakemonById(header, id);
            if (e.getNombre() != null) {
                return Response.status(Response.Status.OK).entity(e).build();//200
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No eetakemon found").build();//204
            }
        }catch(UnauthorizedException ex){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401

        }
    }

    //añadir eetakemon
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newEetakemon(@Context HttpHeaders header,Eetakemon eetakemon) {
        Boolean a;

        try {
            a = manager.addEetakemon(header, eetakemon);
            if (!a) {
                return Response.status(Response.Status.CREATED).entity("Eetakemon added").build();//201
            } else {
                return Response.status(Response.Status.ACCEPTED).entity("Eetakemon already exists").build();//202
            }
        }catch(UnauthorizedException ex){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401

        }catch(NotSuchPrivilegeException ex){
            return Response.status(Response.Status.FORBIDDEN).entity("Forbidden").build();//403
        }
    }

    //foto eetakemon
    @POST
    @Path("/Image")
    @Consumes(MediaType.APPLICATION_JSON)
    public void Image(@Context HttpHeaders header, Eetakemon eetakemon) {
        String base64Image = eetakemon.getFoto().split(",")[1];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        File imageFile = new File("WEB\\images\\" + eetakemon.getNombre() + ".png");
        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            ImageIO.write(bufferedImage, "png", imageFile);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    //borrar eetakemon
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delEetakemon(@Context HttpHeaders header, @PathParam("id") int id) {
        Eetakemon e = new Eetakemon();
        try {
            e = manager.deleteEetakemon(header, id);
            if (e.getNombre() != null)
                return Response.status(Response.Status.OK).entity("Eetakemon deleted").build();//200
            else {
                return Response.status(Response.Status.ACCEPTED).entity("Not deleted").build();//202
            }
        }catch(UnauthorizedException ex){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401

        }catch(NotSuchPrivilegeException ex){
            return Response.status(Response.Status.FORBIDDEN).entity("Forbidden").build();//403
        }
    }

    //Lista de eetac-emons
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarEetakemons(@Context HttpHeaders header) {
        List<Eetakemon> list;
        try {
            list = manager.listAllEetakemon(header);
            if (!list.isEmpty()) {
                GenericEntity<List<Eetakemon>> entity;
                entity = new GenericEntity<List<Eetakemon>>(list) {
                };
                return Response.status(Response.Status.OK).entity(entity).build();//200
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No content").build();//204
            }
        }catch(UnauthorizedException ex){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401
        }
    }


    //Eetakemon nivel normal
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEetakemonTipo(@Context HttpHeaders header, String tipo) {
        Eetakemon e = new Eetakemon();
        try {
            e = manager.getEetakemonByType(header, tipo);
            if (e.getNombre() != null) {
                return Response.status(Response.Status.OK).entity(e).build();//200
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No eetakemon found").build();//204
            }
        }catch(UnauthorizedException ex){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401
        }
    }
}