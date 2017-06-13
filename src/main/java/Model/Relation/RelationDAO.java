package Model.Relation;

import Model.DAO.DAO;

import java.sql.*;
import java.util.ArrayList;
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

    protected List getCaptured(int idUser)
    {
        List <Captured> list= new ArrayList<>();
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT eetakemon.id, eetakemon.nombre, relation.level, " +
                "eetakemon.foto FROM ");
        query.append("Eetakemon, Relation WHERE relation.idEetakemon=Eetakemon.id AND relation.idUser=");
        query.append(idUser);
        query.append(";");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());

            logger.info("INFO: Select captured: " + ps.toString());

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();



            while (rs.next()) {
                list.add(new Captured(rs.getInt(1),rs.getString(2),
                        rs.getInt(3),rs.getString(4)));
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
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

    protected void deletetRelationByUser(int idUser){
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("DELETE FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE idUser='" + idUser +"';");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: Delete relations by user id: " + ps.toString());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    protected void deleteRelationByEetakemon(int idEetakemon){
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("DELETE FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE idEetakemon='" + idEetakemon +"';");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: Delete relations by eetakemon id: " + ps.toString());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    protected String getNumCaptured(int idUser){
        String num="0";
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT COUNT(*) FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE idUser=");
        query.append(idUser);
        query.append(";");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: Get number of eetakemons captured: " + ps.toString());

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                num=rs.getString(1);
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

}

