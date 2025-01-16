package model;

public class TheBigBoss extends Villain {
    public TheBigBoss() {
        super("The Big Boss", 300, "Alpaka-Maskottchen", new FiveInARow());
    }

    @Override
    public void attack() {
        if (!isAlive()) return;
        System.out.println(getName() + " lacht dich aus und setzt seine überlegene Strategie ein!");
    }

    @Override
    public void presentPuzzle() {
        if (isArtefactPresent()) {
            System.out.println(getName() + " fordert dich zu einem Spiel 5 in einer Reihe heraus, um das Alpaka-Maskottchen zu gewinnen!");
            getPuzzle().playNextRound();
        } else {
            System.out.println(getName() + " hat dir das Alpaka-Maskottchen bereits gegeben. Kein Rätsel mehr!");
        }
    }
}