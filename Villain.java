package model;

import java.util.Random;
import java.util.UUID;
public class Villain {
    
    //Attribute
    private final UUID id;
    private String name;
    private int energyPointsCurrent;
    private boolean alive;
    private String guardedArtefact;
    private boolean artefactPresent;
    private Game puzzle;

    //Konstruktor
    public Villain(String name, int energyPointsCurrent, String guardedArtefact, Game puzzle) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.energyPointsCurrent = energyPointsCurrent;
        this.alive = true;
        this.guardedArtefact = guardedArtefact;
        this.artefactPresent = true;
        this.puzzle = puzzle;
    }

     // Getter und Setter
     public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergyPointsCurrent() {
        return energyPointsCurrent;
    }

    public void setEnergyPointsCurrent(int energyPointsCurrent) {
        this.energyPointsCurrent = energyPointsCurrent;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getGuardedArtefact() {
        return guardedArtefact;
    }

    public void setGuardedArtefact(String guardedArtefact) {
        this.guardedArtefact = guardedArtefact;
    }

    public boolean isArtefactPresent() {
        return artefactPresent;
    }

    public void setArtefactPresent(boolean artefactPresent) {
        this.artefactPresent = artefactPresent;
    }

    public Game getPuzzle() {
        return puzzle;
    }

    //Methoden
    public void setPuzzle(Game puzzle) {
        this.puzzle = puzzle;
    }
    
    
    public void attack() {
        if (!alive) return;
        System.out.println(name + " attacks!");
    }

    public void takeDamage(int damage) {
        energyPointsCurrent -= damage;
        if (energyPointsCurrent <= 0) {
            alive = false;
            System.out.println(name + " has been defeated!");
        }
    }

    public void presentPuzzle() {
        if (puzzle != null) {
            puzzle.playNextRound();
        }
    }

    public static Villain createRandomVillain() {
        Random rand = new Random();
    
        String[] artefacts = {
            "Decompiler Source Code",
            "Internal Memo",
            "Alpaca Mascot"
        };
    
        String artefact = artefacts[rand.nextInt(artefacts.length)];
        int energyPoints = 50 + rand.nextInt(51);
    
        // Villain-Typ bestimmen
        int villainType = rand.nextInt(4);
        String name;
        Game puzzle;
    
        if (villainType == 0) {
            name = "Employee";
            puzzle = null; // Employees haben kein Puzzle
        } else if (villainType == 1) {
            name = "Admin";
            puzzle = new NrGuessing();
        } else if (villainType == 2) {
            name = "Manager";
            puzzle = new RollDice();
        } else {
            name = "The Big Boss";
            puzzle = new FiveInARow(); // Falls implementiert
        }
    
        return new Villain(name, energyPoints, artefact, puzzle);
    }


}