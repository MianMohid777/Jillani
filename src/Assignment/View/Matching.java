package Assignment.View;

import Assignment.Controller.StrategyController;
import Assignment.Model.Entity.Applicant;
import Assignment.Model.Entity.Skill;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Matching {


    private JFrame frame;

    private JPanel hiPanel;
    private JPanel panel1;

    private JRadioButton box1;
    private JRadioButton box2;

    private JButton btn;

    private StrategyController control;
    public Matching(StrategyController controller)
    {
        control = controller;

        frame = new JFrame("Resource Allocator");
        frame.setSize(650,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1 = new JPanel(new FlowLayout());

        JLabel lbl1 = new JLabel("Exact Match");
        JLabel lbl12 = new JLabel("Skill-Only Match");

        box1 = new JRadioButton();
        box2 = new JRadioButton();

        btn = new JButton();
        btn.setText("Generate");

        ButtonGroup group = new ButtonGroup();

        group.add(box1);
        group.add(box2);

        panel1.add(lbl1,FlowLayout.LEFT);
        panel1.add(box1, FlowLayout.CENTER);

        panel1.add(lbl12,FlowLayout.RIGHT);
        panel1.add(box2, FlowLayout.LEADING);

        panel1.add(btn,FlowLayout.TRAILING);

        hiPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();


        DefaultTableModel resModel = new DefaultTableModel();
        JTable resTable = new JTable(resModel);
        resTable.setSize(500,250);

        resModel.addColumn("Task Name");
        resModel.addColumn("Resource(s)");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(box1.isSelected()) {
                    resModel.setRowCount(0);
                    HashMap<Integer,List<Applicant>> result= control.getExactMatched();
                    int itr = 1;
                    for(;itr<=control.getTaskMap().size();itr++)
                    {
                        Vector<Object> rowData = new Vector<>();
                        rowData.add(control.getTaskMap().get(itr).getTaskName());
                        rowData.add("");
                        resModel.addRow(rowData);

                    }

                        Set<Integer> keys = result.keySet();

                       for(Integer k: keys)
                       {
                           List<Applicant> list = result.get(k);
                           StringBuilder set = new StringBuilder();


                           for(Applicant a: list)
                           {
                               set.append(a.getName()).append(" , ");
                           }

                           resModel.setValueAt(set,k-1,1);

                       }

                }
                else if(box2.isSelected())
                {
                    resModel.setRowCount(0);
                    HashMap<Integer,List<Applicant>> result= control.getSkillMatched();
                    int itr = 1;
                    for(;itr<=control.getTaskMap().size();itr++)
                    {
                        Vector<Object> rowData = new Vector<>();
                        rowData.add(control.getTaskMap().get(itr).getTaskName());
                        rowData.add("");
                        resModel.addRow(rowData);

                    }

                    Set<Integer> keys = result.keySet();

                    for(Integer k: keys)
                    {
                        List<Applicant> list = result.get(k);
                        StringBuilder set = new StringBuilder();


                        for(Applicant a: list)
                        {
                            set.append(a.getName()).append(" , ");
                        }

                        resModel.setValueAt(set,k-1,1);

                    }
                }
                else
                    JOptionPane.showMessageDialog(frame,"Select a Matching Strategy to Find Resource");
            }
        });

        JScrollPane scrollPane = new JScrollPane(resTable);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTH;

        panel1.add(scrollPane,c);

        c.gridy = 1;
        c.weighty = 1.0;




        hiPanel.add(panel1,c);

        frame.setContentPane(hiPanel);

        frame.setResizable(false);

        frame.setVisible(true);

    }

    public static void main(String[] args)
    {
        Matching m = new Matching(new StrategyController());
    }
}
