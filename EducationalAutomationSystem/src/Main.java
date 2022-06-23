import com.sun.xml.internal.messaging.saaj.soap.GifDataContentHandler;
import javafx.util.converter.LocalDateTimeStringConverter;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        Student student1 = new Student("Reihaneh Akbarifard" ,"comp", "9932107",
                "123");
        DataBase.getStudentsArrayList().add(student1);
        Student student2 = new Student("Sogol Bazrafshan" ,"comp", "9932111",
                "123");
        DataBase.getStudentsArrayList().add(student2);
        Student student3 = new Student("Milad Noraie" ,"comp", "9932110",
                "123");
        DataBase.getStudentsArrayList().add(student3);
        Professor professor1 = new Professor("Ali Hamze", "comp", "123", "123");
        DataBase.getProfessorsArrayList().add(professor1);
        Duration periodOfClass = Duration.ofMinutes(90);
        Class class1 = new Class(professor1, "ap", periodOfClass);
        LocalTime timeOfStartTheClass = LocalTime.of(9,00,00);
        class1.getClassSchedulingAndSection().put(Day.SUNDAY,timeOfStartTheClass);
        Class class2 = new Class(professor1, "fla", periodOfClass);
        LocalTime timeOfStartTheClass2 = LocalTime.of(10,00,00);
        class2.getClassSchedulingAndSection().put(Day.SUNDAY,timeOfStartTheClass2);
        professor1.getAllClasses().add(class1);
        professor1.getAllClasses().add(class2);
        DataBase.getClassesArrayList().add(class1);
        DataBase.getClassesArrayList().add(class2);
        class1.getStudentsOfThisClass().add(student1);
        student1.getAllClasses().add(class1);
        class1.getStudentsOfThisClass().add(student2);
        student2.getAllClasses().add(class1);
        class1.getQuestionBank().add("How?");
        class1.getQuestionBank().add("Why?");
        ArrayList questions = new ArrayList();
        Question question1 = new Question(class1.getQuestionBank().get(0), 10);
        Question question2 = new Question(class1.getQuestionBank().get(1), 10);
        questions.add(question1);
        questions.add(question2);
        LocalDateTime timeOfStartTheHomeWork = LocalDateTime.of(2022,6,01,20,00,00);
        LocalDateTime timeOfEndTheHomeWork = LocalDateTime.of(2022,8,5,20,00,00);
        HomeWork homeWork1 = new HomeWork(class1, "Homework1", 20, questions,timeOfStartTheHomeWork,timeOfEndTheHomeWork);
        class1.getAllHomeWorks().add(homeWork1);
        ///////////////////////////////////////////////////////////////////////////////////////////


        boolean isAppRunning = true;
        JOptionPane.showMessageDialog(null, "Welcome To Educational Automation System!",
                "Welcome", JOptionPane.INFORMATION_MESSAGE);
        while (isAppRunning){
            switch (Integer.parseInt(JOptionPane.showInputDialog(null, "What Do You Want To Do?\n1. Log-In\n2. Sign-Up\n" +
                    "3. Exit","Log-In/Sign-Up Page",JOptionPane.QUESTION_MESSAGE))){
                case 1:
                    switch (Integer.parseInt(JOptionPane.showInputDialog(null, "Please " +
                            "Determine You Role\n1. Professor\n2. Student\n3. Back", "Sign-Up Page",JOptionPane.QUESTION_MESSAGE))){
                        case 1:
                            Professor currentProfessor = Professor.professorLogIn();
                            if(currentProfessor != null){
                                boolean inHomePage = true;
                                while (inHomePage){
                                    switch (Integer.parseInt(JOptionPane.showInputDialog(null, "What " +
                                                    "Do You Want To Do?\n1. Show All Classes\n2. Create A New Class\n3. Log-Out","Home Page",
                                            JOptionPane.QUESTION_MESSAGE))){
                                        case 1:
                                            int chosenOpt = Professor.showAllClasses(currentProfessor);
                                            switch (chosenOpt){
                                                case 0:
                                                    inHomePage = false;
                                                    break;
                                                default:
                                                    Class chosenClass = currentProfessor
                                                            .goToTheClass(chosenOpt - 1, currentProfessor);
                                                    switch (Integer.parseInt(JOptionPane.showInputDialog(null, "What " +
                                                            "Do You Want To Do?\n1. Add Student To Class\n2. Remove Student From Class\n" +
                                                            "3. Add Question To QuestionBank\n4. Remove Question From QuestionBank\n" +
                                                            "5. Add New Reference\n6. Send New Announcement\n7. Set A Homework\n8. See All Homeworks\n" +
                                                            "9. Take An Exam\n10. See All Exams\n11. Back"))){
                                                        case 1:
                                                            currentProfessor.addStudentToAClass(chosenClass);
                                                            break;
                                                        case 2:
                                                            currentProfessor.removeStudentFromAClass(chosenClass);
                                                            break;
                                                        case 3:
                                                            currentProfessor.addQuestionToQuestionBank(chosenClass);
                                                            break;
                                                        case 4:
                                                            currentProfessor.removeQuestionFromQuestionBank(chosenClass);
                                                            break;
                                                        case 5:
                                                            currentProfessor.addReferences(chosenClass);
                                                            break;
                                                        case 6:
                                                            currentProfessor.sendAnAnnouncement(chosenClass);
                                                            break;
                                                        case 7:
                                                            currentProfessor.setAHomeWork(chosenClass);
                                                            break;
                                                        case 8:
                                                            HomeWork chosenHomeWork = currentProfessor.seeHomeWorks(chosenClass);
                                                            switch (Integer.parseInt(JOptionPane.showInputDialog(null,"What Do You Want To Do?\n" +
                                                                    "1. See HomeWork\n2. Grade To HomeWork\n3. See All Students' Grade\n" +
                                                                    "4. Back","Home Work Page",JOptionPane.QUESTION_MESSAGE))) {
                                                                case 1:
                                                                    currentProfessor.seeHomework(chosenHomeWork);
                                                                    break;
                                                                case 2:
                                                                    currentProfessor.giveScoreToQuestionOfHomework(chosenHomeWork);
                                                                    break;
                                                                case 3:
                                                                    chosenHomeWork.showAllStudentsScores();
                                                                    break;
                                                                case 4:
                                                                    break;
                                                            }
                                                            break;
                                                        case 9:
                                                            currentProfessor.takeAnExam(chosenClass);
                                                            break;
                                                        case 10:
                                                            Exam chosenExam = currentProfessor.seeExams(chosenClass);
                                                            switch (Integer.parseInt(JOptionPane.showInputDialog(null,"What Do You Want To Do?\n" +
                                                                    "1. Enable The Exam\n2. Grade To Exam\n3. See All Students' Grade\n" +
                                                                    "4. Back","Exam Page",JOptionPane.QUESTION_MESSAGE))) {
                                                                case 1:
                                                                    chosenExam.enableTheExam();
                                                                    break;
                                                                case 2:
                                                                    currentProfessor.giveScoreToQuestionOfExam(chosenExam);
                                                                    break;
                                                                case 3:
                                                                    chosenExam.showAllStudentsScoresOfExams();
                                                                    break;
                                                                case 4:
                                                                    break;
                                                            }
                                                        case 11:
                                                            break;
                                                    }

                                            }
                                            break;
                                        case 2:
                                            currentProfessor.createANewClass(currentProfessor);
                                            break;
                                        case 3:
                                            inHomePage = false;
                                            JOptionPane.showMessageDialog(null, "You " +
                                                    "Logged Out From This Account", "Log-Out",JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                    }
                                }
                            }
                            break;
                        case 2:
                            Student currentStudent = Student.studentsLogIn();
                            if(currentStudent != null){
                                boolean inHomePage = true;
                                while (inHomePage) {
                                    switch (Integer.parseInt(JOptionPane.showInputDialog(null, "What " +
                                                    "Do You Want To Do?\n1. Show All Classes\n2. Search For A Class\n3. Log-Out", "Home Page",
                                            JOptionPane.QUESTION_MESSAGE))) {
                                        case 1:
                                            int chosenOpt = currentStudent.showAllClasses();
                                            switch (chosenOpt) {
                                                case 0:
                                                    break;
                                                default:
                                                    Class chosenClass = currentStudent.goToTheClass(chosenOpt - 1, currentStudent);
                                                    switch (Integer.parseInt(JOptionPane.showInputDialog(null,
                                                            "What Do You Want To Do?\n1. See All References\n2. See All HomeWorks\n" +
                                                                    "3. See All Exams\n4. Back"))){
                                                        case 1:
                                                            currentStudent.seeReferences(chosenClass);
                                                            break;
                                                        case 2:
                                                            HomeWork chosenHomeWork = currentStudent.seeHomeWorks(chosenClass);
                                                            if(chosenHomeWork != null){
                                                                switch (Integer.parseInt(JOptionPane.showInputDialog(null,"What Do You Want To Do?\n" +
                                                                        "1. See HomeWork\n2. Answer To HomeWork\n3. See The Score Of Each Question\n" +
                                                                        "4. See The Score Of This Homework\n5. Back","Home Work Page",JOptionPane.QUESTION_MESSAGE))){
                                                                    case 1:
                                                                        currentStudent.seeHomework(chosenHomeWork);
                                                                        break;
                                                                    case 2:
                                                                        currentStudent.answerToQuestionsOfHomeWork(chosenHomeWork);
                                                                        break;
                                                                    case 3:
                                                                        currentStudent.seeTheScoreOfEachQuestionHomework(chosenHomeWork);
                                                                        break;
                                                                    case 4:
                                                                        currentStudent.seeTheScoreOfAHomework(chosenHomeWork);
                                                                        break;
                                                                    case 5:
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                        case 3:
                                                            Exam chosenExam = currentStudent.seeExams(chosenClass);
                                                            if(chosenExam != null){
                                                                switch (Integer.parseInt(JOptionPane.showInputDialog(null,"What Do You Want To Do?\n" +
                                                                                "1. See Exam\n2. Answer To Exam\n3. See The Score Of Each Question\n" +
                                                                                "4. See The Score Of This Exam\n5. Back","Exam Page",
                                                                        JOptionPane.QUESTION_MESSAGE))){
                                                                    case 1:
                                                                        currentStudent.seeExam(chosenExam);
                                                                        break;
                                                                    case 2:
                                                                        currentStudent.answerTheQuestionOfExam(chosenExam);
                                                                        break;
                                                                    case 3:
                                                                        currentStudent.seeTheScoreOfEachQuestionExam(chosenExam);
                                                                        break;
                                                                    case 4:
                                                                        currentStudent.seeTheScoreOfAnExam(chosenExam);
                                                                        break;
                                                                    case 5:
                                                                        break;
                                                                }
                                                            }
                                                        case 4:
                                                            break;
                                                    }

                                            }
                                            break;
                                        case 2:
                                            Class searchedClass = currentStudent.searchForAClass();
                                            if(searchedClass != null){
                                                switch (Integer.parseInt(JOptionPane.showInputDialog(null, "Do You " +
                                                        "Want To Join To This Class?\n1. Yes\n2. No", "Join To The Class", JOptionPane.QUESTION_MESSAGE))){
                                                    case 1:
                                                        if (currentStudent.getAllClasses().contains(searchedClass)){
                                                        JOptionPane.showMessageDialog(null, "You Joined This Class" +
                                                                " Before", "Join To The Class", JOptionPane.QUESTION_MESSAGE);
                                                        }
                                                        else {
                                                            currentStudent.joinClass(searchedClass, currentStudent);
                                                        }
                                                        break;
                                                    case 2:
                                                        break;
                                                }
                                            }
                                            break;
                                        case 3:
                                            inHomePage = false;
                                            JOptionPane.showMessageDialog(null, "You " +
                                                    "Logged Out From This Account", "Log-Out",JOptionPane.INFORMATION_MESSAGE);
                                            break;

                                    }
                                }

                            }
                            break;
                        case 3:
                            break;

                }
                break;
                case 2:
                    User.signUp();
                    break;
                case 3:
                    isAppRunning = false;
                    break;
            }
        }

    }

}
