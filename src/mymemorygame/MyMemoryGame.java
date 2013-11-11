package mymemorygame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyMemoryGame implements ActionListener
{
    JFrame mainBoard;
    ImageIcon cardImage;
    ImageIcon cardCover;
    int width = 1200;
    int height = 1200;
    JButton button1;
    JButton button2;
    JPanel gamePanel;
    JPanel controlPanel;
    JButton[][] cardArray;
    int[] int52List = new int[52];
    int[] topTenList = new int[10];
    int[] bottomTenList = new int[10];
    JButton matchDisplay;
    int matchNum = 0;
    Random r = new Random();

    public static void main(String[] args)
    {
        MyMemoryGame thisGame; //declare which type
        thisGame = new MyMemoryGame(); //instantiate
        thisGame.getGoing(); // call on get going
    }

    public void getGoing()
    {
        for (int lnumber = 0; lnumber < 52; lnumber++)
        {
            int52List[lnumber] = lnumber;
        }
        for (int tl = 0; tl < 10; tl++)
        {
            int randomStore = r.nextInt(51);
            if (tl != 99)    //Problems with 52 list - going into top ten array
            {
                topTenList[tl] = int52List[randomStore];
            }
            int52List[randomStore] = 99;
            System.out.println(topTenList[tl]);
        }
        mainBoard = new JFrame();
        mainBoard.setBackground(Color.blue);
        mainBoard = new JFrame("The Memory Game");
        mainBoard.setSize(width, height);
        mainBoard.setLayout(new GridLayout(2, 1));
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
                cardArray[row][column] = new JButton(row + "/" + column); //loading the array list with buttons
                cardArray[row][column].addActionListener(this); //adds an action listener to each button
            }
        }

        for (int row = 0; row < 4; row++)
        {
            for (int column = 0; column < 5; column++)
            {
                gamePanel.add(cardArray[row][column]); // adds buttons to the panel 
            }
        }
        cardImage = new ImageIcon(getClass().getResource("images/Card" + 4 + ".jpg"));
        cardArray[2][2].setIcon(cardImage);
        cardCover = new ImageIcon(getClass().getResource("images/CardCover_" + 3 + ".jpg"));
        cardArray[2][3].setIcon(cardCover);
        gamePanel.setVisible(true);
        mainBoard.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
    }
}
