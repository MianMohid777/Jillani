package Assignment.Entity;


import java.util.LinkedList;
import java.util.List;


public class Applicant {

    private String id;
    private String name;
    private List<Skill> skillSet;

    public Applicant()
    {
        id="";
        name = "";
        skillSet = new LinkedList<>();

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Skill> getSkillSet() {
        return skillSet;
    }

    public void addSkillSet(Skill sk) {
        this.skillSet.add(sk);
    }



}
