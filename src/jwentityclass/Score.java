/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jwentityclass;

/**
 *
 * @author Asus
 */
public class Score implements Comparable<Score>{
    private int accumulatedMarks;
    private Player currentPlayer;
    //private int level;

    public Score(int accumulatedMarks, Player currentPlayer) {
        this.accumulatedMarks = accumulatedMarks;
        this.currentPlayer = currentPlayer;
    }
    
    public int getAccumulatedMarks() {
        return accumulatedMarks;
    }

    public void setAccumulatedMarks(int accumulatedMarks) {
        this.accumulatedMarks = accumulatedMarks;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public String toString() {
        return currentPlayer.toString() + "," + accumulatedMarks;
    }

    @Override
    public int compareTo(Score temp) {
        if(accumulatedMarks == temp.getAccumulatedMarks())
            return 0;
        else if(accumulatedMarks > temp.getAccumulatedMarks())
            return 1;
        else
            return -1;
    }

}
