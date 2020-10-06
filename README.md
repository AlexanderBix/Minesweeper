# Minesweeper
Minesweeper game implemented in Java
By: Alexander Bix

Installation:

  - Go to 'src' folder
  - Compile program using the following command: 'javac MineSweeper.java'
  - Run Program using the following command: 'java MineSweeper'
  
Playing:
  - The user is greeted with a 12x12 game of minesweeper with 40 bombs, and 3 buttons
    - Reset
      - Resets the current play field 
    - Help
      - Gives basic instructions for the game
      - Green cells are good, red cells are bad. 
      - Each green cell will display the number of bombs adjacent to it
    - Setup
      - Allows the user to:
         - Pick a preset difficulty
           - Beginner: 4x4 with 4 mintes
           - Intermediate: 8x8 with 15 mines
           - Expert: 12x12 with 40 mines
         - Create a custom difficulty by selecting a width, height, and number of bombs they wish to play with
 - The game ends with either the user getting every green cell displayed without touching a red cell, or by touching a red cell.
         
