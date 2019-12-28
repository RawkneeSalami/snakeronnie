import java.util.*;

public class Snake
{
    /**
     * The ANSI keybaord standard allows for colors in terminal with text and background variants.
     * Here we set those to variables so that they are easier to reuse in code. See Resources.
     * local board variable
     * USING FINAL TO MAKE AURE THE VLAUE IS NOT MODIFIED AFTERWARD FOR PROPER ENCAPSULATION
     */
    private final String ANSI_GREEN = "\u001B[32m";
    private final String PURPLE_BRIGHT = "\033[0;95m";
    private final Board board;
    /**
     * The advantage of linked list is that it allows me to modify the array size, unlike an array, the linkedList can change size
     * which is usefull for adding the size of the snake. See Resources.
     * local string for the users inputed direction imported from the Game class
     */
    private final LinkedList<Cell> snakeList;
    private String direction;
    
    /**
     * 
     * @param Snake we are constructing a new board 
     * and setting up our LINKEDLIST array which will serve to set the positions of the snake
     * dummy setting the variable for direction so it does not fall into one of the null checking statements in other classes.
     */
    public Snake(Board board) {
        this.board = board;
        this.snakeList = new LinkedList<>();
        this.direction = "right";
    }

    /**
     * Initializes the snake on the board. As long as it is drawn before the apple there should be no conflicts.
     * I made sure to call them in correct order to avoid them crossing paths upon game generation.
     * The if loop sets the snakes length to be 3 cells long and connected 
     * since our Board object is public we are able to call it directly into the Cell object curcell
     * From there we set that cell to the snake evertime, and then the loop sets the nearby cells to snake aswell. The snake will always start here.
     * I set the cell to our STRING snake @param setType and we have a additionaly argument ANSI_GREEN to set its color to green which a color is expected in Cell class.
     * I then add that object to our LINKED LIST to help us with movement for adding and removing cells, growing the snake, and checking if snake "hit itself".
     */
	public void initSnake()
	{
        for(int i = 3; i < 6; i++)
        {
            Cell currCell = board.boardArray.get(3).get(i);
            currCell.setType("snake", ANSI_GREEN);
            snakeList.add(currCell);
        }
    }

    /**
     * I take the direction the user input and set it to this.direction
     * then we compare it with what movement they want with the .equals method
     * the return values BY HOW MUCH THEY NEED TO MOVE in either X or Y dimensions. For some reason the X and Y dimentions are flipped int the array.
     * @param newDirection is how to set the local direction variable
     * @return we return true so that the if statment in Game class does not recieve a flase that ends Causes the game over screen
     */
    
    public boolean moveSnake(String newDirection) {
        this.direction = newDirection;

        if(direction.equals("d"))
        {
            return moveCell(0, 1);
        }
        else if(direction.equals("a"))
        {
            return moveCell(0, -1);
        }
        else if(direction.equals("w"))
        {
            return moveCell(-1, 0);
        }
        else if(direction.equals("s"))
        {
            return moveCell(1, 0);
        }

        board.showBoard();
        return true;
    }

    /**
     * @param moveCell takes the input in x and y format from the user input form above
     * @param Cell snakehead is what I call the First item in the LINKEDLIST array, @param peekLast is a method of linked list that helps
     * to determine where the head of our snake is.so we know what index to move.
     * by setting this exactly to an object its easier and cleaner to move the snake in code
     * @param newRow I simply add , if any, the value to move by 1,-1,0 depending on the direction and do the same for @param newCol
     */

    private boolean moveCell(int rowChange, int colChange) {
        Cell snakeHead = this.snakeList.peekLast();
        int newRow = rowChange + snakeHead.getRow();
        int newCol = colChange + snakeHead.getCol();

        /**
         * This if statment is for borderchecking, here if the player tries to put the snake cell on a border cell ( I check with integers set in board) a purple X override the 
         * cells current info and the flase is returned so that the GAME OVER sequence in initiated in the @param gameLoop in the Game class.
         */
        if(newRow < 1 || newRow > board.boardHeight - 2 || newCol < 1 || newCol > board.boardWidth - 2)
        {
            board.boardArray.get(newRow).get(newCol).setType("x", PURPLE_BRIGHT);
            return false;
        }

        /**
         * SNAKE HIT CHECK
         * The for loop here is a FOREACH loop( it checks the snakeList for each Cell type object) that makes writing the for loop so much eaiser, its specific to arrays/LINKEDLISTS.See resources
         * In the if statment to see what the snake is hitting, EX if it hits itself it makes a purple x to show where the player lost.
         */
        for(Cell cell : this.snakeList)
        {
            if(newRow == cell.getRow() && newCol == cell.getCol())
            {
                board.boardArray.get(newRow).get(newCol).setType("x", PURPLE_BRIGHT);
                return false;
            }
        }
        /**
         *  Remove tail of snake and set to blank, unless our next space is an apple. Otherwise create new apple.
         */

        if(board.boardArray.get(newRow).get(newCol).getType().equals("apple"))
        {
            board.setNewApple();
        }
        else
        {
            this.snakeList.remove().setType("blank", "");
        }

        /**
         * Modify the next cell
         * then we move the snake because it did not get hit( if it did the program would have ended before reaching this method.)
         * then we add that new position to our snakeList array.
         */
        System.out.printf("R: %d, C: %d\n", newRow, newCol);
        board.boardArray.get(newRow).get(newCol).setType("snake", ANSI_GREEN);
        this.snakeList.add(board.boardArray.get(newRow).get(newCol));


        return true;
    }
}