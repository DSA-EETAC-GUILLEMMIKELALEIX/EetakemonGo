package Modelo.Relation;

import Modelo.DAO.DAO;

import java.util.List;

public class RelationDAO extends DAO {
    protected void insertRelation(){
        insert();
    }
    protected boolean updateRelation(){
        return update();
    }
    protected void selectEetakemonById(int id){
        select(id);
    }
    protected void deleteEetakemon(){
        delete();
    }
    protected List findAllEetakemons(){
        return findAll();
    }
    protected boolean checkEetakemonExistent(String nombre){
        return checkExistent("nombre",nombre);
    }

}

