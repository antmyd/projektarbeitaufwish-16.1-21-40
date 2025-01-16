package model;

import java.util.Scanner;
import java.util.UUID;

/**
 * @author antonmyd
 */
public class Superhero {

    // Attribute
    private final UUID id; 
    private String name;
    private int energyPointsCurrent;
    private int energyPointsMax;
    private int experiencePoints; 
    private int lives;
    private boolean readyToFight;
    private boolean inFight;
    private boolean alive;

    // Konstruktor ohne Parameter
    public Superhero(String name) {
        this.id = UUID.randomUUID(); 
        this.name = name;
        this.energyPointsMax = 100; 
        this.energyPointsCurrent = energyPointsMax; 
        this.experiencePoints = 0; 
        this.lives = 3; 
        this.readyToFight = true; 
        this.inFight = false; 
        this.alive = true; 
    }

    // Konstruktor mit Parametern
    public Superhero(String name, int energyPointsMax, int lives) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.energyPointsMax = energyPointsMax;
        this.energyPointsCurrent = energyPointsMax;
        this.experiencePoints = 0;
        this.lives = lives;
        this.readyToFight = true;
        this.inFight = false;
        this.alive = true;
    }

    // Getter und Setter
    public String getId() {
        return id.toString(); // UUID als String zurückgeben
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

    public int getEnergyPointsMax() {
        return energyPointsMax;
    }

    public void setEnergyPointsMax(int energyPointsMax) {
        this.energyPointsMax = energyPointsMax;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
        this.energyPointsMax = 20 + experiencePoints / 3; // Energiepunkte skalieren mit Erfahrung
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isReadyToFight() {
        return readyToFight;
    }

    public void setReadyToFight(boolean readyToFight) {
        this.readyToFight = readyToFight;
    }

    public boolean isInFight() {
        return inFight;
    }

    public void setInFight(boolean inFight) {
        this.inFight = inFight;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // Methoden
    public void attack() {
        if (!readyToFight || !alive) {
            System.out.println(name + " kann nicht angreifen.");
            return;
        }
        System.out.println(name + " greift an!");
    }

    public void takeDamage(int damage) {
        energyPointsCurrent -= damage;
        if (energyPointsCurrent <= 0) {
            lives--;
            if (lives > 0) {
                energyPointsCurrent = energyPointsMax;
                System.out.println(name + " hat ein Leben verloren, regeneriert aber Energie.");
            } else {
                alive = false;
                System.out.println(name + " ist gestorben!");
            }
        }
    }

    public void dance() {
        System.out.println(this.getName() + " tanzt!");
    }
    
    public boolean isReady() {
        return this.alive && this.energyPointsCurrent > 0;
    }
    
    public Superhero selectHero(Superhero[] heroes) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Wähle einen Superhelden:");

    // Einfache Schleife, um nur die Namen der Helden anzuzeigen
    for (int i = 0; i < heroes.length; i++) {
        System.out.println(heroes[i].getName());
    }

    // Keine Überprüfung der Wahl, einfach die Eingabe abfragen
    int choice = sc.nextInt();
    // Rückgabe des gewählten Helden ohne Validierung
    return heroes[choice - 1];
}
}