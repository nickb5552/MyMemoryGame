package MemNew;

/**
 * **************************************************
 * Copyright 2014 Nick Barber version 0.10, 12/31/13
 * **************************************************
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Memory extends JComponent implements ActionListener, Runnable
{

    JFrame mainBoard;
    ImageIcon cardImage;
    ImageIcon cardCoverIcon;
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
    int matchCount = 0;
    Random r = new Random();
    JButton firstButton;
    JButton secondButton;
    ImageIcon selectedIcon;
    int turnCounter = 0;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Memory());
    }

    @Override
    public void run()
    {
        System.out.println("starting");
        firstButton = new JButton(); // Dummy buttons
        secondButton = new JButton();
        mainBoard = new JFrame();
        mainBoard.add(this);
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
        matchDisplay = new JButton();
        controlPanel.add(matchDisplay);
        cardCoverIcon = new ImageIcon(getClass().getResource("images/CardCover_" + 3 + ".jpg"));
        
        for (int i = 0; i < 52; i++) // i = index into 52 list -> Filling the list with integers 0 through 51
        {
            fiftyTwoIntegerList.add(i);
        }
        Collections.shuffle(fiftyTwoIntegerList);

        for (int j = 0; j < 10; j++) //j = index into top and bottom list -> Filling top and bottom list with the same random shuffled numbers from 52 list
        {
            topTenIntegerList.add(fiftyTwoIntegerList.get(j));
            bottomTenIntegerList.add(fiftyTwoIntegerList.get(j));
        }

        Collections.shuffle(bottomTenIntegerList); // Shuffle the bottom list to make it different from the top list (same numbers in different order)

        for (int row = 0; row < 2; row++) // Set up topList
        {
            for (int column = 0; column < 5; column++)
            {
                topButtonArray[row][column] = new JButton(); //loading the array list with buttons
                topButtonArray[row][column].addActionListener(this); //adds an action listener to each button
                gamePanel.add(topButtonArray[row][column]); // adds buttons to the panel 
                topButtonArray[row][column].setIcon(cardCoverIcon);
                topButtonArray[row][column].setMnemonic(topTenIntegerList.get(row * 5 + column));
            }
        }

        for (int row = 0; row < 2; row++) // Set up bottom list
        {
            for (int column = 0; column < 5; column++)
            {
                bottomButtonArray[row][column] = new JButton(); //loading the array list with buttons
                bottomButtonArray[row][column].addActionListener(this); //adds an action listener to each button
                gamePanel.add(bottomButtonArray[row][column]); // adds buttons to the panel 
                bottomButtonArray[row][column].setIcon(cardCoverIcon);
                bottomButtonArray[row][column].setMnemonic(bottomTenIntegerList.get(row * 5 + column));
            }
        }
        gamePanel.setVisible(true);
        mainBoard.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent joe)
    {
        turnCounter++;
        if (turnCounter % 2 == 1)
        {
            firstButton = (JButton) joe.getSource();
            int firstCardMnomic = firstButton.getMnemonic();
            firstButton.setIcon(new ImageIcon(getClass().getResource("images/Card" + firstCardMnomic + ".jpg")));
        } else
        {
            secondButton = (JButton) joe.getSource();
            int secondCardMnomic = secondButton.getMnemonic();
            secondButton.setIcon(new ImageIcon(getClass().getResource("images/Card" + secondCardMnomic + ".jpg")));
            if (firstButton.getMnemonic() == secondButton.getMnemonic())
            {
                matchCount++;
            }
            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException ex)
            {
            }
            secondButton.setIcon(cardCoverIcon);
            firstButton.setIcon(cardCoverIcon);
        }
        matchDisplay.setText("Number of matches = " + matchCount + ", Number of tries = " + turnCounter);
    }

    @Override
    public void paint(Graphics g)
    {
        repaint();
        System.out.println("paint");
    }
}
//package org.wintrisstech.examples.gui;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//
//import javax.swing.JPanel;
//
//@SuppressWarnings("serial")
//public class GameBoard extends JPanel {
//        
//
//        @Override
//        public void paintComponent(Graphics g) {
//                Graphics2D g2 = (Graphics2D) g;
//                g2.setColor(Color.WHITE);
//                g2.fillRect(0, 0, getWidth(), getHeight());
//                g2.setColor(Color.BLUE);
//                // draw other stuff
//        }
//
//
//}

//package org.wintrisstech.examples.gui;
//
//import java.awt.BorderLayout;
//import java.awt.Toolkit;
//
//import javax.swing.JFrame;
//import javax.swing.SwingUtilities;
//import javax.swing.JPanel;
//
//@SuppressWarnings("serial")
//public class GameFrame extends JFrame implements Runnable {
//
//        public static void main(String[] args) {
//                SwingUtilities.invokeLater(new GameFrame());
//        }
//
//        @Override
//        public void run() {
//                setTitle("My Game");
//                // set the layout and add all the components
//                // ...
//                // For instance ...
//                setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
//                setLayout(new BorderLayout());
//                JPanel gb = new GameBoard();
//                add(gb, BorderLayout.CENTER);
//                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                pack();
//                setVisible(true);
//        }
//
//}
