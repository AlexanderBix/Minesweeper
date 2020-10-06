import Board.CellCreation;
import Board.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {

    // Timer logic (with display)
    final static int[] timeInt = {1};
    static JPanel sideUI = new JPanel();
    static JLabel time = new JLabel("<html>"+ "Mines: " + Integer.toString(MineSweeper.mines) + "<br>" + "Time: 0" + "</html>");
    static ActionListener incrementTime = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            time.setText("<html>" + "Mines: " + Integer.toString(MineSweeper.mines) + "<br>" +  "Time: " + String.valueOf(timeInt[0]) + "</html>");
            sideUI.add(time);
            timeInt[0]++;

        }
    };
    static Timer timeCounter = new Timer(1000,incrementTime);

    // Adding Top UI elements
    public static JPanel uiTop(int mines, JFrame frame, int width, int height) {
        // Initializing panel
        JPanel topUI = new JPanel();
        GridLayout grid = new GridLayout(0,3);

        // Adding buttons and counters
        JButton setup = new JButton("Setup");
        JButton help = new JButton("Help");
        JButton reset = new JButton("Reset");
        JLabel numMines = new JLabel(Integer.toString(mines));
        JLabel time = new JLabel();
        topUI.add(setup);
        topUI.add(reset);
        topUI.add(help);

        // Reset Button Logic
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                CellCreation.reset();
                MineSweeper.frameStart(frame,width,height,mines);

            }
        });

        // Help button logic
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Setting up a JFrame for help menu popup
                JFrame helpMenu = new JFrame();
                helpMenu.setTitle("Help");
                JPanel helpPanel = new JPanel();
                helpMenu.setSize(600,300);
                GridLayout grid = new GridLayout(6,0);
                helpPanel.setLayout(grid);

                // Setting up help menu text
                JLabel part1 = new JLabel("Press 'Setup' to configure the game.");
                JLabel part2 = new JLabel("Press 'Reset' to restart the game.");
                JLabel part3 = new JLabel("Press on the grid to reveal squares");
                JLabel part4 = new JLabel("Use the numbers on the squares to help avoid red squares. That means game over!");
                JLabel part5 = new JLabel("You win if you can find every green square!");
                helpPanel.add(part1);
                helpPanel.add(part2);
                helpPanel.add(part3);
                helpPanel.add(part4);
                helpPanel.add(part5);
                helpMenu.add(helpPanel);
                helpMenu.setVisible(true);
            }
        });

        // Setup button logic
        setup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Setting up a JFrame for options menu popup
                JFrame gameSelection = new JFrame();
                gameSelection.setTitle("Setup Menu");
                JPanel selection = new JPanel();
                gameSelection.setSize(600,300);
                GridLayout grid = new GridLayout(0,3);
                selection.setLayout(grid);

                // Difficulty Buttons
                JButton easy = new JButton("Beginner");
                JButton medium = new JButton("Intermediate");
                JButton hard = new JButton("Expert");
                selection.add(easy);
                selection.add(medium);
                selection.add(hard);

                // Custom Difficulty

                // Width
                JSlider width = new JSlider(3,12,5);
                JLabel widthL =  new JLabel("Width:");
                width.setPaintTrack(true);
                width.setPaintLabels(true);
                width.setMajorTickSpacing(1);

                // Height
                JSlider height = new JSlider(3,12,5);
                JLabel heightL = new JLabel("Height:");
                height.setPaintTrack(true);
                height.setPaintLabels(true);
                height.setMajorTickSpacing(1);

                // Mines
                JLabel minesL = new JLabel("Mines:");
                JTextField mine = new JTextField();

                // Custom difficulty acceptance
                JButton custom = new JButton("Custom");

                selection.add(widthL);
                selection.add(width);
                selection.add(new JLabel(" "));
                selection.add(heightL);
                selection.add(height);
                selection.add(new JLabel(" "));
                selection.add(minesL);
                selection.add(mine);
                selection.add(custom);

                gameSelection.add(selection);

                // Easy button click
                easy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        CellCreation.reset();
                        MineSweeper.frameStart(frame,4,4,4);
                        gameSelection.dispose();
                    }
                });

                // Medium button click
                medium.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        CellCreation.reset();
                        MineSweeper.frameStart(frame,8,8,15);
                        gameSelection.dispose();
                    }
                });

                // Hard button click
                hard.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        CellCreation.reset();
                        MineSweeper.frameStart(frame,12,12,40);
                        gameSelection.dispose();
                    }
                });

                // Custom difficulty click
                custom.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Making sure that number of mines is valid
                        if(Integer.parseInt(mine.getText()) >= 2 && Integer.parseInt(mine.getText()) <= (width.getValue() * height.getValue() / 2)) {
                            frame.getContentPane().removeAll();
                            frame.repaint();
                            CellCreation.reset();
                            MineSweeper.frameStart(frame,height.getValue(),width.getValue(),Integer.parseInt(mine.getText()));
                            gameSelection.dispose();
                        } else { // If number of mines isn't valid
                            JOptionPane.showMessageDialog(null, "INVALID NUMBER OF BOMBS");
                        }
                    }
                });

                gameSelection.setVisible(true);

            }
        });



        return topUI;
    }

    // Adding Side UI Elements - Timer and Number of mines
    public static JPanel uiSide(int mines, int width, int height, JFrame frame) {
        // Reinitializing timer and mines
        timeInt[0] = 1;
        time.setText("<html>"+ "Mines: " + Integer.toString(MineSweeper.mines) + "<br>" + "Time: 0" + "</html>");

        // Displaying number of mines and time
        sideUI.add(time);

        // Starting timer
        timeCounter.start();

        return sideUI;
    }

}
