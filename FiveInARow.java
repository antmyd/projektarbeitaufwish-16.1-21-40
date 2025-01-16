package model;

public class FiveInARow implements Game {
    private char[][] board;
    private char currentPlayer;
    private int round;
    private boolean gameFinished;
    private boolean gameWon;
    private boolean gameTie;

    public FiveInARow() {
        board = new char[5][5];  // 5x5 Spielfeld
        currentPlayer = 'X';  // Startspieler
        round = 1;
        gameFinished = false;
        gameWon = false;
        gameTie = false;

        // Initialisiere das Spielfeld mit Leerzeichen
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Methode zum Ausdrucken des Spielfeldes
    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Methode, um einen Zug zu machen
    public boolean makeMove(int row, int col) {
        if (row >= 0 && row < 5 && col >= 0 && col < 5 && board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;  // Ungültiger Zug
    }

    // Methode, um zu überprüfen, ob ein Spieler gewonnen hat
    public boolean checkWin() {
        // Überprüfe Reihen, Spalten und Diagonalen
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] != ' ' && (
                    checkRow(i, j) || checkColumn(i, j) || checkDiagonal(i, j))) {
                    return true;
                }
            }
        }
        return false;
    }

    // Überprüfe eine Reihe
    private boolean checkRow(int row, int col) {
        if (col + 4 < 5) {
            for (int i = 0; i < 5; i++) {
                if (board[row][col + i] != currentPlayer) return false;
            }
            return true;
        }
        return false;
    }

    // Überprüfe eine Spalte
    private boolean checkColumn(int row, int col) {
        if (row + 4 < 5) {
            for (int i = 0; i < 5; i++) {
                if (board[row + i][col] != currentPlayer) return false;
            }
            return true;
        }
        return false;
    }

    // Überprüfe eine Diagonale
    private boolean checkDiagonal(int row, int col) {
        if (row + 4 < 5 && col + 4 < 5) {
            for (int i = 0; i < 5; i++) {
                if (board[row + i][col + i] != currentPlayer) return false;
            }
            return true;
        }
        return false;
    }

    // Überprüfe, ob das Spiel unentschieden endet
    public boolean checkTie() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Spieler wechseln
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Getter für den aktuellen Spieler
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    // Implementierungen der Methoden aus der Game-Schnittstelle

    @Override
    public void playNextRound() {
        // Einfaches Beispiel für das Fortschreiten der Runde
        round++;
    }

    @Override
    public int getCurrentRound() {
        return round;
    }

    @Override
    public boolean isFinished() {
        return gameFinished;
    }

    @Override
    public boolean isWon() {
        return gameWon;
    }

    @Override
    public boolean isLost() {
        // In einem klassischen 5-in-einer-Reihe-Spiel gibt es kein explizites "Verlieren" ohne gewonnenes Spiel
        return !gameWon && !gameTie && isTie();
    }

    @Override
    public boolean isTie() {
        return gameTie;
    }

    // Weitere Getter und Setter für den Status des Spiels

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public void setGameTie(boolean gameTie) {
        this.gameTie = gameTie;
    }
}
