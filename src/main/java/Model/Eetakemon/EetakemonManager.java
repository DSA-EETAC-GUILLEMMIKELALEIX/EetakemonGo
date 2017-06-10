package Model.Eetakemon;

import Model.Exceptions.NotSuchPrivilegeException;
import Model.Exceptions.UnauthorizedException;
import Model.Relation.Relation;
import Model.Relation.RelationManager;
import Model.Security.AuthenticationManager;
import Model.Security.Verification;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.ws.rs.core.HttpHeaders;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import java.util.Random;


public class EetakemonManager {
    private final static Logger logger = Logger.getLogger(EetakemonManager.class);//
    private AuthenticationManager authManager;

    public EetakemonManager(){
        authManager=new AuthenticationManager();
    }


    public Eetakemon getEetakemonById(HttpHeaders header, int id) throws UnauthorizedException{
        Eetakemon e= new Eetakemon();
        Verification v = new Verification();
        try {
            authManager.verify(header,v);
            e.selectEetakemonById(id);
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }

        return e;
    }

    //añadir eetakemon
    public boolean addEetakemon(HttpHeaders header, Eetakemon e) throws UnauthorizedException, NotSuchPrivilegeException{
        Boolean exist=false;
        Verification v = new Verification();
        try {
            authManager.verify(header,v);
            authManager.verifyAdmin(v);
            exist = e.checkEetakemonExistent(e.getNombre());
            if (!exist) {
                saveImage(e);
                e.insertEetakemon();
            }
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }catch (NotSuchPrivilegeException ex){
            throw new NotSuchPrivilegeException("Forbidden: User has not privileges");

        }
        return exist;
    }

    //corregir da error al borrar
    public Eetakemon deleteEetakemon(HttpHeaders header, int id) throws UnauthorizedException, NotSuchPrivilegeException{
        Eetakemon e = new Eetakemon();
        Verification v = new Verification();
        RelationManager rm = new RelationManager();

        try {
            authManager.verify(header,v);
            authManager.verifyAdmin(v);
            rm.deleteRelationByEetakemon(id, header);
            e.selectEetakemonById(id);
            e.deleteEetakemon();
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }catch (NotSuchPrivilegeException ex){
            throw new NotSuchPrivilegeException("Forbidden: User has not privileges");

        }

        return e;
    }

    //listar eetakemons
    public List listAllEetakemon(HttpHeaders header) throws UnauthorizedException{
        List<Eetakemon> list;
        Verification v = new Verification();

        try {
            authManager.verify(header, v);
            list = new Eetakemon().findAllEetakemons();
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }

        return list;
    }

    //falta acabar
    public Eetakemon getEetakemonByTypeeee(HttpHeaders header,String tipo) throws UnauthorizedException{
        List<Eetakemon> list;
        Eetakemon e = new Eetakemon();
        Verification v = new Verification();
        try {
            authManager.verify(header, v);
            list = new Eetakemon().getByType(tipo);
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }

        return e;
    }

    //falta acabar
    public Eetakemon getEetakemonByType(String tipo){
        List<Eetakemon> list;
        Eetakemon e = new Eetakemon();
        System.out.println("tipo: "+tipo);

        list = new Eetakemon().getByType(tipo);


        Random rand = new Random();
        int a = list.size();
        System.out.println("AAAAA: "+a);
        int n = rand.nextInt(a);
        e=list.get(n);
        System.out.println("ddddd:" + e);
        return e;
    }

    public List listAllEetakemon(){
        List<Eetakemon> list;
        list = new Eetakemon().findAllEetakemons();
        return list;
    }



    private void saveImage(Eetakemon e){
        String base64Image = e.getFoto().split(",")[1];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        File imageFile = new File("WEB\\images\\" + e.getNombre() + ".png");
        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            ImageIO.write(bufferedImage, "png", imageFile);
            e.setFoto("http://localhost:8081/images/"+e.getNombre()+".png");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
