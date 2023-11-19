package Assignment.Presentation.View;

import Assignment.Application.Model.Applicant;
import Assignment.Presentation.Controller.StrategyController;
import Assignment.Application.Model.Skill;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class ResourceForm {

    private JFrame formFrame;
    private JPanel fPanel;

    private JPanel fPanel2;
    private JTextField name;
    private JComboBox<String> skills;
    private JComboBox<Integer> exp;

    private JComboBox<String> existingSkills;
    private JButton addBtn1;
    private JButton addBtn2;

    private JButton deleteBtn;
    private JButton deleteBtn2;

    private JButton updateBtn;
    private JButton updateBtn2;

    private JTable resourceTable;

    private StrategyController control;

    public ResourceForm(StrategyController controller) throws SQLException {

        control = controller;

        formFrame = new JFrame("Resource Form");
        formFrame.setSize(900, 450);
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


        List<Applicant> applicantList = control.getAllR();

        for(Applicant a: applicantList) {
            Vector<Object> k = new Vector<>();

            k.add(a.getId());
            k.add(a.getName());

            a.setSkillSet(control.getSkillR((long)a.getId()));
            List<Skill> list = a.getSkillSet();
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

        Integer[] y = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        JLabel expVal = new JLabel("Experience: ");
        exp = new JComboBox<>(y);

        addBtn1 = new JButton("Add");
        addBtn2 = new JButton("Add Skill");
        deleteBtn = new JButton("Delete");
        updateBtn = new JButton("Update");

        addBtn1.setSize(50, 50);
        deleteBtn.setSize(50, 50);
        updateBtn.setSize(50, 50);

        addBtn1.setForeground(Color.ORANGE);
        addBtn2.setForeground(Color.ORANGE);
        deleteBtn.setForeground(Color.RED);

        updateBtn.setForeground(Color.BLUE);


        fPanel.add(rName);
        fPanel.add(name);

        fPanel.add(addBtn1);
        fPanel.add(updateBtn);
        fPanel.add(deleteBtn);

        GridBagConstraints c = new GridBagConstraints();


        addBtn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Object> rowData = new Vector<>();
                String val = name.getText();

                if(!val.isEmpty())
                {
                    if(resourceTable.getSelectedRow() == -1) {
                        try {
                            Applicant a = control.addResource(val);
                            rowData.add(a.getId());
                            rowData.add(name.getText());
                            rowData.add("");

                            tableModel.addRow(rowData);
                            name.setText("");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }


                    }
                    else {
                        JOptionPane.showMessageDialog(formFrame, "Resource Already Added !");
                        resourceTable.getSelectionModel().clearSelection();
                        name.setText("");
                    }
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


        JPanel dyno = new JPanel(new BorderLayout());

       resourceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
           @Override
           public void valueChanged(ListSelectionEvent e) {

               if(resourceTable.getSelectedRow() != -1)
               {
                   name.setText((String) resourceTable.getValueAt(resourceTable.getSelectedRow(),1));

               }

           }
       });

       updateBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               if(resourceTable.getSelectedRow() != -1)
               {
                   Applicant a = control.getResource((Integer) resourceTable.getValueAt(resourceTable.getSelectedRow(),0));

                   if(!(a.getName().equals(name.getText())))
                   {
                        a.setName(name.getText());
                        resourceTable.setValueAt(name.getText(),resourceTable.getSelectedRow(),1);
                       try {
                           control.updateR(resourceTable.getSelectedRow()+1,a.getId());
                       } catch (SQLException ex) {
                           throw new RuntimeException(ex);
                       }
                       resourceTable.getSelectionModel().clearSelection();
                   }
               }
           }
       });

       deleteBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               if(resourceTable.getSelectedRow() != -1)
               {
                   try {
                       control.deleteResource((long) (resourceTable.getSelectedRow() +1));
                   } catch (SQLException ex) {
                       throw new RuntimeException(ex);
                   }
                   tableModel.removeRow(resourceTable.getSelectedRow());
                   resourceTable.getSelectionModel().clearSelection();
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

                if(resourceTable.getSelectedRow() >-1)
                {
                    String skillName = (String) skills.getSelectedItem();
                    int skId =skills.getSelectedIndex();

                    if(skills.getSelectedItem().equals("-Select-"))
                    {
                        JOptionPane.showMessageDialog(formFrame,"Select a Valid Option");
                    }
                    else {

                        int id1 = (int) resourceTable.getValueAt(resourceTable.getSelectedRow(),0);

                        try {
                            for(Skill sk : control.getSkillR((long)id1))
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
                            control.addSkills(id1, skillName,skId, num);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        Applicant app = control.getResource(id1);

                        List<Skill> list = app.getSkillSet();
                        StringBuilder set = new StringBuilder();

                        for (Skill s : list) {
                            set.append(s.getSkillName()).append("(").append(s.getExp()).append(") ");

                        }

                        tableModel.setValueAt(set, resourceTable.getSelectedRow(), 2);
                        resourceTable.getSelectionModel().clearSelection();

                    }
                }
            }
        });

        updateBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(resourceTable.getSelectedRow() >-1)
                {
                    String skillName = (String) skills.getSelectedItem();
                    int skId =skills.getSelectedIndex();

                    if(skills.getSelectedItem().equals("-Select-"))
                    {
                        JOptionPane.showMessageDialog(formFrame,"Select a Valid Option");
                    }
                    else {

                        int id1 = (int) resourceTable.getValueAt(resourceTable.getSelectedRow(),0);

                        try {
                            for(Skill sk : control.getSkillR((long)id1))
                            {
                                if(sk.getSkillName().equals(skills.getSelectedItem()))
                                {
                                    int num = exp.getSelectedIndex()+1;

                                    control.addSkills(id1, skillName,skId, num);

                                    Applicant app = control.getResource(id1);

                                    List<Skill> list = app.getSkillSet();
                                    StringBuilder set = new StringBuilder();

                                    for (Skill s : list) {
                                        set.append(s.getSkillName()).append("(").append(s.getExp()).append(") ");

                                    }

                                    tableModel.setValueAt(set, resourceTable.getSelectedRow(), 2);
                                    resourceTable.getSelectionModel().clearSelection();
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

                if(resourceTable.getSelectedRow() >-1)
                {
                    String skillName = (String) skills.getSelectedItem();
                    int skId =skills.getSelectedIndex();

                    if(skills.getSelectedItem().equals("-Select-"))
                    {
                        JOptionPane.showMessageDialog(formFrame,"Select a Valid Option");
                    }
                    else {

                        int id1 = (int) resourceTable.getValueAt(resourceTable.getSelectedRow(), 0);
                        try {

                            for(Skill sk : control.getSkillR((long)id1)) {

                                if(sk.getSkillName().equals(skills.getSelectedItem())) {

                                    control.deleteSkillR((long) id1, (long) skId);

                                    Applicant app = control.getResource(id1);

                                    List<Skill> list = app.getSkillSet();
                                    StringBuilder set = new StringBuilder();

                                    for (Skill s : list) {
                                        set.append(s.getSkillName()).append("(").append(s.getExp()).append(") ");

                                    }

                                    tableModel.setValueAt(set, resourceTable.getSelectedRow(), 2);

                                    resourceTable.getSelectionModel().clearSelection();
                                    return;
                                }
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
            }
        }});

        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        topPanel.add(fPanel, c);


        JScrollPane scrollPane = new JScrollPane(resourceTable);
        scrollPane.setPreferredSize(new Dimension(500, 300));
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

    public JFrame getFormFrame() {
        return formFrame;
    }

    public static void main(String[]args) throws SQLException {
        ResourceForm r = new ResourceForm(new StrategyController());
    }

}


