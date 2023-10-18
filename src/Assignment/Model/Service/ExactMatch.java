package Assignment.Model.Service;

import Assignment.Model.Entity.Applicant;
import Assignment.Model.Entity.Task;
import Assignment.Model.Entity.Skill;

import java.io.IOException;
import java.util.*;

public class ExactMatch implements Strategy {


    private HashMap<Integer, Applicant> resourceMap;
    private HashMap<Integer, Task> taskMap;


    public ExactMatch() {
        resourceMap = new HashMap<>();
        taskMap = new HashMap<>();
    }

    public ExactMatch(HashMap<Integer, Applicant> resourceMap, HashMap<Integer, Task> taskMap) {
        this.resourceMap = resourceMap;
        this.taskMap = taskMap;
    }

    public HashMap<Integer,List<Applicant>> matchingStrategy() {

        HashMap<Integer,List<Applicant>> exactMatchMap = new HashMap<>();

        for(int m = 1; m <=taskMap.size();m++) {

            Task taskNode = taskMap.get(m); // Get the Task Object from Map

            List<Skill> reqList = taskNode.getReqSkillSet(); // Required Skill List
            Skill reqSkill; // For getting skill node from Task;

            Applicant resourceNode; // For getting Applicant from Resource List
            List<Skill> currList; // For getting the skill set of applicants
            Skill currSkill; // For getting skill node from Applicant;


            boolean isMatch = false;
            int exactCheck = 0;

            Vector<Boolean> tracker = new Vector<>(); // track the skill match
            List<Applicant> exactMatch = new LinkedList<>(); // Matched Resource


            for (int i = 0, j = 1; j <=resourceMap.size(); j++) {
                reqSkill = reqList.get(i); // Retrieving the Required Skill

                resourceNode = resourceMap.get(j); // Get a Resource
                currList = resourceNode.getSkillSet(); // Skill Set of Resource

                int store = i; // Store the index of skill Node passed

                for (int k = 0; k < currList.size() && !isMatch; ) {
                    currSkill = currList.get(k); // Get Skill from Set

                    int currLevel = (currSkill.getExp() > 3) ? 3 : currSkill.getExp();

                    if (tracker.size() == reqList.size()) {
                        isMatch = true;
                    } else if (Objects.equals(reqSkill.getSkillName(), currSkill.getSkillName()) && reqSkill.getExp() == currLevel) {

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

            if(!exactMatch.isEmpty())
             exactMatchMap.put(m,exactMatch);

        }

        return exactMatchMap;
    }



}
