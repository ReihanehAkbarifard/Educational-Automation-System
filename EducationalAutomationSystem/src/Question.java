import java.util.HashMap;

public class Question {
    private String question;
    private float score;
    private HashMap<Student, String> answer = new HashMap<>();
    private HashMap<Student, Float> scoreForEachQuestion = new HashMap<>();
    private String answerForExam;
    private float scoreForExam;

    public Question(String question, float score) {
        this.question = question;
        this.score = score;
    }

    public String getQuestion() {
        return question;
    }
    public float getScore() {
        return score;
    }
    public HashMap<Student, String> getAnswer() {
        return answer;
    }
    public HashMap<Student, Float> getScoreForEachQuestion() {
        return scoreForEachQuestion;
    }
    public String getAnswerForExam() {
        return answerForExam;
    }
    public void setAnswerForExam(String answerForExam) {
        this.answerForExam = answerForExam;
    }
    public void setScore(float score) {
        this.score = score;
    }
    public void setScoreForExam(Float scoreForExam) {
        this.scoreForExam = scoreForExam;
    }
}
