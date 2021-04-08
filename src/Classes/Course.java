package Classes;

public class Course {


    private String id;
    private String name;
    private String numberOfCredits;


    //Constructor
    public Course(String id, String name, String credit) {
        this.id = id;
        this.name = name;
        this.numberOfCredits = credit;
    }


    //Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(String numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    @Override
    public String toString() {
        return "Course ID: " + id + " " + name + "  Credit: " + numberOfCredits;
    }
}
