package Assignment.Presentation.Controller;

import Assignment.Application.Model.Applicant;
import Assignment.Application.Model.Skill;
import Assignment.Application.Model.Task;
import Assignment.Application.Service.ExactMatch;
import Assignment.Application.Service.SkillMatch;
import Assignment.Application.Service.Strategy;
import Assignment.Persistance.DAO.Implementation.ResourceDAO;
import Assignment.Persistance.DAO.Implementation.ResourceSkillDAO;
import Assignment.Persistance.DAO.Implementation.TaskDAO;
import Assignment.Persistance.DAO.Implementation.TaskSkillDao;
import Assignment.Persistance.DAO.Interface.Resource_IDAO;
import Assignment.Persistance.DAO.Interface.Skill_DAO;
import Assignment.Persistance.DAO.Interface.Task_IDAO;
import Assignment.Presentation.View.StrategyUI;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class StrategyController {

    private HashMap<Integer, Applicant> resourceMap;
    private HashMap<Integer, Task> taskMap;

    private Strategy finder;

    private StrategyUI view;

    private Resource_IDAO resourceRepo;
    private Task_IDAO taskRepo;

    private Skill_DAO skillResourceRepo;
    private Skill_DAO skillTaskRepo;

    public StrategyController() throws SQLException {
        resourceMap = new HashMap<>();
        taskMap = new HashMap<>();
        view = new StrategyUI(this);
        finder = null;

        resourceRepo = new ResourceDAO();
        taskRepo = new TaskDAO();
        skillResourceRepo = new ResourceSkillDAO();
        skillTaskRepo = new TaskSkillDao();

        for(Applicant a: getAllR())
        {
            a.setSkillSet(skillResourceRepo.getSkills((long)a.getId()));
            resourceMap.put(a.getId(),a);
        }

        for(Task t:getAllT())
        {
            t.setReqSkillSet(skillTaskRepo.getSkills((long)t.getId()));
            taskMap.put(t.getId(),t);
        }
    }

    public Applicant addResource(String name) throws SQLException {

        Applicant a = new Applicant();

        a.setName(name);
        resourceRepo.save(a);
        resourceMap.put(a.getId(), a);

        return a;

    }

    public Task addTask(String name) throws SQLException {

        Task t = new Task();

        t.setTaskName(name);
        taskRepo.save(t);

        taskMap.put(t.getId(), t);

        return t;
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

    public void addSkills(int id, String skillName,int skillId, int exp) throws SQLException {

        if (resourceMap.containsKey(id)) {

            Skill sk = new Skill();
            sk.setSkillName(skillName);
            sk.setExp(exp);

            Applicant a = resourceMap.get(id);


            List<Skill> skSet = a.getSkillSet();

            if (skSet.isEmpty()) {
                skSet.add(sk);
                skillResourceRepo.saveSkill((long) id, (long) skillId, exp);

            } else {
                for (Skill s : skSet) {
                    if (s.getSkillName().equals(skillName)) {
                        s.setExp(exp);
                        skillResourceRepo.update((long) id, (long) skillId, exp);
                        return;
                    }
                }
                skSet.add(sk);
                skillResourceRepo.saveSkill((long) id, (long) skillId, exp);
            }
        }
    }



    public void addReqSkills(int id, String skillName ,int skillId, int exp) throws SQLException
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
                skillTaskRepo.saveSkill((long) id, (long) skillId,exp);
                return;
            }
            else
            {
                for(Skill s:skSet)
                {
                    if(s.getSkillName().equals(skillName))
                    {
                        s.setExp(exp);
                        skillTaskRepo.update((long) id, (long) skillId,exp);
                        return;
                    }
                }

                skSet.add(sk);
                skillTaskRepo.saveSkill((long) id, (long) skillId, exp);
            }
        }

    }

    public Applicant getResource(int id)
    {
        return resourceMap.get(id);
    }

    public Task getTask(int id) {
        return taskMap.get(id);
    }

    public HashMap<String,List<Applicant>> getSkillMatched()
    {
        finder = new SkillMatch(resourceMap,taskMap);
        return finder.matchingStrategy();


    }

    public HashMap<String,List<Applicant>> getExactMatched()
    {
        finder = new ExactMatch(resourceMap,taskMap);
        return finder.matchingStrategy();



    }

    public void deleteResource(Long id) throws SQLException {
        resourceRepo.delete(id);
        resourceMap.remove(Math.toIntExact(id));
    }

    public void deleteTask(Long id) throws SQLException {
        taskRepo.delete(id);
        taskMap.remove(Math.toIntExact(id));
    }

    public void deleteSkillR(Long id,Long skId) throws SQLException {
        skillResourceRepo.delete(id,skId);

      String name = skillResourceRepo.findBYId(skId);



      for(Skill s :  resourceMap.get(Math.toIntExact(id)).getSkillSet())
      {
          if(s.getSkillName().equals(name))
          {
              resourceMap.get(Math.toIntExact(id)).getSkillSet().remove(s);
              return;
          }
      }
    }

    public void deleteSkillT(Long id,Long skId) throws SQLException {
        skillTaskRepo.delete(id,skId);

        String name =skillTaskRepo.findBYId(skId);



        for(Skill s :  taskMap.get(Math.toIntExact(id)).getReqSkillSet())
        {
            if(s.getSkillName().equals(name))
            {
                taskMap.get(Math.toIntExact(id)).getReqSkillSet().remove(s);
                return;
            }
        }



    }
    public List<Applicant> getAllR() throws SQLException {
        try {
            return resourceRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getAllT() throws SQLException {
        try {
            return taskRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateR(int index,int id) throws SQLException {
        resourceRepo.update((long) index,resourceMap.get(id));
    }
    public List<Skill> getSkillR(Long id) throws SQLException {
        return skillResourceRepo.getSkills(id);
    }

    public List<Skill> getSkillT(Long id) throws SQLException {
        return skillTaskRepo.getSkills(id);
    }

    public void updateT(int index, int id) throws SQLException {
        taskRepo.update((long) index,taskMap.get(id));
    }
}
