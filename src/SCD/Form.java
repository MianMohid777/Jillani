package SCD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Form {

    private JFrame frame;

    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JPanel centre;
    private JPanel bottom;

    private JButton addBtn;
    private JButton delBtn;

    private JRadioButton r1;
    private JRadioButton r2;
    private JRadioButton r3;

    private JCheckBox one;
    private JCheckBox two;

    private JTextArea area;

    private JTextField rollNum;
    private JTextField name;


    public Form()
    {
        frame = new JFrame("Student Data");
        frame.setSize(500,500);

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent e)
            {

                String[] option = {"1","2","3"};

                JOptionPane.showOptionDialog(null,"oops","Title",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE,null,option,option[0]);
            }
            @Override
            public void windowClosing(WindowEvent e)
            {
                JOptionPane.showMessageDialog(null,"Windows is Closing","Alert",JOptionPane.INFORMATION_MESSAGE);

            }
        });

        top = new JPanel(new BorderLayout());

        r1 = new JRadioButton("Muslim");
        r2 = new JRadioButton("Christian");
        r3 = new JRadioButton("Hindu");

        ButtonGroup group = new ButtonGroup();

        group.add(r1);
        group.add(r2);
        group.add(r3);

        top.add(r1,FlowLayout.LEFT);
        top.add(r2,FlowLayout.CENTER);
        top.add(r3,FlowLayout.RIGHT);


        left = new JPanel(new GridBagLayout());

        addBtn = new JButton("Add Student");
        delBtn = new JButton("Delete Student");

        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.VERTICAL;

        left.add(addBtn,c);

        c.gridy = 1;

        left.add(delBtn,c);

        centre = new JPanel();

        area = new JTextArea(15,20);

        area.setText("This is a Text Area");

        centre.add(area);

        one = new JCheckBox("BS (SE)");
        two = new JCheckBox("MS (SE)");

        ButtonGroup group2 = new ButtonGroup();
        group2.add(one);
        group2.add(two);

        right = new JPanel(new GridBagLayout());

        c.gridy = 0;
        right.add(one,c);

        c.gridy = 1;

        right.add(two,c);

        bottom = new JPanel(new FlowLayout());

        rollNum = new JTextField(15);
        name = new JTextField(15);

        name.setText("Add a Name");
        rollNum.setText("Add a RollNum");


        bottom.add(name);
        bottom.add(rollNum);


        frame.add(top,BorderLayout.NORTH);
        frame.add(left,BorderLayout.WEST);
        frame.add(area,BorderLayout.CENTER);
        frame.add(right,BorderLayout.EAST);
        frame.add(bottom,BorderLayout.SOUTH);




        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        Form f = new Form();
    }
}
