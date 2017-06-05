package Controller;

import Model.Capturar.Capturar;
import Model.Eetakemon.Eetakemon;
import Model.Eetakemon.EetakemonManager;
import Model.Exceptions.NotSuchPrivilegeException;
import Model.Exceptions.UnauthorizedException;
import Model.Location.Location;
import Model.Location.LocationManager;
import Model.Security.AuthenticationManager;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


@Path("/Eetakemon")
@Singleton
public class EetakemonService {
    private EetakemonManager manager;
    private AuthenticationManager authManager;
    private LocationManager locManager;

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
    @POST
    @Path("/Tipo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEetakemonTipo(Eetakemon eetak) {
        System.out.println("holalalalalalala");
        Eetakemon e = new Eetakemon();
        e=manager.getEetakemonByType(eetak.getTipo());
        System.out.println(e);
        if (e.getNombre()!=null) {
            return Response.status(201).entity(e).build();
        }
        else{
            return Response.status(202).entity("No se ha podido visualizar el usuario: ").build();
        }
    }

    //Lista de eetac-emons
    @GET
    @Path("/ListApp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarEetakemons() {
        List<Eetakemon> list;
        System.out.println("AAAAAA");
            list = manager.listAllEetakemon();
            if (!list.isEmpty()) {
                GenericEntity<List<Eetakemon>> entity;
                entity = new GenericEntity<List<Eetakemon>>(list) {
                };
                System.out.println(entity);
                return Response.status(Response.Status.OK).entity(entity).build();//200
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No content").build();//204
            }

    }

    //Lista de eetac-emons
    @GET
    @Path("/ListMapa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarMapaEetakemons() {
        List<Capturar> liste = new ArrayList<Capturar>();

        Eetakemon e = new Eetakemon();
        Capturar capt = new Capturar();
        Location loc= new Location();
        
        for (int i=0;i<3;i++) {
            e = manager.getEetakemonByType("Inferior");
            capt.setNombre(e.getNombre());
            capt.setTipo(e.getTipo());
            loc = locManager.locat();
            capt.setLatLong(loc);
            System.out.println(e);
            liste.add(i, capt);
        }
        e = manager.getEetakemonByType("Normal");
        capt.setNombre(e.getNombre());
        capt.setTipo(e.getTipo());
        loc = locManager.locat();
        capt.setLatLong(loc);
        System.out.println(e);
        liste.add(capt);

        e = manager.getEetakemonByType("Legendario");
        capt.setNombre(e.getNombre());
        capt.setTipo(e.getTipo());
        loc = locManager.locat();
        capt.setLatLong(loc);
        System.out.println(e);
        liste.add(capt);

        System.out.println("list:"+liste.size());

        if (!liste.isEmpty()) {
            GenericEntity<List<Capturar>> entity;
            entity = new GenericEntity<List<Capturar>>(liste) {
            };
            return Response.status(200).entity(entity).build();//200
        } else {
            return Response.status(Response.Status.NO_CONTENT).entity("No content").build();//204
        }

    }
}