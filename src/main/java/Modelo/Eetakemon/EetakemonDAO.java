package Modelo.Eetakemon;


import Modelo.DAO.DAO;

import java.sql.*;
import java.util.ArrayList;
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

    protected List getByType(String tipo){
        Connection con = getConnection();
        List<Eetakemon> list= new ArrayList<>();
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append("WHERE tipo='");
        query.append(tipo);
        query.append("';");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: Get by type statement: "+ps.toString());
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            while (rs.next()) {
                Class classToLoad = this.getClass();
                Eetakemon e = new Eetakemon();
                setClassFields(rs, resultSetMetaData, e);
                list.add(e);
            }
            ps.close();
            con.close();

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return list;
    }

}
