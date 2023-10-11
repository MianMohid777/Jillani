package Lab_4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloWorld {

    private JFrame helloFrame;

    HelloWorld()
    {
        helloFrame = new JFrame();
    }
    

    public void createFrame()
    {
        helloFrame.setName("Hello World Frame");
        helloFrame.setSize(500,500);

        JButton btn = new JButton("Click Me !");
        JPanel helloPanel = new JPanel();




        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(helloFrame,"Hello World !");
            }
        };

        btn.addActionListener(a);

        helloPanel.add(btn);

        helloFrame.setContentPane(helloPanel);
        helloFrame.setVisible(true);
    }

    public static void main(String[] args)
    {
        HelloWorld hello = new HelloWorld();

        hello.createFrame();
    }
}
