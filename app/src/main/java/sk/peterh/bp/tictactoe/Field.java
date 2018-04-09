package sk.peterh.bp.tictactoe;

import android.graphics.Color;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */
public class Field {
    private String player = "";
    private surrField up = new surrField();
    private surrField down = new surrField();
    private surrField left = new surrField();
    private surrField right = new surrField();
    private surrField up_left = new surrField();
    private surrField up_right = new surrField();
    private surrField down_left = new surrField();
    private surrField down_right = new surrField();
    private long scoreX, scoreO;


    public Field() {                                                        //constructor
        up.count = down.count = left.count = right.count = up_left.count = up_right.count = down_left.count = down_right.count = 0;
        up.player = down.player = left.player = right.player = up_left.player = up_right.player = down_left.player = down_right.player = "";
        scoreX = scoreO = 0;
    }

    public Field(Field aField) {                                            //copy constructor
        this.player = aField.player;
        this.scoreX = aField.scoreX;
        this.scoreO = aField.scoreO;
        this.left = aField.left;
        this.right = aField.right;
        this.up = aField.up;
        this.down = aField.down;
        this.up_left = aField.up_left;
        this.up_right = aField.up_right;
        this.down_left = aField.down_left;
        this.down_right = aField.down_right;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player.isEmpty();
    }

    public int getColor() {
        if (player == "X") {
            return Color.RED;
        }
        return Color.WHITE;
    }

    public int getSurroundUpCount() {
        return this.up.count;
    }

    public int getSurroundDownCount() {
        return this.down.count;
    }

    public int getSurroundLeftCount() {
        return this.left.count;
    }

    public int getSurroundRightCount() {
        return this.right.count;
    }

    public int getSurroundUpLeftCount() {
        return this.up_left.count;
    }

    public int getSurroundUpRightCount() {
        return this.up_right.count;
    }

    public int getSurroundDownLeftCount() {
        return this.down_left.count;
    }

    public int getSurroundDownRightCount() {
        return this.down_right.count;
    }

    public String getSurroundUpPlayer() {
        return this.up.player;
    }

    public String getSurroundDownPlayer() {
        return this.down.player;
    }

    public String getSurroundLeftPlayer() {
        return this.left.player;
    }

    public String getSurroundRightPlayer() {
        return this.right.player;
    }

    public String getSurroundUpLeftPlayer() {
        return this.up_left.player;
    }

    public String getSurroundUpRightPlayer() {
        return this.up_right.player;
    }

    public String getSurroundDownLeftPlayer() {
        return this.down_left.player;
    }

    public String getSurroundDownRightPlayer() {
        return this.down_right.player;
    }

    public void setSurroundUpCount(int count) {
        this.up.count = count;
    }

    public void setSurroundDownCount(int count) {
        this.down.count = count;
    }

    public void setSurroundLeftCount(int count) {
        this.left.count = count;
    }

    public void setSurroundRightCount(int count) {
        this.right.count = count;
    }

    public void setSurroundUpLeftCount(int count) {
        this.up_left.count = count;
    }

    public void setSurroundUpRightCount(int count) {
        this.up_right.count = count;
    }

    public void setSurroundDownLeftCount(int count) {
        this.down_left.count = count;
    }

    public void setSurroundDownRightCount(int count) {
        this.down_right.count = count;
    }

    public void setSurroundUpPlayer(String surrPlayer) {
        this.up.player = surrPlayer;
    }

    public void setSurroundDownPlayer(String surrPlayer) {
        this.down.player = surrPlayer;
    }

    public void setSurroundLeftPlayer(String surrPlayer) {
        this.left.player = surrPlayer;
    }

    public void setSurroundRightPlayer(String surrPlayer) {
        this.right.player = surrPlayer;
    }

    public void setSurroundUpLeftPlayer(String surrPlayer) {
        this.up_left.player = surrPlayer;
    }

    public void setSurroundUpRightPlayer(String surrPlayer) {
        this.up_right.player = surrPlayer;
    }

    public void setSurroundDownLeftPlayer(String surrPlayer) {
        this.down_left.player = surrPlayer;
    }

    public void setSurroundDownRightPlayer(String surrPlayer) {
        this.down_right.player = surrPlayer;
    }


    public void setScoreX(long score) {
        this.scoreX = score;
    }

    public void setScoreO(long score) {
        this.scoreO = score;
    }

    public long getScoreX() {
        return this.scoreX;
    }

    public long getScoreO() {
        return this.scoreO;
    }

    public void resetSurroundCount() {
        this.up.count = this.down.count = this.left.count = this.right.count = this.up_left.count = this.up_right.count = this.down_left.count = this.down_right.count = 0;
    }

    class surrField {
        public int count = 0;
        public String player = "";
    };
}
