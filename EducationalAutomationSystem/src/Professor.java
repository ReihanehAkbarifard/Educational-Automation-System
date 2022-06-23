import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Professor extends User {

    private String professorNumber;

    public Professor(String fullName, String field, String professorNumber, String passWord) {
        setFullName(fullName);
        setField(field);
        this.professorNumber = professorNumber;
        setPassWord(passWord);
    }
    public String getProfessorNumber() {
        return professorNumber;
    }


    public static Professor professorLogIn(){
        String professorNumber = JOptionPane.showInputDialog(null, "Please Enter Your Professor Number :\n",
                "Log-In Page", JOptionPane.QUESTION_MESSAGE);
        Professor currentProfessor = DataBase.getProfessorFromProfessorNumber(professorNumber);

        if (DataBase.getProfessorsArrayList().contains(currentProfessor)){
            boolean isTruePassWord = true;
            String passWord;
            while (isTruePassWord){
                passWord = JOptionPane.showInputDialog(null, "Please Enter Your PassWord :\n",
                        "Sign-Up Page", JOptionPane.QUESTION_MESSAGE);
                if(passWord.equals(currentProfessor.getPassWord())){
                    JOptionPane.showMessageDialog(null ,"Welcome Professor " +
                                    currentProfessor.getFullName() ,"Welcome"
                            ,JOptionPane.INFORMATION_MESSAGE);
                    return currentProfessor;
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

    public static void addStudentToAClass(Class chosenClass){
        String studentNumber = JOptionPane.showInputDialog(null, "Please Enter The StudentsNumber :\n",
                "Add Student To Class", JOptionPane.QUESTION_MESSAGE);
        Student student = DataBase.getStudentFromStudentNumber(studentNumber);
        if(DataBase.getStudentsArrayList().contains(student)){
            if(!chosenClass.getStudentsOfThisClass().contains(student)){
                chosenClass.getStudentsOfThisClass().add(student);
                student.getAllClasses().add(chosenClass);
                JOptionPane.showMessageDialog(null, "You Successfully Added Student" +
                                student.getFullName() + "To " + chosenClass.getName() + " Class",
                        "Add Student To Class", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "This Student Was Added" +
                        " To This Class Before", "Add Student To Class", JOptionPane.INFORMATION_MESSAGE);
            }


        }
        else {
            JOptionPane.showMessageDialog(null, "No Such Student Found!",
                    "Add Student To A Class",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void removeStudentFromAClass(Class chosenClass){
        String studentNumber = JOptionPane.showInputDialog(null, "Please Enter The StudentsNumber :\n",
                "Remove Student From Class", JOptionPane.QUESTION_MESSAGE);
        Student student = DataBase.getStudentFromStudentNumber(studentNumber);
        if(DataBase.getStudentsArrayList().contains(student)){
            chosenClass.getStudentsOfThisClass().remove(student);
            student.getAllClasses().remove(chosenClass);
            JOptionPane.showMessageDialog(null, "You Successfully Removed Student " +
                    student.getFullName() + "From " + chosenClass.getName() + " Class",
                    "Remove Student From Class", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "No Such Student Found!",
                    "Add Student To A Class",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void createANewClass(Professor currentProfessor){
        String nameOfClass = JOptionPane.showInputDialog(null, "Please Choose A " +
                "Name For Your Class : ","Create A New Class",JOptionPane.QUESTION_MESSAGE);
        Duration duration = Duration.ofMinutes(Long.parseLong(JOptionPane.showInputDialog(null,
                "Please Enter The Duration Of Class : ","Create A New Class",JOptionPane.QUESTION_MESSAGE)));
        Class newClass = new Class(currentProfessor, nameOfClass, duration );
        DataBase.getClassesArrayList().add(newClass);
        currentProfessor.getAllClasses().add(newClass);
        int dayOfClass = Integer.parseInt(JOptionPane.showInputDialog(null, "How Many " +
                "Days You Want To Make A Class?","Create A New Class",JOptionPane.QUESTION_MESSAGE));
        for (int i = 0; i < dayOfClass; i++){
            int numberDay = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Please Choose The Day of Your Class :1. Sunday\n2. Monday\n3 .Tuesday" +
                            "\n4. Wednesday\n5. Thursday\n6. Friday\n7. Saturday"
                    ,"Create A New Class",JOptionPane.QUESTION_MESSAGE));
            Day chosenDay = null;
            switch (numberDay){
                case 1:
                    chosenDay = Day.SUNDAY;
                    break;
                case 2:
                    chosenDay = Day.MONDAY;
                    break;
                case 3:
                    chosenDay = Day.TUESDAY;
                    break;
                case 4:
                    chosenDay = Day.WEDNESDAY;
                    break;
                case 5:
                    chosenDay = Day.THURSDAY;
                    break;
                case 6:
                    chosenDay = Day.FRIDAY;
                    break;
                case 7:
                    chosenDay = Day.SATURDAY;
                    break;
                default:
                    break;


            }
            LocalTime timeOfClass = LocalTime.parse(JOptionPane.showInputDialog(null,
                    "Please Enter The Time Of End The Exam In HH:mm:ss Format :",
                    "Create A New Class" ,JOptionPane.QUESTION_MESSAGE));
            newClass.getClassSchedulingAndSection().put(chosenDay, timeOfClass);
        }

        JOptionPane.showMessageDialog(null, "Class " + newClass.getName() +
                " Was Successfully Created");
    }

    public static int showAllClasses(Professor currentProfessor){
        StringBuilder allClasses = new StringBuilder();
        int count = 1;
        for(Class eachClass : currentProfessor.getAllClasses()){
            allClasses.append(count + " : ").append(eachClass.getName() + "\n");
            count++;
        }
        int chosenOpt = Integer.parseInt(JOptionPane.showInputDialog(null, "These Are All Of Your Classes :\n" +
                "Please Enter A Number To Show More.\n" +
                "0. Back\n" + allClasses , "All Classes" ,JOptionPane.QUESTION_MESSAGE));
        return chosenOpt;
    }

    public Class goToTheClass(int index, Professor currentProfessor){
        return currentProfessor.getAllClasses().get(index);
    }

    public void addQuestionToQuestionBank(Class chosenClass){
        String newQuestion = JOptionPane.showInputDialog(null, "Please Enter The New Question" +
                " Which You Want To Add To The QuestionBank :\n", "Add Question To Question Bank",JOptionPane.QUESTION_MESSAGE);
        chosenClass.getQuestionBank().add(newQuestion);
        JOptionPane.showMessageDialog(null, "New Question Just Added To " +
                "QuestionBank Of Class \n" + chosenClass.getName(), "Add Question To Question Bank",JOptionPane.INFORMATION_MESSAGE);


    }

    public void removeQuestionFromQuestionBank(Class chosenClass){
        int chosenOpt = Integer.parseInt(JOptionPane.showInputDialog(null, "Please Enter The Number Of Question" +
                        " Which You Want To Remove :\n" + showAllQuestionOfQuestionBank(chosenClass), "Remove Question From Question Bank",
                JOptionPane.QUESTION_MESSAGE));
        chosenClass.getQuestionBank().remove(chosenOpt - 1);
        JOptionPane.showMessageDialog(null, "Chosen Question Just Removed From " +
                "QuestionBank Of Class \n" + chosenClass.getName(), "Remove Question From Question Bank",JOptionPane.INFORMATION_MESSAGE);


    }

    public String showAllQuestionOfQuestionBank(Class chosenClass){
        StringBuilder allQuestion = new StringBuilder();
        int count = 1;
        for (String question : chosenClass.getQuestionBank()){
            allQuestion.append(count + " : ").append(question + "\n");
        }
        return allQuestion.toString();
    }

    public void addReferences(Class chosenClass){
        String newReference = JOptionPane.showInputDialog(null, "Enter The New" +
                " Reference :", "Add New References", JOptionPane.QUESTION_MESSAGE);
        chosenClass.getReferences().add(newReference);
        JOptionPane.showMessageDialog(null, "New Reference Was Added To " +
                chosenClass.getName() +" Class", "Add New References", JOptionPane.INFORMATION_MESSAGE);
    }
    public void sendAnAnnouncement(Class chosenClass){
        String announcement = JOptionPane.showInputDialog(null, "Enter The New" +
                " Announcement Which You Want Send To Students Of " + chosenClass.getName() + " Class",
                "Send An Announcement", JOptionPane.QUESTION_MESSAGE);
        chosenClass.getAnnouncements().add(announcement);
        JOptionPane.showMessageDialog(null, "New Announcement Was Added To " +
                chosenClass.getName() +" Class's Announcements", "Add New Announcement", JOptionPane.INFORMATION_MESSAGE);
    }
    public void takeAnExam(Class chosenClass){
        String titleOfTheExam = JOptionPane.showInputDialog(null, "Please Enter" +
                " The Title Of The Exam :", "Take An Exam",JOptionPane.QUESTION_MESSAGE);
        LocalDateTime timeOfStartTheExam = LocalDateTime.parse(JOptionPane.showInputDialog(null,
                "Please Enter The Time Of Start The Exam In yyyy-MM-ddTHH:mm:ss Format :"));
        LocalDateTime timeOfEndTheExam = LocalDateTime.parse(JOptionPane.showInputDialog(null,
                "Please Enter The Time Of End The Exam In yyyy-MM-ddTHH:mm:ss Format :"));
        int totalScore = Integer.parseInt(JOptionPane.showInputDialog(null, "Please Enter" +
                " The Total Score Of This Exam :", "Take An Exam",JOptionPane.QUESTION_MESSAGE));
        HashMap<Student, ArrayList<Question>> randomQuestionForEachStudent = pickQuestionForExam(chosenClass, totalScore);
        Exam newExam = new Exam(chosenClass, titleOfTheExam, totalScore, randomQuestionForEachStudent,
                timeOfStartTheExam, timeOfEndTheExam);
        chosenClass.getAllExams().add(newExam);

    }
    public void setAHomeWork(Class chosenClass){

        String titleOfHomeWork = JOptionPane.showInputDialog(null, "Please Enter" +
                " The Title Of The HomeWork :", "Set A HomeWork", JOptionPane.QUESTION_MESSAGE);
        LocalDateTime timeOfStartTheHomeWork = LocalDateTime.parse(JOptionPane.showInputDialog(null,
                "Please Enter The Time Of Start The HomeWork In yyyy-MM-ddTHH:mm:ss Format :"));
        LocalDateTime timeOfEndTheHomeWork = LocalDateTime.parse(JOptionPane.showInputDialog(null,
                "Please Enter The Time Of End The HomeWork In yyyy-MM-ddTHH:mm:ss Format :"));
        ArrayList<Question> chosenQuestionForHomeWork = pickQuestion(chosenClass);
        float grade = 0;
        for(Question question : chosenQuestionForHomeWork){
            grade += question.getScore();
        }
        HomeWork newHomeWork = new HomeWork(chosenClass, titleOfHomeWork, grade, chosenQuestionForHomeWork,
                timeOfStartTheHomeWork, timeOfEndTheHomeWork);
        chosenClass.getAllHomeWorks().add(newHomeWork);

    }


    public static String showAllQuestionFromQuestionBank(Class chosenClass){
        StringBuilder allQuestion = new StringBuilder();
        int count = 1;
        for (String question : chosenClass.getQuestionBank()){
            allQuestion.append(count + " : " + question + "\n");
            count++;
        }

        return allQuestion.toString();

    }
    public HashMap<Student, ArrayList<Question>> pickQuestionForExam(Class chosenClass, int totalScore){
        int numberOfQuestion = Integer.parseInt(JOptionPane.showInputDialog(null, "How Many Question You Want To " +
                "Pick?", "Pick Question", JOptionPane.QUESTION_MESSAGE));
        int scoreForEachQuestion = totalScore / numberOfQuestion;
        String chosenQuestion;
        HashMap<Student, ArrayList<Question>> randomQuestionForEachStudent = new HashMap<>();
        Random random = new Random();
        for (Student student : chosenClass.getStudentsOfThisClass()){
            ArrayList<Question> randomQuestion = new ArrayList<>();
            for (int i = 0; i < numberOfQuestion; i++){
                chosenQuestion = chosenClass.getQuestionBank().get(random.nextInt(chosenClass.getQuestionBank().size()));
                Question question = new Question(chosenQuestion, scoreForEachQuestion);
                randomQuestion.add(question);
            }
            randomQuestionForEachStudent.put(student, randomQuestion);
        }
        return randomQuestionForEachStudent;
    }
    public ArrayList pickQuestion(Class chosenClass){
        int numberOfQuestion = Integer.parseInt(JOptionPane.showInputDialog(null, "How Many Question You Want To " +
                "Pick?", "Pick Question", JOptionPane.QUESTION_MESSAGE));
        String allQuestion = showAllQuestionFromQuestionBank(chosenClass);
        int chosenQuestion;
        float gradeForThisQuestion;
        ArrayList<Question> chosenQuestionForHomeWork = new ArrayList<>();
        for (int i = 0 ;i < numberOfQuestion; i++){
            chosenQuestion = Integer.parseInt(JOptionPane.showInputDialog(null, "Choose The Number Of The Question " +
                    "You Want To Add To The Exam Question :\n" + allQuestion, "Pick Question",JOptionPane.QUESTION_MESSAGE));
            gradeForThisQuestion = Float.parseFloat(JOptionPane.showInputDialog(null, "Please Enter The Grade You Considered" +
                    " For This Question :\n", "Pick Question",JOptionPane.QUESTION_MESSAGE));
            Question question = new Question(chosenClass.getQuestionBank().get(chosenQuestion - 1), gradeForThisQuestion);
            chosenQuestionForHomeWork.add(question);

        }
        return chosenQuestionForHomeWork;
    }

    public void giveScoreToQuestionOfHomework(HomeWork chosenHomeWork){

        int count = 1;
        for (Question question : chosenHomeWork.getChosenQuestionForHomeWork()){
            for (Map.Entry answer : question.getAnswer().entrySet()){
                Student student = (Student) answer.getKey();
                float score = Float.parseFloat(JOptionPane.showInputDialog(null,count + " - " +
                                question.getQuestion() + "\n" + student.getFullName() + " 's Answer : \n" +
                                answer.getValue() + "\nPlease Enter The Score Of This Question :"
                        ," give Score To Question Of Homework",JOptionPane.QUESTION_MESSAGE));
                question.getScoreForEachQuestion().put(student, score);
            }
            count++;
        }
    }
    public void giveScoreToQuestionOfExam(Exam chosenExam){

        for (Student student : chosenExam.getChosenQuestion().keySet()){
            int count = 1;
            float totalScore = 0;
            for (Question question : chosenExam.getChosenQuestion().get(student)){
                float score = Float.parseFloat(JOptionPane.showInputDialog(null,count + " - " +
                                question.getQuestion() + "\n" + student.getFullName() + " 's Answer : \n" +
                                 question.getAnswerForExam() + "\nPlease Enter The Score Of This Question :"
                        ," give Score To Question Of Exam",JOptionPane.QUESTION_MESSAGE));
                count++;
                question.setScoreForExam(score);
                totalScore += score;

            }
            chosenExam.getTotalScoreForExam().put(student, totalScore);

        }

    }


}
