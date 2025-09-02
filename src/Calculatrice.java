import java.awt.*;
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
    JPanel displayPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

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
        frame.setVisible(true);
        frame.setSize(boardWeidth, boardHeigth);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // set the label
        displayLabel.setBackground(black);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("arial", Font.PLAIN, 70));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        // set the panel
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        // set buttonPanel
        buttonPanel.setLayout(new GridLayout(5,4));
        buttonPanel.setBackground(black);
        frame.add(buttonPanel);

        for(int i = 0 ; i < buttonValues.length ;i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("arial" , Font.PLAIN , 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(black));
            

            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(ligthGray);
                button.setForeground(black);
            }
            else if(Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(orange);
                button.setForeground(Color.white);
            }
            else{
                button.setBackground(darkGray);
                button.setForeground(Color.white);
            }
            buttonPanel.add(button);
        }


    }
}