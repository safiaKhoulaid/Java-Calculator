import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.util.Arrays;

public class Calculatrice {

    int boardWeidth = 360;
    int boardHeigth = 540;

    Color ligthGray = new Color(212, 212, 210);
    Color darkGray = new Color(80, 80, 80);
    Color black = new Color(28, 28, 28);
    Color orange = new Color(255, 149, 0);

    JFrame frame = new JFrame("calculatrice");
    JLabel displayLabel = new JLabel();
    JLabel operationLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    String A = "0";
    String operator = null;
    String B = "0";

    String[] buttonValues = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };
    String[] rightSymbols = { "÷", "×", "-", "+", "=" };
    String[] topSymbols = { "AC", "+/-", "%" };

    Calculatrice() {

        // set the frame
        // frame.setVisible(true);
        frame.setSize(boardWeidth, boardHeigth);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // set the label
        displayLabel.setBackground(black);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("arial", Font.PLAIN, 50));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        // set the display panel
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        // set buttonPanel
        buttonPanel.setLayout(new GridLayout(5, 4));
        buttonPanel.setBackground(black);
        frame.add(buttonPanel);

        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(black));

            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(ligthGray);
                button.setForeground(black);
            } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(orange);
                button.setForeground(Color.white);
            } else {
                button.setBackground(darkGray);
                button.setForeground(Color.white);
            }
            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    if (Arrays.asList(topSymbols).contains(buttonValue)) {
                        if (buttonValue == "AC") {
                            clearAll();
                            displayLabel.setText("0");
                        } else if (buttonValue == "+/-") {
                            double num = Double.parseDouble(displayLabel.getText());
                            num *= (-1);
                            displayLabel.setText(removeDoubleZero(num));
                        } else if (buttonValue == "%") {
                            double num = Double.parseDouble(displayLabel.getText());
                            num /= 100;
                            displayLabel.setText(removeDoubleZero(num));
                        }

                    } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                        if (buttonValue == "=") {
                            if (A != "0" && B == "0" && operator != null) {
                                
                                B = displayLabel.getText();
                                // displayLabel.setText("0");
                            
                                switch (operator) {
                                    case "+" -> displayLabel
                                            .setText(removeDoubleZero(Double.parseDouble(A) + Double.parseDouble(B)));
                                    case "-" -> displayLabel
                                            .setText(removeDoubleZero(Double.parseDouble(A) - Double.parseDouble(B)));
                                    case "×" -> displayLabel
                                            .setText(removeDoubleZero(Double.parseDouble(A) * Double.parseDouble(B)));
                                    case "÷" -> displayLabel
                                            .setText(removeDoubleZero(Double.parseDouble(A) / Double.parseDouble(B)));
                                }

                            }
                            System.out.println(A);
                            System.out.println(B);
                            System.out.println(operator);

                        } else if("+-÷×".contains(buttonValue)){
                            if (operator == null && A == "0") {
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                operator = buttonValue;
                                B = "0";
                            // } else if (A != "0" && operator != null && B == "0") {
                            //     B = displayLabel.getText();
                            //     displayLabel.setText("0");
                             }
                        }
                    } else {
                        if (buttonValue == ".") {
                            if (!displayLabel.getText().contains(buttonValue)) {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        } else if ("0123456789".contains(buttonValue)) {
                            if (displayLabel.getText() == "0") {
                                displayLabel.setText(buttonValue);
                            } else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }

                    }

                }

            });
            buttonPanel.add(button);
        }

        frame.setVisible(true);
    }

    public void clearAll() {
        A = "0";
        B = "0";
        operator = null;
    }

    public String removeDoubleZero(double num) {
        if (num % 1 == 0) {
            return Integer.toString((int) num);
        }

        return Double.toString(num);

    }
}