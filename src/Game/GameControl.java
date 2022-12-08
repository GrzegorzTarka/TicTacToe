package Game;
import Board.Board;
import Players.Players;
import Players.PlayersSigns;
import java.util.EnumSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import static Board.Board.rowLetters;


public class GameControl {
    public Board board;
    public Players players;
    public PlayersSigns signs;
    // Custom game variables
    private int customGameSize;   
    private int customNumberOfPlayers;
    private int customSignsInRowToWin;

    // Standard game variables
    private final int standardNumberOfPlayers = 2;
    private final int standardGameSize = 3;
    private final int standardSignsInRowToWin = 3;
    private final String standardPlayer1Sign = "O";
    private final String standardPlayer2Sign = "X";

    // Common game variables

    private final int maxGameSize = rowLetters.length; // the max size of the game is determined by the number of alphabet letters.
    private final static int allowedDataAmountForMove = 2;
    private int [] coordinates;

    public void createBoard (int size) {
        board = new Board(size);
    }
    public void createPlayers () {
        players = new Players();
    }
    public void createPlayersSigns () {
        signs = new PlayersSigns();
    }


//    MENU
//    In order to declare the menu options introduce the numeric value of the Game menu enum or its corresponding integer number.
    public final int [] mainMenu ={
        GameMenu.startGame.getNumericValue(),
        GameMenu.quitGame.getNumericValue()};

    public final int [] gameTypeMenu ={
            GameMenu.back.getNumericValue(),
            GameMenu.classicGame.getNumericValue(),
            GameMenu.customGame.getNumericValue()};

    // Game flow control
    private boolean continueMainWhileLoop;
    private boolean continuePlaying;

    private int choice;

    public GameControl() {
        System.out.println("Starting the game!");
        coordinates = new int[2];
    }
    public int getChoice() {return choice;}
    public void setContinuePlaying(boolean value){        continuePlaying = value;}
    public boolean getContinuePlaying() {return continuePlaying;}
    public int getStandardNumberOfPlayers() {return standardNumberOfPlayers;}
    public int getStandardGameSize() {return standardGameSize;}
    public void setContinueMainWhileLoop(boolean value){continueMainWhileLoop = value;}
    public boolean getContinueMainWhileLoop() {return continueMainWhileLoop;}
    public int getStandardSignsInRowToWin() { return standardSignsInRowToWin;}
    public String getStandardPlayer1Sign() { return standardPlayer1Sign;}
    public String getStandardPlayer2Sign() { return standardPlayer2Sign;}
    public int getCustomGameSize() {return customGameSize;}
    public int getCustomSignsInRowToWin() { return customSignsInRowToWin;}
    public int getCustomNumberOfPlayers() { return customNumberOfPlayers;}

    public void communicate(String communicate) { System.out.println(communicate);}
    public int getX () { return this.coordinates[0];}
    public int getY () { return this.coordinates[1];}
    private void setChoice() {
        Scanner scanner = new Scanner(System.in);
        try {
            this.choice = scanner.nextInt();}
        catch (InputMismatchException e) {
            System.out.println("Introduced option does not exist.");}
    }

    public void chooseMenuOption(int [] menu) {
        boolean choiceInRange = false;
        System.out.println("Please, choose menu option: ");

        while (!choiceInRange) {
            setChoice();
            for (int j : menu) {
                if (this.choice == j) {
                    choiceInRange = true;
                    break;}
            }
            if (!choiceInRange) System.out.println("Please, choose correct menu option: ");
        }
    }

    private static void displayMenuOptionDescriptions(int [] menuOptionList) {
        for(GameMenu menuOption : EnumSet.allOf(GameMenu.class)){
            for (int j : menuOptionList)
                if (menuOption.getNumericValue() == j) {
                    System.out.println(menuOption.getDescription());
                }
        }
    }
    public void displayMenu(int [] menuToDisplay){
        System.out.println("=====================================");
        displayMenuOptionDescriptions(menuToDisplay);
        System.out.println("=====================================");
        }

    public void clearChoice(){
        choice = 0;
    }

    private int [] prepareMove(Players players, int choiceLimit){  // choice limit is equal to the game size, it determines the max row and column values.
        boolean correctRow = false;
        boolean correctColumn = false;
        int dataAmount;
        String row;
        int column = 0;
        int count = 0;
        String move;
        Scanner scanner = new Scanner(System.in);
        int [] returnArray = new int[GameControl.allowedDataAmountForMove];

        while (!(correctColumn && correctRow)){
            System.out.println(players.getNameOfCurrentPlayer() + " (sign: " + players.getSignOfCurrentPlayer() + "), please, indicate your move [row, column]. Example: A3");
            move = scanner.next();
            dataAmount = move.length();

            if (dataAmount == GameControl.allowedDataAmountForMove) {
                row = move.substring(0, 1);
                try {
                    column = Integer.valueOf(move.substring(1,2));
                }
                catch (NumberFormatException e) {
                    System.out.println("Incorrect input (" + move.charAt(1) + ") for the column.");
                }

                // Checking ROW correctness
                if (row.matches("[a-zA-Z]")) {
                    row = row.toUpperCase();
                    for (char letter : rowLetters) {
                        if (letter == row.charAt(0)) break;
                        else count++;
                    }
                    if (count < choiceLimit) {
                        correctRow = true;
                        System.out.println("Board.Row: " + row);}
                    else{
                        count = 0;
                        System.out.println("The given row (" + row + ") Is not in the range. The max row is: (" + rowLetters[choiceLimit-1] + ").");           }
                }
                else System.out.println("The given row (" + row + ") Is not a letter within the range. The max row is: (" + rowLetters[choiceLimit - 1] + ").");

                // Checking COLUMN correctness
                if (column > 0 && column <= choiceLimit) {
                    correctColumn = true;
                    System.out.println("Column: " + column);
                }
                else System.out.println("The given column (" + move.charAt(1)+ ") Is not in the range. The column should be an integer number from 1 to " + choiceLimit + ". ");

            }
            else {
                System.out.println("Incorrect entry (" + move + ")");
                System.out.println("The move should consist of ONE letter and ONE integer number.");}
        }
        returnArray [0] = count;
        returnArray [1] = column - 1; // -1 is because the user passes the columns counting from 1, so we need to subtract -1 to get the index number
        return returnArray;
    }

    public void makeMove(int gameSize){
        boolean correctMove = false;
        String signFromBoardToCheck;
        String sign = this.players.playerList.get(Players.numberOfCurrentPlayer).playerSign;

        while (!correctMove) {
            coordinates = prepareMove(this.players,gameSize);
            signFromBoardToCheck = this.board.getSignFromBoard(this);

            if (signFromBoardToCheck.equalsIgnoreCase("-")) {
                this.board.setSignOnBoard(this , sign);
                correctMove = true;
            }
            else System.out.println("Forbidden move. Try again");
        }
    }
    public boolean checkWinningConfig(int numbersOfSignsToWin){
        String signOfCurrentPlayer = this.players.getSignOfCurrentPlayer();
        return (this.board.checkSignInRow(signOfCurrentPlayer, numbersOfSignsToWin) ||
                this.board.checkSignInColumn(signOfCurrentPlayer, numbersOfSignsToWin) ||
                this.board.checkSignInDiagonal1(signOfCurrentPlayer, numbersOfSignsToWin) ||
                this.board.checkSignInDiagonal2(signOfCurrentPlayer, numbersOfSignsToWin));}

    private int getInt(Scanner scanner){
        int integer = 0;
        boolean continueInput = true;
        while (continueInput) {
            try {
                System.out.println("Please, enter an integer number.");
                integer = scanner.nextInt();
                continueInput = false;}
            catch (InputMismatchException d) {
                System.out.println("Incorrect input");
                scanner.nextLine();}
        }
        return integer;
    }
    public void setCustomGameSize(Scanner scanner){
        int input = 0;
        while(input < 3 || input > this.maxGameSize){
            System.out.println("Choose the game board size ( 3 -  " + this.maxGameSize + " ).");
            input = this.getInt(scanner);
        }
        this.customGameSize = input;
    }

    public void setCustomNumberOfPlayers(Scanner scanner){
        int input = 0;
        while(input < 2 || input > this.maxGameSize) {
            System.out.println("Choose the number of players. ( 2 -  " + this.maxGameSize + " ).");
            input = this.getInt(scanner);
        }
        this.customNumberOfPlayers = input;
    }

    public void setCustomSignsInRowToWin(Scanner scanner){
        int input = 0;
        while(input < 3 || input > this.maxGameSize) {
            System.out.println("How many signs in row to win? ( 3 -  " + this.maxGameSize + " ).");
            input = this.getInt(scanner);
        }
        this.customSignsInRowToWin = input;
    }
}


