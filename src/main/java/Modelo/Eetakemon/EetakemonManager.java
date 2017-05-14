package Modelo.Eetakemon;

import Modelo.TrippleDes;
import org.apache.log4j.Logger;

import java.util.List;


public class EetakemonManager {
    private TrippleDes td;
    private final static Logger logger = Logger.getLogger(EetakemonManager.class);//

    public EetakemonManager(){
        try{
            td=new TrippleDes();
        }
        catch (Exception e){

        }
    }

    public Eetakemon getEetakemonById(int id){
        Eetakemon e= new Eetakemon();
        e.selectEetakemonById(id);

        return e;
    }

    public boolean addEetakemon(Eetakemon e){
        Boolean exist=false;
        exist=e.checkEetakemonExistent(e.getNombre());
        if(!exist){
            e.insertEetakemon();
        }
        return exist;
    }

    public Eetakemon deleteEetakemon(int id){
        Eetakemon e = new Eetakemon();
        e.selectEetakemonById(id);
        e.deleteEetakemon();

        return e;
    }

    public List listAllEetakemon(){
        List<Eetakemon> list;
        list = new Eetakemon().findAllEetakemons();

        return list;
    }

}
