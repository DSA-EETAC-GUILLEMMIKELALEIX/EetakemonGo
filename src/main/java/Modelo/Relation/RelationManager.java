package Modelo.Relation;

import Modelo.Exceptions.NotSuchPrivilegeException;
import Modelo.Exceptions.UnauthorizedException;
import Modelo.Security.AuthenticationManager;
import Modelo.Security.Verification;
import Modelo.Security.TrippleDes;
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
    private final static Logger logger = Logger.getLogger(Modelo.Relation.RelationManager.class);//


    public Relation getRelationById(int id, HttpHeaders header)throws UnauthorizedException{
        Verification v = new Verification();
        Relation e= new Relation();
        try {
            authManager.verify(header, v);
            e.selectRelationById(id);
        }
    catch (UnauthorizedException ex) {
        throw new UnauthorizedException("Unauthorized: user is not authorized");
    }
        return e;
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
}
