package Lab_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Calculator {

    private JFrame calcFrame;

    private JPanel screenPanel;
    private JPanel numBtnPanel;
    private JPanel opBtnPanel;

    private JTextField inputField;

    private String operator;

    private String input;


    public Calculator() {
        input = "";
        operator = "";
        calcFrame = new JFrame();
        calcFrame.setTitle("Calculator");
        calcFrame.setSize(500, 450);
        calcFrame.setLayout(new BorderLayout());


        screenPanel = new JPanel(new BorderLayout());
        numBtnPanel = new JPanel(new BorderLayout());
        opBtnPanel = new JPanel(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setEditable(false);
        inputField.setSize(500, 600);
        screenPanel.add(inputField, BorderLayout.NORTH);


        for (int i = 9; i >= 0; i--) {
            JButton numBtn = new JButton();
            numBtn.setText(Integer.toString(i));
            numBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    input += e.getActionCommand();
                    inputField.setText(input);

                }
            });

            numBtnPanel.add(numBtn);

        }
        numBtnPanel.setLayout(new GridLayout(4, 3));
        screenPanel.add(numBtnPanel, BorderLayout.CENTER);

        JButton add = new JButton("+");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "+";
                if ("+-/*=".contains(input.substring(input.length() - 1))) {
                    inputField.requestFocusInWindow();
                } else {
                    input += e.getActionCommand();
                    inputField.setText(input);
                }
            }
        });

        JButton subtract = new JButton("-");
        subtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "-";
                if ("+-/*=".contains(input.substring(input.length() - 1))) {
                    inputField.requestFocusInWindow();
                } else {
                    input += e.getActionCommand();
                    inputField.setText(input);
                }
            }
        });
        JButton divide = new JButton("/");

        divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "/";
                if (input.isEmpty() || "+-/*=".contains(input.substring(input.length() - 1))) {
                    inputField.requestFocusInWindow();
                } else {
                    input += e.getActionCommand();
                    inputField.setText(input);
                }
            }
        });
        JButton multiply = new JButton("*");
        multiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "*";
                if (input.isEmpty() || "+-/*=".contains(input.substring(input.length() - 1))) {
                    inputField.requestFocusInWindow();
                } else {
                    input += e.getActionCommand();
                    inputField.setText(input);
                }
            }
        });
        JButton equal = new JButton("=");

        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText(String.valueOf(Work()));
            }
        });


        JButton reset = new JButton("C");

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                operator = "";
                input = "";
            }
        });

        opBtnPanel.setLayout(new GridLayout(6, 1));
        opBtnPanel.add(reset);
        opBtnPanel.add(add);
        opBtnPanel.add(subtract);
        opBtnPanel.add(multiply);
        opBtnPanel.add(divide);
        opBtnPanel.add(equal);


        screenPanel.add(opBtnPanel, BorderLayout.EAST);
        calcFrame.setContentPane(screenPanel);


        calcFrame.setVisible(true);

    }

    private Double Work() {
        if (input.isEmpty() || input.length() == 1) {
            return 0.00;

        }


        boolean flag = true;
        int ii = 0;
        String s = "";

        if (input.charAt(0) == '-') {
            flag = false;
            ii++;
        } else if (input.charAt(0) == '+') {
            flag = true;
            ii++;
        }


        Queue<Double> inputVal = new LinkedList<>();
        Queue<String> operation = new LinkedList<>();


        String[] val = input.split("[-+/*]");
        String[] tokens = input.replaceAll("[0-9]+", "").split("");



        for (int i = 0; i < val.length; i++) {
            inputVal.add(Double.valueOf(val[i]));
        }

        for (; ii < tokens.length; ii++) {

            operation.add(tokens[ii]);

        }

        double res = inputVal.remove();
        for (int k = 0; k < operation.size() && !inputVal.isEmpty();) {


            res = Process(res, inputVal.remove(), operation.remove());
        }


        return res;
    }

    private double Process(double a, double b, String op) {

        if (op.equals("+")) {
            return a + b;
        } else if (op.equals("-")) {
            return a - b;
        } else if (op.equals("/")) {
            return a / b;
        } else if (op.equals("*")) {
            return a * b;
        } else {
            return 0.00;
        }

    }

    public static void main(String[] args) {

        Calculator c = new Calculator();

    }


}
