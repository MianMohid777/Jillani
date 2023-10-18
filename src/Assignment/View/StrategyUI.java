package Assignment.View;

import Assignment.Controller.StrategyController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StrategyUI {

    private JFrame frame;

    private JPanel optionPanel;

    private ResourceForm rf;
    private TaskForm tf;

    private Matching mf;

    boolean isFormOpen1 = false;
    boolean isFormOpen2 = false;
    boolean isFormOpen3 = false;

    public StrategyUI(StrategyController controller)
    {



        frame = new JFrame("Resource Allocator");
        frame.setSize(300,300);
        optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout());

        JLabel addR = new JLabel();
        JLabel addT = new JLabel();
        JLabel match = new JLabel();

        addR.setText("Add a Resource");
        addT.setText("Add a Task");
        match.setText("Find Match(s)");

        JButton r = new JButton("Open");
        JButton t = new JButton("Open");
        JButton m = new JButton("Find");

        r.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!isFormOpen1)
                {rf = new ResourceForm(controller);
                    isFormOpen1 = true;}
            }
        });

        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!isFormOpen2) {
                    tf = new TaskForm(controller);
                    isFormOpen2 = true;
                }
            }
        });

        m.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!isFormOpen1) {
                    rf = new ResourceForm(controller);
                    rf.getFormFrame().setVisible(false);
                    isFormOpen1 = true;
                }

                if(!isFormOpen2) {
                    tf = new TaskForm(controller);
                    tf.getFormFrame().setVisible(false);
                    isFormOpen2 = true;
                }

                if(!isFormOpen3)
                { mf = new Matching(controller);
                  isFormOpen3 = true;
                }
            }
        });

        optionPanel.add(addR,FlowLayout.LEFT);
        optionPanel.add(r);
        optionPanel.add(addT,FlowLayout.CENTER);
        optionPanel.add(t);
        optionPanel.add(match,FlowLayout.RIGHT);
        optionPanel.add(m);

        frame.setContentPane(optionPanel);

        frame.setResizable(false);
        frame.setVisible(true);


    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getOptionPanel() {
        return optionPanel;
    }

    public void setOptionPanel(JPanel optionPanel) {
        this.optionPanel = optionPanel;
    }

    public ResourceForm getRf() {
        return rf;
    }

    public void setRf(ResourceForm rf) {
        this.rf = rf;
    }

    public static void main(String[] args)
    {
        StrategyUI s  = new StrategyUI(new StrategyController());
    }


}
