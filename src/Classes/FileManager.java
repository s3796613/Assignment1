package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {


    public static boolean startFile(String filePath) {
        try {
            File myObj = new File(filePath);
            return myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

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
        String[] studentData = {data[0],data[1],data[2]};
        String[] courseData = {data[3],data[4],data[5]};
        String enrollSemester = data[6];
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
            ioe.printStackTrace();
        }

        return data;
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
