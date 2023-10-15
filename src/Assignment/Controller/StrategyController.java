package Assignment.Controller;

import Assignment.Entity.Applicant;
import Assignment.Entity.Skill;
import Assignment.View.StrategyUI;

import java.util.HashMap;
import java.util.List;

public class StrategyController {

    private HashMap<Integer, Applicant> resourceMap;

    private StrategyUI view;

    public StrategyController()
    {
        resourceMap = new HashMap<>();
        view = new StrategyUI();
    }

    public void addResource(String name) {
        Applicant a = new Applicant();

        a.setName(name);
        a.setId(String.valueOf(resourceMap.size() + 1));

        resourceMap.put(resourceMap.size() + 1, a);
    }

    public HashMap<Integer, Applicant> getResourceMap() {
        return resourceMap;
    }

    public void setResourceMap(HashMap<Integer, Applicant> resourceMap) {
        this.resourceMap = resourceMap;
    }

    public StrategyUI getView() {
        return view;
    }


    public void setView(StrategyUI view) {
        this.view = view;
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


    public Applicant getResource(int id)
    {
        return resourceMap.get(id);
    }

}
