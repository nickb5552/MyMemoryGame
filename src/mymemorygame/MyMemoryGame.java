package mymemorygame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyMemoryGame implements ActionListener
{
    JFrame gameBoard;
    int width = 1200;
    int height = 1200;
    JButton button1;
    JButton button2;
    JButton[][] cardArray;
    JPanel gamePanel;
    JPanel controlPanel;

    public static void main(String[] args)
    {
        MyMemoryGame thisGame; //declare which type
        thisGame = new MyMemoryGame(); //instantiate
        thisGame.getGoing(); // call on get going
    }

    public void getGoing()
    {
        gameBoard = new JFrame();
        gameBoard = new JFrame("The Memory Game");
        gameBoard.setSize(width, height);
        gameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameBoard.setLayout(new FlowLayout()); //check this layout
        gamePanel = new JPanel();
        gamePanel.setBackground(Color.yellow);
        controlPanel = new JPanel();
        controlPanel.setSize(200, 200);
        gamePanel.setBackground(Color.red);
        gamePanel.setSize(200, 200);
        cardArray = new JButton[4][5];
        gameBoard.add(gamePanel);
        gameBoard.add(controlPanel);

//        for (int row = 0; row < 4; row++)
//        {
//            for (int column = 0; column < 5; column++)
//            {
//                cardArray[row][column] = new JButton(row + "/" + column);
//                cardArray[row][column].addActionListener(this);
//            }
//        }
//
//        for (int row = 0; row < 4; row++)
//        {
//            for (int column = 0; column < 5; column++)
//            {
//                gameBoard.add(cardArray[row][column]);
//            }
//        }
        gameBoard.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
    }
}
