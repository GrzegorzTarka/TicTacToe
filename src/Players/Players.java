package Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Players {
    private int objectNumber;
    private static int numberOfCreatedInstances;
    public static int numberOfCurrentPlayer;
    public List<Player> playerList;

    public Players() {
        this.playerList = new ArrayList<>();
     }

    public void initializePlayerList(int playersToCreate) {
        for (int i = 0; i < playersToCreate; i++){
            numberOfCreatedInstances++;
            objectNumber = numberOfCreatedInstances;

            Player newPlayer = new Player(objectNumber);
            playerList.add(newPlayer);
        }
        numberOfCurrentPlayer = 0;
        numberOfCreatedInstances = 0;
    }
    public void setSignsForPlayers(PlayersSigns signs) {
        int count = 0;
        String signToAssign;

        for (Player player : this.playerList) {
            signToAssign = signs.getSignFromList(count);
            player.setIndividualPlayerSign(signToAssign);
            count++;
        }
    }

    public String getSignOfCurrentPlayer(){
        return this.playerList.get(Players.numberOfCurrentPlayer).playerSign;
    }
    public String getNameOfCurrentPlayer(){
        return this.playerList.get(Players.numberOfCurrentPlayer).getName();
    }

    public void printNameOfCurrentPlayer(String text){
        System.out.println(this.playerList.get(Players.numberOfCurrentPlayer).getName() + text);
    }

    public void alternatePlayer(){
        numberOfCurrentPlayer++;
        if (numberOfCurrentPlayer > (this.playerList.size()-1)) numberOfCurrentPlayer = 0; // go to 0 index after the last player
    }

    public void displayPlayers(){
        int count =0;
        System.out.println("========================================================================================================================");
        for(Player player : this.playerList){
            count++;
            // Displaying the "vs." when there are more players to display
            if (count < this.playerList.size()){
                System.out.print(player.getName() + " playing with " + player.playerSign + " vs. ");}
            // displaying the last player, no "vs."
            else System.out.println(player.getName() + " playing with " + player.playerSign);
        }
        System.out.println("========================================================================================================================");
    }
    public void setMultiplePlayersNames(Scanner scanner) {
        String input;
        String playerDecision;
        boolean nameConfirmed = false;
        boolean providedYorN = false;
        boolean nameOccupied = false;

        for (Player player : this.playerList) {
            while (!nameConfirmed) {
                System.out.println("Introduce new name for the " + player.getName().substring(16));
                input = scanner.next();

                for (Player playerForCheckingName : this.playerList){
                    if (playerForCheckingName.getName().equalsIgnoreCase(input)){
                        nameOccupied = true;
                        break;
                    }
                }
                if (!nameOccupied) {
                    System.out.println("Confirm the introduced name (" +  input + ") for the " + player.getName().substring(16) + " (Y/N)");
                    while (!providedYorN) {
                        playerDecision = scanner.next();
                        playerDecision = playerDecision.toUpperCase();

                        if (playerDecision.equalsIgnoreCase("Y")) {
                            System.out.println("Name confirmed");
                            player.setName(input);
                            providedYorN = true;
                            nameConfirmed = true;
                        } else if (playerDecision.equalsIgnoreCase("N")) {
                            System.out.println("Name not confirmed");
                            providedYorN = true;
                            nameConfirmed = false;
                        } else {
                            System.out.println("Introduced answer (" +  playerDecision + ")  is not Y or N. Confirm name. (Y/N)");
                            providedYorN = false;
                            nameConfirmed = false;
                        }
                    }
                    providedYorN = false;
                }
                else {
                    System.out.println("The name (" + input + ") is already occupied.");
                    nameConfirmed = false;
                    nameOccupied = false;}
            }
            nameConfirmed = false;
            providedYorN = false;
        }
    }
}
