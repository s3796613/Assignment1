package com.thang;

import Classes.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static int menu() {
        System.out.println("---------MAIN MENU---------");
        System.out.println("1. Add a new enrollment");
        System.out.println("2. Edit an enrollment");
        System.out.println("3. View report");
        System.out.println("4. Exit");

        return Console.validateInt("Type in your choice (1-4): ",1,4);

    }

    public static void addEnrollmentMenu(String fileName) throws IOException {
        System.out.println("----------------------------------------");
        Console.displayStudentList();
        int studentIndex = Console.validateID("Type in student ID: ",EnrolmentList.getInstance().getStudentList());
        Console.displayCourseList();
        int courseIndex = Console.validateCourseID("Type in course ID: ",EnrolmentList.getInstance().getCourseList());
        String semester = Console.semesterInput();
        StudentEnrollment newEnrollment = new StudentEnrollment(EnrolmentList.getInstance().getStudentList().get(studentIndex),
                EnrolmentList.getInstance().getCourseList().get(courseIndex),
                semester);
        EnrolmentList.getInstance().add(newEnrollment);
        FileManager.writeEnrollmentToCSV(EnrolmentList.getInstance().getAll(), fileName);
        System.out.println("Enrollment successfully!");
    }

    public static void updateEnrollmentMenu(String fileName) throws IOException {
        System.out.println("---------------------");
        Console.displayStudentList();
        int studentIndex = Console.validateID("Type in a student ID to update their enrollment",EnrolmentList.getInstance().getStudentList());
        System.out.println("All enrollment of this student: ");
        List<StudentEnrollment> enrollments = EnrolmentList.getInstance().getAllEnrollmentOfStudent(studentIndex);
        Console.displayObjectByIndex(enrollments);
        int enrollmentIndex = Console.validateInt("Your selection: ",0,enrollments.size()-1);
        String studentID = EnrolmentList.getInstance().getStudentList().get(studentIndex).getId();
        String courseID = enrollments.get(enrollmentIndex).getCourse().getId();
        String semester = enrollments.get(enrollmentIndex).getSemester();
        int enrollmentListIndex = Console.getEnrollmentIndex(studentID,courseID,semester);

        System.out.println("1. Update");
        System.out.println("2. Delete");
        int choice = Console.validateInt("Select: ");
        if (choice == 1) {
            EnrolmentList.getInstance().update(enrollmentListIndex);
        } else {
            EnrolmentList.getInstance().delete(enrollmentListIndex);
        }

        FileManager.writeEnrollmentToCSV(EnrolmentList.getInstance().getAll(), fileName);
        System.out.println("Update successfully!");
    }

    public static void reportMenu() throws IOException {
        List<String[]> data = new ArrayList<>();
        String semester;
        String fileName = "";
        System.out.println("===============================");
        System.out.println("1. All enrollment of a student");
        System.out.println("2. All students in a course");
        System.out.println("3. All course offered in a semester");
        int choice = Console.validateInt("Your selection (1-3): ",1,3);
        switch (choice) {
            case 1 -> {
                Console.displayStudentList();
                int studentIndex = Console.validateID("Type in student ID: ", EnrolmentList.getInstance().getStudentList());
                fileName = EnrolmentList.getInstance().getStudentList().get(studentIndex).getId().concat("_");
                System.out.println("This student enrolled in these semester:");
                List<String> semesters = EnrolmentList.getInstance().getStudentSemester(studentIndex);
                Console.displayObjectByIndex(semesters);
                int semesterIndex = Console.validateInt("Select the semester you want to view (index number): ", 0, semesters.size() - 1);
                semester = semesters.get(semesterIndex);
                List<Course> enrolledCourse = EnrolmentList.getInstance().getEnrolledCourse(studentIndex, semester);
                for (Course course : enrolledCourse) {
                    data.add(course.objectToString());
                }
                System.out.println("Course this student enrolled: ");
                Console.displayObjectByIndex(enrolledCourse);
            }
            case 2 -> {
                Console.displayCourseList();
                int courseIndex = Console.validateCourseID("Type in course ID: ", EnrolmentList.getInstance().getCourseList());
                fileName = EnrolmentList.getInstance().getCourseList().get(courseIndex).getId().concat("_");
                System.out.println("Semester this course offered: ");
                List<String> semesters = EnrolmentList.getInstance().getCourseSemester(courseIndex);
                Console.displayObjectByIndex(semesters);
                int semesterIndex = Console.validateInt("Selection: ", 0, semesters.size() - 1);
                semester = semesters.get(semesterIndex);
                List<Student> students = EnrolmentList.getInstance().getEnrolledStudent(courseIndex, semester);
                for (Student student : students) {
                    data.add(student.objectToString());
                }
                System.out.println("Student in enrolled " + EnrolmentList.getInstance().getCourseList().get(courseIndex).getName() + " semester " + semester);
                Console.displayObjectByIndex(students);

            }
            default -> {
                semester = Console.semesterInput();
                List<Course> courseList = EnrolmentList.getInstance().getEnrolledCourse(semester);
                System.out.println("Course offered in semester " + semester);
                Console.displayObjectByIndex(courseList);
                for (Course course : courseList) {
                    data.add(course.objectToString());
                }
            }
        }
        if (data.isEmpty()) {
            System.out.println("No data found!");
            System.out.println("Nothing to export...");
        } else {
            System.out.println("-----------------------");
            System.out.println("Save as csv file?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            choice = Console.validateInt("Your selection: ");
            if (choice == 1) {
                String saveName = fileName.concat(semester);
                FileManager.saveAsCSVFile(data, saveName);
                System.out.println("File save as " + saveName + ".csv");
                System.out.println("Exiting.....");
                System.exit(0);
            } else {
                System.out.println("Returning to main menu....");
            }
        }

    }

    public static void main(String[] args) throws IOException {
	// write your code here
        //Load data from csv files
        String courseFile = "courses.csv";
        String studentFile = "students.csv";
        String enrollmentFile = "default.csv";
        List<String[]> courseData = FileManager.readFileCSV(courseFile);
        List<String[]> studentData = FileManager.readFileCSV(studentFile);
        EnrolmentList.getInstance().setCourseList(FileManager.stringToCourseObj(courseData));
        EnrolmentList.getInstance().setStudentList(FileManager.stringToStudentObj(studentData));


        System.out.println("-----------------------------------------");
        System.out.println("Do you want to load any enrollment data?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = Console.validateInt("Your choice: ");
        if (choice == 1) {
            boolean valid;
            do {
                enrollmentFile = Console.input("Type in your file name: ").toLowerCase();
                File temp = new File(enrollmentFile);
                valid = temp.exists();
            } while (!enrollmentFile.matches("^.*\\.(CSV)$") && !valid);

        }
        List<String[]> enrollmentData = FileManager.readFileCSV(enrollmentFile);
        EnrolmentList.getInstance().setStudentEnrollmentList(FileManager.stringToEnrollmentObj(enrollmentData));
        while (true) {
            Console.displayEnrollmentList();

            choice = menu();
            switch (choice) {
                case 1 -> addEnrollmentMenu(enrollmentFile);
                case 2 -> updateEnrollmentMenu(enrollmentFile);
                case 3 -> reportMenu();
                default -> {
                    System.out.println("shutting down...");
                    System.exit(0);
                }
            }
        }

    }
}
