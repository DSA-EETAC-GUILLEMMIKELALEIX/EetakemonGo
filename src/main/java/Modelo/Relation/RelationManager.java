package Modelo.Relation;
import org.apache.log4j.Logger;

import java.util.List;


public class RelationManager {
    private final static Logger logger = Logger.getLogger(Modelo.Relation.RelationManager.class);//
    

    public Relation getRelationById(int id){
        Relation e= new Relation();
        e.selectRelationById(id);

        return e;
    }

    public boolean addRelation(Relation e){
        Boolean exist=false;
        exist=e.checkRelationExistent(e.getIdUser(),e.getIdEetakemon());
        if(!exist){
            e.insertRelation();
        }else{
            Relation temp= new Relation();
            temp.selectRelation(e.getIdUser(),e.getIdEetakemon());
            temp.setLevel(temp.getLevel()+e.getLevel());
            temp.updateRelation();
        }
        return exist;
    }

    public Relation deleteRelation(int id){
        Relation e = new Relation();
        e.selectRelationById(id);
        e.deleteRelation();

        return e;
    }

    public List listAllRelation(){
        List<Relation> list;
        list = new Relation().findAllRelations();

        return list;
    }

}
