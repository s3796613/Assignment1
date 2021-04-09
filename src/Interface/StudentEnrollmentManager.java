package Interface;

import Classes.StudentEnrollment;

import java.util.List;

public interface StudentEnrollmentManager {
    void add(StudentEnrollment enrollment);
    void update(int index);
    void delete(int index);
    StudentEnrollment getOne(int index);
    List<StudentEnrollment> getAll();
}
