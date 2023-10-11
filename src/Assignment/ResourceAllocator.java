package Assignment;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class ResourceAllocator {


    private List<Applicant> resourceList;
    private List<Task> taskList;


    public ResourceAllocator() throws IOException {

        Utility util = new Utility();
        this.resourceList = util.fetchAppData();
        this.taskList = util.fetchTaskData();
    }

    private int displayTask()
    {

        if(!taskList.isEmpty())
        {
            int index = 1;

            for (Task value : taskList) {
                System.out.println("Task " + index + ": " + value.getTaskName());
                index++;
            }

            System.out.print("\n For which Task you want allocate resources: \n");

            Scanner in = new Scanner(System.in);

            return in.nextInt();

        }

        return -1;
    }
    public void MatchingStrategy()
    {
        int t = displayTask(); // Get Task index

        if(t > 0 && t<= taskList.size())
        {
            Task taskNode = taskList.get(t-1); // Get the Task Object from List

            List<Skill> reqList = taskNode.getReqSkillSet(); // Required Skill List
            Skill reqSkill; // For getting skill node from Task;

            Applicant resourceNode; // For getting Applicant from Resource List
            List<Skill> currList; // For getting the skill set of applicants
            Skill currSkill; // For getting skill node from Applicant;


            boolean isMatch = false;
            int exactCheck = 0;

            Vector<Boolean> tracker = new Vector<>(); // track the skill match
            Vector<Applicant> exactMatch = new Vector<>(); // Matched Resource
            Vector<Applicant> skillMatch = new Vector<>(); // Matched Resource


            for(int i = 0,j = 0; j < resourceList.size();j++)
            {
                reqSkill = reqList.get(i); // Retrieving the Required Skill

                resourceNode = resourceList.get(j); // Get a Resource
                currList = resourceNode.getSkillSet(); // Skill Set of Resource

                int store = i; // Store the index of skill Node passed

                for(int k = 0; k < currList.size() && !isMatch;)
                {
                    currSkill = currList.get(k); // Get Skill from Set

                    if(tracker.size() == reqList.size())
                    {
                        isMatch = true;
                    }
                    else if(Objects.equals(reqSkill.getSkillName(), currSkill.getSkillName()) && reqSkill.getExp() == currSkill.getExp()) {

                        if (i + 1 < reqList.size()) {
                            reqSkill = reqList.get(++i); // Retrieving the Required Skill
                            k = 0;
                        }
                        tracker.add(true); // Tracked
                        exactCheck++;
                    }
                    else if(Objects.equals(reqSkill.getSkillName(), currSkill.getSkillName())) {

                        if (i + 1 < reqList.size()) {
                            reqSkill = reqList.get(++i); // Retrieving the Required Skill
                            k = 0;
                        }
                        tracker.add(true); // Tracked

                    }
                    else
                        k++;

                }

                if(tracker.size() == reqList.size() && exactCheck == reqList.size())
                {
                     exactMatch.add(resourceNode); // Matched Successfully
                     skillMatch.add(resourceNode);
                }
                else if(tracker.size() == reqList.size())
                {
                    skillMatch.add(resourceNode);
                }

                tracker.clear(); // tracker cleared
                i = 0;
                isMatch = false;
                exactCheck = 0;

            }

            System.out.println("\nExactly Matched Resource(s) for this Task => ");
            for(Applicant a: exactMatch)
            {
                System.out.println(a.getName());
            }


            System.out.println("\nSkill Only Matched Resource(s) for this Task => ");
            for(Applicant a: skillMatch)
            {
                System.out.println(a.getName());
            }
        }
        else
            System.out.println( "Error !");

    }


    public static void main(String[] args) throws IOException {
        ResourceAllocator matcher = new ResourceAllocator();

        matcher.MatchingStrategy();
    }
}
