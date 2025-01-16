package model;

public class Manager extends Villain {
    public Manager() {
        super("Manager", 200, "Interne Memo", new RollDice());
    }

    @Override
    public void attack() {
        if (!isAlive()) return;
        System.out.println(getName() + " hält eine motivierende Rede, um deine Konzentration zu stören!");
    }

    @Override
    public void presentPuzzle() {
        if (isArtefactPresent()) {
            System.out.println(getName() + " fordert dich zu einem Würfelspiel heraus, um die Interne Memo zu gewinnen!");
            getPuzzle().playNextRound();
        } else {
            System.out.println(getName() + " hat dir die Interne Memo bereits gegeben. Kein Rätsel mehr!");
        }
    }
}