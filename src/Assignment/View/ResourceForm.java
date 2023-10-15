package Assignment.View;

import Assignment.Entity.Applicant;
import Assignment.Controller.StrategyController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        formFrame.setSize(550, 350);
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



        String[] s = {
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

        fPanel2.add(expVal);
        fPanel2.add(exp);

        addBtn2.setSize(50, 50);

        fPanel2.add(addBtn2);

        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        topPanel.add(fPanel, c);


        JScrollPane scrollPane = new JScrollPane(resourceTable);
        scrollPane.setPreferredSize(new Dimension(150, 50));

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

    public void setFormFrame(JFrame formFrame) {
        this.formFrame = formFrame;
    }

    public JPanel getfPanel() {
        return fPanel;
    }

    public void setfPanel(JPanel fPanel) {
        this.fPanel = fPanel;
    }

    public JPanel getfPanel2() {
        return fPanel2;
    }

    public void setfPanel2(JPanel fPanel2) {
        this.fPanel2 = fPanel2;
    }

    public JTextField getName() {
        return name;
    }

    public void setName(JTextField name) {
        this.name = name;
    }

    public JComboBox<String> getSkills() {
        return skills;
    }

    public void setSkills(JComboBox<String> skills) {
        this.skills = skills;
    }

    public JComboBox<Integer> getExp() {
        return exp;
    }

    public void setExp(JComboBox<Integer> exp) {
        this.exp = exp;
    }

    public JButton getAddBtn1() {
        return addBtn1;
    }

    public void setAddBtn1(JButton addBtn1) {
        this.addBtn1 = addBtn1;
    }

    public JButton getAddBtn2() {
        return addBtn2;
    }

    public void setAddBtn2(JButton addBtn2) {
        this.addBtn2 = addBtn2;
    }

    public JTable getResourceTable() {
        return resourceTable;
    }

    public void setResourceTable(JTable resourceTable) {
        this.resourceTable = resourceTable;
    }

    public static void main(String[]args)
    {
        ResourceForm r = new ResourceForm();
    }

}
