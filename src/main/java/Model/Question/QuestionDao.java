package Model.Question;

import Model.DAO.DAO;
import Model.Eetakemon.Eetakemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class QuestionDao extends DAO {
    protected void insertQuestion()throws Exception{

        try {
            insert();
        }catch (Exception ex){
            throw new Exception();
        }
    }
    protected boolean updatetQuestion()throws Exception{

        try {
            return update();
        }        catch (Exception ex){
            throw new Exception();
        }
    }
    protected void selectQuestionById(int id)throws Exception{

        try {
            select(id);
        }catch (Exception ex){
            throw new Exception();
        }
    }
    protected void deleteQuestion()throws Exception{

        try {
            delete();
        }catch (Exception ex){
            throw new Exception();
        }
    }
    protected List findAllQuestions()throws Exception{
        try {
            return findAll();
        }catch (Exception ex){
            throw new Exception();
        }
    }

    protected boolean checkQuestionExistent(String question)throws Exception{
        try {
            return checkExistent("question", question);
        }catch (Exception ex){
            throw new Exception();
        }
    }

    protected List getByType(String tipo) throws Exception{
        Connection con = getConnection();
        List<Question> list= new ArrayList<>();
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE tipo='");
        query.append(tipo);
        query.append("';");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: Get by tipo statement: "+ps.toString());
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            while (rs.next()) {
                Class classToLoad = this.getClass();
                Question q = new Question();
                setClassFields(rs, resultSetMetaData, q);
                list.add(q);
            }
            ps.close();
            con.close();

        } catch (Exception ex){
            ex.printStackTrace();
            throw new Exception();
        }

        return list;
    }
}
