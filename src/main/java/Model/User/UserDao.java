package Model.User;

import Model.DAO.DAO;

import java.sql.*;
import java.util.List;

public class UserDao extends DAO {

    protected void insertUser()throws Exception{
        try {
            insert();
        }catch (Exception ex){
            throw new Exception();
        }
    }
    protected boolean updatetUser()throws Exception{
        try {
            return update();
        }        catch (Exception ex){
                throw new Exception();
            }
    }
    protected void selectUserById(int id)throws Exception{

        try {
            select(id);
        }catch (Exception ex){
            throw new Exception();
        }
    }
    protected void deleteUser()throws Exception{
        try {
            delete();
        }catch (Exception ex){
            throw new Exception();
        }

    }
    protected List findAllUsers()throws Exception{
        try {
            return findAll();
        }catch (Exception ex){
            throw new Exception();
        }
    }
    protected boolean checkUserExistent(String email)throws Exception{
        try {
            return checkExistent("email", email);
        }catch (Exception ex){
            throw new Exception();
        }
    }

    protected boolean login(String email, String password) throws Exception{
        boolean logeado = false;
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT nombre,contrasena FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE email='" + email + "' AND contrasena='" + password + "';");
        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                logger.info("INFO: No logeado: " + email);
                logeado = false;

            } else {
                logger.info("INFO: Logeado: " + email);
                logeado = true;
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        return logeado;
    }

    //buscar por email en la base de datos
    protected void selectUserByMail(String email) throws Exception{
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE email='");
        query.append(email);
        query.append("';");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: Select by email  statement: " + ps.toString());

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                setClassFields(rs, rsmd, this);
            }
            ps.close();
            con.close();
        } catch (Exception ex){
            throw new Exception();
        }
    }

    protected void changeAdmin(int admin)throws Exception{
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("UPDATE ");
        query.append(this.getClass().getSimpleName());
        query.append(" SET Admin=");
        query.append(admin);

        query.append(" WHERE id=");
        query.append(this.getPrimaryKey());
        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            ps.executeUpdate();
            logger.info("INFO: Change admin statement: "+ps.toString());
            ps.close();
            con.close();
        } catch (Exception ex){
            throw new Exception();
        }
    }

    protected String getNumUsers() throws Exception{
        String num="0";
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT COUNT(*) FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(";");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: Get number of users: " + ps.toString());

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                num=rs.getString(1);
            }
            ps.close();
            con.close();
        } catch (Exception ex){
            throw new Exception();
        }

        return num;
    }
}
