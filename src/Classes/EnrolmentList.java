package Classes;

import Interface.StudentEnrollmentManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> courseID = new ArrayList<>();
        List<Course> enrolledCourse = new ArrayList<>();
        for (StudentEnrollment enrollment : studentEnrollmentList) {
            if (semester.equals(enrollment.getSemester())) {
                courseID.add(enrollment.getCourse().getId());
            }
        }

        //Sort out duplicate course object in list
        List<String> uniqueCourseIDs = courseID.stream().distinct().sorted().collect(Collectors.toList());
        for (String uniqueID : uniqueCourseIDs) {
            for (Course course : courseList) {
                if (uniqueID.equals(course.getId())) {
                    enrolledCourse.add(course);
                }
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

    public List<StudentEnrollment> getAllEnrollmentOfStudent(int studentIndex) {
        String sID = studentList.get(studentIndex).getId();
        List<StudentEnrollment> enrollmentList = new ArrayList<>();
        for (StudentEnrollment enrollment : studentEnrollmentList) {
            if (sID.equals(enrollment.getStudent().getId())) {
                enrollmentList.add(enrollment);
            }
        }
        return enrollmentList;
    }

    public List<String> getStudentSemester(int studentIndex) {
        List<String> semesters = new ArrayList<>();
        String studentID = studentList.get(studentIndex).getId();
        for (StudentEnrollment enrollment : studentEnrollmentList) {
            if (studentID.equals(enrollment.getStudent().getId())) {
                semesters.add(enrollment.getSemester());
            }
        }
        return semesters;
    }

    public List<String> getCourseSemester(int courseIndex) {
        List<String> semesters = new ArrayList<>();
        List<String> uniqueSemesters = new ArrayList<>();
        String courseID = courseList.get(courseIndex).getId();
        for (StudentEnrollment enrollment: studentEnrollmentList) {
            if (courseID.equals(enrollment.getCourse().getId())) {
                semesters.add(enrollment.getSemester());
            }
        }
        uniqueSemesters = semesters.stream().distinct().sorted().collect(Collectors.toList());
        return uniqueSemesters;
    }









    @Override
    public void add(StudentEnrollment enrollment) {
        studentEnrollmentList.add(enrollment);
    }

    //update one enrollment of a student
    @Override
    public void update(int index) {
        Console.displayCourseList();
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
