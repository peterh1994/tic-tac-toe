package sk.peterh.bp.tictactoe;

import android.util.Log;

import java.util.List;

import static ConstatntPackage.Constant.BORDER_X;
import static ConstatntPackage.Constant.BORDER_Y;
import static ConstatntPackage.Constant.WINNIG_COUNT;
import static android.content.ContentValues.TAG;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */

public class GameResult {

    public static String checkWinner(List<List<Field>> fields, String player) {
        int count = 0;
        String result = "";
        // check lines
        for (int i = 0; i < BORDER_X; i++) {
            for (int j = 0; j < BORDER_Y; j++) {
                if (fields.get(i).get(j).getPlayer().equals(player)) count++;
                else count = 0;
                if (count == WINNIG_COUNT && player.equals("X")) return "Vyhral si!";
                if (count == WINNIG_COUNT && player.equals("O")) return "Prehral si!";
            }
            count = 0;
        }

        //check columns
        for (int i = 0; i < BORDER_X; i++) {
            for (int j = 0; j < BORDER_Y; j++) {
                if (fields.get(j).get(i).getPlayer().equals(player)) count++;
                else count = 0;
                if (count == WINNIG_COUNT && player.equals("X")) return "Vyhral si!";
                if (count == WINNIG_COUNT && player.equals("O")) return "Prehral si!";
            }
            count = 0;
        }

        //check diagonals from left down to right up
        for (int k = 0; k < BORDER_X; k++) {
            if (k < WINNIG_COUNT - 1) {
                for (int i = WINNIG_COUNT - 1 + k, j = 0; (j <= WINNIG_COUNT - 1 + k) && (i >= 0); i--, j++) {
                    if (fields.get(i).get(j).getPlayer().equals(player)) count++;
                    else count = 0;
                    if (count == WINNIG_COUNT && player.equals("X")) return "Vyhral si!";
                    if (count == WINNIG_COUNT && player.equals("O")) return "Prehral si!";
                }
            } else {
                for (int i = BORDER_Y - 1, j = k - (BORDER_Y - WINNIG_COUNT); (i > k - (WINNIG_COUNT)) && (j < (BORDER_Y)); i--, j++) {
                    if (fields.get(i).get(j).getPlayer().equals(player)) count++;
                    else count = 0;
                    if (count == WINNIG_COUNT && player.equals("X")) return "Vyhral si!";
                    if (count == WINNIG_COUNT && player.equals("O")) return "Prehral si!";
                }
                count = 0;
            }

        }

            //check diagonals from right top to left down
            for (int k = 0; k < BORDER_X; k++) {
                if (k < WINNIG_COUNT - 1) {
                    for (int i = (BORDER_Y / 2) - k - 1, j = 0; (j < (BORDER_Y / 2) + k + 1) && (i < BORDER_Y); i++, j++) {
                        if (fields.get(i).get(j).getPlayer().equals(player)) count++;
                        else count = 0;
                        if (count == WINNIG_COUNT && player.equals("X")) return "Vyhral si!";
                        if (count == WINNIG_COUNT && player.equals("O")) return "Prehral si!";
                    }
                } else {
                    for (int i = 0, j = (k-3); (i < BORDER_Y-(k-3)) && (j < BORDER_Y ); i++, j++) {
                        if (fields.get(i).get(j).getPlayer().equals(player)) count++;
                        else count = 0;
                        if (count == WINNIG_COUNT && player.equals("X")) return "Vyhral si!";
                        if (count == WINNIG_COUNT && player.equals("O")) return "Prehral si!";
                    }
                    count = 0;
                }
            }
        return result;
    }
}
