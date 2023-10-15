package Assignment.Entity;

import java.util.LinkedList;
import java.util.List;


public class Task {

    private String taskName;
    private List<Skill> reqSkillSet;


    public Task() {
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




}
