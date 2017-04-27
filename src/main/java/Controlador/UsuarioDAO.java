package Controlador;

import Modelo.Usuario;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDAO extends DAO{

    protected boolean login(String nombre, String password) {
        boolean logeado=false;
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT nombre,contrasena FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE nombre='" + nombre + "' AND contrasena='" + password+"';");
        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();


            if(!rs.next()){
                logger.info("INFO: No logeado: "+nombre);
                logeado=false;

            }else{
                logger.info("INFO: Logeado: "+nombre);
                logeado=true;
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logeado;
    }
}
