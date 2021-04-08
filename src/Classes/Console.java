package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);
    private Console() {}



    //-------------- Input validation ------------------
    //check if the input is integer

    public static int validateInt(String prompt) {
        System.out.print(prompt);
        int input;
        while (!scanner.hasNextInt()) {
            System.out.print(prompt);
            scanner.next();
        }
        input = scanner.nextInt();
        return input;
    }

    //check if the input is integer and is in range

    public static int validateInt(String prompt, int min, int max) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextInt();
        } while (!((input >= min) && (input <= max)));
        return input;
    }


    //return the index of object in the list by ID
    public static int validateID(String prompt, List<Student> studentList) {
        String input = input(prompt);
        while (true) {
            for (Student student : studentList) {
                if (input.equals(student.getId())) {
                    System.out.println("You are viewing: " + input);
                    return studentList.indexOf(student);
                }
            }
            input = input("ID not found, type again: ");
        }

    }

    public static int validateCourseID(String prompt, List<Course> courseList) {
        String input = input(prompt);
        while (true) {
            for (Course course : courseList) {
                if (input.equals(course.getId())) {

                    return courseList.indexOf(course);
                }
            }
            input = input("No course found with that ID, try again: ");
        }
    }

    public static String input(String prompt) {

        System.out.println(prompt);
        String input = scanner.nextLine();
        return input;
    }

    public static int getIndexObj(Object o,List list) {
        int index = list.indexOf(o);
        return 0;
    }

}
