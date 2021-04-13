package Classes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {



    //Create course object from string data
    private static Course createCourse(String[] data) {
        String id = data[0];
        String name = data[1];
        String credit = data[2];
        return new Course(id,name,credit);
    }

    //Create student object from string data
    private static Student createStudent(String[] data) {
        String id = data[0];
        String name = data[1];
        String birthDate = data[2];
        return new Student(id,name,birthDate);
    }

    //Create Student enrollment object from string data
    public static StudentEnrollment createEnrolment(String[] data) {
        String[] studentData = new String[3];
        String[] courseData = new String[3];
        String enrollSemester = null;
        try {
            studentData = new String[]{data[0], data[1], data[2]};
            courseData = new String[]{data[3], data[4], data[5]};
            enrollSemester = data[6];

        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("The file is empty or invalid data format");
            System.out.println("Shutting down the program.....");
            System.exit(0);
        }

        return new StudentEnrollment(createStudent(studentData),createCourse(courseData),enrollSemester);
    }

    //Read csv file and return data as string
    public static List<String[]> readFileCSV(String fileName) throws IOException {
        List<String[]> data = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                data.add(attributes);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            System.out.println("File not found");
            System.out.println("Ending the program....");
            ioe.printStackTrace();
            System.exit(0);
        }
        if (data.size() == 0) {
            System.out.println("The file data is empty or the format is invalid");
            System.exit(0);
        }

        return data;
    }


    //write the enrollment to the current csv file
    public static void writeEnrollmentToCSV(List<StudentEnrollment> studentEnrollmentList, String fileName) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName,false));){

            List<String[]> dataToWrite = new ArrayList<>();
            for (StudentEnrollment studentEnrollment : studentEnrollmentList) {
                dataToWrite.add(studentEnrollment.objectToString());
            }
            for (String[] tokens : dataToWrite) {
                pw.write(String.join(",",tokens));
                pw.println();
            }

        }
        catch (FileNotFoundException exception) {
            System.out.println("Error occur while saving");
            exception.printStackTrace();
        }


    }

    //save string data to a csv file
    public static void saveAsCSVFile(List<String[]> data, String fileName) throws IOException {
        fileName = fileName.concat(".csv");
//        System.out.println("Type in file name *.csv and cant be default.csv");
//        do {
//            fileName = Console.input("Type in file name: ");
//        } while (fileName.equals("DEFAULT.CSV") || !fileName.matches("^.*\\.(CSV)$"));
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName,false))) {
            for (String[] tokens : data) {
                pw.write(String.join(",",tokens));
                pw.println();
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Error occur");
            exception.printStackTrace();
        }
    }

    //Convert string data to course object and store it in a list
    public static List<Course> stringToCourseObj( List<String[]> data ) {
        List<Course> courseList = new ArrayList<>();
        for (String[] tokens : data) {
            Course course = createCourse(tokens);
            courseList.add(course);
        }
        return courseList;
    }

    //Convert string data to student object and store it in a list
    public static List<Student> stringToStudentObj( List<String[]> data ) {
        List<Student> studentList = new ArrayList<>();
        for (String[] tokens : data) {
            Student student = createStudent(tokens);
            studentList.add(student);
        }
        return studentList;
    }

    //Convert String data to student enrollment object and store it in a list
    public static List<StudentEnrollment> stringToEnrollmentObj( List<String[]> data ) {
        List<StudentEnrollment> enrollmentList = new ArrayList<>();
        for (String[] tokens : data) {
            StudentEnrollment enrollment = createEnrolment(tokens);
            enrollmentList.add(enrollment);
        }
        return enrollmentList;
    }









}
