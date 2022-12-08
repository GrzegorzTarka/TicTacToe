package Board;
import Game.GameControl;
import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Row> columns;
    public static final char[] rowLetters = "ABCDEFGHI".toCharArray();     // allowed letters for describing the rows, the array length is also the max game size.
    private void initialize(int size) {
        for (int i = 0; i < size; i++) {
            Row newRow = new Row();
            newRow.initialize(size);
            columns.add(newRow);
        }
    }
    public Board(int size) {
        this.columns = new ArrayList<>();
        this.initialize(size);
    }


    public char getCharFromRowLetters(int index){ return rowLetters[index];}

    public void setSignOnBoard(GameControl gameControl, String sign){
            if (gameControl.getX() <= this.columns.size() && gameControl.getY()<= this.columns.get(0).fields.size()) {
                this.columns.get(gameControl.getX()).fields.get(gameControl.getY()).setSign(sign);
            }
            else {
                System.out.println("Given value(values) out of range");
                System.out.println("Max row number: " + this.columns.size());
                System.out.println("Max column number: " + this.columns.get(0).fields.size());
            }
        }

        public String getSignFromBoard(GameControl gameControl){
            if (gameControl.getY()  <= this.columns.size() && gameControl.getX()  <= this.columns.get(0).fields.size()) {
                return this.columns.get(gameControl.getX() ).fields.get(gameControl.getY()).getSign();
            } else {
                System.out.println("Given value(values) out of range");
                System.out.println("Max row number: " + this.columns.size());
                System.out.println("Max column number: " + this.columns.get(0).fields.size());
                return "ERROR";
            }
        }

        public void showBoardWithRowsColumns() {
            int boardSize = this.columns.size();

            System.out.print("   ");
            for (int i = 1; i <= boardSize; i++) {
                System.out.print(i + "|");
            }

            System.out.println("     ");

            for (int i = 0; i < boardSize; i++) {
                System.out.print(getCharFromRowLetters(i) + "| ");
                this.columns.get(i).show();
            }
        }

        public boolean checkSignInRow(String signToCheck, int numbersOfSignsToWin){
            int count = 0;
            for (Row column : this.columns) {
                for (int j = 0; j < column.fields.size(); j++) {
                    if (column.fields.get(j).getSign().equalsIgnoreCase(signToCheck)) {
                        count++;
                        if (count == numbersOfSignsToWin) return true;
                    } else count = 0;
                }
                count = 0;
            }
            return false;
        }


        public boolean checkSignInColumn(String signToCheck, int numbersOfSignsToWin){

            int count = 0;
            for (int i = 0; i < this.columns.get(0).fields.size(); i++) {
                for (Row column : this.columns) {

                    if (column.fields.get(i).getSign().equalsIgnoreCase(signToCheck)) {
                        count++;
                        if (count == numbersOfSignsToWin) return true;
                    } else count = 0;
                }
                count = 0;
            }
            return false;
        }

        public boolean checkSignInDiagonal1(String signToCheck, int numbersOfSignsToWin){
            int count = 0;
            for (int i = 0; i < this.columns.size(); i++) {
                if (this.columns.get(i).fields.get(i).getSign().equalsIgnoreCase(signToCheck)) {
                    count++;
                    if (count == numbersOfSignsToWin) return true;
                }
                else count = 0;
            }
            return false;
        }
        public boolean checkSignInDiagonal2(String signToCheck, int numbersOfSignsToWin){
            int count = 0;
            for (int i = (this.columns.size() - 1); i >= 0; i--) {
                int j = (this.columns.size() - 1) - i;
                if (this.columns.get(i).fields.get(j).getSign().equalsIgnoreCase(signToCheck)) {
                    count++;
                    if (count == numbersOfSignsToWin) return true;
                }
                else count = 0;
            }
            return false;
        }

    public boolean checkIfAllFiledsOccupied(int gameSize){
        int count = 0;
        for (Row column : this.columns) {
            for (int j = 0; j < column.fields.size(); j++) {
                if (column.fields.get(j).getSign().equalsIgnoreCase("-")) {
                    break;

                } else count++;

            }
        }
        if (count == (gameSize*gameSize)) return true;
        return false;
    }

}

