package sk.peterh.bp.tictactoe;

import android.util.Log;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */
public class GameResult {

    public static String checkWinner(List<Field> fields, String player) {
        int count = 0;
        String result = "";
        // check lines
        for(int i = 0 ; i < 64; i++) {
            if (i % 8 == 0) count = 0;
            if (fields.get(i).getPlayer().equals(player)) count++;
            else count = 0;
            if (count == 5 && player.equals("X")) return "Vyhral si!";
            if (count == 5 && player.equals("O")) return "Prehral si!";
        }

        //check columns
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8;j++) {
                if (fields.get(i + j *8).getPlayer().equals(player)) count++;
                else count = 0;
                if (count == 5 && player.equals("X")) return "Vyhral si!";
                if (count == 5 && player.equals("O")) return "Prehral si!";
            }
            count = 0;
        }

        //check diagonals from  left top to right down
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 8 - i ;j++) {
                if (fields.get(i + j *8 + j).getPlayer().equals(player)) count++;
                else count = 0;
                if (count == 5 && player.equals("X")) return "Vyhral si!";
                if (count == 5 && player.equals("O")) return "Prehral si!";
            }
            count = 0;
        }

        for(int i = 1; i < 4; i++) {
            for(int j = 0 + i; j < 8 ;j++) {
                if (fields.get(j *8 + j -i).getPlayer().equals(player)) count++;
                else count = 0;
                if (count == 5 && player.equals("X")) return "Vyhral si!";
                if (count == 5 && player.equals("O")) return "Prehral si!";
            }
            count = 0;
        }

        //check diagonals from left down to right top
        for(int i = 1; i < 4; i++) {
            for(int j = 7 ; j >= 0 + i ;j--) {
                if (fields.get(j *8 + i + 7 - j).getPlayer().equals(player)) count++;
                else count = 0;
                if (count == 5 && player.equals("X")) return "Vyhral si!";
                if (count == 5 && player.equals("O")) return "Prehral si!";
            }
            count = 0;
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 7 - i; j >= 0 ;j--) {
                if (fields.get(j *8 + 7 - j - i).getPlayer().equals(player)) count++;
                else count = 0;
                if (count == 5 && player.equals("X")) return "Vyhral si!";
                if (count == 5 && player.equals("O")) return "Prehral si!";
            }
            count = 0;
        }
        return result;
    }
}
