package Lab_6;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;


public class Table {
    private JFrame frame;
    private JPanel panel;
    private JPanel inputPanel;

    private JTable table;

    private JButton addBtn;
    private JButton removedBtn;

    public Table()
    {
        frame = new JFrame("My Table");
        frame.setSize(750,500);

        panel = new JPanel(new BorderLayout());
        inputPanel = new JPanel(new FlowLayout());

        JLabel name = new JLabel("Name:");
        JLabel price = new JLabel("Price:");
        JLabel qty = new JLabel("Quantity:");

        JTextField nameField = new JTextField(10);
        JTextField priceField = new JTextField(10);
        JTextField qtyField = new JTextField(10);

        addBtn = new JButton("Add");
        removedBtn = new JButton("Delete");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!Objects.equals(nameField.getText(), "") && Float.parseFloat(priceField.getText()) > 0 && Integer.parseInt(qtyField.getText()) > 0)
                {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    Vector<Object> rowData = new Vector<>();
                    rowData.add(nameField.getText());
                    rowData.add(Float.parseFloat(priceField.getText()));
                    rowData.add(Integer.parseInt(qtyField.getText()));

                    model.addRow(rowData);
                }
                else JOptionPane.showMessageDialog(frame,"Baaz Ajaa");
            }

        });

        removedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                if(table.getSelectedRow() > -1)
                  model.removeRow(table.getSelectedRow());


            }
        });



        inputPanel.add(name);
        inputPanel.add(nameField);

        inputPanel.add(price);
        inputPanel.add(priceField);

        inputPanel.add(qty);
        inputPanel.add(qtyField);

        inputPanel.add(addBtn);
        inputPanel.add(removedBtn);


        String[][] data = {{"ProductA","10.0","50"},{"ProductB","12.0","40"},{"ProductC","15.0","35"}};
        String[] column = {"Name","Price","Quantity"};

        DefaultTableModel tableModel = new DefaultTableModel(data,column);
        table = new JTable(tableModel);



        panel.add(inputPanel,BorderLayout.NORTH);
        panel.add(table,BorderLayout.CENTER);

        frame.setContentPane(panel);

        frame.setVisible(true);

    }


    public void remove(String name)
    {

    }

    public static void main(String[] args)
    {
        Table t = new Table();

    }

}
