package Model.Eetakemon;

import Model.Exceptions.NotSuchPrivilegeException;
import Model.Exceptions.UnauthorizedException;
import Model.Relation.Relation;
import Model.Relation.RelationManager;
import Model.Security.AuthenticationManager;
import Model.Security.Verification;
import View.Main;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.ws.rs.core.HttpHeaders;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;


public class EetakemonManager {
    private final static Logger logger = Logger.getLogger(EetakemonManager.class);//
    private AuthenticationManager authManager;

    public EetakemonManager(){
        authManager=new AuthenticationManager();
    }


    public Eetakemon getEetakemonById(HttpHeaders header, int id) throws UnauthorizedException,
            Exception{
        Eetakemon e= new Eetakemon();
        Verification v = new Verification();
        try {
            authManager.verify(header,v);
            e.selectEetakemonById(id);
            setUrlImage(e);
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }catch (Exception ex){
            throw new Exception();
        }

        return e;
    }

    //añadir eetakemon
    public boolean addEetakemon(HttpHeaders header, Eetakemon e) throws UnauthorizedException, NotSuchPrivilegeException,
            Exception{
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

        }catch (Exception ex){
            throw new Exception();
        }
        return exist;
    }

    //borrar eetakemon
    public Eetakemon deleteEetakemon(HttpHeaders header, int id) throws UnauthorizedException, NotSuchPrivilegeException,
            Exception{
        Eetakemon e = new Eetakemon();
        Verification v = new Verification();
        RelationManager rm = new RelationManager();

        try {
            authManager.verify(header,v);
            authManager.verifyAdmin(v);
            rm.deleteRelationByEetakemon(id, header);
            e.selectEetakemonById(id);
            e.deleteEetakemon();
            //deleteImage(e);
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }catch (NotSuchPrivilegeException ex){
            throw new NotSuchPrivilegeException("Forbidden: User has not privileges");

        }catch (Exception ex){
            throw new Exception();
        }

        return e;
    }

    //listar eetakemons
    public List listAllEetakemon(HttpHeaders header) throws UnauthorizedException,
            Exception{
        List<Eetakemon> list;
        Verification v = new Verification();

        try {
            authManager.verify(header, v);
            list = new Eetakemon().findAllEetakemons();
            for(int i=0; i<list.size();i++){
                setUrlImage(list.get(i));
            }
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }catch (Exception ex){
            throw new Exception();
        }

        return list;
    }

    public String getNumEetakemons(HttpHeaders header)throws UnauthorizedException,
            Exception{
        Verification v = new Verification();
        try {
            authManager.verify(header, v);
            return new Eetakemon().getNumEetakemons();
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }catch (Exception ex){
            throw new Exception();
        }
    }

    public List<Eetakemon> getEetakemonByType(HttpHeaders header,String tipo) throws UnauthorizedException,
            Exception{
        List<Eetakemon> list;
        Eetakemon e = new Eetakemon();
        Verification v = new Verification();

        try {
            authManager.verify(header, v);
            list = new Eetakemon().getByType(tipo);

            for(int i=0; i<list.size();i++){
                setUrlImage(list.get(i));
            }
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }catch (Exception ex){
            throw new Exception();
        }

        return list;
    }

    public Eetakemon getOneByType(HttpHeaders header, String tipo) throws UnauthorizedException,
             Exception{
        Eetakemon e;
        List <Eetakemon> temp;
        try{
           temp= getEetakemonByType(header,tipo);
           Random rand = new Random();
           int i=temp.size();
           int j= rand.nextInt(i);
           e=temp.get(j);

        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }catch (Exception ex){
            throw new Exception();
        }

        return e;
    }


    /*Private methods*/
    private void saveImage(Eetakemon e)throws Exception{
        String base64Image = e.getFoto().split(",")[1];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        File imageFile = new File("/home/ea0/EetakemonGo/WEB/images/" + e.getNombre() + ".png");
        System.out.println("fuera: "+imageFile.getPath());
        try {
            System.out.println("dentro");
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            System.out.println(ImageIO.write(bufferedImage, "png", imageFile));
            e.setFoto("/images/"+e.getNombre().toLowerCase()+".png");
        }
        catch(Exception ex){
            ex.printStackTrace();
            throw new Exception();
        }

    }

    private void deleteImage(Eetakemon e)throws Exception{
        try {
            Files.deleteIfExists(Paths.get("WEB\\images\\" + e.getNombre().toLowerCase() + ".png"));
        }catch(Exception ex){
            ex.printStackTrace();
            throw new Exception();
        }
    }


    private void setUrlImage(Eetakemon e){
        String temp;
        String imgUrl;

        temp=e.getFoto();
        imgUrl= Main.BASE_URI+temp;
        e.setFoto(imgUrl);
    }
}
