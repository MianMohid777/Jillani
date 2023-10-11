package Quiz;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Management {

    private Map<String, Student> studentMap;
    private Map<Integer, Course> courseMap;

    private Map<String, Teacher> teacherMap;

    private LinkedList<StudCourse> studCourse;

    public Management() throws IOException {
        this.studentMap = new HashMap<>();
        this.courseMap = new HashMap<>();
        this.teacherMap = new HashMap<>();
        studCourse = new LinkedList<>();

        readStudentTable();
        readCourseTable();
        readTeacherTable();
    }

    private void readStudentTable() throws IOException {
        FileReader file = new FileReader("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Quiz/Student.csv");

        BufferedReader buffer = new BufferedReader(file);

        String line = buffer.readLine();

        while ((line = buffer.readLine()) != null) {
            String[] student = line.split(",");

            Student s = new Student(student[0], student[1], new LinkedList<>());

            studentMap.put(student[0], s);


        }

        buffer.close();

    }

    private void readTeacherTable() throws IOException {
        FileReader file = new FileReader("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Quiz/Teacher.csv");

        BufferedReader buffer = new BufferedReader(file);

        String line = buffer.readLine();

        while ((line = buffer.readLine()) != null) {
            String[] teacher = line.split(",");

            Teacher t = new Teacher(teacher[0], teacher[1], Integer.parseInt(teacher[2]),null);

            teacherMap.put(teacher[0], t);
        }

        buffer.close();

    }


    private void readCourseTable() throws IOException {
        FileReader file = new FileReader("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Quiz/Course.csv");

        BufferedReader buffer = new BufferedReader(file);

        String line = buffer.readLine();

        while ((line = buffer.readLine()) != null) {
            String[] course = line.split(",");

            Course c = new Course(Integer.parseInt(course[0]), course[1], course[2]);

            courseMap.put(Integer.parseInt(course[0]), c);
        }

        buffer.close();

    }

    public void addStudent(String rollNo, String name) throws IOException {
        Student s = new Student(rollNo,name,new LinkedList<>());

        if(!studentMap.containsKey(rollNo))
        {
            studentMap.put(rollNo,s);

            FileWriter write = new FileWriter("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Quiz/Student.csv");

            BufferedWriter buffWrite = new BufferedWriter(write);

            String line = rollNo + "," + name  + "\n";

            buffWrite.write(line);

            buffWrite.close();

            return;
        }

        System.out.println("Error");
    }

    public void addTeacher(String cnic, String name) throws IOException {
        Teacher t = new Teacher(cnic,name,0,null);

        if(!teacherMap.containsKey(cnic))
        {
            teacherMap.put(cnic,t);

            FileWriter write = new FileWriter("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Quiz/Teacher.csv");

            BufferedWriter buffWrite = new BufferedWriter(write);

            String line = cnic + "," + name + "," + "0" +  "\n";

            buffWrite.write(line);

            buffWrite.close();

            return;
        }

        System.out.println("Error");

    }

    public void addCourse(int id, String c_Name,String t_cnic) throws IOException {
        Course c = new Course(id,c_Name,t_cnic);

        if(!courseMap.containsKey(id))
        {
            courseMap.put(id,c);

            FileWriter write = new FileWriter("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Quiz/Course.csv");

            BufferedWriter buffWrite = new BufferedWriter(write);

            String line = id + "," + c_Name + "," + t_cnic +  "\n";

            buffWrite.write(line);

            buffWrite.close();

            return;
        }

        System.out.println("Error");

    }


    public void assignCourseStud(String rollNo,int courseId)
    {
        if(studentMap.containsKey(rollNo) && courseMap.containsKey(courseId))
        {
            Student s = studentMap.get(rollNo);
            Course c = courseMap.get(courseId);
            s.addCourse(c);

            return;
        }
        System.out.println("Error");
    }


    private void assignTeach(String cnic,int courseId) throws IOException {
        FileReader file = new FileReader("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Quiz/Course.csv");

        BufferedReader buffer = new BufferedReader(file);

        String line = buffer.readLine();

        boolean found = false;
        while(!found)
        {
            line = buffer.readLine();
            if(line == null)
                found = true;
            else {
                String[] temp = line.split(",");

                if (temp[0].equals(String.valueOf(courseId))) {

                    temp[2] = String.valueOf(courseId);

                    line = temp[0] + "," + temp[1] + "," + temp[2] + "\n";


                    FileWriter write = new FileWriter("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Quiz/Course.csv");

                    BufferedWriter buffWrite = new BufferedWriter(write);

                    buffWrite.write(line);
                    buffWrite.close();


                    return;
                }

            }
        }
        System.out.println("Error");

    }
    public void assignCourseTeach(String cnic,int courseId) throws IOException {

        if(teacherMap.containsKey(cnic) && courseMap.containsKey(courseId))
        {
            Teacher t = teacherMap.get(cnic);
            Course c = courseMap.get(courseId);
            t.setCourseId(courseId);
            t.setCurrCourse(c);


            FileReader file = new FileReader("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Quiz/Teacher.csv");

            BufferedReader buffer = new BufferedReader(file);

            String line = buffer.readLine();

            boolean found = false;
            while(!found)
            {
                line = buffer.readLine();
               if(line == null)
                   found = true;
               else {
                   String[] temp = line.split(",");

                   if (temp[0].equals(cnic)) {
                       line = line.replace("0", String.valueOf(courseId));


                       FileWriter write = new FileWriter("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Quiz/Teacher.csv");

                       BufferedWriter buffWrite = new BufferedWriter(write);

                       buffWrite.write(line);
                       buffWrite.close();

                       assignTeach(cnic,courseId);
                       return;
                   }
               }
            }


        }
        System.out.println("Error");
    }


    public void setMarks(String rollNo, int courseId,int marks) throws IOException {
        for(int i = 0; i< studCourse.size();i++)
        {
            if(studCourse.get(i).getRollNo().equals(rollNo) && Integer.valueOf(studCourse.get(i).getCourseId()).equals(courseId))
            {
                studCourse.get(i).setMarks(marks);
                return;
            }
        }


        StudCourse s = new StudCourse(rollNo, courseId, marks);

        studCourse.add(s);


        String line = rollNo + "," + courseId + "," + marks + "\n";

        FileWriter write = new FileWriter("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Quiz/Marks.csv");

        BufferedWriter buffWrite = new BufferedWriter(write);

        buffWrite.write(line);


    buffWrite.close();
    }

    private char calculateGrade(int marks)
    {
        if(marks > 90)
            return 'A';
        else if(marks < 90 && marks > 70)
            return 'B';
        else if(marks < 70 && marks > 60)
            return 'C';
        else if(marks < 60 && marks > 50)
            return 'D';
        else if(marks < 50 && marks > -1)
            return 'F';
        else return 'X';

    }

    public void gradeFinder(int courseId)
    {
        for(int i = 0; i <studCourse.size();i++)
        {
            if(Integer.valueOf(studCourse.get(i).getCourseId()).equals(courseId))
            {
                System.out.println(studentMap.get(studCourse.get(i).getRollNo()).getName() + " has Grade => "+ calculateGrade(studCourse.get(i).getMarks()));
            }
        }
    }

    public void searchStudent(String rollNo)
    {
        Student s = studentMap.get(rollNo);

        for(int i = 0; i < s.getCourses().size();i++)
        {
            System.out.println("Course Name; " + s.getCourses().get(i).getCourseName());
            System.out.println("Teacher Name: " + teacherMap.get(s.getCourses().get(i).getTeacherCnic()).getName());
        }
    }

 public static void main(String[] args) throws IOException {
     Management m = new Management();

     m.assignCourseStud("1",1);
     m.searchStudent("1");

     m.setMarks("1",1,100);
     m.gradeFinder(1);

 }
}
