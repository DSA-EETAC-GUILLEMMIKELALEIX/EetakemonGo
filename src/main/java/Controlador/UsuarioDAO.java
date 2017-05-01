package Controlador;

import Modelo.Usuario;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;


public class UsuarioDAO extends DAO{

    protected boolean login(String email, String password) {
        boolean logeado=false;
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT nombre,contrasena FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE email='" + email + "' AND contrasena='" + password+"';");
        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();


            if(!rs.next()){
                logger.info("INFO: No logeado: "+email);
                logeado=false;

            }else{
                logger.info("INFO: Logeado: "+email);
                logeado=true;
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logeado;
    }
    //buscar por email en la base de datos
    public void select(String email) {
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE email='");
        query.append(email);
        query.append("';");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: Select by email  statement: "+ps.toString());;
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
