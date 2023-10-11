package Quiz;

public class StudCourse {

    private String rollNo;
    private int courseId;

    private int marks;

    public StudCourse(String rollNo, int courseId, int marks) {
        this.rollNo = rollNo;
        this.courseId = courseId;
        this.marks = marks;
    }

    public String getRollNo() {
        return rollNo;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
