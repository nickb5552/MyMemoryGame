package MemNew;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Memory implements ActionListener
{
    JFrame mainBoard;
    ImageIcon cardImage;
    ImageIcon cardCover;
    int width = 1000;
    int height = 1200;
    JButton button1;
    JButton button2;
    JPanel gamePanel;
    JPanel controlPanel;
    JButton[][] topButtonArray;
    JButton[][] bottomButtonArray;
    ArrayList<Integer> fiftyTwoIntegerList = new ArrayList<Integer>();
    ArrayList<Integer> topTenIntegerList = new ArrayList<Integer>();
    ArrayList<Integer> bottomTenIntegerList = new ArrayList<Integer>();
    JButton matchDisplay;
    int matchNum = 0;
    Random r = new Random();
    JButton selectedButton;
    ImageIcon selectedIcon;

    public static void main(String[] args)
    {
        Memory thisGame; //declare which type
        thisGame = new Memory(); //instantiate
        thisGame.getGoing(); // call on get going
    }

    public void getGoing()
    {
        for (int i = 0; i < 52; i++) // i = index into 52 list -> Filling the list with integers 0 through 51
        {
            fiftyTwoIntegerList.add(i);
        }
        Collections.shuffle(fiftyTwoIntegerList);

        for (int j = 0; j < 10; j++) //j = index into top list -> Filling top and bottom list with the same random shuffled numbers from 52 list
        {
            topTenIntegerList.add(fiftyTwoIntegerList.get(j));
            bottomTenIntegerList.add(fiftyTwoIntegerList.get(j));
        }
        
        Collections.shuffle(bottomTenIntegerList); // Shuffle the bottom list to make it different from the top list (same numbers in different order)
        mainBoard = new JFrame();
        mainBoard.setBackground(Color.blue);
        mainBoard = new JFrame("The Memory Game");
        mainBoard.setSize(width, height);
        mainBoard.setLayout(new FlowLayout());
        mainBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new JPanel();
        controlPanel = new JPanel();
        gamePanel.setBackground(Color.red);
        controlPanel.setBackground(Color.green);
        mainBoard.add(gamePanel);
        mainBoard.add(controlPanel);
        gamePanel.setLayout(new GridLayout(4, 5)); 
        topButtonArray = new JButton[2][5];
        bottomButtonArray = new JButton[2][5];
        matchDisplay = new JButton("Number of matches: " + matchNum);
        controlPanel.add(matchDisplay);
        cardCover = new ImageIcon(getClass().getResource("images/CardCover_" + 3 + ".jpg"));
        
        for (int row = 0; row < 2; row++) // Set up topList
        {
            for (int column = 0; column < 5; column++)
            {
                topButtonArray[row][column] = new JButton(); //loading the array list with buttons
                topButtonArray[row][column].addActionListener(this); //adds an action listener to each button
                gamePanel.add(topButtonArray[row][column]); // adds buttons to the panel 
                topButtonArray[row][column].setIcon(cardCover);
                topButtonArray[row][column].setMnemonic(topTenIntegerList.get(row * 5 + column));
            }
        }

        for (int row = 0; row < 2; row++) // Set up 4 x 5 matrix of buttons on gamePanel
        {
            for (int column = 0; column < 5; column++)
            {
                bottomButtonArray[row][column] = new JButton(); //loading the array list with buttons
                bottomButtonArray[row][column].addActionListener(this); //adds an action listener to each button
                gamePanel.add(bottomButtonArray[row][column]); // adds buttons to the panel 
                bottomButtonArray[row][column].setIcon(cardCover);
                bottomButtonArray[row][column].setMnemonic(bottomTenIntegerList.get(row * 5 + column));
            }
        }
        gamePanel.setVisible(true);
        mainBoard.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent joe)
    {
        selectedButton = (JButton)joe.getSource();
        int cardImageNumber = selectedButton.getMnemonic();
        selectedButton.setIcon(new ImageIcon(getClass().getResource("images/Card" + cardImageNumber + ".jpg")));
    }
}
