import Board.CellCreation;
import Board.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class MineSweeper {

    static int width = 12; // Width of play field
    static int height = 12; // Height of play field
    static int mines = 40; // Number of mines present

    // Setting up JFrame
    public static void frameStart(JFrame frame, int w, int h, int mine) {
        frame.setTitle("MineSweeper: By Alexander Bix");
        // JFrame Setup
        width = w;
        height = h;
        mines = mine;
        frame.setSize(600,600);

        // Top and side UI setup
        frame.add(UI.uiTop(mines, frame, width, height), BorderLayout.NORTH);
        frame.add(UI.uiSide(mines, width, height, frame), BorderLayout.EAST);

        // Add grid layout
        frame.add(Layout.cellLayout(width,height,mines,frame));
        frame.setVisible(true);

        // Timer to check if the game is over
        ActionListener timeIncrement = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //If game has ended - stop timer
                if(CellCreation.bombPressed || CellCreation.cellsPressed == ((width * height) - mines)) {
                    UI.timeCounter.stop();
                }
            }
        };
        Timer gameTimer = new Timer(100, timeIncrement);
        gameTimer.start();

    }

    public static void main(String args[]) {
        // Send layout of the board
        JFrame frame = new JFrame();
        frameStart(frame,width,height,mines);

    }

}
