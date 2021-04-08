package Interface;

import Classes.StudentEnrollment;

import java.util.List;

public interface StudentEnrollmentManager {
    void add(StudentEnrollment enrollment);
    void update(StudentEnrollment enrollment);
    void delete(StudentEnrollment enrollment);

    StudentEnrollment getOne();

    List<StudentEnrollment> getAll();
}
