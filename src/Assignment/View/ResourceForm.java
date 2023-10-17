package Assignment.View;

import Assignment.Entity.Applicant;
import Assignment.Controller.StrategyController;
import Assignment.Entity.Skill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

public class ResourceForm {

    private JFrame formFrame;
    private JPanel fPanel;

    private JPanel fPanel2;
    private JTextField name;
    private JComboBox<String> skills;
    private JComboBox<Integer> exp;

    private JButton addBtn1;
    private JButton addBtn2;

    private JTable resourceTable;

    private StrategyController control;

    public ResourceForm() {

        control = new StrategyController();

        formFrame = new JFrame("Resource Form");
        formFrame.setSize(600, 350);
        formFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fPanel = new JPanel(new FlowLayout());
        fPanel2 = new JPanel(new FlowLayout());
        JPanel topPanel = new JPanel(new GridBagLayout());

        JLabel rName = new JLabel("Resource Name: ");
        name = new JTextField(20);



        DefaultTableModel tableModel = new DefaultTableModel();
        resourceTable = new JTable(tableModel);
        tableModel.addColumn("Id");
        tableModel.addColumn("Name");
        tableModel.addColumn("Skill Set");


        Vector<Object> k = new Vector<>();
        k.add(1);
        k.add("Tester");
        k.add("Java(1) JavaScript(2) SQL(3) ");

        tableModel.addRow(k);

        control.addResource("Tester");

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

        Integer[] y = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

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
                    control.addResource(val);
                    rowData.add(control.getResourceMap().size());
                    rowData.add(name.getText());
                    rowData.add("");

                    tableModel.addRow(rowData);
                    name.setText("");

                }

                else
                    JOptionPane.showMessageDialog(formFrame,"Please ! Enter a Name of Resource to Add");
            }
        });


        fPanel2.add(skName);
        fPanel2.add(skills);

        skills.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()== ItemEvent.SELECTED)
                {
                    if(skills.getSelectedItem().equals("-Select-"))
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

                if(resourceTable.getSelectedRow() >-1)
                {
                    String skillName = (String) skills.getSelectedItem();

                    if(skills.getSelectedItem().equals("-Select-"))
                    {
                        JOptionPane.showMessageDialog(formFrame,"Select a Valid Option");
                    }
                    else {

                        int num = exp.getSelectedIndex() + 1;
                        int id = (int) resourceTable.getValueAt(resourceTable.getSelectedRow(), 0);

                        control.addSkills(id, skillName, num);

                        Applicant app = control.getResource(id);

                        List<Skill> list = app.getSkillSet();
                        StringBuilder set = new StringBuilder();

                        for (Skill s : list) {
                            set.append(s.getSkillName()).append("(").append(s.getExp()).append(") ");

                        }

                        tableModel.setValueAt(set, resourceTable.getSelectedRow(), 2);

                        JOptionPane.showMessageDialog(formFrame, skillName + " for " + id + " with exp " + num);
                        JOptionPane.showMessageDialog(formFrame, set);
                    }
                }
            }
        });

        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        topPanel.add(fPanel, c);


        JScrollPane scrollPane = new JScrollPane(resourceTable);
        scrollPane.setPreferredSize(new Dimension(350, 200));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        c.gridy = 1;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        topPanel.add(scrollPane, c);


        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.weighty = 1.0;
        topPanel.add(fPanel2, c);


        formFrame.setContentPane(topPanel);



        formFrame.setResizable(false);
        formFrame.setVisible(true);
    }



    public static void main(String[]args)
    {
        ResourceForm r = new ResourceForm();
    }

}
