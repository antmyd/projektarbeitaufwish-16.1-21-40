package model;

import java.util.Random;
import java.util.Scanner;

/**
 * Ein einfaches Würfelspiel, bei dem die Spielerin und der Gegner
 * abwechselnd würfeln und versuchen, möglichst nah an 17 zu kommen.
 */
public class RollDice implements Game {
    private int playerSum; // Summe der Würfe der Spielerin
    private int opponentSum; // Summe der Würfe des Gegners
    private int playerLives; // Leben der Spielerin
    private int opponentLives; // Leben des Gegners
    private int currentRound; // Aktuelle Runde
    private boolean finished; // Flag, ob das Spiel beendet ist

    private final int targetNumber = 17; // Zielwert (17)
    private final int maxLives = 3; // Maximale Leben

    private Scanner sc = new Scanner(System.in); // Scanner für Eingaben
    private Random rand = new Random(); // Zufallsgenerator für Würfeln

    // Konstruktor
    public RollDice() {
        this.playerSum = 0;
        this.opponentSum = 0;
        this.playerLives = maxLives;
        this.opponentLives = maxLives;
        this.currentRound = 0;
        this.finished = false;
    }

    @Override
    public void playNextRound() {
        if (finished) {
            return; // Spiel ist schon beendet
        }

        currentRound++; // Runde erhöhen
        System.out.println("Runde " + currentRound);

        // Spielerin würfelt
        System.out.println("Spielerin, du würfelst...");
        int playerRoll = rand.nextInt(6) + 1; // Zufallszahl zwischen 1 und 6
        playerSum += playerRoll;
        System.out.println("Du hast eine " + playerRoll + " geworfen. Deine Summe: " + playerSum);

        // Spielerin entscheidet, ob sie noch einmal würfeln möchte
        if (playerSum < targetNumber) {
            System.out.print("Möchtest du noch einmal würfeln? (y/n): ");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                return; // Wenn sie weiterhin würfeln möchte, wird die Runde beendet
            }
        }

        // Gegner würfelt
        System.out.println("Jetzt ist der Gegner dran...");
        while (opponentSum <= 13) {
            int opponentRoll = rand.nextInt(6) + 1; // Gegner würfelt
            opponentSum += opponentRoll;
            System.out.println("Gegner hat eine " + opponentRoll + " geworfen. Gegner Summe: " + opponentSum);
        }

        // Vergleichen der Summen, falls der Gegner <17 bleibt
        if (opponentSum > 13 && opponentSum < targetNumber) {
            if (Math.abs(targetNumber - playerSum) < Math.abs(targetNumber - opponentSum)) {
                System.out.println("Die Spielerin hat gewonnen!");
            } else if (Math.abs(targetNumber - playerSum) > Math.abs(targetNumber - opponentSum)) {
                System.out.println("Der Gegner hat gewonnen!");
                opponentLives--; // Gegner verliert ein Leben
            } else {
                System.out.println("Unentschieden!");
            }
        }

        // Überprüfen, ob jemand die 17 überschreitet
        if (playerSum > targetNumber) {
            System.out.println("Die Spielerin hat mehr als 17 geworfen und verliert ein Leben!");
            playerLives--; // Spielerin verliert ein Leben
        }
        if (opponentSum > targetNumber) {
            System.out.println("Der Gegner hat mehr als 17 geworfen und verliert ein Leben!");
            opponentLives--; // Gegner verliert ein Leben
        }

        // Überprüfen, ob das Spiel vorbei ist
        if (playerLives <= 0) {
            finished = true;
            System.out.println("Die Spielerin hat keine Leben mehr. Das Spiel ist verloren.");
        } else if (opponentLives <= 0) {
            finished = true;
            System.out.println("Der Gegner hat keine Leben mehr. Die Spielerin hat gewonnen!");
        }
    }

    @Override
    public int getCurrentRound() {
        return currentRound;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public boolean isWon() {
        return playerLives > 0 && opponentLives <= 0;
    }

    @Override
    public boolean isLost() {
        return playerLives <= 0;
    }

    @Override
    public boolean isTie() {
        return false; // Unentschieden ist nicht Teil des Spiels
    }
}
