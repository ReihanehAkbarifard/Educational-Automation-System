import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Student extends User {

    private String studentNumber;
    private float gradeAverage;
    private ArrayList<Class> studentsClass = new ArrayList<>();


    public Student(String studentName, String field,String studentNumber, String passWord) {
        setFullName(studentName);
        setField(field);
        this.studentNumber = studentNumber;
        setPassWord(passWord);

    }

    public String getStudentNumber() {
        return studentNumber;
    }
    public float getGradeAverage() {
        return gradeAverage;
    }
    public ArrayList<Class> getStudentsClass() {
        return studentsClass;
    }

    public static Student studentsLogIn(){

        String studentNumber = JOptionPane.showInputDialog(null, "Please Enter Your Student Number :\n",
                "Log-In Page", JOptionPane.QUESTION_MESSAGE);
        Student currentStudent = DataBase.getStudentFromStudentNumber(studentNumber);
        if (DataBase.getStudentsArrayList().contains(currentStudent)){
            boolean isTruePassWord = true;
            String passWord;
            while (isTruePassWord){
                passWord = JOptionPane.showInputDialog(null, "Please Enter Your PassWord :\n",
                        "Sign-Up Page", JOptionPane.QUESTION_MESSAGE);
                if(passWord.equals(currentStudent.getPassWord())){
                    JOptionPane.showMessageDialog(null ,"Welcome Dear " +
                                    currentStudent.getFullName() ,"Welcome"
                            ,JOptionPane.INFORMATION_MESSAGE);
                    return currentStudent;
                }
                else {
                    JOptionPane.showMessageDialog(null ,"Your PassWord Is Incorrect" +
                                    ", Try Again","Welcome"
                            ,JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
        else {
            JOptionPane.showMessageDialog(null, "No Such Person" +
                    " Found!", "Log-In Page", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        return null;

    }

    public int showAllClasses(){
        StringBuilder allClasses = new StringBuilder();
        int count = 1;
        for(Class eachClass : this.getAllClasses()){
            allClasses.append(count + " : ").append(eachClass.getName());
            count++;
        }
        int chosenOpt = Integer.parseInt(JOptionPane.showInputDialog(null, "These Are All Of Your Classes :\nPlease " +
                "Enter A Number To Show More.\n" +
                "0. Back\n" + allClasses , "All Classes" ,JOptionPane.QUESTION_MESSAGE));
        return chosenOpt;

    }
    public Class goToTheClass(int index, Student currentStudent){
        return currentStudent.getAllClasses().get(index);
    }
    public Class searchForAClass(){
        String nameOfClass = JOptionPane.showInputDialog(null, "Enter The Name Of" +
                " Class You Are Searching For :\n", "Search For A Class", JOptionPane.QUESTION_MESSAGE);
        if (DataBase.getClassesArrayList().contains(DataBase.getClassFromClassName(nameOfClass))){
            return DataBase.getClassFromClassName(nameOfClass);
        }
        else {
            JOptionPane.showMessageDialog(null, "No Such Class Was Found",
                    "Search For A Class", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

    }
    public Boolean isOverlapped(Student student, Class searchedClass){
        for (Class eachClass : student.getAllClasses()){
            for (Enum eachSection : eachClass.getClassSchedulingAndSection().keySet()){
                for (Enum eachSectionOfSearchClass : searchedClass.getClassSchedulingAndSection().keySet()){

                    if(eachSection.equals(eachSectionOfSearchClass) &&
                            eachClass.getClassSchedulingAndSection().get(eachSection).isBefore(searchedClass.getClassSchedulingAndSection().
                                    get(eachSectionOfSearchClass)) && eachClass.getClassSchedulingAndSection().
                            get(eachSection).plusMinutes(eachClass.getPeriodOfClass().toMinutes()).isAfter(searchedClass.getClassSchedulingAndSection().
                            get(eachSectionOfSearchClass))){

                        return true;

                    }
                    else if (eachSection.equals(eachSectionOfSearchClass) && eachClass.getClassSchedulingAndSection().get(eachSection).
                            equals(searchedClass.getClassSchedulingAndSection().
                            get(eachSectionOfSearchClass))){

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void joinClass(Class searchedClass, Student student){
        if(isOverlapped(student, searchedClass)){
            JOptionPane.showMessageDialog(null, "You Can't Join This Class" +
                    " Because You Are Not Able To Take Overlapped Sections"
                    ,"Join Class", JOptionPane.INFORMATION_MESSAGE);
        }
        else {

            student.getStudentsClass().add(searchedClass);
            searchedClass.getStudentsOfThisClass().add(student);
            JOptionPane.showMessageDialog(null, "You Joined This Class Successfully" ,
                    "Join Class", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void seeReferences(Class chosenClass){
        if(!chosenClass.getReferences().isEmpty()){
            StringBuilder allReferences = new StringBuilder();
            int count = 1;
            for (String reference : chosenClass.getReferences()){
                allReferences.append(count + " : " + reference + "\n");
                count++;
            }
            JOptionPane.showMessageDialog(null, "All References Of " + chosenClass.getName() +
                    " Class :\n" + allReferences, "See References",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "No Reference To See",
                    "See References",JOptionPane.INFORMATION_MESSAGE);
        }

    }


    public void answerToQuestionsOfHomeWork(HomeWork chosenHomeWork){
        int count = 1;
        LocalDateTime datetimeNow = LocalDateTime.now();
        if (datetimeNow.isAfter(chosenHomeWork.getTimeOfEndTheHomeWork())) {
            JOptionPane.showMessageDialog(null, "The Time Of HomeWork is Over",
                    "answer To Questions Of HomeWork", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            for (Question question : chosenHomeWork.getChosenQuestionForHomeWork()){
                String answer = JOptionPane.showInputDialog(null,count + " - " +
                        question.getQuestion(),"Answer The Question of Homework",JOptionPane.QUESTION_MESSAGE);
                question.getAnswer().put(this, answer);
                count++;
            }
            JOptionPane.showMessageDialog(null, "You Answered Question Successfully",
                    "Answer The Question of Homework", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    public void seeTheScoreOfAHomework(HomeWork chosenHomeWork){
        float totalScore;

        if (chosenHomeWork.getTotalScoreForHomework().keySet().contains(this)){
            totalScore = chosenHomeWork.getTotalScoreForHomework().get(this);
            JOptionPane.showMessageDialog(null, "Your Score Of This Homework is :" +
                    totalScore + " From " + chosenHomeWork.getTotalScoreForHomework() ,
                    "see The Score Of A Homework", JOptionPane.INFORMATION_MESSAGE);

        }
        else {
            JOptionPane.showMessageDialog(null, "This Homework Doesn't " +
                    "Scored Yet", "see The Score Of A Homework", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void seeExam(Exam chosenExam){
        if(chosenExam.isStatus()) {
            LocalDateTime datetimeNow = LocalDateTime.now();
            if (datetimeNow.isAfter(chosenExam.getTimeOfEndTheExam())) {
                JOptionPane.showMessageDialog(null, "The Time Of Exam is Over",
                        "See Exam", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                StringBuilder allQuestionOfThisExam = new StringBuilder();
                int count = 1;
                Student currentStudent = findStudentInExam(chosenExam, this);

                for (Question question : chosenExam.getChosenQuestion().get(currentStudent)) {
                    allQuestionOfThisExam.append(count + " : " + question.getQuestion() + "\n");
                    count++;
                }
                JOptionPane.showInputDialog(null, "All Questions Of " + chosenExam.getName() +
                                "Exam :\n" + allQuestionOfThisExam,
                        "See Exam's Question", JOptionPane.QUESTION_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null ,"The Exam Is Not" +
                    "Available Yet", "See Exam", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public Student findStudentInExam(Exam chosenExam, Student currentStudent){

        for (Student student : chosenExam.getChosenQuestion().keySet()){
            if (student.equals(currentStudent))
                 return student;
        }
        return null;
    }
    public void answerTheQuestionOfExam(Exam chosenExam){
        LocalDateTime datetimeNow = LocalDateTime.now();
        if(chosenExam.isStatus()){
            if(datetimeNow.isAfter(chosenExam.getTimeOfEndTheExam())){
                JOptionPane.showMessageDialog(null,"The Time Of Exam is Over",
                        "Answer The Question of Exam", JOptionPane.INFORMATION_MESSAGE);
            }
            else {

                int count = 1;
                Student currentStudent = findStudentInExam(chosenExam, this);
                for (Question question : chosenExam.getChosenQuestion().get(currentStudent)){
                    String answer = JOptionPane.showInputDialog(null,count + " - " +
                            question.getQuestion(),"Answer The Question of Exam",JOptionPane.QUESTION_MESSAGE);
                    question.setAnswerForExam(answer);
                }
                JOptionPane.showMessageDialog(null, "You Answered Question Successfully",
                        "Answer The Question of Exam", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null ,"The Exam Is Not" +
                    "Available Yet", "Answer The Question Of Exam", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void seeTheScoreOfAnExam(Exam chosenExam){
        float totalScore;
        if (chosenExam.getTotalScoreForExam().keySet().contains(this)){
            totalScore = chosenExam.getTotalScoreForExam().get(this);
            JOptionPane.showMessageDialog(null, "Your Score Of This Exam is :" +
                            totalScore + " From " + chosenExam.getGrade() ,
                    "see The Score Of An Exam", JOptionPane.INFORMATION_MESSAGE);

        }
        else {
            JOptionPane.showMessageDialog(null, "This Exam Doesn't " +
                    "Scored Yet", "see The Score Of A Exam", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void seeTheScoreOfEachQuestionExam(Exam chosenExam){
        int count = 1;
        StringBuilder allQuestionsWithScore = new StringBuilder();
        for (Question question : chosenExam.getChosenQuestion().get(this)){
             allQuestionsWithScore.append(count + " - " + question.getQuestion()
                     + " :\n" + question.getAnswerForExam() + " Score : "
                     + question.getScoreForEachQuestion().get(this) + "\n");
            count++;

        }
        JOptionPane.showMessageDialog(null, "All Questions With its Scores" ,
                "See The Score Of Each Question", JOptionPane.INFORMATION_MESSAGE);
    }
    public void seeTheScoreOfEachQuestionHomework(HomeWork chosenHomework){
        int count = 1;
        StringBuilder allQuestionsWithScore = new StringBuilder();
        for (Question question : chosenHomework.getChosenQuestionForHomeWork()){
            allQuestionsWithScore.append(count + " - " + question.getQuestion()
                    + " :\n" + question.getAnswer().get(this) + " Score : "
                    + question.getScoreForEachQuestion().get(this) + "\n");
            count++;

        }
        JOptionPane.showMessageDialog(null, "All Questions With its Scores" ,
                "See The Score Of Each Question", JOptionPane.INFORMATION_MESSAGE);
    }

}

