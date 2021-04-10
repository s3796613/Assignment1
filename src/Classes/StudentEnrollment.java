package Classes;

public class StudentEnrollment {
    private Student student;
    private Course course;
    private String semester;

    //Getters and Setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }


    public StudentEnrollment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    @Override
    public String toString() {
        return student.toString() + "  " + course.toString() + " Sem:" + semester;
    }

    public String[] objectToString() {
        return new String[]{student.getId(), student.getName(), student.getBirthDate(), course.getId(), course.getName(), course.getNumberOfCredits(), semester};
    }
}
