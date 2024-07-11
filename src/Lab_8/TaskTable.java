package Lab_8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskTable {
    class Task {
        int taskId;
        String taskDescription;
        Boolean isCompleted;

        public Task(String taskDescription, Boolean isCompleted) {
            this.taskDescription = taskDescription;
            this.isCompleted = isCompleted;
        }

    }

    TableView view;
    TaskTable(TableView v) throws SQLException {
        view = v;
        refreshTableData();
        addListeners();
    }

    void addListeners(){
        view.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task task = new Task(view.taskDescription.getText(), view.taskCompleted.isSelected());

                try (Connection connection = DB_Connection.getConnection()) {
                    String sql = "INSERT INTO tasks (taskDescription, isCompleted) VALUES (?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                        preparedStatement.setString(1, view.taskDescription.getText());
                        preparedStatement.setBoolean(2, view.taskCompleted.isSelected());

                        int affectedRows = preparedStatement.executeUpdate();

                        if (affectedRows > 0) {
                            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    task.taskId = generatedKeys.getInt(1);
                                } else {
                                    throw new SQLException("Creating task failed, no ID obtained.");
                                }
                            }
                        }

                        refreshTableData();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



        view.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow >= 0) {
                    int taskId = Integer.parseInt(view.table.getValueAt(selectedRow, 0).toString());
                    try {
                        deleteTask(taskId);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        refreshTableData();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(view.frame, "Select a task to delete.");
                }
            }
        });
    }

    void deleteTask(int taskId) throws SQLException {
        Connection connection = DB_Connection.getConnection();
        String sql = "DELETE FROM tasks WHERE taskId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, taskId);
            int rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    void refreshTableData() throws SQLException {
        Connection connection = DB_Connection.getConnection();
        String sql = "SELECT taskId,taskDescription, isCompleted FROM tasks";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            view.model.setRowCount(0); //Clear table

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String description = resultSet.getString(2);
                String isCompleted = resultSet.getBoolean(3) ? "Yes" : "No";
                view.model.addRow(new Object[]{id,description, isCompleted});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
