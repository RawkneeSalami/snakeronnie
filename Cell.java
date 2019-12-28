import java.util.*;

public class Cell
{
    private final ArrayList<String> possibleTypes;
    private final int row;
    private final int col;
    private String ANSI_COLOR;
    private String type;

	public Cell(int row, int col, String type, ArrayList<String> possibleTypes)
	{
        this.possibleTypes = possibleTypes;
        this.row = row;
        this.col = col;
        this.type = type.toLowerCase();
        this.ANSI_COLOR = "";
    }
    
    /**
     * @param setType takes in the types (apple,snake,etc) and a color which we pass in as a variable representing the ANSI value.
     * @param newType input handling makes sure the input is lower cased for good measure.
     * @param color set the color here as a private variable for this class.
     */
    public void setType(String newType, String color) {
        newType = newType.toLowerCase();
        this.ANSI_COLOR = color;
    /**
     * if w have passed in a valid type for the cell we will set the cell, otherwise we do not set the cell to prevent errors while checking.
     */
        if(possibleTypes.contains(newType)) {
            this.type = newType;
        }
        else {
            System.out.println("Internal error! Invalid type set to cell. Ignoring set command.");
        }
    }
    /**
     * These getters are public and are very usefull in the snake class
     * @return gives the "type AS A STRING" so we can use it for checking in other class.
     */

    public String getType()
    {
        return this.type;
    }

    public int getRow()
    {
        return this.row;
    }

    /**
     * I can set the items in every cell to what they need to look like for the Board
     * @return the color and the String I want on the board.
     */

    public String getString() {
        if(this.type.equals("apple"))
        {
            return ANSI_COLOR + "@";
        }
        else if(this.type.equals("snake")) {
            return ANSI_COLOR + "o";
        }
        else if(this.type.equals("x")) {
            return ANSI_COLOR + "X";
        }
        else {
            return ANSI_COLOR + " ";
        }
    }

    public int getCol()
    {
        return this.col;
    }
}