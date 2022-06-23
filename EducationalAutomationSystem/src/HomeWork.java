import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeWork {
    private Class classForHomeWork;
    private String name;
    private float grade;
    private ArrayList<Question> chosenQuestionForHomeWork;
    private LocalDateTime timeOfStartTheHomeWork;
    private LocalDateTime timeOfEndTheHomeWork;
    private HashMap<Student, Float> totalScoreForHomework = new HashMap<>();


    public HomeWork(Class classForHomeWork, String name, float grade,ArrayList<Question> chosenQuestionForHomeWork,
                    LocalDateTime timeOfStartTheHomeWork, LocalDateTime timeOfEndTheHomeWork) {
        this.classForHomeWork = classForHomeWork;
        this.name = name;
        this.grade = grade;
        this.chosenQuestionForHomeWork = chosenQuestionForHomeWork;
        this.timeOfStartTheHomeWork = timeOfStartTheHomeWork;
        this.timeOfEndTheHomeWork = timeOfEndTheHomeWork;
    }

    public Class getClassForExam() {
        return classForHomeWork;
    }
    public String getName() {
        return name;
    }
    public float getGrade() {
        return grade;
    }
    public ArrayList<Question> getChosenQuestionForHomeWork() {
        return chosenQuestionForHomeWork;
    }
    public LocalDateTime getTimeOfStartTheHomeWork() {
        return timeOfStartTheHomeWork;
    }
    public LocalDateTime getTimeOfEndTheHomeWork() {
        return timeOfEndTheHomeWork;
    }
    public HashMap<Student, Float> getTotalScoreForHomework() {
        return totalScoreForHomework;
    }

    public void theTotalScoreOfHomework(Student student){
        float finalScore = 0;
        for (Question question : this.getChosenQuestionForHomeWork()){
            for (Student studentWhichWeSearchFor : question.getScoreForEachQuestion().keySet()){
                if(studentWhichWeSearchFor.equals(student)){
                    finalScore += question.getScoreForEachQuestion().get(student);
                    break;
                }

            }

        }
        this.getTotalScoreForHomework().put(student, finalScore);
    }
    public void showAllStudentsScores(){
        for (Student student : this.getClassForExam().getStudentsOfThisClass()){
            theTotalScoreOfHomework(student);
        }
        StringBuilder allScores = new StringBuilder();
        int count = 1;
        for (Student student : this.getTotalScoreForHomework().keySet()){
            allScores.append(count + " - " + student.getFullName() + " ,Grade : " +
                    this.getTotalScoreForHomework().get(student) + "\n");
        }
        JOptionPane.showMessageDialog(null, "All Students' Scores :\n" +
                allScores);
    }
}
