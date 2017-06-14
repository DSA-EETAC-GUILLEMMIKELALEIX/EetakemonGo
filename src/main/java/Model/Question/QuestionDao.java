package Model.Question;

import Model.DAO.DAO;
import Model.Eetakemon.Eetakemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class QuestionDao extends DAO {
    protected void insertQuestion(){
        insert();
    }
    protected boolean updatetQuestion(){
        return update();
    }
    protected void selectQuestionById(int id){
        select(id);
    }
    protected void deleteQuestion(){
        delete();
    }
    protected List findAllQuestions(){
        return findAll();
    }
    protected boolean checkQuestionExistent(String question){
        return checkExistent("question",question);
    }

    protected List getByType(String tipo){
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

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return list;
    }
}
