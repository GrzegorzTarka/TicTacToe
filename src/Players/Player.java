package Players;

public class Player {
    private String name;
    public String playerSign;

    public  Player (int objectNumber){
        this.name = "Players.Players.Player " + objectNumber;
        this.playerSign = "\"no sign yet\"";}

    public void setName(String newName) {
        this.name = newName;
    }
    public String getName() {
        return this.name;
    }

    public void setIndividualPlayerSign(String signToSet){
        this.playerSign = signToSet;
    }
}
