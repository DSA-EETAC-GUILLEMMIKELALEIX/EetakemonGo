package Modelo.Relation;


import java.util.List;

public class RelationManager {

    public List listAllRelation(){
        List<Relation> list;
        list = new Relation().findAllRelation();

        return list;
    }
}
