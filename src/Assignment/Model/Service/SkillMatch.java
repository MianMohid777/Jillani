package Assignment.Model.Service;

import Assignment.Model.Entity.Applicant;
import Assignment.Model.Entity.Task;
import Assignment.Model.Entity.Skill;

import java.util.*;

public class SkillMatch implements Strategy {



    private HashMap<Integer, Applicant> resourceMap;
    private HashMap<Integer, Task> taskMap;


    public SkillMatch() {
        resourceMap = new HashMap<>();
        taskMap = new HashMap<>();
    }

    public SkillMatch(HashMap<Integer, Applicant> resourceMap, HashMap<Integer, Task> taskMap) {
        this.resourceMap = resourceMap;
        this.taskMap = taskMap;
    }

    public HashMap<Integer,List<Applicant>> matchingStrategy() {

        HashMap<Integer,List<Applicant>> skillMatchMap = new HashMap<>();

        for(int n = 1; n <= taskMap.size();n++) {

            Task taskNode = taskMap.get(n); // Get the Task Object from Map

            List<Skill> reqList = taskNode.getReqSkillSet(); // Required Skill List
            Skill reqSkill; // For getting skill node from Task;

            Applicant resourceNode; // For getting Applicant from Resource List
            List<Skill> currList; // For getting the skill set of applicants
            Skill currSkill; // For getting skill node from Applicant;


            boolean isMatch = false;

            Vector<Boolean> tracker = new Vector<>(); // track the skill match
            List<Applicant> skillMatch = new LinkedList<>(); // Matched Resource


            for (int i = 0, j = 1; j <=resourceMap.size(); j++) {
                reqSkill = reqList.get(i); // Retrieving the Required Skill

                resourceNode = resourceMap.get(j); // Get a Resource
                currList = resourceNode.getSkillSet(); // Skill Set of Resource

                int store = i; // Store the index of skill Node passed

                for (int k = 0; k < currList.size() && !isMatch; ) {
                    currSkill = currList.get(k); // Get Skill from Set

                    if (tracker.size() == reqList.size()) {
                        isMatch = true;
                    } else if (Objects.equals(reqSkill.getSkillName(), currSkill.getSkillName())) {

                        if (i + 1 < reqList.size()) {
                            reqSkill = reqList.get(++i); // Retrieving the Required Skill
                            k = 0;
                        }
                        tracker.add(true); // Tracked

                    } else
                        k++;

                }

                if (tracker.size() == reqList.size()) {
                    skillMatch.add(resourceNode);
                }

                tracker.clear(); // tracker cleared
                i = 0;
                isMatch = false;


            }

            if(!skillMatch.isEmpty())
            skillMatchMap.put(n,skillMatch);
        }

        return skillMatchMap;


    }
}
