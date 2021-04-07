package Interface;

import Classes.StudentEnrolment;

import java.util.ArrayList;

public interface StudentErolmentManager {
    void add();
    void update();
    void delete();

    StudentEnrolment getOne();

    ArrayList<StudentEnrolment> getAll();
}
