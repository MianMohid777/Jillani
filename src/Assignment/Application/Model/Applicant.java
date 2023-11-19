package Assignment.Application.Model;


import java.util.LinkedList;
import java.util.List;


public class Applicant {

    private int id;
    private String name;
    private List<Skill> skillSet;

    public Applicant()
    {
        id=-1;
        name = "";
        skillSet = new LinkedList<>();

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Skill> getSkillSet() {
        return skillSet;
    }

    public void addSkillSet(Skill sk) {
        this.skillSet.add(sk);
    }

    public void setSkillSet(List<Skill> skillSet) {
        this.skillSet = skillSet;
    }

    public int getId() {
        return id;
    }
}
