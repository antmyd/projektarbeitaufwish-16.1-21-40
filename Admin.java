package model;

public class Admin extends Villain {
    public Admin() {
        super("Admin", 150, "Decompiler", new NrGuessing());
    }

    @Override
    public void attack() {
        if (!isAlive()) return;
        System.out.println(getName() + " nutzt seinen Computer, um dir den Zugang zu erschweren!");
    }

    @Override
    public void presentPuzzle() {
        if (isArtefactPresent()) {
            System.out.println(getName() + " fordert dich zu einem Zahlen-Rätsel heraus, um den Decompiler zu gewinnen!");
            getPuzzle().playNextRound();
        } else {
            System.out.println(getName() + " hat dir den Decompiler bereits gegeben. Kein Rätsel mehr!");
        }
    }
}
