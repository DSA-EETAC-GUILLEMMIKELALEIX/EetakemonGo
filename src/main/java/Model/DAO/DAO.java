package Model.DAO;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DAO {
    protected final static Logger logger = Logger.getLogger(DAO.class);//
    private static DAO dao;

    protected static DAO getDao() {
        if (dao == null) {dao = new DAO();}
        return dao;
    }

    //obtener la conexión con la base de datos
    protected Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Proyecto", "root", "mysql");
            logger.info("INFO: conexión creada");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    //insertar en la base de datos
    protected void insert() throws  Exception{
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("INSERT INTO ");
        query.append(this.getClass().getSimpleName());
        query.append(" (");

        Field[] attributes = this.getClass().getDeclaredFields();

        for(int i =1; i<attributes.length;i++){
            query.append(attributes[i].getName());
            query.append(",");
        }

        query.deleteCharAt(query.length() - 1);
        query.append(") VALUES (");

        for(int i =1; i<attributes.length;i++){
            query.append("?,");
        }

        query.deleteCharAt(query.length() - 1);
        query.append(");");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            addFieldsToQuery(ps);
            logger.info("INFO: Insert statement: "+ps.toString());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
            logger.info("ALERT: Objeto ya existente");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }


    //actualizar base de datos
    protected Boolean update()throws  Exception {
        Boolean a = false;
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("UPDATE ");
        query.append(this.getClass().getSimpleName());
        query.append(" SET ");
        Field[] attributes = this.getClass().getDeclaredFields();

        for(int i =1; i<attributes.length;i++){
            query.append(attributes[i].getName());
            query.append("=?,");
        }

        query.deleteCharAt(query.length() - 1);
        query.append(" WHERE id=");
        query.append(getPrimaryKey());
        query.append(";");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            addFieldsToQuery(ps);
            ps.executeUpdate();
            logger.info("INFO: Update statement: "+ps.toString());
            a=true;
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        return a;
    }

    //buscar por id en la base de datos
    protected void select(int id) throws  Exception{
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE id=" + id);
        query.append(";");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: Select  by id statement: "+ps.toString());;
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                setClassFields(rs, rsmd, this);
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    //eliminar de la base de datos
    protected void delete() throws  Exception{
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("DELETE FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE id=");
        query.append(getPrimaryKey() + ";");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            ps.executeUpdate();
            logger.info("INFO: Delete statement: "+ps.toString());
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    //seleccionar la tabla de una clase de la base de datos
    protected List findAll() throws  Exception{
        Connection con = getConnection();
        List<Object> list= new ArrayList<>();
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(";");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: List statement: "+ps.toString());
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            while (rs.next()) {
                Class classToLoad = this.getClass();
                Object newObject = classToLoad.newInstance();
                setClassFields(rs, resultSetMetaData, newObject);
                list.add(newObject);
            }
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }

        return list;
    }

    //validar Registro
    protected boolean checkExistent(String field, String value) throws  Exception{
        boolean existent=false;
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE "+field+" ='" + value+"';");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                logger.info("INFO: Objeto ya existente: "+value);
                existent=true;
            }
            else{
                logger.info("INFO: Objeto no existente: "+value);
                existent=false;
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        return existent;
    }

    //asigna los valores obtenidos de la consulta SELECT a los atributos de la clase
    protected void setClassFields(ResultSet rs, ResultSetMetaData rsmd, Object o) {
        try {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String columnType = rsmd.getColumnTypeName(i);
                String columnName = rsmd.getColumnLabel(i);

                if (columnType.equals("VARCHAR")) {//si el valor de la columna es de tipo VARCHAR
                    String resultString = rs.getString(i);
                    setStringField(resultString, columnName, o);
                } else if (columnType.equals("INT")) {//si el valor de la columna es de tipo INT
                    int resultInt = rs.getInt(i);
                    setIntField(resultInt, columnName, o);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected int getPrimaryKey() {
        int pk = -1;
        //Field[] attributes = this.getClass().getDeclaredFields();
        Field f = this.getClass().getDeclaredFields()[0];
        Method method;

        try {
            method = this.getClass().getMethod(getGetterName(f.getName()));
            Object object = method.invoke(this);
            pk = Integer.parseInt(object.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return pk;
    }


    /*-------PRIVATE  METHODS---------*/

    //inserta el valor del resultset en el atributo correspondiente String
    private void setStringField(String result, String name, Object object) {
        Method method;
        try {
            method = object.getClass().getMethod(getSetterName(name), result.getClass());//obtiene el método set
            method.invoke(object, result);//invoca el método set
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //inserta el valor del resultset en el atributo correspondiente int
    private void setIntField(int result, String name, Object object) {
        Method method;
        try {
            Class[] arguments = new Class[1];
            arguments[0] = int.class;
            method = object.getClass().getMethod(getSetterName(name), arguments);
            method.invoke(object, result);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //obtiene el nombre del método set de un atributo
    private String getSetterName(String fieldName) {
        StringBuilder setterName = new StringBuilder("set");
        setterName.append(capitalizeWord(fieldName));
        return setterName.toString();
    }

    //obtiene el nombre del método get
    private String getGetterName(String fieldName) {
        StringBuilder getterName = new StringBuilder("get");
        getterName.append(capitalizeWord(fieldName));
        return getterName.toString();
    }

    //pone la primera letra de una palabra en mayúscula
    private String capitalizeWord(String word) {
        String capitalizedFieldName;
        capitalizedFieldName = word.substring(0, 1).toUpperCase() + word.substring(1);
        return capitalizedFieldName;
    }

    //sustituir interrogates por valores de los campos de la clasel
    private void addFieldsToQuery(PreparedStatement ps) {
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i=1;i<fields.length;i++) {
            try {
                Method method = this.getClass().getMethod(getGetterName(fields[i].getName()));
                Object object = method.invoke(this);
                ps.setObject(i, object);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
