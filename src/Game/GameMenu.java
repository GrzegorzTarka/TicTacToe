package Game;

public enum  GameMenu {
    startGame("Start game", 1),
    quitGame("Quit game", 2),
    back("Back", 4),
    classicGame("Classic TIC TAC TOE", 5),
    customGame("Custom TIC TAC TOE", 6);

    private final String description;
    private final int number;

    GameMenu(String description, Integer number) {
        this.number = number;
        this.description = "\t[ " + this.number + " ] " + description;
    }
    public String getDescription() { return this.description;}
    public int getNumericValue() { return this.number;}
}