import java.util.ArrayList;
import java.util.Random;

public class Board {
    private final String YELLOW_BACKGROUND = "\033[43m";
    private final String WHITE_BACKGROUND = "\033[47m";
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final Random rand;
    private final String reDrawString;
    public final ArrayList<ArrayList<Cell>> boardArray;
    public final int boardWidth;
    public final int boardHeight;

    /**
     * Constructor for the board.
     * 
     * @param boardWidth  defines the width of the board.
     * @param boardHeight defines the height of the board.
     * @param reDrawFlag  enables redrawing of the terminal window in UNIX based
     *                    systems or consoles. See Resources. 
     * @param this.rand I am getting a new random number to be used for the position of the apple
     */
    public Board(int boardWidth, int boardHeight, boolean reDrawFlag) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.boardArray = new ArrayList<>();
        this.rand = new Random();

        if (reDrawFlag) {
            this.reDrawString = "\u001b[2J";
        } else {
            this.reDrawString = "";
        }
    }

    /**
     * Initializes the board.
     * 
     * @param possibleTypes defines the possible types there can be for error
     *                      checking.
     */
    public void initializeBoard(ArrayList<String> possibleTypes) {
        for (int row = 0; row < this.boardHeight; row++) {
            // Setup Rows, Initialize a new ArrayList for each Row.
            boardArray.add(new ArrayList<Cell>());
            for (int col = 0; col < this.boardWidth; col++) {
                // Initialize Cols. Creating a new Cell object for each cell.
                Cell cell = new Cell(row, col, "blank", possibleTypes);
                /**
                 * Below I am setting the Borders for the board to be WHiteBackrounds, different from shite Char in ansi.
                 */
                if(row == 0 || row == this.boardHeight - 1 || col == 0 || col == this.boardWidth - 1)
                {
                    cell.setType("blank", WHITE_BACKGROUND);
                }
                boardArray.get(row).add(cell);
            }
        }
    }

    /**
     * Sets the apple's location within the border I have created and to where their is not a snake.
     */
    public void setNewApple() {
        while(true)
        {
            int row = rand.nextInt(this.boardWidth - 2) + 1;
            int col = rand.nextInt(this.boardHeight - 2) + 1;
            Cell randomCell = this.boardArray.get(row).get(col);
            String cellType = randomCell.getType();

            if(cellType.equals("blank")) {
                randomCell.setType("apple", YELLOW_BACKGROUND + ANSI_RED);
                break;
            }
        }
    }

	public void showBoard()
	{
        // Redraws if the string is set to the redraw sequence.
        // Otherwise it writes an empty string to the console.
        System.out.print(reDrawString);
        System.out.flush();

		drawTopBottom();
		for(int row = 0; row < this.boardHeight; row++)
		{
			System.out.print("|");
			for(int col = 0; col < this.boardWidth; col++)
			{
				System.out.print(this.boardArray.get(row).get(col).getString() + " " + ANSI_RESET);
			}

			System.out.println("|");
        }

        drawTopBottom();
    }

    public void clearCell(int row, int col) {
        this.boardArray.get(row).get(col).setType("blank", "");
    }
    
    private void drawTopBottom()
    {
        System.out.print(" ");
        for(int i = 0; i < boardWidth; i++)
        {
            System.out.print("__");
            
        }

        System.out.print("\n");
        System.out.println("--------------------------------------------------------");
        System.out.println("TO MOVE, enter 'w' for UP, 's' for DOWN,\n 'a' for LEFT, or 'd' for RIGHT FOLLOWED BY THE | ENTER | KEY.");
        System.out.println("--------------------------------------------------------");
    }

	
}