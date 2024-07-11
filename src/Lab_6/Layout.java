package Lab_6;

import javax.swing.*;
import java.awt.*;

public class Layout {

    private JFrame frame;
    private JPanel panel;

    public Layout()
    {
        frame = new JFrame("Layout");
        frame.setSize(250,250);
        panel = new JPanel(new FlowLayout());

        JButton btn1 = new JButton("Button 1");
        JButton btn2 = new JButton("Button 2");
        JButton btn3 = new JButton("Button 3");

        panel.add(btn1,FlowLayout.LEFT);
        panel.add(btn2,FlowLayout.CENTER);
        panel.add(btn3,FlowLayout.RIGHT);

        frame.setContentPane(panel);

        frame.setVisible(true);

    }

    public void changeFrameSize()
    {
       frame.setSize(150,150);
       frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        Layout l = new Layout();

        l.changeFrameSize();


    }

}
