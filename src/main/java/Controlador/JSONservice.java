package Controlador;

import Modelo.Eetakemon;
import Modelo.Usuario;

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

    //foto eetakemon
    @POST
    @Path("/EetakemonImage")
    @Consumes(MediaType.APPLICATION_JSON)
    public void Image(Eetakemon eetakemon) {
        String base64Image = eetakemon.getFoto().split(",")[1];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        File imageFile = new File("C:\\Users\\Aleix\\IdeaProjects\\EetakemonConsola\\WEB\\images\\" + eetakemon.getNombre() + ".png");

        System.out.println(imageFile);
        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            ImageIO.write(bufferedImage, "png", imageFile);
        }
        catch(Exception e){
            e.printStackTrace();
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
        String e,c;
        int theid;
        e=usuario.getEmail();
        c=usuario.getContrasena();
        a=usuario.login(e,c);
        Usuario u = new Usuario();
        u.select(usuario.getEmail());
        if (a) {
            System.out.println(u.getId());
            return Response.status(201).entity(u.getId()).build();
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
        List<Usuario> list;
        list = new Usuario().findAll();
        GenericEntity< List <Usuario> > entity;
        entity  = new GenericEntity< List< Usuario > >( list ) { };
        if (!list.isEmpty()) {
            System.out.println("Lista a enviar" + entity);
            return Response.status(201).entity(entity).build();
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