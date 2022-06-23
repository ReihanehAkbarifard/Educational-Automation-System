import java.util.ArrayList;

public class DataBase {
    private static ArrayList<Student> studentsArrayList = new ArrayList<>();
    private static ArrayList<Professor> professorsArrayList = new ArrayList<>();
    private static ArrayList<Class> classesArrayList = new ArrayList<>();

    public static ArrayList<Student> getStudentsArrayList() {
        return studentsArrayList;
    }

    public static ArrayList<Professor> getProfessorsArrayList() {
        return professorsArrayList;
    }

    public static ArrayList<Class> getClassesArrayList() {
        return classesArrayList;
    }

    public static ArrayList<String> getFullNameOfStudents(){
        ArrayList<String> studentsFullNamesArrayList = new ArrayList<>();
        for (Student student : studentsArrayList ){
            studentsFullNamesArrayList.add(student.getFullName());
        }
        return studentsFullNamesArrayList;
    }

    public static ArrayList<String> getStudentsNumberOfStudents(){
        ArrayList<String> getStudentsNumberOfStudents = new ArrayList<>();
        for (Student student : studentsArrayList ){
            getStudentsNumberOfStudents.add(student.getStudentNumber());
        }
        return getStudentsNumberOfStudents;
    }

    public static boolean isProfessor(Professor isProfessor){
        for (Professor professor : getProfessorsArrayList()){
            if(isProfessor.equals(professor))
                return true;
        }
        return false;
    }


    public static Professor getProfessorFromProfessorNumber(String professorNumber){
        for (Professor professor : getProfessorsArrayList()){
            if(professor.getProfessorNumber().equals(professorNumber))
                return professor;
        }
        return null;
    }


    public static Student getStudentFromStudentNumber(String StudentNumber){
        for (Student student : getStudentsArrayList()){
            if(student.getStudentNumber().equals(StudentNumber))
                return student;
        }
        return null;
    }

    public static Class getClassFromClassName(String className){
        for (Class eachClass : getClassesArrayList()){
            if(eachClass.getName().equals(className))
                return eachClass;
        }
        return null;
    }



}
