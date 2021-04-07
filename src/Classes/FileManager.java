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
    private static List<Course> courseList = new ArrayList<>();
    private static List<Student> studentList = new ArrayList<>();


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

    private static Course createCourse(String[] data) {
        String id = data[0];
        String name = data[1];
        String credit = data[2];
        return new Course(id,name,credit);
    }

    private static Student createStudent(String[] data) {
        String id = data[0];
        String name = data[1];
        String birthDate = data[2];
        return new Student(id,name,birthDate);
    }

    public static List<Course> readFileCSV(String fileName, String type) throws IOException {

        Path pathToFile = Paths.get(fileName);
        try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                if (type == "course") {
                    Course course = createCourse(attributes);
                    courseList.add(course);
                } else {
                    Student student = createStudent(attributes);
                    studentList.add(student);
                }

                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return courseList;
    }







}
