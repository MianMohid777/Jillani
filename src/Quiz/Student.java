package Quiz;

import java.util.LinkedList;

public class Student {

    private String rollNo;
    private String name;

    LinkedList<Course> courses;

    public Student(String rollNo, String name, LinkedList<Course> courses) {
        this.rollNo = rollNo;
        this.name = name;
        this.courses = courses;
    }

    public void addCourse(Course c)
    {
        courses.add(c);
    }

    public String getName() {
        return name;
    }

    public LinkedList<Course> getCourses() {
        return courses;
    }
}
