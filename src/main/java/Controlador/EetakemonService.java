package Controlador;

import Modelo.Eetakemon;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


@Path("/Eetakemon")
@Singleton
public class EetakemonService {
    protected DAO dao;
    public EetakemonService() {
        dao=DAO.getEetakemonManagerClass();
    }

    //Obtener eetakemon por id
    @GET
    @Path("/{id}")
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

    //foto eetakemon
    @POST
    @Path("/Image")
    @Consumes(MediaType.APPLICATION_JSON)
    public void Image(Eetakemon eetakemon) {
        String base64Image = eetakemon.getFoto().split(",")[1];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        File imageFile = new File("WEB\\images\\" + eetakemon.getNombre() + ".png");

        System.out.println(imageFile);
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

    //Lista de eetac-emons
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarEetakemons() {
        List<Eetakemon> list = new ArrayList<>();
        list =new Eetakemon().findAll();
        GenericEntity< List <Eetakemon> > entity;
        entity  = new GenericEntity< List< Eetakemon > >( list ) { };
        if (!list.isEmpty()) {
            return Response.status(201).entity(entity).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
    }

}
