package Quiz;

public class Teacher {

    private String cnic;
    private String name;

    private Course currCourse;

    private int courseId;

    public Teacher(String cnic, String name,int courseId, Course currCourse) {
        this.cnic = cnic;
        this.name = name;
        this.courseId = courseId;
        this.currCourse = currCourse;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCurrCourse(Course currCourse) {
        this.currCourse = currCourse;
    }

    public String getCnic() {
        return cnic;
    }

    public String getName() {
        return name;
    }
}
