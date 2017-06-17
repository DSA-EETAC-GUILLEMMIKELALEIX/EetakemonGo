package Model.Question;

import Model.Exceptions.*;
import Model.Security.AuthenticationManager;
import Model.Security.Verification;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;

import javax.ws.rs.core.HttpHeaders;


public class QuestionManager {
    private final static Logger logger = Logger.getLogger(QuestionManager.class);//
    private AuthenticationManager authManager;

    public QuestionManager(){
        authManager= new AuthenticationManager();
    }


    //obtener pregunta por id
    public Question getQuestionById(HttpHeaders header, int id) throws UnauthorizedException , Exception{
       Question q = new Question();
        Verification v = new Verification();
       try {
           authManager.verify(header,v);
           q.selectQuestionById(id);
       }catch (UnauthorizedException ex) {
           throw new UnauthorizedException("Unauthorized: user is not authorized");

       }catch (Exception ex){
           throw new Exception();
       }
        return q;
    }

    //añadir pregunta
    public boolean addQuestion(HttpHeaders header,Question q) throws UnauthorizedException,
            NotSuchPrivilegeException, Exception{
        Boolean exist=false;
        Verification v = new Verification();
        try{
            authManager.verify(header,v);
            authManager.verifyAdmin(v);
            exist =q.checkQuestionExistent(q.getQuestion());
            if (!exist) {
                q.insertQuestion();
            }
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }catch (NotSuchPrivilegeException ex){
            throw new NotSuchPrivilegeException("Forbidden: User has not privileges");

        }catch (Exception ex){
            throw new Exception();
        }

        return exist;
    }

    //
    public Question deleteQuestion(HttpHeaders header, int id) throws UnauthorizedException,
            NotSuchPrivilegeException, Exception{
        Question e = new Question();
        Verification v = new Verification();
        try {
            authManager.verify(header,v);
            authManager.verifyAdmin(v);
            e.selectQuestionById(id);
            e.deleteQuestion();
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }catch (NotSuchPrivilegeException ex){
            throw new NotSuchPrivilegeException("Forbidden: User has not privileges");

        }catch (Exception ex){
            throw new Exception();
        }

        return e;
    }

    //listar Questions
    public List listAllQuestion(HttpHeaders header) throws UnauthorizedException, Exception{
        List<Question> list;
        Verification v = new Verification();

        try {
            authManager.verify(header, v);
            list = new Question().findAllQuestions();
        } catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        } catch (Exception ex) {
            throw new Exception();
        }

        return list;

    }

    //falta acabar
    public Question getQuestionByType(HttpHeaders header, String tipo)throws UnauthorizedException, Exception{
        List<Question> list;
        Question q = new Question();
        Verification v = new Verification();

        try {
            authManager.verify(header,v);
            list = new Question().getByType(tipo);
            Random rand = new Random();
            int a = list.size();
            int n = rand.nextInt(a);
            q=list.get(n);
            return q;
        }catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Unauthorized: user is not authorized");

        }catch (Exception ex){
            throw new Exception();
        }
    }
}
