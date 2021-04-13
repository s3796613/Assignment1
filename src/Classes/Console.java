package Classes;

import java.util.List;
import java.util.Locale;
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
                    System.out.println("You are selecting: " + input + "-" + student.getName());
                    return studentList.indexOf(student);
                }
            }
            input = input("ID not found, type again: ");
        }

    }

    //validate course id and return the course index from course list
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

    //get the index of enrollment in student enrollment list
    public static int getEnrollmentIndex(String studentID, String courseID, String semester) {

        for (StudentEnrollment enrollment : EnrollmentList.getInstance().getAll()) {
            if (studentID.equals(enrollment.getStudent().getId()) && courseID.equals(enrollment.getCourse().getId()) && semester.equals( enrollment.getSemester())) {
                return EnrollmentList.getInstance().getAll().indexOf(enrollment);
            }
        }
        return -1;
    }


    //get string input from the user
    public static String input(String prompt) {
        System.out.println(prompt);
        return scanner.next().toUpperCase(Locale.ROOT);
    }

    //get semester input from the user
   public static String semesterInput() {
        System.out.println("Choose the semester:");
        System.out.println("1. 2020A");
        System.out.println("2. 2020B");
        System.out.println("3. 2020C");
        System.out.println("4. 2021A");
        System.out.println("5. 2021B");
        System.out.println("6. 2021C");
        int choice = Console.validateInt("Type in your choice (1 - 6): ",1,6);

       return switch (choice) {
          case 1 -> "2020A";
          case 2 -> "2020B";
          case 3 -> "2020C";
          case 4 -> "2021A";
          case 5 -> "2021B";
          default -> "2021C";
      };
   }


   //Display functions
   public static void displayStudentList() {
       System.out.printf("%-20s %-20s %-15s %n","STUDENT ID","NAME","DATE OF BIRTH");
       System.out.println("---------------------------------------------------------");
        for (Student student : EnrollmentList.getInstance().getStudentList()) {
            System.out.printf("%-20s %-20s %-15s %n",student.getId(),student.getName(),student.getBirthDate());
        }
       System.out.println();
   }

   public static void displayCourseList() {
       System.out.printf("%-20s %-20s %-15s %n","COURSE ID","COURSE NAME","NUMBER OF CREDIT");
       System.out.println("---------------------------------------------------------------");
       for (Course course : EnrollmentList.getInstance().getCourseList()) {
           System.out.printf("%-15s %-35s %-15s %n",course.getId(),course.getName(),course.getNumberOfCredits());
       }
       System.out.println();

   }

   public static void displayEnrollmentList() {
       System.out.printf("%-15s %-22s %-17s %-15s %-35s %-15s %-10s %n","SID","SNAME","BIRTHDATE","CID","CNAME","CREDIT","SEMESTER");
       System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

       for (StudentEnrollment enrollment : EnrollmentList.getInstance().getAll()) {
           String sID = enrollment.getStudent().getId();
           String sName = enrollment.getStudent().getName();
           String sDate = enrollment.getStudent().getBirthDate();
           String courseID = enrollment.getCourse().getId();
           String courseName = enrollment.getCourse().getName();
           String courseCredit = enrollment.getCourse().getNumberOfCredits();
           String semester = enrollment.getSemester();
           System.out.printf("%-15s %-22s %-17s %-15s %-35s %-15s %-10s %n",sID,sName,sDate,courseID,courseName,courseCredit,semester);
       }
       System.out.println();
   }

   public static void displayObjectByIndex(List objects) {
        for (int i = 0; i < objects.size(); i++) {
            System.out.println(i + ". " + objects.get(i).toString());
        }
   }




}
