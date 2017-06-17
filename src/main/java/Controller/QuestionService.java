package Controller;

import Model.Eetakemon.Eetakemon;
import Model.Exceptions.UnauthorizedException;
import Model.Question.Question;
import Model.Question.QuestionManager;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Question")
@Singleton
public class QuestionService {
    private QuestionManager manager;

    public QuestionService() {
        manager= new QuestionManager();
    }


    //Pregunta
    @POST
    @Path("/Question")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestionTipo(@Context HttpHeaders header, Eetakemon eetak) {
        Question q = new Question(); //Poner funcion para la pregunta
        try {
            q = manager.getQuestionByType(header, eetak.getTipo());

            if (q.getQuestion() != null) {
                return Response.status(Response.Status.OK).entity(q).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No content ").build();
            }
        } catch (UnauthorizedException ex) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();//401
        }catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal error").build();//500
        }
    }

}
