import javax.swing.*;
import java.util.ArrayList;

public class User {
    private String fullName;
    private String passWord;
    private String field;
    private ArrayList<Class> allClasses = new ArrayList<>();

    public String getFullName() {
        return fullName;
    }
    public String getPassWord() {
        return passWord;
    }
    public ArrayList<Class> getAllClasses() {
        return allClasses;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public void setField(String field) {
        this.field = field;
    }

    public static void signUp(){
        switch (Integer.parseInt(JOptionPane.showInputDialog(null, "Please " +
                "Determine You Role\n1. Professor\n2. Student", "Sign-Up Page",JOptionPane.QUESTION_MESSAGE))){
            case 1:
                String fullName = JOptionPane.showInputDialog(null, "Please Enter" +
                        " Your Full Name :\n", "Sign-Up Page", JOptionPane.QUESTION_MESSAGE);

                String field = JOptionPane.showInputDialog(null, "Please Enter" +
                        " Your Field :\n", "Sign-Up Page", JOptionPane.QUESTION_MESSAGE);

                String professorNumber = JOptionPane.showInputDialog(null, "Please Enter" +
                        " Your Professor Number :\n", "Sign-Up Page", JOptionPane.QUESTION_MESSAGE);

                String passWord = JOptionPane.showInputDialog(null, "Please Choose" +
                        " A PassWord For YourSelf :\n", "Sign-Up Page", JOptionPane.QUESTION_MESSAGE);

                Professor newProfessor = new Professor(fullName, field, professorNumber, passWord);
                DataBase.getProfessorsArrayList().add(newProfessor);
                break;

            case 2:
                fullName = JOptionPane.showInputDialog(null, "Please Enter" +
                        " Your Full Name :\n", "Sign-Up Page", JOptionPane.QUESTION_MESSAGE);

                field = JOptionPane.showInputDialog(null, "Please Enter" +
                        " Your Field :\n", "Sign-Up Page", JOptionPane.QUESTION_MESSAGE);

                String studentNumber = JOptionPane.showInputDialog(null, "Please Enter" +
                        " Your Student Number :\n", "Sign-Up Page", JOptionPane.QUESTION_MESSAGE);

                passWord = JOptionPane.showInputDialog(null, "Please Choose" +
                        " A PassWord For YourSelf :\n", "Sign-Up Page", JOptionPane.QUESTION_MESSAGE);

                Student newStudent = new Student(fullName, field, studentNumber, passWord);
                DataBase.getStudentsArrayList().add(newStudent);
                break;

        }

    }
    public HomeWork seeHomeWorks(Class chosenClass){
        if(!chosenClass.getAllHomeWorks().isEmpty()){
            StringBuilder allHomeWorks = new StringBuilder();
            int count = 1;
            for (HomeWork homeWork : chosenClass.getAllHomeWorks()){
                allHomeWorks.append(count + " . " + homeWork.getName() + "\n");
                count++;
            }
            int chosenOpt = Integer.parseInt(JOptionPane.showInputDialog(null, "All HomeWorks Of " + chosenClass.getName() +
                            "Class :\nChose One Of The Number To See More\n" + allHomeWorks,
                    "See HomeWorks",JOptionPane.QUESTION_MESSAGE));
            return  chosenClass.getAllHomeWorks().get(chosenOpt - 1);
        }
        else {
            JOptionPane.showMessageDialog(null, "There is No HomeWork Yet",
                    "See HomeWorks", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

    }
    public void seeHomework(HomeWork chosenHomeWork){
        StringBuilder allQuestionOfThisHomeWork = new StringBuilder();
        int count = 1;
        for (Question question : chosenHomeWork.getChosenQuestionForHomeWork()){
            allQuestionOfThisHomeWork.append(count + " : " + question.getQuestion() + "\n");
            count++;
        }
        JOptionPane.showMessageDialog(null, "All Questions Of " + chosenHomeWork.getName() +
                        " HomeWork :\n" + allQuestionOfThisHomeWork,
                "See HomeWork's Question",JOptionPane.INFORMATION_MESSAGE);

    }

    public Exam seeExams(Class chosenClass){
        if(!chosenClass.getAllExams().isEmpty()){
            StringBuilder allExams = new StringBuilder();
            int count = 1;
            for (Exam exam : chosenClass.getAllExams()){
                allExams.append(count + " : " + exam.getName() + "\n");
                count++;
            }
            int chosenOpt = Integer.parseInt(JOptionPane.showInputDialog(null, "All Exams Of " + chosenClass.getName() +
                            "Class :\n" + allExams,
                    "See HomeExams",JOptionPane.QUESTION_MESSAGE));
            return  chosenClass.getAllExams().get(chosenOpt - 1);
        }
        else {
            JOptionPane.showMessageDialog(null, "There is No Exam Yet",
                    "See Exams", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

    }


}
