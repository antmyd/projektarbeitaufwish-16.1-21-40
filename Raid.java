package model;

import java.util.Random;
import java.util.Scanner;

public class Raid {

    private Superhero[] heroes; // Liste der Superhelden
    private int heroCount; // Anzahl der Superhelden
    private boolean raidActive;
    private boolean[] artefactsCollected; // Um zu verfolgen, welche Artefakte gesammelt wurden.

    // Konstruktor, um Superhelden zu initialisieren
    public Raid(Superhero[] heroes) {
        this.heroes = heroes;
        this.heroCount = heroes.length;
        this.artefactsCollected = new boolean[3]; // 3 Artefakte (Admin, Manager, BigBoss)
        this.raidActive = false;
    }

    // Raubzug starten
    public void startRaid() {
        raidActive = true;
        System.out.println("Raid gestartet!");
        Scanner sc = new Scanner(System.in);

        while (raidActive) {
            System.out.println("=== RAID MENU ===");
            System.out.println("(1) Erforschen");
            System.out.println("(2) Tanzen");
            System.out.println("(3) Raubzug beenden");
            System.out.print("Wähle eine Option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    explore();
                    break;
                case 2:
                    dance();
                    break;
                case 3:
                    raidActive = false;
                    System.out.println("Raubzug beendet.");
                    break;
                default:
                    System.out.println("Ungültige Wahl. Versuche es nochmal.");
            }
        }
    }

    // Erforschen der Gegend
    private void explore() {
        Random rand = new Random();
        Villain encounteredVillain = createRandomVillain(); // Zufälligen Gegner erstellen

        System.out.println("\nDu erforschst die feindliche Zentrale...");
        if (rand.nextBoolean()) {
            System.out.println("Du hast einen " + encounteredVillain.getName() + " getroffen!");
            if (encounteredVillain.isArtefactPresent()) {
                meetEnemy(encounteredVillain);
            } else {
                System.out.println("Du hast diesen Gegner schon besiegt.");
                System.out.println("Der Gegner hatte kein Artefakt. Schleich dich davon.");
            }
        } else {
            System.out.println("Kein Gegner gefunden. Es ist sicher.");
        }
    }

    // Gegner treffen
    private void meetEnemy(Villain villain) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Your team has detected an enemy. Which superhero do you want to choose for this quest?");
        
        // Liste der Superhelden anzeigen
        for (int i = 0; i < heroes.length; i++) {
            System.out.println((i + 1) + ". " + heroes[i].getName());
        }
        
        // Auswahl des Superhelden
        int choice = sc.nextInt();
        if (choice > 0 && choice <= heroes.length) {
            Superhero selectedHero = heroes[choice - 1]; // Ausgewählter Superheld
            
            // Überprüfen, ob der Superheld bereit ist zu kämpfen
            if (selectedHero.isReadyToFight() && selectedHero.isAlive()) {
                System.out.println("Superhero " + selectedHero.getName() + " has been selected for the quest and will fight against " + villain.getName());
                
                // Entscheidung: Kämpfen oder Herausfordern
                System.out.println("(1) Fight");
                System.out.println("(2) Challenge Enemy");
                int action = sc.nextInt();
    
                if (action == 1) {
                    // Szenario 1: Kämpfen
                    fightEnemy(selectedHero, villain);
                } else if (action == 2) {
                    // Szenario 2: Rätsel spielen
                    challengeEnemy(selectedHero, villain);
                } else {
                    System.out.println("Invalid choice.");
                }
            } else {
                System.out.println(selectedHero.getName() + " is not ready to fight or is dead.");
            }
        } else {
            System.out.println("Invalid choice. Please select a valid superhero.");
        }
    }
    
    // Kampf gegen den Gegner
    private void fightEnemy(Superhero hero, Villain villain) {
        Random rand = new Random();
        int damageToVillain = rand.nextInt(50) + 1; // Zufälliger Schaden an Gegner
        int damageToHero = rand.nextInt(50) + 1; // Zufälliger Schaden am Superhelden

        System.out.println("Fight: " + hero.getName() + " vs " + villain.getName());
        System.out.println("Damage to enemy: " + damageToVillain);
        System.out.println("Damage to superhero: " + damageToHero);

        villain.takeDamage(damageToVillain);
        hero.takeDamage(damageToHero);

        if (!villain.isAlive()) {
            System.out.println("Enemy defeated!");
            System.out.println("However, since the enemy was carrying an artifact, you won’t get it.");
        } else {
            System.out.println("You lost the fight.");
            hero.takeDamage(20); // Leben verlieren
        }
    }

    // Gegner herausfordern (Rätsel)
    private void challengeEnemy(Superhero hero, Villain villain) {
        System.out.println(hero.getName() + " is challenging " + villain.getName() + "!");
        villain.presentPuzzle(); // Der Gegner präsentiert sein spezifisches Rätsel
    
        if (!villain.isAlive()) {
            System.out.println(villain.getName() + " has been defeated and the artifact is yours!");
            villain.setArtefactPresent(false); // Artefakt wird gesammelt
            hero.setExperiencePoints(hero.getExperiencePoints() + 10); // Erfahrungspunkte hinzufügen
        } else {
            System.out.println("The challenge was not successful. Try again!");
            hero.takeDamage(20); // Schaden, wenn die Herausforderung scheitert
        }
    }
    
    // Tanz-Methode
    private void dance() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Wähle einen Superhelden zum Tanzen:");
        for (int i = 0; i < heroes.length; i++) {
            System.out.println((i + 1) + ". " + heroes[i].getName());
        }
        int choice = sc.nextInt();
        if (choice > 0 && choice <= heroes.length) {
            Superhero selectedHero = heroes[choice - 1];
            selectedHero.dance(); // Tanzmethoden des Superhelden aufrufen
        } else {
            System.out.println("Ungültige Wahl.");
        }
    }

    // Zufälligen Gegner erstellen
    private Villain createRandomVillain() {
        Random rand = new Random();
        int villainType = rand.nextInt(2);  // 50% chance für Employee und 50% für andere Gegner
        if (villainType == 0) {
            return new Employee(); // Mitarbeiter
        } else {
            int opponentType = rand.nextInt(3); // 0, 1 oder 2 für Admin, Manager oder BigBoss
            switch (opponentType) {
                case 0: return new Admin();    // Admin
                case 1: return new Manager();  // Manager
                case 2: return new TheBigBoss(); // Big Boss
                default: return new Employee(); // Default zu Employee
            }
        }
    }

    // Prüfen, ob alle Artefakte gesammelt wurden
    private void checkGameWin() {
        boolean allCollected = true;
        for (boolean collected : artefactsCollected) {
            if (!collected) {
                allCollected = false;
                break;
            }
        }
    
        if (allCollected) {
            System.out.println("Herzlichen Glückwunsch! Du hast alle drei Artefakte gesammelt und das Spiel gewonnen!");
            raidActive = false; // Spiel beenden
        }
    }
}



