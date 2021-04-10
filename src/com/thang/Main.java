package com.thang;

import Classes.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void menu() {
        String courseFile = "course.csv";
        String studentFile = "student.csv";
        String enrollmentFile = "default.csv";

        System.out.println("MAIN MENU");

    }

    public static void main(String[] args) throws IOException {
	// write your code here
        //Load data from csv files
        String courseFile = "courses.csv";
        String studentFile = "students.csv";
        String enrollmentFile = "default.csv";
        String testFile = "test2.csv";
        List<String[]> courseData = FileManager.readFileCSV(courseFile);
        List<String[]> studentData = FileManager.readFileCSV(studentFile);
        List<String[]> enrollmentData = FileManager.readFileCSV(enrollmentFile);

        EnrolmentList listManage = EnrolmentList.getInstance();
        listManage.setCourseList(FileManager.stringToCourseObj(courseData));
        listManage.setStudentList(FileManager.stringToStudentObj(studentData));
        listManage.setStudentEnrollmentList(FileManager.stringToEnrollmentObj(enrollmentData));

        FileManager.writeEnrollmentToCSV(listManage.getAll(),testFile);
        System.out.println("Save student list as new file?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = Console.validateInt("Your choice (1/2): ",1,2);
        if (choice == 1) {
            FileManager.saveAsCSVFile(studentData,listManage.getStudentList().get(0).getId());
        }



//
//        Console.displayEnrollmentList();
//        int index = Console.validateID("Type in id: ",listManage.getStudentList());
//        Console.displayCourseList();
//        int cindex = Console.validateCourseID("Course ID: ",listManage.getCourseList());
//        String a = Console.semesterInput();
//
//
//
//        //add new enrollment
//        StudentEnrollment enroll = new StudentEnrollment(listManage.getStudentList().get(index),listManage.getCourseList().get(cindex),a);
//        listManage.add(enroll);
//        System.out.println("Enrollment successful!");
//
//        Console.displayEnrollmentList();
//
//
//
//        //view enrolled courses
//        index = Console.validateID("Type in id: ",listManage.getStudentList());
//
//        String sem = Console.semesterInput();
//
//
//        List<Course> enrolledCourse = listManage.getEnrolledCourse(index,sem);
//
//        System.out.println("Enrolled courses in sem " + sem);
//        Console.displayObjectByIndex(enrolledCourse);
//
//        int courseInt = Console.validateInt("Choose course to update: ",0,enrolledCourse.size()-1);
//        String selectedStudentId = EnrolmentList.getInstance().getStudentList().get(index).getId();
//        String selectedCourseID = enrolledCourse.get(courseInt).getId();
//
//        //update a student enrollment
//        int enrollIndex = Console.getEnrollmentIndex(selectedStudentId,selectedCourseID,sem);
//        EnrolmentList.getInstance().update(enrollIndex);
//
//        Console.displayEnrollmentList();
//
//        index = Console.validateID("Type in id: ",listManage.getStudentList());
//        sem = Console.semesterInput();
//        enrolledCourse = listManage.getEnrolledCourse(index,sem);
//        System.out.println("Enrolled courses in sem " + sem);
//        Console.displayObjectByIndex(enrolledCourse);
//
//
//        courseInt = Console.validateInt("Choose course to delete: ",0,enrolledCourse.size()-1);
//        selectedStudentId = EnrolmentList.getInstance().getStudentList().get(index).getId();
//        selectedCourseID = enrolledCourse.get(courseInt).getId();
//        enrollIndex = Console.getEnrollmentIndex(selectedStudentId,selectedCourseID,sem);
//        EnrolmentList.getInstance().delete(enrollIndex);
//
//        Console.displayEnrollmentList();








    }
}
