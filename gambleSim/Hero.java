/**
 * The Hero class is the characters that can be obtained by gambling in the game.
 * Used as rewards in the GambleCasino and as selectable characters in battles.
 */

public class Hero extends Character {


    public Hero(String inName, String inRarity, int inPower) {
        super(inName, inRarity, inPower);
    }

    // Overrides the toString() method to use in the JComboBox (drop-down menu) in BattleDialog.
    public String toString() {
        return super.toString();
    }

}
