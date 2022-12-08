import Game.GameControl;
import Game.GameMenu;
import Players.Players;
import Players.PlayersSigns;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GameControl gameControl = new GameControl();
        gameControl.setContinueMainWhileLoop(true);
        gameControl.displayMenu(gameControl.mainMenu);
        gameControl.chooseMenuOption(gameControl.mainMenu);

        while (gameControl.getContinueMainWhileLoop()) {
            if (gameControl.getChoice() == GameMenu.startGame.getNumericValue()){
                gameControl.clearChoice();
                gameControl.communicate("Start Game!");
                gameControl.displayMenu(gameControl.gameTypeMenu);
                gameControl.chooseMenuOption(gameControl.gameTypeMenu);

                if (gameControl.getChoice() == GameMenu.classicGame.getNumericValue()) {
                    gameControl.clearChoice();
                    gameControl.communicate("Let's start Classic TIC TAC TOE game!");
                    gameControl.createBoard(gameControl.getStandardGameSize());
                    gameControl.createPlayers();
                    gameControl.players.initializePlayerList(gameControl.getStandardNumberOfPlayers());
                    gameControl.createPlayersSigns();
                    gameControl.signs.setSingleSignToList(gameControl.getStandardPlayer1Sign());
                    gameControl.signs.setSingleSignToList(gameControl.getStandardPlayer2Sign());
                    gameControl.players.setSignsForPlayers( gameControl.signs);
                    gameControl.players.displayPlayers();
                    gameControl.setContinuePlaying(true);

                    while (gameControl.getContinuePlaying()){
                        gameControl.board.showBoardWithRowsColumns();
                        gameControl.makeMove(gameControl.getStandardGameSize());
                        System.out.println(gameControl.getStandardSignsInRowToWin());
                        if (gameControl.checkWinningConfig(gameControl.getStandardSignsInRowToWin())){
                            System.out.println();
                            gameControl.board.showBoardWithRowsColumns();
                            gameControl.communicate("=============================");
                            gameControl.players.printNameOfCurrentPlayer(" WINS THE MATCH!");
                            gameControl.communicate("=============================");
                            System.out.println();
                            gameControl.setContinuePlaying(false);
                            gameControl.clearChoice();
                        }
                        else {
                            if (gameControl.board.checkIfAllFiledsOccupied(gameControl.getStandardGameSize())){
                                System.out.println();
                                gameControl.board.showBoardWithRowsColumns();
                                gameControl.communicate("=============================");
                                gameControl.communicate(" T I E!");
                                gameControl.communicate("=============================");
                                System.out.println();
                                gameControl.setContinuePlaying(false);
                                gameControl.clearChoice();
                            }
                        }
                        gameControl.players.alternatePlayer();
                    }
                    gameControl.displayMenu(gameControl.gameTypeMenu);
                    gameControl.chooseMenuOption(gameControl.gameTypeMenu);
                }

                if (gameControl.getChoice() == GameMenu.customGame.getNumericValue()){
                    gameControl.clearChoice();
                    gameControl.communicate("Let's prepare custom TIC TAC TOE game!");
                    // Set custom values
                    gameControl.setCustomGameSize(scanner);
                    gameControl.setCustomNumberOfPlayers(scanner);
                    gameControl.setCustomSignsInRowToWin(scanner);
                    // Initialize game with custom values and set Signs and Players.Player objects
                    gameControl.createBoard(gameControl.getCustomGameSize());
                    gameControl.createPlayers();
                    gameControl.players.initializePlayerList(gameControl.getCustomNumberOfPlayers());
                    gameControl.createPlayersSigns();
                    gameControl.players.setMultiplePlayersNames(scanner);
                    gameControl.signs.setMultipleSignsToList(scanner, gameControl.players);
                    gameControl.players.setSignsForPlayers(gameControl.signs);
                    gameControl.players.displayPlayers();
                    gameControl.setContinuePlaying(true);

                    while (gameControl.getContinuePlaying()){
                        gameControl.board.showBoardWithRowsColumns();
                        gameControl.makeMove(gameControl.getCustomGameSize());
                        if (gameControl.checkWinningConfig(gameControl.getCustomSignsInRowToWin())){
                            System.out.println();
                            gameControl.board.showBoardWithRowsColumns();
                            gameControl.communicate("=============================");
                            gameControl.players.printNameOfCurrentPlayer(" WINS THE MATCH!");
                            gameControl.communicate("=============================");
                            System.out.println();
                            gameControl.setContinuePlaying(false);
                            gameControl.clearChoice();
                        }
                        else {
                            if (gameControl.board.checkIfAllFiledsOccupied(gameControl.getCustomGameSize())){
                                System.out.println();
                                gameControl.board.showBoardWithRowsColumns();
                                gameControl.communicate("=============================");
                                gameControl.communicate(" T I E!");
                                gameControl.communicate("=============================");
                                System.out.println();
                                gameControl.setContinuePlaying(false);
                                gameControl.clearChoice();
                            }
                        }
                        gameControl.players.alternatePlayer();
                    }
                    gameControl.displayMenu(gameControl.gameTypeMenu);
                    gameControl.chooseMenuOption(gameControl.gameTypeMenu);
                }

                if (gameControl.getChoice() == GameMenu.back.getNumericValue()){
                    gameControl.clearChoice();
                    gameControl.communicate("Back");
                    gameControl.displayMenu(gameControl.mainMenu);
                    gameControl.chooseMenuOption(gameControl.mainMenu);
                }
            }

            if (gameControl.getChoice() == GameMenu.quitGame.getNumericValue()){
                gameControl.clearChoice();
                gameControl.communicate("Quit Game!");
                gameControl.setContinueMainWhileLoop(false);
            }
        }
        gameControl.communicate("End of the game.");
    }
}
