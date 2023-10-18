package Assignment.View;


import Assignment.Controller.StrategyController;
import Assignment.Model.Entity.Skill;
import Assignment.Model.Entity.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

    private JTable taskTable;

    private StrategyController control;

    public TaskForm(StrategyController controller) {

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


        Vector<Object> k = new Vector<>();
        k.add(1);
        k.add("Web Development");
        k.add("JavaScript(E) Java(I) ");
        control.addTask("Web Development");
        control.addReqSkills(1,"JavaScript",3);
        control.addReqSkills(1,"Java",2);
        tableModel.addRow(k);

        k = new Vector<>();
        k.add(2);
        k.add("Data Analytics");
        k.add("Python(E) JavaScript(I) ");
        control.addTask("Data Analytics");
        control.addReqSkills(2,"Python",3);
        control.addReqSkills(2,"JavaScript",2);
        tableModel.addRow(k);

        k = new Vector<>();
        k.add(3);
        k.add("System Programming");
        k.add("C(E) C++(I) Assembly(I)");
        control.addTask("System Programming");
        control.addReqSkills(3,"C",2);
        control.addReqSkills(3,"C++",2);
        control.addReqSkills(3,"Assembly",2);
        tableModel.addRow(k);



        String[] s = {
                "-Select-",
                "Java",
                "Python",
                "JavaScript",
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

        addBtn1.setSize(50, 50);

        fPanel.add(rName);
        fPanel.add(name);

        fPanel.add(addBtn1);

        addBtn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Object> rowData = new Vector<>();
                String val = name.getText();
                JOptionPane.showMessageDialog(formFrame,val);
                if(!val.isEmpty())
                {
                    control.addTask(val);
                    rowData.add(control.getTaskMap().size());
                    rowData.add(name.getText());
                    rowData.add("");

                    tableModel.addRow(rowData);
                    name.setText("");

                }

                else
                    JOptionPane.showMessageDialog(formFrame,"Please ! Enter a Name of Task to Add");
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

        addBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(taskTable.getSelectedRow() >-1)
                {
                    String skillName = (String) skills.getSelectedItem();

                    if(Objects.equals(skills.getSelectedItem(), "-Select-"))
                    {
                        JOptionPane.showMessageDialog(formFrame,"Select a Valid Option");
                    }
                    else {

                        int num = exp.getSelectedIndex() + 1;
                        int id = (int) taskTable.getValueAt(taskTable.getSelectedRow(), 0);

                        control.addReqSkills(id, skillName, num);

                        Task tk = control.getTask(id);

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


                        JOptionPane.showMessageDialog(formFrame, skillName + " for " + id + " with exp " + k);
                        JOptionPane.showMessageDialog(formFrame, set);
                    }
                }
            }
        });

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

    public static void main(String[]args)
    {
        TaskForm t = new TaskForm(new StrategyController());
    }

}
