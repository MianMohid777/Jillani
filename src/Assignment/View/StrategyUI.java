package Assignment.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StrategyUI {

    private JFrame frame;

    private JPanel optionPanel;

    private ResourceForm rf;

    public StrategyUI()
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

                frame.setVisible(false);
                rf = new ResourceForm();
            }
        });
        optionPanel.add(addR,FlowLayout.LEFT);
        optionPanel.add(addT,FlowLayout.CENTER);
        optionPanel.add(match,FlowLayout.RIGHT);

        optionPanel.add(r);
        optionPanel.add(t);
        optionPanel.add(m);

        frame.setContentPane(optionPanel);

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
        StrategyUI s  = new StrategyUI();
    }


}
