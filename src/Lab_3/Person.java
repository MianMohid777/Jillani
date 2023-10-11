package Lab_3;


import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Person {

    private String name;
    private int age;
    private String location;

    private int scd;
    private int pf;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getScd() {
        return scd;
    }

    public void setScd(int scd) {
        this.scd = scd;
    }

    public int getPf() {
        return pf;
    }

    public void setPf(int pf) {
        this.pf = pf;
    }

    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Lab_3/input.csv");

        BufferedReader buffer = new BufferedReader(file);

        String line = buffer.readLine();;


        FileWriter outputFile = new FileWriter("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Lab_3/output.csv");
        BufferedWriter bufferWriter = new BufferedWriter(outputFile);

        String head = "SCD AVG,PF AVG\n";

        bufferWriter.write(head);

        int totalScd = 0;
        int totalPf = 0;

        int itr = 0;


        List<Person> p = new LinkedList<>();
        while ((line = buffer.readLine()) != null)
        {
            String[] person = line.split(",");

            Person node = new Person();
            node.setName(person[0]);
            node.setAge(Integer.parseInt(person[1]));
            node.setLocation(person[2]);
            node.setScd(Integer.parseInt(person[3]));
            node.setPf(Integer.parseInt(person[4]));

            totalScd+=node.getScd();
            totalPf+=node.getPf();
            itr++;
            p.add(node);

        }

        String avgLine = String.valueOf((totalScd/(float)itr)) + "," + String.valueOf((totalPf/(float)itr)) + "\n";
        bufferWriter.write(avgLine);
        buffer.close();
        bufferWriter.close();
    }
}
