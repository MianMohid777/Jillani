package Assignment.Application.Model;

public class Skill {

    private String skillName;
    private int exp;

    public Skill()
    {
        skillName = "";
        exp = 0;
    }
    public Skill(String skillName, int exp) {
        this.skillName = skillName;
        this.exp = exp;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
