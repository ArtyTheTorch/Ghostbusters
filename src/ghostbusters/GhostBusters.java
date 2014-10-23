/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * http://stackoverflow.com/questions/2714663/how-can-i-align-all-elements-to-the-left-in-jpanel
 */
package ghostbusters;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GhostBusters {

    //GUI Objects
    // Overall container
    final static JFrame frame = new JFrame("Ghostbusters vrs Ghost");
    //Button used to compute  
    final static JButton b1 = new JButton("Draw");
    //Lable for instuctions
    final static JLabel label = new JLabel("Enter the number of Ghosts/Ghostbusters");
    //Text box
    final static JTextField textField = new JTextField(10);
    //Layout all the GUI components
    final static JPanel topPanela = new JPanel();
    final static JPanel topPanel = new JPanel();
    final static JPanel centerPanel = new JPanel();
    final static JPanel centerPanela = new JPanel();
    final static JPanel fillerWestPanel = new JPanel();
    final static JPanel fillerEastPanel = new JPanel();
    
    public static void main(String[] args) {


        /* At this point were Creating the picture and displaying it
         * Now Im going to implement a GUI so that its less lame.
         */

        class Picture extends JPanel {
            //Needed for the graphic part

            LinkedList<GhostBusterGhostPair> gbPairs;

            private Picture(LinkedList<GhostBusterGhostPair> p) {
                super();
                gbPairs = p;
            }

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 370, 360);
                for (int i = 0; i < gbPairs.size(); i++) {
                    g.setColor(Color.GREEN);
                    g.drawLine(gbPairs.get(i).getGhost().getX(), gbPairs.get(i).getGhost().getY(), gbPairs.get(i).getGhostbuster().getX(), gbPairs.get(i).getGhostbuster().getY());
                }
            }
        }//End Picture


        class ButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                Random ran = new Random();
                LinkedList<Point> ghostAndGhostbusterPoints = new LinkedList<Point>();
                // Get the user input
                String userInput = textField.getText();
                int numberOfPoints = Integer.parseInt(userInput);
                for (int i = 0; i < numberOfPoints; i++) {
                    Point ghosts = new Point(ran.nextInt(370), ran.nextInt(360), false);
                    ghostAndGhostbusterPoints.add(ghosts);
                }
                for (int i = 0; i < numberOfPoints; i++) {
                    Point ghostbusters = new Point(ran.nextInt(370), ran.nextInt(360), true);
                    ghostAndGhostbusterPoints.add(ghostbusters);
                }
                GhostbustersAttack solution = new GhostbustersAttack(ghostAndGhostbusterPoints);
                solution.recursiveAttack(ghostAndGhostbusterPoints);

                centerPanel.removeAll();
                centerPanela.removeAll();
                Picture picturePanel = new Picture(solution.getPairs());
                picturePanel.setSize(370, 360);
                centerPanela.add(picturePanel);
                picturePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
                centerPanel.add(centerPanela,BorderLayout.CENTER);
                //centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                //picturePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
                //centerPanela.add(fillerEastPanel, BorderLayout.EAST);
                //centerPanela.add(fillerWestPanel, BorderLayout.WEST);
                
                frame.add(centerPanel, BorderLayout.CENTER);
                
                frame.repaint();
                frame.setVisible(true);
            }
        }//End ActionListener Class


        //GUI STUFF
        centerPanel.setSize(200,200);
        centerPanela.setSize(200,200);
        fillerWestPanel.setBackground(Color.red);
        fillerEastPanel.setBackground(Color.blue);
//        centerPanel.add(fillerEastPanel, BorderLayout.EAST);
//        centerPanel.add(fillerWestPanel, BorderLayout.WEST);
        centerPanel.setLayout(new BorderLayout());
        centerPanela.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Reslut"));
        
        b1.addActionListener(new ButtonListener());
        topPanela.setLayout(new BorderLayout());
        topPanela.add(label, BorderLayout.NORTH);
        
        topPanela.add(textField, BorderLayout.CENTER);
        topPanela.add(b1, BorderLayout.SOUTH);
        topPanel.add(topPanela);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(200, 350);
        frame.setSize(400, 500);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.setVisible(true);


    }// End main   
}
