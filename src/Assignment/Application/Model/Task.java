package Assignment.Application.Model;

import java.util.LinkedList;
import java.util.List;


public class Task {

    private int id;
    private String taskName;
    private List<Skill> reqSkillSet;


    public Task() {
        id = -1;
        taskName = "";
        reqSkillSet  = new LinkedList<>();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<Skill> getReqSkillSet() {
        return reqSkillSet;
    }

    public void addReqSkillSet(Skill sk)
    {
        this.reqSkillSet.add(sk);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReqSkillSet(List<Skill> reqSkillSet) {
        this.reqSkillSet = reqSkillSet;
    }
}
