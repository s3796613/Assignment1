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


    //Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumberOfCredits() {
        return numberOfCredits;
    }


    @Override
    public String toString() {
        return "Course ID: " + id + " " + name + "  Credit: " + numberOfCredits;
    }

    public String[] objectToString() {
        return new String[]{id,name,numberOfCredits};
    }
}
