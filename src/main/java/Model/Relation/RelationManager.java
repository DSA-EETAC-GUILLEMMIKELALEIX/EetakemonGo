package Model.Relation;

import Model.Eetakemon.Eetakemon;
import Model.Eetakemon.EetakemonManager;
import Model.Exceptions.NotSuchPrivilegeException;
import Model.Exceptions.UnauthorizedException;
import Model.Security.AuthenticationManager;
import Model.Security.Verification;
import org.apache.log4j.Logger;

import javax.ws.rs.core.HttpHeaders;
import java.util.List;


public class RelationManager {
    private AuthenticationManager authManager;

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
    private final static Logger logger = Logger.getLogger(Model.Relation.RelationManager.class);//


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
           c= new Captured(r.getIdEetakemon(),e.getNombre(),r.getLevel());
        }
    catch (UnauthorizedException ex) {
        throw new UnauthorizedException("Unauthorized: user is not authorized");
    }
        return c;
    }

    public boolean addRelation(Relation e, HttpHeaders header) throws UnauthorizedException{
        Verification v = new Verification();
        Boolean exist=false;
        try {
            authManager.verify(header, v);

            exist = e.checkRelationExistent(e.getIdUser(), e.getIdEetakemon());
            if (!exist) {
                e.insertRelation();
            } else {
                Relation temp = new Relation();
                temp.selectRelation(e.getIdUser(), e.getIdEetakemon());
                temp.setLevel(temp.getLevel() + e.getLevel());
                temp.updateRelation();
            }
        }
         catch (UnauthorizedException ex) {
                throw new UnauthorizedException("Unauthorized: user is not authorized");
            }

        return exist;
    }

    public Relation deleteRelation(int id, HttpHeaders header) throws UnauthorizedException{
        Verification v = new Verification();
        Relation e = new Relation();
        try {
            authManager.verify(header, v);

            e.selectRelationById(id);
            e.deleteRelation();
        }
        catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }

        return e;
    }

    public List getCaptured(int idUser, HttpHeaders header) throws UnauthorizedException{
        Verification v = new Verification();
        List<Captured> list;
        try {
            authManager.verify(header, v);

            list = new Relation().getCaptured(idUser);
        }
        catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }
        return list;
    }

    public void deleteRelationByUser(int idUser,HttpHeaders header) throws UnauthorizedException{
        Verification v = new Verification();
        List<Relation> e;

        try {
            authManager.verify(header, v);

            new Relation().deletetRelationByUser(idUser);
        }
        catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }
    }

    public void deleteRelationByEetakemon(int idEetakemon,HttpHeaders header) throws UnauthorizedException{
        Verification v = new Verification();
        List<Relation> e;

        try {
            authManager.verify(header, v);

            new Relation().deleteRelationByEetakemon(idEetakemon);
        }
        catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");
        }
    }

    public List listAllRelation(){

        List<Relation> list = new Relation().findAllRelation();

        return list;
    }
}
