package sk.peterh.bp.tictactoe;

import java.util.List;

import static ConstatntPackage.Constant.BORDER_X;
import static ConstatntPackage.Constant.BORDER_Y;
import static ConstatntPackage.Constant.WINNIG_COUNT;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */

public class GameResult {


    public static String checkWinner(List<List<Field>> fields, String player, int X, int Y, int playerCounter, FieldAdapter.bestScore bestScoreX, FieldAdapter.bestScore bestScoreO) {

        int count = 0;

        if (fields.get(X).get(Y).getSurroundUpPlayer().equals(player))
            count += fields.get(X).get(Y).getSurroundUpCount();
        if (fields.get(X).get(Y).getSurroundDownPlayer().equals(player))
            count += fields.get(X).get(Y).getSurroundDownCount();
        if (((count + 1) >= WINNIG_COUNT) && player.equals("X"))
            return "Vyhral si!";
        else if (((count + 1) >= WINNIG_COUNT) && player.equals("O"))
            return "Prehral si!";
        else count = 0;

        if (fields.get(X).get(Y).getSurroundLeftPlayer().equals(player))
            count += fields.get(X).get(Y).getSurroundLeftCount();
        if (fields.get(X).get(Y).getSurroundRightPlayer().equals(player))
            count += fields.get(X).get(Y).getSurroundRightCount();
        if (((count + 1) >= WINNIG_COUNT) && player.equals("X"))
            return "Vyhral si!";
        else if (((count + 1) >= WINNIG_COUNT) && player.equals("O"))
            return "Prehral si!";
        else count = 0;

        if (fields.get(X).get(Y).getSurroundUpLeftPlayer().equals(player))
            count += fields.get(X).get(Y).getSurroundUpLeftCount();
        if (fields.get(X).get(Y).getSurroundDownRightPlayer().equals(player))
            count += fields.get(X).get(Y).getSurroundDownRightCount();
        if (((count + 1) >= WINNIG_COUNT) && player.equals("X"))
            return "Vyhral si!";
        else if (((count + 1) >= WINNIG_COUNT) && player.equals("O"))
            return "Prehral si!";
        else count = 0;

        if (fields.get(X).get(Y).getSurroundDownLeftPlayer().equals(player))
            count += fields.get(X).get(Y).getSurroundDownLeftCount();
        if (fields.get(X).get(Y).getSurroundUpRightPlayer().equals(player))
            count += fields.get(X).get(Y).getSurroundUpRightCount();
        if (((count + 1) >= WINNIG_COUNT) && player.equals("X"))
            return "Vyhral si!";
        else if (((count + 1) >= WINNIG_COUNT) && player.equals("O"))
            return "Prehral si!";
        else count = 0;

        if (playerCounter == (BORDER_X*BORDER_Y))
            return "Remíza";

        FieldScore.getFieldScore(fields,player,X,Y, bestScoreX, bestScoreO);

        return "";

    }
}
