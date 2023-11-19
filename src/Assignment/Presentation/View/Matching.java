package Assignment.Presentation.View;

import Assignment.Application.Model.Task;
import Assignment.Presentation.Controller.StrategyController;
import Assignment.Application.Model.Applicant;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class Matching {


    private JFrame frame;

    private JPanel hiPanel;
    private JPanel panel1;

    private JRadioButton box1;
    private JRadioButton box2;

    private JButton btn;

    private JButton export;


    private StrategyController control;
    public Matching(StrategyController controller)
    {
        control = controller;

        frame = new JFrame("Resource Allocator");
        frame.setSize(650,400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                int option = JOptionPane.showConfirmDialog(null,"Do you really want to exit !");

                if(option == JOptionPane.YES_OPTION)
                    System.exit(0);

            }

        });

        panel1 = new JPanel(new FlowLayout());

        JLabel lbl1 = new JLabel("Exact Match");
        JLabel lbl12 = new JLabel("Skill-Only Match");
        JLabel lbl13 = new JLabel("Export (.txt) ");

        box1 = new JRadioButton();
        box2 = new JRadioButton();

        btn = new JButton();
        btn.setText("Generate");

        export = new JButton("Export");
        export.setForeground(Color.RED);



        ButtonGroup group = new ButtonGroup();

        group.add(box1);
        group.add(box2);

        panel1.add(lbl1,FlowLayout.LEFT);
        panel1.add(box1, FlowLayout.CENTER);

        panel1.add(lbl12,FlowLayout.RIGHT);
        panel1.add(box2, FlowLayout.LEADING);

        panel1.add(export,FlowLayout.TRAILING);

        panel1.add(btn,FlowLayout.TRAILING);



        hiPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();


        DefaultTableModel resModel = new DefaultTableModel();
        JTable resTable = new JTable(resModel);
        resTable.setSize(500,250);

        resModel.addColumn("Task Name");
        resModel.addColumn("Resource(s)");

        StringBuilder txt = new StringBuilder();

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (box1.isSelected()) {
                    resModel.setRowCount(0);
                    HashMap<String, List<Applicant>> result = control.getExactMatched();
                    int itr = 0;

                    try {
                        List<Task> listTask = control.getAllT();

                        for (; itr < listTask.size(); itr++) {
                            Vector<Object> rowData = new Vector<>();
                            rowData.add(listTask.get(itr).getTaskName());
                            rowData.add("");
                            resModel.addRow(rowData);

                        }

                        Set<String> keys = result.keySet();


                        for (String k : keys) {
                            int row = 0;

                            while (!resModel.getValueAt(row, 0).equals(k)) {
                                row++;
                            }
                            List<Applicant> list = result.get(k);
                            StringBuilder set = new StringBuilder();


                            for (Applicant a : list) {
                                set.append(a.getName()).append(" , ");
                            }

                            resModel.setValueAt(set, row, 1);

                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if (box2.isSelected()) {
                    resModel.setRowCount(0);
                    HashMap<String, List<Applicant>> result = control.getSkillMatched();
                    int itr = 0;

                    try {
                        List<Task> listTask = control.getAllT();

                        for (; itr < listTask.size(); itr++) {
                            Vector<Object> rowData = new Vector<>();
                            rowData.add(listTask.get(itr).getTaskName());
                            rowData.add("");
                            resModel.addRow(rowData);

                        }

                        Set<String> keys = result.keySet();


                        for (String k : keys) {
                            int row = 0;

                            while (!resModel.getValueAt(row, 0).equals(k)) {
                                row++;
                            }
                            List<Applicant> list = result.get(k);
                            StringBuilder set = new StringBuilder();


                            for (Applicant a : list) {
                                set.append(a.getName()).append(" , ");
                            }

                            resModel.setValueAt(set, row, 1);

                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else
                    JOptionPane.showMessageDialog(frame, "Select a Matching Strategy to Find Resource");
            }
        });


        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for(int i = 0; i< resTable.getRowCount();i++)
                {
                    for(int j = 0; j < resTable.getColumnCount();j++)
                    {
                        txt.append(resTable.getValueAt(i,j)).append("\t");
                    }

                    txt.append("\n");
                }

                FileWriter writer = null;
                try {
                    writer = new FileWriter("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Assignment/Result.txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                BufferedWriter buffer = new BufferedWriter(writer);

                try {
                    buffer.write(String.valueOf(txt));
                    buffer.close();
                    JOptionPane.showMessageDialog(null,"Result Exported !");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


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

    public static void main(String[] args) throws SQLException
    {
        Matching m = new Matching(new StrategyController());
    }
}
