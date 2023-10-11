package Assignment;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Applicant {

    private String name;
    private List<Skill> skillSet;

    public Applicant()
    {
        name = "";
        skillSet = new LinkedList<>();

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Skill> getSkillSet() {
        return skillSet;
    }

    public void addSkillSet(Skill sk) {
        this.skillSet.add(sk);
    }



}
