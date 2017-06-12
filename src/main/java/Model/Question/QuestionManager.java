package Model.Question;

import Model.Exceptions.*;
import Model.Security.AuthenticationManager;
import Model.Security.Verification;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.core.HttpHeaders;

/**
 * Created by usuario on 06/06/2017.
 */
public class QuestionManager {
    private final static Logger logger = Logger.getLogger(QuestionManager.class);//
    private AuthenticationManager authManager;


    //obtener pregunta por id
    public Question getEetakemonById(HttpHeaders header, int id) throws UnauthorizedException {
       Question q = new Question();
        q.selectQuestionById(id);
        return q;
    }

    //añadir pregunta
    public boolean addQuestion(HttpHeaders header,Question q) throws UnauthorizedException, NotSuchPrivilegeException {
        Boolean exist=false;
        Verification v = new Verification();
        exist =q.checkQuestionExistent(q.getQuestion());
        if (!exist) {
            q.insertQuestion();
        }
        return exist;
    }

    //
    public Question deleteQuestion(HttpHeaders header, int id) throws UnauthorizedException, NotSuchPrivilegeException{
        Question e = new Question();
        Verification v = new Verification();

        e.selectQuestionById(id);
        e.deleteQuestion();
        /*try {
            authManager.verify(header,v);
            authManager.verifyAdmin(v);
            e.selectQuestionById(id);
            e.deleteQuestion();
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }catch (NotSuchPrivilegeException ex){
            throw new NotSuchPrivilegeException("Forbidden: User has not privileges");

        }*/

        return e;
    }

    //listar Questions
    public List listAllQuestion(HttpHeaders header) throws UnauthorizedException{
        List<Question> list;
        Verification v = new Verification();

        /*try {
            authManager.verify(header, v);
            list = new Question().findAllQuestions();
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }*/
        list = new Question().findAllQuestions();

        return list;
    }

    //falta acabar
    public Question getQuestionByTipo(String Tipo){
        List<Question> list;
        Question q = new Question();
        /*Verification v = new Verification();

        try {
            authManager.verify(header, v);
            list = new Question().getByType(tipo);
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }*/
        list = new Question().getByLevel(Tipo);

        Random rand = new Random();
        int a = list.size();
        int n = rand.nextInt(a);
        q=list.get(n);
        return q;
    }
}
