import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Exam {
    private Class classForExam;
    private String name;
    private float grade;
    private HashMap<Student, ArrayList<Question>> chosenQuestion;
    private LocalDateTime timeOfStartTheExam;
    private LocalDateTime timeOfEndTheExam;
    private HashMap<Student, Float> totalScoreForExam = new HashMap<>();
        private boolean status = false;

    public Exam(Class classForExam, String name, float grade, HashMap<Student, ArrayList<Question>> chosenQuestion,
                LocalDateTime timeOfStartTheExam, LocalDateTime timeOfEndTheExam) {
        this.classForExam = classForExam;
        this.name = name;
        this.grade = grade;
        this.chosenQuestion = chosenQuestion;
        this.timeOfStartTheExam = timeOfStartTheExam;
        this.timeOfEndTheExam = timeOfEndTheExam;
    }

    public Class getClassForExam() {
        return classForExam;
    }
    public String getName() {
        return name;
    }
    public float getGrade() {
        return grade;
    }
    public HashMap<Student, ArrayList<Question>> getChosenQuestion() {
        return chosenQuestion;
    }
    public LocalDateTime getTimeOfStartTheExam() {
        return timeOfStartTheExam;
    }
    public LocalDateTime getTimeOfEndTheExam() {
        return timeOfEndTheExam;
    }
    public HashMap<Student, Float> getTotalScoreForExam() {
        return totalScoreForExam;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean isStatus() {
        return status;
    }

    public void showAllStudentsScoresOfExams(){
        StringBuilder allScores = new StringBuilder();
        int count = 1;
        for (Student student : this.getTotalScoreForExam().keySet()){
            allScores.append(count + " - " + student.getFullName() + " ,Grade : " +
                    this.getTotalScoreForExam().get(student) + "\n");
        }
        JOptionPane.showMessageDialog(null, "All Students' Scores :\n" +
                allScores);
    }
    public void enableTheExam(){
        this.setStatus(true);
        JOptionPane.showMessageDialog(null, "You enabled The Exam",
                "Enable The Exam", JOptionPane.INFORMATION_MESSAGE);
    }
}
