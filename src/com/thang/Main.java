package com.thang;

import Classes.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        String courseFile = "courses.csv";
        String studentFile = "students.csv";
        String enrollmentFile = "default.csv";
        List<String[]> courseData = FileManager.readFileCSV(courseFile);
        List<String[]> studentData = FileManager.readFileCSV(studentFile);
        List<String[]> enrollmentData = FileManager.readFileCSV(enrollmentFile);

        EnrolmentList listManage = EnrolmentList.getInstance();
        listManage.setCourseList(FileManager.stringToCourseObj(courseData));
        listManage.setStudentList(FileManager.stringToStudentObj(studentData));
        listManage.setStudentEnrollmentList(FileManager.stringToEnrollmentObj(enrollmentData));
        int mchoice;
        int index = Console.validateID("Type in id: ",listManage.getStudentList());
        int cindex = Console.validateCourseID("Course ID: ",listManage.getCourseList());
        String a;
        String sem;

        System.out.println("1. 2020A");
        System.out.println("2. 2020B");
        System.out.println("3. 2020C");
        System.out.println("4. 2021A");
        System.out.println("5. 2021B");
        System.out.println("6. 2021C");
        mchoice = Console.validateInt("Type in your choice (1 - 6): ",1,6);

        sem = switch (mchoice) {
            case 1 -> "2020A";
            case 2 -> "2020B";
            case 3 -> "2020C";
            case 4 -> "2021A";
            case 5 -> "2021B";
            default -> "2021C";
        };


        StudentEnrollment enroll = new StudentEnrollment(listManage.getStudentList().get(index),listManage.getCourseList().get(cindex),sem);
        listManage.add(enroll);
        System.out.println("Enrollment successful!");

        for (StudentEnrollment enrollment : listManage.getStudentEnrollmentList()) {
            System.out.println(enrollment.toString());
        }

        index = Console.validateID("Type in id: ",listManage.getStudentList());
        System.out.println("1. 2020A");
        System.out.println("2. 2020B");
        System.out.println("3. 2020C");
        System.out.println("4. 2021A");
        System.out.println("5. 2021B");
        System.out.println("6. 2021C");
        mchoice = Console.validateInt("Type in your choice (1 - 6): ",1,6);

        sem = switch (mchoice) {
            case 1 -> "2020A";
            case 2 -> "2020B";
            case 3 -> "2020C";
            case 4 -> "2021A";
            case 5 -> "2021B";
            default -> "2021C";
        };


        List<Course> enrolledCourse = listManage.getEnrolledCourse(index,sem);

        System.out.println("Enrolled courses in sem " + sem);

        for (Course course : enrolledCourse) {
            System.out.println(course.toString());
        }







    }
}
