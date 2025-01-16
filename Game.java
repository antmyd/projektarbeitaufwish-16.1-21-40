package model;

/**
 * Represents a generic game with functionality to play rounds and check the game's state.
 */
public interface Game {

    /**
     * Advances the game to the next round. This method handles the game logic and leads to
     * playing the next round in the console game.
     */
    void playNextRound();

    /**
     * Retrieves the current round number of the game.
     *
     * @return the current round number.
     */
    int getCurrentRound();

    /**
     * Checks if the game is finished.
     *
     * @return {@code true} if the game is finished, {@code false} otherwise.
     */
    boolean isFinished();

    /**
     * Checks if the game is won.
     *
     * @return {@code true} if the game is won, {@code false} otherwise.
     */
    boolean isWon();

    /**
     * Checks if the game is lost.
     *
     * @return {@code true} if the game is lost, {@code false} otherwise.
     */
    boolean isLost();

    /**
     * Checks if the game ended in a tie (only relevant for some games).
     *
     * @return {@code true} if the game is tied, {@code false} otherwise.
     */
    boolean isTie();
}

