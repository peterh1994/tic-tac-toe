package sk.peterh.bp.tictactoe;

import android.graphics.Color;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */
public class Field {
    private String player = "";

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public boolean isEmpty(){
        return player.isEmpty();
    }

    public int getColor(){
        if (player == "X") {
            return Color.RED;
        }
        return Color.WHITE;
    }
}
