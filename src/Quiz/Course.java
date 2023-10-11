package Quiz;

import java.util.LinkedList;

public class Course {

    private int id;
    private String courseName;

    private String teacherCnic;


    public Course(int id, String courseName, String teacherCnic) {
        this.id = id;
        this.courseName = courseName;
        this.teacherCnic = teacherCnic;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherCnic() {
        return teacherCnic;
    }
}
