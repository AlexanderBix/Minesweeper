package Board;

import java.util.Arrays;

import static Board.CellIndex.adjacentCells;

public class Bomb {
    public int x;
    public int y;

    // Pushes new value into a Bomb Index Array
    public static Bomb[] increaseIndex(Bomb entry, Bomb[] bombIndex) {
        Bomb[] newBomb = Arrays.copyOf(bombIndex, bombIndex.length + 1);
        newBomb[newBomb.length - 1] = entry;
        return newBomb;
    }

    // Initializes location of bombs
    public static Bomb[] bombInitializer(int x, int y,int width, int height, int mines) {

        // Initializing bomb index
        Bomb[] bombIndex = {};

        int i = 0;
        int match = 0;

        // Initialize bomb locations
        int bombCopy = mines;
        while(bombCopy > 0) {
            int bombX = (int) (height * Math.random());
            int bombY = (int) (width * Math.random());

            // Making sure that bomb is not clicked x/y coordinate
            if (bombX == x && bombY == y) {
                match = 1;
            }

            // Making sure that bomb is not in the same position as another bomb
            for (int j = 0; j < bombIndex.length; j++) {
                if (bombIndex[j].x == bombX && bombIndex[j].y == bombY) {
                    match = 1;
                }
            }

            // If bomb is not in an already selected position
            if(match != 1) {
                // Add bomb to bomb list
                Bomb newBomb = new Bomb();
                newBomb.x = bombX;
                newBomb.y = bombY;
                bombIndex = increaseIndex(newBomb,bombIndex);
                i++;
                bombCopy = bombCopy - 1;
            }

            match = 0;

        }

        return bombIndex;
    }

    // Determines how many bombs are located near an adjacent cell
    public static int adjacentBombs(Bomb[] bombIndex, CellIndex adjEntry, int width, int height) {
        int counter = 0;

        // Get adjacent cells of adjacent entry
        CellIndex[] adjacentEntry = adjacentCells(adjEntry.x,adjEntry.y,width,height, bombIndex, true);

        for(int i = 0; i < bombIndex.length; i++) {
            for(int j = 0; j < adjacentEntry.length; j++) {
                if(adjacentEntry[j].x == bombIndex[i].x && adjacentEntry[j].y == bombIndex[i].y) {
                    counter++;
                }
            }
        }
        return counter;
    }

    // Determines if coordinates is a bomb
    public static boolean isBomb(int x, int y, Bomb[] bombIndex) {
        for(int i = 0; i < bombIndex.length; i++) {
            if(bombIndex[i].x == x && bombIndex[i].y == y) {
                return true;
            }
        }
        return false;
    }

}
