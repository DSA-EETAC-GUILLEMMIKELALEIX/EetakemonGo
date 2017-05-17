package Modelo.Relation;

import Modelo.DAO.DAO;

import java.sql.*;
import java.util.List;

public class RelationDAO extends DAO {
    protected void insertRelation(){
        insert();
    }
    protected boolean updateRelation(){
        return update();
    }
    protected void selectRelationById(int id){
        select(id);
    }
    protected void deleteRelation(){
        delete();
    }
    protected List findAllRelation(){
        return findAll();
    }

    protected boolean checkRelationExistent(int idUser, int idEetakemon){
        boolean existent = false;
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE idUser='" + idUser + "' AND idEetakemon='" + idEetakemon + "';");
        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                logger.info("INFO: Eetakemon ya capturado");
                existent = false;

            } else {
                logger.info("INFO: Nuevo Eetakemon capturado");
                existent = true;
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existent;
    }

    protected void selectRelation(int idUser, int idEetakemon){
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE idUser='" + idUser + "' AND idEetakemon='" + idEetakemon + "';");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: Select relation: " + ps.toString());
            ;
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                setClassFields(rs, rsmd, this);
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

