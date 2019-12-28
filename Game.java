import java.io.Console;
import java.util.*;

public class Game
{
    Board board;
    Snake snake;
    
    /**
     * @author Ronnie Saini
     * 
     * Constructor for game.
     */
    public Game() 
    {
    /**
     * @param new board - Creating a board that is 20 by 20 text character boxs tall and wide and setting the boolean to wipe the screen to true
     * @param new Snake - passes our Board object named board to be used in snake to set a cell in the board to Snake String.
     * @param initializeBoard - Creates a new Array list from the Arrays class was IMPORTED, this creates an array with a set numebr of strings our objects for each cell
     * @param initSnake - We passed the board above and now we are setting a snake within the boards cells
     * @param setNewApple - Here we are calling the method to Set a cell to an apple on the board
     */
        this.board = new Board(20, 20, true);
        this.snake = new Snake(board);
        this.board.initializeBoard(new ArrayList<>(Arrays.asList("blank", "apple", "snake", "x")));
        this.snake.initSnake();
        this.board.setNewApple();
    }

    /**
     * @param gameLoop - is used to validate that the game is still active 
     * @param showBoard  - reset the terminal to be clear then flushes the method and buffer.
     * @param Console terminal  - creates a terminal object of the System console for the input in the following code
     * If the terminal is not null then we make sc an object for us to accept the consoles input
     * then we create a Scanner object that we will use for the input of the terminal expecting a character for the movement.
     * Then we move the snake in the direction the user inputs by passing the input character to the @param moveSnake
     * IF THE USER DOES NOT PASS @param moveCell, then they have either hit the border or hit tehmseleves and the game is over and be go to lines  49,50,51.
     * --------False---------
     * @param showBoard redraws the board
     * Then the game ends, the loop exits and the sc object is closed.
     */

    public void gameLoop() {
        while(true) {
            board.showBoard();
            Console terminal = System.console();
            if(terminal != null) {
                Scanner sc = new Scanner(terminal.reader());
                if(!snake.moveSnake(sc.next().toString())) {
                    board.showBoard();
                    System.out.println("GAME OVER");
                    break;
                }
                sc.close();
            }
        }
    }


    /**
     * 
     * @param main() we are calling the methods theat we defined above to start the game and passing in directions on how to play, since this only runs once.
     * we create the newGame object in here for us to use on the gameLoop 
     */
	public static void main(String [] args)
	{
        System.out.println("\n TO MOVE ENTER 'w' for UP, 's' for DOWN, 'a' for LEFT, or 'd' for RIGHT \n FOLLOWED BY THE ENTER KEY.");
        Game newGame = new Game();
        newGame.gameLoop();
	}
}