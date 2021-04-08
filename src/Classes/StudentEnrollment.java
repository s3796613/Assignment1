package Classes;

public class StudentEnrollment {
    private Student student;
    private Course course;
    private String semester;


    public StudentEnrollment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    @Override
    public String toString() {
        return student.toString() + "  " + course.toString() + " Sem:" + semester;
    }
}
