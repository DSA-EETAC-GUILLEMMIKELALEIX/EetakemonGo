package Controlador;

import Modelo.Usuario;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDAO extends DAO{
    private static int lastId=-1;


    public int getLastId(){
        if (lastId==-1){
            Connection con = getConnection();
            StringBuffer query = new StringBuffer("SELECT MAX(id) FROM ");
            query.append(this.getClass().getSimpleName());
            query.append(";");
            try {
                PreparedStatement ps = con.prepareStatement(query.toString());
                System.out.println(ps.toString());
                ResultSet rs=ps.executeQuery();
                rs.next();
                lastId=rs.getInt(1)+1;
                ps.close();
                con.close();
            } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
                System.out.println("Error");
            }
            catch (SQLException e) {e.printStackTrace();}
            catch (NullPointerException e){e.printStackTrace();}
        }
        return lastId;
    }

    private void setLastId(int lastId) {
        UsuarioDAO.lastId = lastId;
    }

    public void crear(){
        Method method;
        try {
            Class[] arguments = new Class[1];
            arguments[0] = int.class;
            method = this.getClass().getMethod(getSetterName("id"),arguments);
            //method = object.getClass().getMethod(getSetterName(name));
            method.invoke(this, getLastId());
        } catch (NoSuchMethodException e) {e.printStackTrace();}
        catch(InvocationTargetException e){e.printStackTrace();}
        catch (IllegalAccessException e){e.printStackTrace();}

        insert();
        setLastId(getLastId()+1);
    }

    public void actualizar(){
        update();
    }

    public void borrar(){
        delete();
    }

    public void buscarPorId(int id){
        select(id);
    }

    public String Loguearse(Usuario usuario)
    {
        login(usuario.getNombre(),usuario.getContrasena());
        String a = "Logueado";
        return a;

    }

}
