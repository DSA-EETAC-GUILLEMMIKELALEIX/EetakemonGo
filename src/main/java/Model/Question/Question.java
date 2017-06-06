package Model.Question;


public class Question extends QuestionDao{
    private int id;
    private String level;
    private String question;
    private int answer;

    public Question(){}

    public Question(int id, String level, String question, int answer){
        this.id=id;
        this.level=level;
        this.question=question;
        this.answer=answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
