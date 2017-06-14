package Model.Relation;

import Model.Eetakemon.Eetakemon;
import Model.Eetakemon.EetakemonManager;
import Model.Exceptions.NotSuchPrivilegeException;
import Model.Exceptions.UnauthorizedException;
import Model.Security.AuthenticationManager;
import Model.Security.Verification;
import View.Main;
import org.apache.log4j.Logger;

import javax.ws.rs.core.HttpHeaders;
import java.util.List;


public class RelationManager {
    private AuthenticationManager authManager;
    private final static Logger logger = Logger.getLogger(Model.Relation.RelationManager.class);//

    public RelationManager(){
        authManager=new AuthenticationManager();
    }
    public List listAllRelation(HttpHeaders header)throws UnauthorizedException, NotSuchPrivilegeException{
        Verification v = new Verification();
        List<Relation> list;

        try {
            authManager.verify(header, v);
            authManager.verifyAdmin(v);

            list = new Relation().findAllRelation();
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }
        catch (NotSuchPrivilegeException ex){
            throw new NotSuchPrivilegeException("Forbidden: User has not privileges");

        }
        return list;
    }


    public Captured getRelationById(int id, HttpHeaders header)throws UnauthorizedException{
        Verification v = new Verification();
        Eetakemon e= new Eetakemon();
        EetakemonManager em = new EetakemonManager();
        Relation r= new Relation();
        Captured c;
        try {
            authManager.verify(header, v);
            r.selectRelationById(id);
           e=em.getEetakemonById(header, id);
           c= new Captured(r.getIdEetakemon(),e.getNombre(),r.getLevel(), e.getFoto());
        }
    catch (UnauthorizedException ex) {
        throw new UnauthorizedException("Unauthorized: user is not authorized");
    }
        return c;
    }

    public boolean addRelation(Relation r, HttpHeaders header) throws UnauthorizedException{
        Verification v = new Verification();
        Boolean exist=false;
        Eetakemon e;
        EetakemonManager em=new EetakemonManager();
        try {
            authManager.verify(header, v);
            e=em.getEetakemonById(header,r.getIdEetakemon());
            r.setLevel(e.getNivel());
            exist = r.checkRelationExistent(r.getIdUser(), r.getIdEetakemon());
            if (!exist) {
                r.insertRelation();
            } else {
                Relation temp = new Relation();
                temp.selectRelation(r.getIdUser(), r.getIdEetakemon());
                temp.setLevel(temp.getLevel() + r.getLevel());
                temp.updateRelation();
            }
        }
         catch (UnauthorizedException ex) {
                throw new UnauthorizedException("Unauthorized: user is not authorized");
            }

        return exist;
    }

    public Relation deleteRelation(int id, HttpHeaders header) throws UnauthorizedException, NotSuchPrivilegeException{
        Verification v = new Verification();
        Relation r = new Relation();
        try {
            authManager.verify(header, v);
            r.selectRelationById(id);
            authManager.verifyCorrectUser(v, r.getIdUser());
            r.selectRelationById(id);
            r.deleteRelation();
        }
        catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }catch (NotSuchPrivilegeException ex){
            throw new NotSuchPrivilegeException("Forbidden: User has not privileges");
        }

        return r;
    }

    public List getCaptured(int idUser, HttpHeaders header) throws UnauthorizedException{
        Verification v = new Verification();
        List<Captured> list;
        try {
            authManager.verify(header, v);
            list = new Relation().getCaptured(idUser);
            for(int i=0; i<list.size();i++){
                setUrlImage(list.get(i));
            }
        }
        catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }
        return list;
    }

    public void deleteRelationByUser(int idUser,HttpHeaders header) throws UnauthorizedException, NotSuchPrivilegeException{
        Verification v = new Verification();
        List<Relation> e;

        try {
            authManager.verify(header, v);
            authManager.verifyAdmin(v);
            new Relation().deletetRelationByUser(idUser);
        }
        catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }catch (NotSuchPrivilegeException ex){
            throw new NotSuchPrivilegeException("Forbidden: User has not privileges");
        }
    }

    public void deleteRelationByEetakemon(int idEetakemon,HttpHeaders header) throws UnauthorizedException, NotSuchPrivilegeException{
        Verification v = new Verification();
        List<Relation> e;
        Relation r=new Relation();

        try {
            authManager.verify(header, v);
            authManager.verifyAdmin(v);
            new Relation().deleteRelationByEetakemon(idEetakemon);
        }
        catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }catch (NotSuchPrivilegeException ex){
            throw new NotSuchPrivilegeException("Forbidden: User has not privileges");
        }
    }


    public String getNumCaptured(HttpHeaders header)throws UnauthorizedException{
        Verification v = new Verification();
        try {
            authManager.verify(header, v);
            return new Relation().getNumCaptured(v.getIdUser());
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }
    }





    public List listAllRelation(){

        List<Relation> list = new Relation().findAllRelation();

        return list;
    }


    /*private methods*/
    private void setUrlImage(Captured c){
        String temp;
        String imgUrl;

        temp=c.getFoto();
        imgUrl= Main.BASE_URI+temp;
        c.setFoto(imgUrl);
    }
}
