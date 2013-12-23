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
    JButton[][] cardArray;
    ArrayList<Integer> int52ArrayList = new ArrayList<Integer>();
    ArrayList<Integer> toptenArrayList = new ArrayList<Integer>();
    ArrayList<Integer> bottomArrayList = new ArrayList<Integer>();
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
        for (int i = 0; i < 52; i++) // i = index into 52 list -> Filling the list with 0 through 51
        {
            int52ArrayList.add(i);
        }
        Collections.shuffle(int52ArrayList);

        for (int tl = 0; tl < 10; tl++) //tl = top list 
        {
            toptenArrayList.add(int52ArrayList.get(tl));
            bottomArrayList.add(int52ArrayList.get(tl));
        }
        Collections.shuffle(bottomArrayList);
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
        gamePanel.setLayout(new GridLayout(4, 5)); //check this layout
        cardArray = new JButton[4][5];
        matchDisplay = new JButton("Number of matches: " + matchNum);
        controlPanel.add(matchDisplay);

        for (int row = 0; row < 4; row++)
        {
            for (int column = 0; column < 5; column++)
            {
                cardArray[row][column] = new JButton(); //loading the array list with buttons
                cardArray[row][column].addActionListener(this); //adds an action listener to each button
                gamePanel.add(cardArray[row][column]); // adds buttons to the panel 
                cardCover = new ImageIcon(getClass().getResource("images/CardCover_" + 3 + ".jpg"));
                cardArray[row][column].setIcon(cardCover);
            }
        }
        gamePanel.setVisible(true);
        mainBoard.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent joe)
    {
        selectedButton = (JButton)joe.getSource();
        selectedButton.setIcon(new ImageIcon(getClass().getResource("images/Card" + 1 + ".jpg")));
    }
}
