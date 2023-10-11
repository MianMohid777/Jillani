package Assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class Utility {

    private Vector<String> parseString(String line)
    {
        Vector<String> token = new Vector<>();

        String name = "";
        String skill = "";
        String exp = "";

        char temp = '\0';
        boolean flag = false;


        for(int i = 0; i < line.length(); i++)
        {
            temp = line.charAt(i);


            if((temp >= 'A' && temp <= 'Z') || (temp >= 'a' && temp <= 'z'))
            {
                if(!flag)
                {
                    name += String.valueOf(temp); // Name Token
                }
                else if(i!= 0 && line.charAt(i-1) == ':')
                {
                    exp += String.valueOf(temp);
                }
                else
                {
                    skill += String.valueOf(temp); // Skill Name Token
                }
            }
            else if( temp == '|') {

                token.add(name);
                flag = true;
            }
            else if((temp >= '0' && temp <= '9'))
            {
                exp += String.valueOf(temp);
            }
            else if(temp == ',' || temp == '\n')
            {
                token.add(skill);
                token.add(exp);

                skill = "";
                exp = "";
            }
            else if(temp != ' ' && temp != ':')
            {
                skill += String.valueOf(temp); // Skill Name Token
            }
            else if(!flag && temp == ' ')
            {
                name += String.valueOf(temp); // Name Token
            }

            if((i+1) == line.length())
            {
                token.add(skill);
                token.add(exp);
            }

        }


        return token;
    }

    private Skill createSkill(String name,int val)
    {
        Skill s = new Skill();
        s.setSkillName(name);
        s.setExp(val);
        return s;
    }


    public List<Applicant> fetchAppData() throws IOException {

        FileReader file = new FileReader("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Assignment/applicants.txt");
        BufferedReader buffer = new BufferedReader(file);

        List<Applicant> applicants = new LinkedList<>();

        String str = buffer.readLine();
        String fetchName = "";
        String fetchExp = "";
        int num = 0;

        while(str != null)
        {
            Vector<String> tokenize = parseString(str);

            Applicant node = new Applicant();

            node.setName(tokenize.get(0)); // Applicant Name


            for(int i = 1;i < tokenize.size();i++)
            {
                fetchName = tokenize.get(i); // Skill Name
                fetchExp = tokenize.get(++i); // Experience
                num = Integer.parseInt(fetchExp);



                node.addSkillSet(createSkill(fetchName,num)); // Create and Push Skill Node in List of Skills

            }

            applicants.add(node); // Pushing applicants in List



            str = buffer.readLine(); // Read Next Line
        }

        buffer.close();

        return applicants;
    }

    public List<Task> fetchTaskData() throws IOException {

        FileReader file = new FileReader("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Assignment/task.txt");

        BufferedReader buffer = new BufferedReader(file);

        List<Task> tasks = new LinkedList<>();

        String str = buffer.readLine();
        String fetchName = "";
        String fetchExp = "";
        int num = 0;
        byte[] b;

        while(str != null)
        {
            Vector<String> tokenize = parseString(str);

            Task node = new Task();

            node.setTaskName(tokenize.get(0)); // Applicant Name


            for(int i = 1;i < tokenize.size();i++)
            {
                fetchName = tokenize.get(i); // Skill Name
                fetchExp = tokenize.get(++i); // Experience


                if(Objects.equals(fetchExp, "b"))
                    num = 1;
                else if(Objects.equals(fetchExp, "i"))
                    num = 2;
                else if(Objects.equals(fetchExp, "e"))
                   num = 3;
                else num = -1;

                node.addReqSkillSet(createSkill(fetchName,num)); // Create and Push Skill Node in List of Skills

            }

            tasks.add(node); // Pushing tasks in List



            str = buffer.readLine(); // Read Next Line
        }

        buffer.close();


        return tasks;
    }

}
