package Model.Question;


public class Question extends QuestionDao{
    private int id;
    private String tipo;
    private String question;
    private int answer;

    public Question(){}

    public Question(int id, String tipo, String question, int answer){
        this.id=id;
        this.tipo=tipo;
        this.question=question;
        this.answer=answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
