package Model.Eetakemon;


import Model.DAO.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EetakemonDAO extends DAO {
    protected void insertEetakemon()throws Exception{

        try {
            insert();
        }catch (Exception ex){
            throw new Exception();
        }
    }
    protected boolean updatetEetakemon()throws Exception{
        try {
            return update();
        }        catch (Exception ex){
            throw new Exception();
        }
    }
    protected void selectEetakemonById(int id)throws Exception{

        try {
            select(id);
        }catch (Exception ex){
            throw new Exception();
        }
    }
    protected void deleteEetakemon()throws Exception{

        try {
            delete();
        }catch (Exception ex){
            throw new Exception();
        }
    }
    protected List findAllEetakemons()throws Exception{

        try {
            return findAll();
        }catch (Exception ex){
            throw new Exception();
        }
    }
    protected boolean checkEetakemonExistent(String nombre)throws Exception{

        try {
            return checkExistent("nombre",nombre);
        }catch (Exception ex){
            throw new Exception();
        }
    }

    protected List getByType(String tipo)throws Exception{
        Connection con = getConnection();
        List<Eetakemon> list= new ArrayList<>();
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE tipo='");
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

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }

        return list;
    }

    protected String getNumEetakemons() throws Exception{
        String num="0";
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT COUNT(*) FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(";");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: Get number of eetakemons: " + ps.toString());

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                num=rs.getString(1);
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }

        return num;
    }

}
