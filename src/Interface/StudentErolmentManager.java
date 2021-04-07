package Interface;

import Classes.StudentEnrolment;

public interface StudentErolmentManager {
    void add();
    void update();
    void delete();
    StudentEnrolment getOne();
    StudentEnrolment getAll();
}
