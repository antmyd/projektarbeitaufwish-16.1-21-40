package app;

import java.util.Scanner;
import model.Admin;
import model.FiveInARow;
import model.Manager;
import model.Raid;
import model.Superhero;
import model.TheBigBoss;
import model.Villain;

public class SuperheroApp {
    
    static Scanner sc = new Scanner(System.in);
    
    static Superhero[] heroes = new Superhero[3];
    
    static int heroCount = 0;

    public static void main(String[] args) {
        System.out.println("Willkommen zum Superhelden-Spiel!");

        while (true) {
            showMenu();
            int choice = readUserInput();
            switch (choice) {
                case 1:
                    createSuperhero(); 
                    break;
                case 2:
                    showSelectedSuperhero(); 
                    break;
                case 3:
                    listAllSuperheroes(); 
                    break;
                case 4:
                    deleteSuperhero(); 
                    break;
                case 5:
                Raid raid = new Raid(heroes);
                raid.startRaid();
                    break;
                case 6:
                    System.out.println("Spiel beendet. Tschüss!"); 
                    return;  // Beendet das Programm
                default:
                    System.out.println("Ungültige Eingabe. Bitte wähle eine gültige Option.");
                    break;
            }
            System.out.println("====================");
        }
    }

    static void showMenu() {
        System.out.println("\nHauptmenü:");
        System.out.println("1. Create Superhero ");
        System.out.println("2. Show Selected Superhero");
        System.out.println("3. List all Superheroes");
        System.out.println("4. Delete Superhero");
        System.out.println("5. Start Raid: ");
        System.out.println("6. Quit");
        System.out.print("Wähle eine Option: ");
    }

    static int readUserInput() {
        while (!sc.hasNextInt()) {  // Sicherstellen, dass  Eingabe Zahl ist
            sc.next();  // Ungültige Eingabe ignorieren
            System.out.print("Ungültige Eingabe. Bitte eine Zahl eingeben: ");
        }
        return sc.nextInt();
    }

    static void createSuperhero() {
        if (heroCount == 0) {
            System.out.println("Du musst 3 Superhelden erstellen, bevor du fortfahren kannst.");

           
            heroes = new Superhero[3];

            // Erstelle den ersten Superhelden
            System.out.print("Gib den Namen des Superhelden Nr. 1 ein: ");
            sc.nextLine(); 
            String name1 = sc.nextLine(); 
            Superhero hero1 = new Superhero(name1); 
            heroes[0] = hero1;
            heroCount++; 
            System.out.println("Superheld '" + name1 + "' wurde erstellt!");

            // Erstelle den zweiten Superhelden
            System.out.print("Gib den Namen des Superhelden Nr. 2 ein: ");
            String name2 = sc.nextLine(); 
            Superhero hero2 = new Superhero(name2);
            heroes[1] = hero2;
            heroCount++;
            System.out.println("Superheld '" + name2 + "' wurde erstellt!");

            // Erstelle den dritten Superhelden
            System.out.print("Gib den Namen des Superhelden Nr. 3 ein: ");
            String name3 = sc.nextLine();
            Superhero hero3 = new Superhero(name3);
            heroes[2] = hero3;
            heroCount++; 
            System.out.println("Superheld '" + name3 + "' wurde erstellt!");

            System.out.println("Du hast 3 Superhelden erstellt! Nun kannst du das Menü verwenden.");
        } else {
            System.out.println("Du hast bereits 3 Superhelden erstellt.");
        }
    }

    static void showSelectedSuperhero() {
        if (heroCount == 0) {
            System.out.println("Keine Superhelden vorhanden.");
            return;
        }
        System.out.println("Wähle einen Superhelden aus:");
        for (int i = 0; i < heroCount; i++) {
            System.out.println((i + 1) + ". " + heroes[i].getName());
        }
        System.out.print("Deine Auswahl: ");
        int choice = sc.nextInt();
        if (choice > 0 && choice <= heroCount) {
            Superhero hero = heroes[choice - 1];
            // Zeige alle relevanten Attribute des ausgewählten Superhelden
            System.out.println("\nDetails des Superhelden:");
            System.out.println("Name: " + hero.getName());
            System.out.println("Aktuelle Energie: " + hero.getEnergyPointsCurrent());
            System.out.println("Maximale Energie: " + hero.getEnergyPointsMax());
            System.out.println("Erfahrungspunkte: " + hero.getExperiencePoints());
            System.out.println("Leben: " + hero.getLives());
            System.out.println("Bereit zu kämpfen: " + hero.isReadyToFight());
            System.out.println("In einem Kampf: " + hero.isInFight());
            System.out.println("Lebendig: " + hero.isAlive());
        } else {
            System.out.println("Ungültige Auswahl. Bitte wähle einen gültigen Superhelden.");
        }
    }

    static void listAllSuperheroes() {
        if (heroCount == 0) {
            System.out.println("Keine Superhelden verfügbar.");
        } else {
            System.out.println("Alle Superhelden:");
            for (int i = 0; i < heroCount; i++) {
                Superhero hero = heroes[i];
                System.out.println("\nSuperheld " + (i + 1) + ":");
                System.out.println("Name: " + hero.getName());
                System.out.println("Aktuelle Energie: " + hero.getEnergyPointsCurrent());
                System.out.println("Maximale Energie: " + hero.getEnergyPointsMax());
                System.out.println("Erfahrungspunkte: " + hero.getExperiencePoints());
                System.out.println("Leben: " + hero.getLives());
                System.out.println("Bereit zu kämpfen: " + hero.isReadyToFight());
                System.out.println("In einem Kampf: " + hero.isInFight());
                System.out.println("Lebendig: " + hero.isAlive());
            }
        }
    }

    static void deleteSuperhero() {
        if (heroCount == 0) {
            System.out.println("Keine Superhelden verfügbar.");
            return;
        }
        System.out.println("Wähle den Superhelden, den du löschen möchtest:");
        for (int i = 0; i < heroCount; i++) {
            System.out.println((i + 1) + ". " + heroes[i].getName());
        }
        System.out.print("Deine Auswahl: ");
        int choice = sc.nextInt();
        if (choice > 0 && choice <= heroCount) {
            System.out.println("Superheld '" + heroes[choice - 1].getName() + "' wurde gelöscht.");
            for (int i = choice - 1; i < heroCount - 1; i++) {
                heroes[i] = heroes[i + 1];
            }
            heroes[heroCount - 1] = null;
            heroCount--;
        } else {
            System.out.println("Ungültige Auswahl. Bitte wähle einen gültigen Superhelden.");
        }
    }
    private void challengeEnemy(Superhero hero, Villain villain) {
    if (villain instanceof Admin) {
        // Admin Rätsel
        System.out.println("Du spielst ein Rätsel gegen den Admin!");
        playPuzzle(hero, villain);
    } else if (villain instanceof Manager) {
        // Manager Rätsel
        System.out.println("Du spielst ein Rätsel gegen den Manager!");
        playPuzzle(hero, villain);
    } else if (villain instanceof TheBigBoss) {
        // Big Boss: 5 in einer Reihe spielen
        System.out.println("Du hast den Big Boss herausgefordert! Es ist Zeit für ein Spiel 5 in einer Reihe.");
        FiveInARow fiveInARowGame = new FiveInARow();
        playFiveInARow(hero, villain, fiveInARowGame);
    }
}

private void playFiveInARow(Superhero hero, Villain villain, FiveInARow game) {
    Scanner sc = new Scanner(System.in);
    boolean gameOver = false;

    while (!gameOver) {
        // Drucke das Spielfeld
        game.printBoard();

        // Spielerzug
        System.out.println("Dein Zug! Gib die Zeile und Spalte ein (0-4): ");
        int row = sc.nextInt();
        int col = sc.nextInt();

        if (!game.makeMove(row, col)) {
            System.out.println("Ungültiger Zug. Versuche es noch einmal.");
            continue;
        }

        // Überprüfe, ob der Spieler gewonnen hat
        if (game.checkWin()) {
            System.out.println("Du hast gewonnen!");
            gameOver = true;
            break;
        }

        // Wechseln zum Big Boss
        game.switchPlayer();

        // Big Boss Zug (Zufallszug)
        boolean validMove = false;
        while (!validMove) {
            int bossRow = (int) (Math.random() * 5);
            int bossCol = (int) (Math.random() * 5);
            if (game.makeMove(bossRow, bossCol)) {
                validMove = true;
            }
        }

        // Überprüfe, ob der Big Boss gewonnen hat
        if (game.checkWin()) {
            System.out.println("Der Big Boss hat gewonnen!");
            gameOver = true;
        }

        // Wechseln zum Spieler
        game.switchPlayer();
    }
}
private void playPuzzle(Superhero hero, Villain villain) {
    // Implementierung des Rätsels
}

}

