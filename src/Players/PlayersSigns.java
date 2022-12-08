package Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PlayersSigns {

    private List<String> list;

    public PlayersSigns() {
        this.list = new ArrayList<>();
    }
    public void setSingleSignToList(String sign){
        this.list.add(sign);
    }
    public void setMultipleSignsToList(Scanner scanner, Players players) {
        String input;
        String playerDecision;
        boolean signConfirmed = false;
        boolean providedYorN = false;

        for (Player player : players.playerList) {
            while (!signConfirmed) {
                System.out.println("Introduce the sign for the " + player.getName());
                input = scanner.next();
                if (input.length() == 1) {
                    if (!list.contains(input)) {
                        System.out.println("Confirm the introduced sign (" + input + ") for the " + player.getName() + " (Y/N)");
                        while (!providedYorN) {
                            playerDecision = scanner.next();
                            playerDecision = playerDecision.toUpperCase();

                            if (playerDecision.equalsIgnoreCase("Y")) {
                                System.out.println("Sign confirmed");
                                this.list.add(input);
                                providedYorN = true;
                                signConfirmed = true;
                            } else if (playerDecision.equalsIgnoreCase("N")) {
                                System.out.println("Sign not confirmed");
                                providedYorN = true;
//                                signConfirmed = false;
                            }
                            else {
                                System.out.println("Introduced answer is not Y or N. Confirm name. (Y/N)");
//                                signConfirmed = false;
                            }
                        }
                        providedYorN = false;
                    } else {
                        System.out.println("The sign " + input + " is already occupied.");
//                        signConfirmed = false;
                    }
                }
                else {
                    System.out.println("The input is to long, introduce one sign.");
//                    signConfirmed = false;
                }
            }
            signConfirmed = false;
//            providedYorN = false;
        }
    }
    public String getSignFromList(int index){
        return this.list.get(index);
    }
}

