import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;

public class Class {
    private Professor professor;
    private String name;
    private ArrayList<Student> studentsOfThisClass = new ArrayList<>();
    private int numberOfStudents = DataBase.getStudentsArrayList().size();
    private ArrayList<String> questionBank = new ArrayList<>();
    private ArrayList<String> references = new ArrayList<>();
    private ArrayList<String> announcements = new ArrayList<>();
    private ArrayList<Exam> allExams = new ArrayList<>();
    private ArrayList<HomeWork> allHomeWorks = new ArrayList<>();
    private HashMap<Enum, LocalTime> classSchedulingAndSection = new HashMap<>();
    private Duration periodOfClass ;



    public Class(Professor professor, String name, Duration periodOfClass){
        this.professor = professor;
        this.name = name;
        this.periodOfClass = periodOfClass;
    }


    public String getName() {
        return name;
    }
    public ArrayList<Student> getStudentsOfThisClass() {
        return studentsOfThisClass;
    }
    public ArrayList<String> getQuestionBank() {
        return questionBank;
    }
    public ArrayList<String> getReferences() {
        return references;
    }
    public ArrayList<String> getAnnouncements() {
        return announcements;
    }
    public ArrayList<Exam> getAllExams() {
        return allExams;
    }
    public ArrayList<HomeWork> getAllHomeWorks() {
        return allHomeWorks;
    }
    public HashMap<Enum, LocalTime> getClassSchedulingAndSection() {
        return classSchedulingAndSection;
    }
    public Duration getPeriodOfClass() {
        return periodOfClass;
    }

}
