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


    //Return course offered in a semester
    public List<Course> getEnrolledCourse(String semester) {
        List<Course> enrolledCourse = new ArrayList<>();
        for (StudentEnrollment enrollment : studentEnrollmentList) {
            if (semester.equals(enrollment.getSemester())) {
                enrolledCourse.add(enrollment.getCourse());
            }
        }
        return enrolledCourse;
    }


    //Get the enrolled courses in one semester of a student by their id
    public List<Course> getEnrolledCourse(int studentIndex, String semester) {
        String sID = studentList.get(studentIndex).getId();
        List<Course> enrolledCourse = new ArrayList<>();
        for (StudentEnrollment enrollment : studentEnrollmentList) {
            if (sID.equals(enrollment.getStudent().getId()) && semester.equals(enrollment.getSemester())) {
                enrolledCourse.add(enrollment.getCourse());
            }
        }
        return enrolledCourse;
    }

    //get the students enrolled in a course in one semester
    public List<Student> getEnrolledStudent(int courseIndex, String semester) {
        String courseID = courseList.get(courseIndex).getId();
        List<Student> enrolledStudent = new ArrayList<>();
        for (StudentEnrollment enrollment : studentEnrollmentList) {
            if (courseID.equals(enrollment.getCourse().getId()) && semester.equals(enrollment.getSemester())) {
                enrolledStudent.add(enrollment.getStudent());
            }
        }
        return enrolledStudent;
    }









    @Override
    public void add(StudentEnrollment enrollment) {
        studentEnrollmentList.add(enrollment);
    }

    //update one enrollment of a student
    @Override
    public void update(int index) {
        int courseIndex = Console.validateCourseID("Type in course ID: ",courseList);
        Course updatedCourse = courseList.get(courseIndex);
        String semester = Console.semesterInput();
        studentEnrollmentList.get(index).setCourse(updatedCourse);
        studentEnrollmentList.get(index).setSemester(semester);

    }

    //delete one enrollment
    @Override
    public void delete(int index) {
        studentEnrollmentList.remove(index);
    }

    //return one enrollment
    @Override
    public StudentEnrollment getOne(int index) {
        return studentEnrollmentList.get(index);
    }

    //get all enrollment
    @Override
    public List<StudentEnrollment> getAll() {
        return studentEnrollmentList;
    }



}
