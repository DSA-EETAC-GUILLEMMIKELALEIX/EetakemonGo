package Model.Eetakemon;

import Model.Exceptions.NotSuchPrivilegeException;
import Model.Exceptions.UnauthorizedException;
import Model.Security.AuthenticationManager;
import Model.Security.Verification;
import org.apache.log4j.Logger;

import javax.ws.rs.core.HttpHeaders;
import java.util.List;


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

        try {
            authManager.verify(header,v);
            authManager.verifyAdmin(v);
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
    public Eetakemon getEetakemonByType(HttpHeaders header,String tipo) throws UnauthorizedException{
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

}
