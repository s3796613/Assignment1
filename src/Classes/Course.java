package Classes;

public class Course {
    private String id;
    private String name;
    private String numberOfCredits;


    public Course(String id, String name, String credit) {
        this.id = id;
        this.name = name;
        this.numberOfCredits = credit;
    }

    @Override
    public String toString() {
        return "Course ID: " + id + " " + name + "  Credit: " + numberOfCredits;
    }
}
