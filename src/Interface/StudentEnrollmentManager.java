package Interface;

import Classes.StudentEnrollment;

import java.util.List;

public interface StudentEnrollmentManager {
    void add();
    void update();
    void delete();

    StudentEnrollment getOne();

    List<StudentEnrollment> getAll();
}
