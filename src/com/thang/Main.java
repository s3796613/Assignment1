package com.thang;

import Classes.Console;
import Classes.Course;
import Classes.FileManager;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        String courseFile = "courses.csv";
        String studentFile = "student.csv";
        int choice;
        List<Course> courseList = FileManager.readFileCSV(courseFile,"course");
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println(i + ". " + courseList.get(i).toString());
        }

        choice = Console.validateInt("Type in your choice: ",0,courseList.size() - 1);
        System.out.println(courseList.get(choice).toString());

    }
}
