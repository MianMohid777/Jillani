package Lab_8;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TableView {

    JFrame frame = new JFrame("Tasks");
    String[] columns = {"TaskId" , "Description", "Completed"};
    String[][] data = {{"", "", ""}};
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    JTextField taskDescription = new JTextField(20);
    JCheckBox taskCompleted = new JCheckBox();
    JButton addButton = new JButton("Add");
    JButton deleteButton = new JButton("Delete");

    TableView() {

        frame.setSize(750,500);
        JScrollPane sp = new JScrollPane(table);
        model.setColumnIdentifiers(columns);

        JPanel formPanel = new JPanel(new FlowLayout());
        formPanel.add(taskDescription);
        formPanel.add(taskCompleted);
        formPanel.add(addButton);
        formPanel.add(deleteButton);


        frame.setLayout(new BorderLayout());

        frame.add(sp, BorderLayout.CENTER);
        frame.add(formPanel, BorderLayout.SOUTH);

        frame.setSize(400, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
