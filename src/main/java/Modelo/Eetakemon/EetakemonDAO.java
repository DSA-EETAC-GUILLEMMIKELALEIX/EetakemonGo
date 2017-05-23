package Modelo.Eetakemon;


import Modelo.DAO.DAO;

import java.util.List;

public class EetakemonDAO extends DAO {
    protected void insertEetakemon(){
        insert();
    }
    protected boolean updatetEetakemon(){
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
    protected List typeEetakemons(){
     return typeof();
    }

}
