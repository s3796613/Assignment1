package com.thang;

import Classes.*;

import java.io.IOException;
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

        StudentEnrollment enroll = new StudentEnrollment(listManage.getStudentList().get(0),listManage.getCourseList().get(0),"2021A");
        listManage.add(enroll);

        for (StudentEnrollment enrollment : listManage.getStudentEnrollmentList()) {
            System.out.println(enrollment.toString());
        }

        String t1 = "s321";
        String t2 = "S321";
        System.out.println(t1.equals(t2));


    }
}
