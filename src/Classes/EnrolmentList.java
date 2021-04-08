package Classes;

import Interface.StudentEnrollmentManager;

import java.util.ArrayList;
import java.util.List;

public class EnrolmentList implements StudentEnrollmentManager {
    private static EnrolmentList instance = null;
    private List<StudentEnrollment> studentEnrollmentList;
    private List<Student> studentList;
    private List<Course> courseList;

    //Getters and Setters
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<StudentEnrollment> getStudentEnrollmentList() {
        return studentEnrollmentList;
    }

    public void setStudentEnrollmentList(List<StudentEnrollment> studentEnrollmentList) {
        this.studentEnrollmentList = studentEnrollmentList;
    }


    //Single-ton
    public static EnrolmentList getInstance() {
        if (instance == null) {
            instance = new EnrolmentList();
        }
        return instance;
    }


    //Get the enrolled courses in one semester of a student by their id
    public List<Course> getEnrolledCourse(int index, String semester) {
        String sID = studentList.get(index).getId();
        List<Course> enrolledCourse = new ArrayList<>();
        for (StudentEnrollment enrollment : studentEnrollmentList) {
            if (sID.equals(enrollment.getStudent().getId()) && semester.equals(enrollment.getSemester())) {
                enrolledCourse.add(enrollment.getCourse());
            }
        }
        return enrolledCourse;
    }



    @Override
    public void add(StudentEnrollment enrollment) {
        studentEnrollmentList.add(enrollment);
    }

    @Override
    public void update(StudentEnrollment enrollment) {

    }

    @Override
    public void delete(StudentEnrollment enrollment) {

    }

    @Override
    public StudentEnrollment getOne() {
        return null;
    }

    @Override
    public List<StudentEnrollment> getAll() {
        return null;
    }



}
