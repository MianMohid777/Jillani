package Assignment.Controller;

import Assignment.Model.Entity.Applicant;
import Assignment.Model.Entity.Skill;
import Assignment.Model.Entity.Task;
import Assignment.Model.Service.ExactMatch;
import Assignment.Model.Service.SkillMatch;
import Assignment.Model.Service.Strategy;
import Assignment.View.StrategyUI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class StrategyController {

    private HashMap<Integer, Applicant> resourceMap;
    private HashMap<Integer, Task> taskMap;

    private Strategy finder;

    private StrategyUI view;

    public StrategyController()
    {
        resourceMap = new HashMap<>();
        taskMap = new HashMap<>();
        view = new StrategyUI(this);
        finder = null;
    }

    public void addResource(String name) {
        Applicant a = new Applicant();

        a.setName(name);
        a.setId(String.valueOf(resourceMap.size() + 1));

        resourceMap.put(resourceMap.size() + 1, a);
    }

    public void addTask(String name) {
        Task t = new Task();

        t.setTaskName(name);
        t.setId(String.valueOf(taskMap.size() + 1));

        taskMap.put(taskMap.size() + 1, t);
    }
    public HashMap<Integer, Applicant> getResourceMap() {
        return resourceMap;
    }

    public HashMap<Integer, Task> getTaskMap() {
        return taskMap;
    }

    public StrategyUI getView() {
        return view;
    }

    public void addSkills(int id, String skillName, int exp)
    {

        if(resourceMap.containsKey(id)) {
            Skill sk = new Skill();
            sk.setSkillName(skillName);
            sk.setExp(exp);

            Applicant a = resourceMap.get(id);

            List<Skill> skSet = a.getSkillSet();

            if(skSet.isEmpty())
            {
                skSet.add(sk);
            }
            else
            {
                for(Skill s:skSet)
                {
                    if(s.getSkillName().equals(skillName))
                    {
                        s.setExp(exp);
                        return;
                    }
                }

                skSet.add(sk);
            }
        }

    }

    public void addReqSkills(int id, String skillName, int exp)
    {

        if(taskMap.containsKey(id)) {
            Skill sk = new Skill();
            sk.setSkillName(skillName);
            sk.setExp(exp);

           Task t = taskMap.get(id);

            List<Skill> skSet = t.getReqSkillSet();

            if(skSet.isEmpty())
            {
                skSet.add(sk);
            }
            else
            {
                for(Skill s:skSet)
                {
                    if(s.getSkillName().equals(skillName))
                    {
                        s.setExp(exp);
                        return;
                    }
                }

                skSet.add(sk);
            }
        }

    }

    public Applicant getResource(int id)
    {
        return resourceMap.get(id);
    }

    public Task getTask(int id)
    {
        return taskMap.get(id);
    }

    public HashMap<Integer,List<Applicant>> getSkillMatched()
    {
        finder = new SkillMatch(resourceMap,taskMap);
        return finder.matchingStrategy();




    }

    public HashMap<Integer,List<Applicant>> getExactMatched()
    {
        finder = new ExactMatch(resourceMap,taskMap);
        return finder.matchingStrategy();



    }


}
