package Assignment.Service;

import Assignment.Entity.Applicant;
import Assignment.Entity.Task;
import Assignment.Entity.Skill;

import java.io.IOException;
import java.util.*;

public class ExactMatch implements Strategy {


    private List<Applicant> resourceList;
    private List<Task> taskList;


    public ExactMatch() throws IOException {


        this.resourceList = new LinkedList<>();
        this.taskList = new LinkedList<>();
    }


    public void matchingStrategy() {


        Task taskNode = taskList.get(0); // Get the Task Object from List

        List<Skill> reqList = taskNode.getReqSkillSet(); // Required Skill List
        Skill reqSkill; // For getting skill node from Task;

        Applicant resourceNode; // For getting Applicant from Resource List
        List<Skill> currList; // For getting the skill set of applicants
        Skill currSkill; // For getting skill node from Applicant;


        boolean isMatch = false;
        int exactCheck = 0;

        Vector<Boolean> tracker = new Vector<>(); // track the skill match
        Vector<Applicant> exactMatch = new Vector<>(); // Matched Resource


        for (int i = 0, j = 0; j < resourceList.size(); j++) {
            reqSkill = reqList.get(i); // Retrieving the Required Skill

            resourceNode = resourceList.get(j); // Get a Resource
            currList = resourceNode.getSkillSet(); // Skill Set of Resource

            int store = i; // Store the index of skill Node passed

            for (int k = 0; k < currList.size() && !isMatch; ) {
                currSkill = currList.get(k); // Get Skill from Set

                if (tracker.size() == reqList.size()) {
                    isMatch = true;
                } else if (Objects.equals(reqSkill.getSkillName(), currSkill.getSkillName()) && reqSkill.getExp() == currSkill.getExp()) {

                    if (i + 1 < reqList.size()) {
                        reqSkill = reqList.get(++i); // Retrieving the Required Skill
                        k = 0;
                    }
                    tracker.add(true); // Tracked
                    exactCheck++;
                } else
                    k++;

            }

            if (tracker.size() == reqList.size() && exactCheck == reqList.size()) {
                exactMatch.add(resourceNode); // Matched Successfully

            }


            tracker.clear(); // tracker cleared
            i = 0;
            isMatch = false;
            exactCheck = 0;

        }

        System.out.println("\nExactly Matched Resource(s) for this Task => ");
        for (Applicant a : exactMatch) {
            System.out.println(a.getName());
        }


    }



}
