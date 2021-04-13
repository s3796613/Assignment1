package Classes;

import java.util.Date;

public class Student {


    private String id;
    private String name;
    private String birthDate;

    public Student(String id, String name, String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }



    @Override
    public String toString() {
        return "Student ID: " + id + "  " + name + "  " + birthDate;
    }



    public String[] objectToString() {
        return new String[]{id,name,birthDate};
    }
}
