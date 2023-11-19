package Assignment.Presentation.View;


import Assignment.Application.Model.Applicant;
import Assignment.Presentation.Controller.StrategyController;
import Assignment.Application.Model.Skill;
import Assignment.Application.Model.Task;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class TaskForm {

    private JFrame formFrame;
    private JPanel fPanel;

    private JPanel fPanel2;
    private JTextField name;
    private JComboBox<String> skills;
    private JComboBox<String> exp;

    private JButton addBtn1;
    private JButton addBtn2;

    private JButton deleteBtn;
    private JButton deleteBtn2;

    private JButton updateBtn;
    private JButton updateBtn2;

    private JTable taskTable;

    private StrategyController control;

    public TaskForm(StrategyController controller) throws SQLException {

        control = controller;

        formFrame = new JFrame("Task Form");
        formFrame.setSize(800, 350);
        formFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fPanel = new JPanel(new FlowLayout());
        fPanel2 = new JPanel(new FlowLayout());
        JPanel topPanel = new JPanel(new GridBagLayout());

        JLabel rName = new JLabel("Task Name: ");
        name = new JTextField(20);



        DefaultTableModel tableModel = new DefaultTableModel();
        taskTable = new JTable(tableModel);
        tableModel.addColumn("Id");
        tableModel.addColumn("Name");
        tableModel.addColumn("Skill Set");


        List<Task> taskList = control.getAllT();

        for(Task t: taskList) {
            Vector<Object> k = new Vector<>();

            k.add(t.getId());
            k.add(t.getTaskName());

            t.setReqSkillSet(control.getSkillT((long)t.getId()));
            List<Skill> list = t.getReqSkillSet();
            StringBuilder set = new StringBuilder();

            for (Skill s : list) {
                set.append(s.getSkillName()).append("(").append(s.getExp()).append(") ");
            }

            k.add(set);

            tableModel.addRow(k);
        }


        String[] s = {
                "-Select-",
                "Java",
                "Python",
                "JavaScript",
                "C",
                "Assembly",
                "C++",
                "C#",
                "Ruby",
                "Swift",
                "Kotlin",
                "SQL"
        };


        JLabel skName = new JLabel("Skill: ");
        skills = new JComboBox<>(s);

        String[] y = {"Beginner", "Intermediate", "Expert"};

        JLabel expVal = new JLabel("Experience: ");
        exp = new JComboBox<>(y);

        addBtn1 = new JButton("Add");
        addBtn2 = new JButton("Add");
        deleteBtn = new JButton("Delete");
        updateBtn = new JButton("Update");


        deleteBtn.setSize(50, 50);
        updateBtn.setSize(50, 50);

        addBtn1.setForeground(Color.ORANGE);
        deleteBtn.setForeground(Color.RED);
        updateBtn.setForeground(Color.BLUE);



        fPanel.add(rName);
        fPanel.add(name);

        fPanel.add(addBtn1);
        fPanel.add(updateBtn);
        fPanel.add(deleteBtn);

        addBtn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Object> rowData = new Vector<>();
                String val = name.getText();

                if(!val.isEmpty())
                {
                    if(taskTable.getSelectedRow() == -1) {
                        try {
                            Task t = control.addTask(val);
                            rowData.add(t.getId());
                            rowData.add(name.getText());
                            rowData.add("");

                            tableModel.addRow(rowData);
                            name.setText("");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }


                    }
                    else {
                        JOptionPane.showMessageDialog(formFrame, "Task Already Added !");
                        taskTable.getSelectionModel().clearSelection();
                        name.setText("");
                    }
                }

                else
                    JOptionPane.showMessageDialog(formFrame,"Please ! Enter a Name of Resource to Add");
            }
        });

        taskTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(taskTable.getSelectedRow() != -1)
                    name.setText((String) taskTable.getValueAt(taskTable.getSelectedRow(),1));

            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(taskTable.getSelectedRow() != -1)
                {
                    Task t = control.getTask((Integer) taskTable.getValueAt(taskTable.getSelectedRow(),0));

                    if(!(t.getTaskName().equals(name.getText())))
                    {
                        t.setTaskName(name.getText());
                        taskTable.setValueAt(name.getText(),taskTable.getSelectedRow(),1);
                        try {
                            control.updateT(taskTable.getSelectedRow()+1,t.getId());
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        taskTable.getSelectionModel().clearSelection();
                    }
                }
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(taskTable.getSelectedRow() != -1)
                {
                    try {
                        control.deleteTask((long) (taskTable.getSelectedRow() +1));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    tableModel.removeRow(taskTable.getSelectedRow());
                    taskTable.getSelectionModel().clearSelection();
                }
            }
        });
        fPanel2.add(skName);
        fPanel2.add(skills);

        skills.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()== ItemEvent.SELECTED)
                {
                    if(Objects.equals(skills.getSelectedItem(), "-Select-"))
                    {
                        JOptionPane.showMessageDialog(formFrame,"Select a Valid Value");
                    }
                }
            }
        });

        fPanel2.add(expVal);
        fPanel2.add(exp);

        addBtn2.setSize(50, 50);

        fPanel2.add(addBtn2);

        deleteBtn2  =new JButton("Delete Skill");
        deleteBtn2.setSize(78,70);
        deleteBtn2.setForeground(Color.RED);

        updateBtn2  =new JButton("Update Experience");
        updateBtn2.setSize(100,100);
        updateBtn2.setForeground(Color.BLUE);

        fPanel2.add(updateBtn2);
        fPanel2.add(deleteBtn2);

        addBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(taskTable.getSelectedRow() >-1)
                {
                    String skillName = (String) skills.getSelectedItem();
                    int skID = skills.getSelectedIndex();

                    if(Objects.equals(skills.getSelectedItem(), "-Select-"))
                    {
                        JOptionPane.showMessageDialog(formFrame,"Select a Valid Option");
                    }
                    else {

                        int id1 = (int) taskTable.getValueAt(taskTable.getSelectedRow(),0);

                        try {
                            for(Skill sk : control.getSkillT((long)id1))
                            {
                                if(sk.getSkillName().equals(skills.getSelectedItem()))
                                {JOptionPane.showMessageDialog(null,"Skill Already Added !");
                                    return;
                                }

                            }

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        int num = exp.getSelectedIndex()+1;


                        try {
                            control.addReqSkills(id1, skillName,skID, num);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        Task tk = control.getTask(id1);
                        List<Skill> list = tk.getReqSkillSet();
                        StringBuilder set = new StringBuilder();

                        String k = "";
                        for (Skill s : list) {

                            if(s.getExp()==1)
                                k = "B";
                            else if(s.getExp() ==2)
                                k = "I";
                            else if(s.getExp()==3)
                                k = "E";
                            else
                                k="";

                            set.append(s.getSkillName()).append("(").append(k).append(") ");

                        }

                        tableModel.setValueAt(set, taskTable.getSelectedRow(), 2);
                        taskTable.getSelectionModel().clearSelection();


                    }
                }
            }
        });


        updateBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(taskTable.getSelectedRow() >-1)
                {
                    String skillName = (String) skills.getSelectedItem();
                    int skId =skills.getSelectedIndex();

                    if(skills.getSelectedItem().equals("-Select-"))
                    {
                        JOptionPane.showMessageDialog(formFrame,"Select a Valid Option");
                    }
                    else {

                        int id1 = (int) taskTable.getValueAt(taskTable.getSelectedRow(),0);

                        try {
                            for(Skill sk : control.getSkillT((long)id1))
                            {
                                if(sk.getSkillName().equals(skills.getSelectedItem()))
                                {
                                    int num = exp.getSelectedIndex()+1;

                                    control.addReqSkills(id1, skillName,skId, num);

                                    Task t= control.getTask(id1);

                                    List<Skill> list = t.getReqSkillSet();
                                    StringBuilder set = new StringBuilder();

                                    String k = "";
                                    for (Skill s : list) {

                                        if (s.getExp() == 1)
                                            k = "B";
                                        else if (s.getExp() == 2)
                                            k = "I";
                                        else if (s.getExp() == 3)
                                            k = "E";
                                        else
                                            k = "";

                                        set.append(s.getSkillName()).append("(").append(k).append(") ");

                                    }

                                    tableModel.setValueAt(set, taskTable.getSelectedRow(), 2);
                                    taskTable.getSelectionModel().clearSelection();
                                    return;
                                }

                            }

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }

            }
        });

        deleteBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(taskTable.getSelectedRow() >-1)
                {
                    String skillName = (String) skills.getSelectedItem();
                    int skId =skills.getSelectedIndex();

                    if(skills.getSelectedItem().equals("-Select-"))
                    {
                        JOptionPane.showMessageDialog(formFrame,"Select a Valid Option");
                    }
                    else {

                        int id1 = (int) taskTable.getValueAt(taskTable.getSelectedRow(), 0);
                        try {

                            for(Skill sk : control.getSkillT((long)id1)) {

                                if(sk.getSkillName().equals(skills.getSelectedItem())) {

                                    control.deleteSkillT((long) id1, (long) skId);

                                    Task t= control.getTask(id1);

                                    List<Skill> list = t.getReqSkillSet();
                                    StringBuilder set = new StringBuilder();

                                    String k = "";
                                    for (Skill s : list) {

                                        if (s.getExp() == 1)
                                            k = "B";
                                        else if (s.getExp() == 2)
                                            k = "I";
                                        else if (s.getExp() == 3)
                                            k = "E";
                                        else
                                            k = "";

                                        set.append(s.getSkillName()).append("(").append(k).append(") ");

                                    }

                                    tableModel.setValueAt(set, taskTable.getSelectedRow(), 2);

                                    taskTable.getSelectionModel().clearSelection();
                                    return;
                                }
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }});




        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        topPanel.add(fPanel, c);


        JScrollPane scrollPane = new JScrollPane(taskTable);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        c.gridy = 1;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(scrollPane, c);


        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.weighty = 1.0;
        topPanel.add(fPanel2, c);


        formFrame.setContentPane(topPanel);



        formFrame.setResizable(false);
        formFrame.setVisible(true);
    }

    public JFrame getFormFrame() {
        return formFrame;
    }

    public static void main(String[]args) throws SQLException
    {
        TaskForm t = new TaskForm(new StrategyController());
    }

}
