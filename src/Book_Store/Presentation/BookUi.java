package Book_Store.Presentation;

import javax.swing.*;
import java.awt.*;

public class BookUi {

    private JFrame bkFrame;
    private JPanel bkPanel;

    public BookUi()
    {
        bkFrame = new JFrame("Book Store");

        bkFrame.setSize(500,500);



        JLabel addCustomer = new JLabel("Add Customer");
        JButton customerBtn = new JButton("Add");
        

        JLabel addBk = new JLabel("Add Book");
        JButton bkBtn = new JButton("Add");

        JLabel addOrder = new JLabel("Create Order");
        JButton orderBtn = new JButton("Create");

        JLabel showOrder = new JLabel("Show Orders");
        JButton showBtn = new JButton("Show");


        bkPanel = new JPanel(new GridLayout(4,2));

        bkPanel.add(addCustomer);
        bkPanel.add(customerBtn);

        bkPanel.add(addBk);
        bkPanel.add(bkBtn);

        bkPanel.add(addOrder);
        bkPanel.add(orderBtn);

        bkPanel.add(showOrder);
        bkPanel.add(showBtn);

        bkFrame.setContentPane(bkPanel);

        bkFrame.setVisible(true);

    }

    public static void main(String[] args)
    {
        BookUi bk = new BookUi();
    }

}
