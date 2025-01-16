package model;

import java.util.Random;
import java.util.Scanner;

public class NrGuessing implements Game {
    private int targetNumber;  // Die Zahl, die erraten werden muss
    private int currentRound;  // Die aktuelle Runde
    private int attemptsLeft;  // Wie viele Versuche die Spielerin noch hat
    private boolean finished;  // Ist das Spiel beendet?
    private boolean won;  // Hat die Spielerin gewonnen?
    private int lives;  // Leben der Spielerin

    // Konstruktor
    public NrGuessing() {
        currentRound = 0;
        attemptsLeft = 3; // 3 Versuche
        finished = false;
        won = false;
        lives = 1;  // Die Spielerin hat zu Beginn ein Leben
        generateNewNumber();  // Eine neue Zufallszahl generieren
    }

    @Override
    public void playNextRound() {
        if (finished) {
            return; // Wenn das Spiel beendet ist, nichts tun
        }

        Scanner scanner = new Scanner(System.in);
        currentRound++; // Erhöhe die Runde
        System.out.println("Runde " + currentRound + ": Du hast noch " + attemptsLeft + " Versuche.");

        System.out.print("Rate eine Zahl zwischen 1 und 10: ");
        int guess = scanner.nextInt(); // Der Versuch, die Zahl zu erraten

        if (guess == targetNumber) {
            // Wenn die Zahl korrekt ist, beende das Spiel und setze den Gewinn-Status
            System.out.println("Herzlichen Glückwunsch! Du hast die Zahl richtig geraten!");
            won = true;
            finished = true;
        } else {
            attemptsLeft--; // Ziehe einen Versuch ab

            if (attemptsLeft > 0) {
                if (guess < targetNumber) {
                    System.out.println("Die Zahl ist größer.");
                } else {
                    System.out.println("Die Zahl ist kleiner.");
                }
            } else {
                // Wenn keine Versuche mehr übrig sind, hat die Spielerin verloren
                System.out.println("Du hast verloren! Die richtige Zahl war " + targetNumber + ".");
                finished = true;
                lives--;  // Spielerin verliert ein Leben
                if (lives == 0) {
                    System.out.println("Du hast alle Leben verloren!");
                } else {
                    System.out.println("Du hast noch " + lives + " Leben.");
                    generateNewNumber();  // Neue Zahl für das nächste Spiel
                    attemptsLeft = 3;  // Setze die Versuche zurück
                    finished = false;  // Spiel neu starten
                }
            }
        }
    }

    @Override
    public int getCurrentRound() {
        return currentRound;  // Gibt die aktuelle Runde zurück
    }

    @Override
    public boolean isFinished() {
        return finished;  // Gibt zurück, ob das Spiel zu Ende ist
    }

    @Override
    public boolean isWon() {
        return won;  // Gibt zurück, ob die Spielerin gewonnen hat
    }

    @Override
    public boolean isLost() {
        return !won && finished && lives > 0;  // Gibt zurück, ob die Spielerin verloren hat
    }

    @Override
    public boolean isTie() {
        return false;  // Bei diesem Spiel gibt es kein Unentschieden
    }

    // Generiert eine neue Zufallszahl zwischen 1 und 10
    private void generateNewNumber() {
        Random rand = new Random();
        targetNumber = rand.nextInt(10) + 1;  // Zufallszahl zwischen 1 und 10
    }
}
