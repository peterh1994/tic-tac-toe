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


    public static String checkWinner(List<List<Field>> fields, String player, int X, int Y) {

        int i = 1;
        String res = "";

//-------------------------From right to left------------------------------------//
        i = 0;
        while ((Y-i) >= 0) {                                                                                    //search from left to right
            if (Y == 0) {                                                                                       //if point is at left border
                fields.get(X).get(Y + 1).setSurroundLeftCount(1);                                               //
                fields.get(X).get(Y + 1).setSurroundLeftPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else if (!(fields.get(X).get(Y - i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X).get(Y - i).getSurroundRightCount() < i) {                                     //count cannot be decreased
                    fields.get(X).get(Y - i).setSurroundRightCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X).get(Y - i).setSurroundRightPlayer(player);
                }
                res = getDebgStr(fields);
                if((Y + 1) < BORDER_Y) {                                                                        //check right border
                    fields.get(X).get(Y + 1).setSurroundLeftCount(i);
                    fields.get(X).get(Y + 1).setSurroundLeftPlayer(player);
                    res = getDebgStr(fields);
                }
                break;
            }
            else if ((Y - i) == 0) {                                                                            //check left border
                fields.get(X).get(Y + 1).setSurroundLeftCount(i+1);
                fields.get(X).get(Y + 1).setSurroundLeftPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else i++;
            res = getDebgStr(fields);
        }



//-------------------------From left to right------------------------------------//
        i = 0;
        while ((Y + i) < BORDER_Y) {
            if (Y == (BORDER_Y-1)) {
                fields.get(X).get(Y - 1).setSurroundRightCount(1);
                fields.get(X).get(Y - 1).setSurroundRightPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else if (!(fields.get(X).get(Y + i).getPlayer().equals(player))) {
                if (fields.get(X).get(Y + i).getSurroundLeftCount() < i) {
                    fields.get(X).get(Y + i).setSurroundLeftCount(i);
                    fields.get(X).get(Y + i).setSurroundLeftPlayer(player);
                    res = getDebgStr(fields);
                }
                if((Y - 1) >= 0) {
                    fields.get(X).get(Y - 1).setSurroundRightCount(i);
                    fields.get(X).get(Y - 1).setSurroundRightPlayer(player);
                    res = getDebgStr(fields);
                }
                break;
            }
            else if ((Y + i) == (BORDER_Y-1)) {
                fields.get(X).get(Y - 1).setSurroundRightCount(i+1);
                fields.get(X).get(Y - 1).setSurroundRightPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else i++;
            res = getDebgStr(fields);
        }


//-------------------------From Down to up------------------------------------//
        i = 0;
        while ((X - i) >= 0) {                                                                                    //search from left to right
            if (X == 0) {                                                                                       //if point is at left border
                fields.get(X).get(Y + 1).setSurroundUpCount(1);                                               //
                fields.get(X).get(Y + 1).setSurroundUpPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else if (!(fields.get(X - i).get(Y).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X - i).get(Y).getSurroundDownCount() < i) {                                     //count cannot be decreased
                    fields.get(X - i).get(Y).setSurroundDownCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X - i).get(Y).setSurroundDownPlayer(player);
                }
                res = getDebgStr(fields);
                if((X + 1) < BORDER_X) {                                                                        //check right border
                    fields.get(X + 1).get(Y).setSurroundUpCount(i);
                    fields.get(X + 1).get(Y).setSurroundUpPlayer(player);
                    res = getDebgStr(fields);
                }
                break;
            }
            else if ((X - i) == 0) {                                                                            //check left border
                fields.get(X + 1).get(Y).setSurroundUpCount(i+1);
                fields.get(X + 1).get(Y).setSurroundUpPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else i++;
            res = getDebgStr(fields);
        }

        //-------------------------From up to down------------------------------------//
        i = 0;
        while ((X + i) < BORDER_X) {
            if (X == (BORDER_X-1)) {
                fields.get(X - 1).get(Y).setSurroundDownCount(1);
                fields.get(X - 1).get(Y).setSurroundDownPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else if (!(fields.get(X + i).get(Y).getPlayer().equals(player))) {
                if (fields.get(X + 1).get(Y).getSurroundUpCount() < i) {
                    fields.get(X + 1).get(Y).setSurroundUpCount(i);
                    fields.get(X + 1).get(Y).setSurroundUpPlayer(player);
                    res = getDebgStr(fields);
                }
                if((X - 1) >= 0) {
                    fields.get(X - 1).get(Y).setSurroundDownCount(i);
                    fields.get(X - 1).get(Y).setSurroundDownPlayer(player);
                    res = getDebgStr(fields);
                }
                break;
            }
            else if ((X + i) == (BORDER_X-1)) {
                fields.get(X - 1).get(Y).setSurroundDownCount(i+1);
                fields.get(X - 1).get(Y).setSurroundDownPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else i++;
            res = getDebgStr(fields);
        }


//-------------------------From Down right to up left------------------------------------//
        i = 0;
        while (((X-i) >= 0) && ((Y-i) >= 0)) {                                                                                    //search from left to right
            if (X == 0 && Y == 0) {                                                                                       //if point is at left border
                fields.get(X + 1).get(Y + 1).setSurroundUpLeftCount(1);                                               //
                fields.get(X + 1).get(Y + 1).setSurroundUpLeftPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else if (!(fields.get(X - i).get(Y - i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X - i).get(Y - i).getSurroundDownRightCount() < i) {                                     //count cannot be decreased
                    fields.get(X - i).get(Y - i).setSurroundDownRightCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X - i).get(Y - i).setSurroundDownRightPlayer(player);
                }
                res = getDebgStr(fields);
                if (((Y + 1) < BORDER_Y) && ((X + 1) < BORDER_X)) {                                                                        //check right border
                    fields.get(X + 1).get(Y + 1).setSurroundUpLeftCount(i);
                    fields.get(X + 1).get(Y + 1).setSurroundUpLeftPlayer(player);
                    res = getDebgStr(fields);
                }
                break;
            }
            else if (((X - i) == 0) && ((Y - i) == 0)) {                                                                            //check left border
                fields.get(X + 1).get(Y + 1).setSurroundUpLeftCount(i+1);
                fields.get(X + 1).get(Y + 1).setSurroundUpLeftPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else i++;
            res = getDebgStr(fields);
        }


        //-------------------------From up left to down right------------------------------------//
        i = 0;
        while (((X + i) < BORDER_X) && ((Y + i) < BORDER_Y)) {                                                                                    //search from left to right
            if (X == (BORDER_X-1) && Y == (BORDER_Y-1)) {                                                                                       //if point is at left border
                fields.get(X - 1).get(Y - 1).setSurroundDownRightCount(1);                                               //
                fields.get(X - 1).get(Y - 1).setSurroundDownRightPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else if (!(fields.get(X + i).get(Y + i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X + i).get(Y + i).getSurroundUpLeftCount() < i) {                                     //count cannot be decreased
                    fields.get(X + i).get(Y + i).setSurroundUpLeftCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X + i).get(Y + i).setSurroundUpLeftPlayer(player);
                }
                res = getDebgStr(fields);
                if (((Y - 1) >= 0) && ((X - 1) >= 0)) {                                                                        //check right border
                    fields.get(X - 1).get(Y - 1).setSurroundDownRightCount(i);
                    fields.get(X - 1).get(Y - 1).setSurroundDownRightPlayer(player);
                    res = getDebgStr(fields);
                }
                break;
            }
            else if (((X + i) == (BORDER_X-1) && ((Y + i) == (BORDER_Y-1)))) {                                                                            //check left border
                fields.get(X - 1).get(Y - 1).setSurroundDownRightCount(i+1);
                fields.get(X - 1).get(Y - 1).setSurroundDownRightPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else i++;
            res = getDebgStr(fields);
        }

        //-------------------------From up left to down right------------------------------------//
        i = 0;
        while (((X - i) >= 0) && ((Y + i) < BORDER_Y)) {                                                                                    //search from left to right
            if ((X == 0) && Y == (BORDER_Y-1)) {                                                                                       //if point is at left border
                fields.get(X - 1).get(Y + 1).setSurroundUpRightCount(1);                                               //
                fields.get(X - 1).get(Y + 1).setSurroundUpRightPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else if (!(fields.get(X - i).get(Y + i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X - i).get(Y + i).getSurroundDownLeftCount() < i) {                                     //count cannot be decreased
                    fields.get(X - i).get(Y + i).setSurroundDownLeftCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X - i).get(Y + i).setSurroundDownLeftPlayer(player);
                }
                res = getDebgStr(fields);
                if (((Y - 1) >= 0) && ((X + 1) < BORDER_X)) {                                                                        //check right border
                    fields.get(X + 1).get(Y - 1).setSurroundUpRightCount(i);
                    fields.get(X + 1).get(Y - 1).setSurroundUpRightPlayer(player);
                    res = getDebgStr(fields);
                }
                break;
            }
            else if ((X + i) == (BORDER_X-1) && ((Y - i) == 0)) {                                                                            //check left border
                fields.get(X - 1).get(Y + 1).setSurroundUpRightCount(i+1);
                fields.get(X - 1).get(Y + 1).setSurroundUpRightPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else i++;
            res = getDebgStr(fields);
        }

        //-------------------------From up left to down right------------------------------------//
        i = 0;
        while (((X + i) < BORDER_X) && ((Y - i) >= 0)) {                                                                                    //search from left to right
            if ((X == BORDER_X-1) && Y == (0)) {                                                                                       //if point is at left border
                fields.get(X + 1).get(Y - 1).setSurroundDownLeftCount(1);                                               //
                fields.get(X + 1).get(Y - 1).setSurroundDownLeftPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else if (!(fields.get(X + i).get(Y - i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X + i).get(Y - i).getSurroundUpRightCount() < i) {                                     //count cannot be decreased
                    fields.get(X + i).get(Y - i).setSurroundUpRightCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X + i).get(Y - i).setSurroundUpRightPlayer(player);
                }
                res = getDebgStr(fields);
                if (((Y + 1) < BORDER_Y) && ((X - 1) >= 0)) {                                                                        //check right border
                    fields.get(X - 1).get(Y + 1).setSurroundDownLeftCount(i);
                    fields.get(X - 1).get(Y + 1).setSurroundDownLeftPlayer(player);
                    res = getDebgStr(fields);
                }
                break;
            }
            else if (((X - i) == 0) && ((Y + i) == (BORDER_Y-1))) {                                                                            //check left border
                fields.get(X + 1).get(Y - 1).setSurroundDownLeftCount(i+1);
                fields.get(X + 1).get(Y - 1).setSurroundDownLeftPlayer(player);
                res = getDebgStr(fields);
                break;
            }
            else i++;
            res = getDebgStr(fields);
        }

/*        i = 1;
        while(Y + i < BORDER_Y && X - i >= 0) {
            fields.get(X - i).get(Y + i).setSurroundDownLeftCount(i);
            fields.get(X - i).get(Y + i).setSurroundDownLeftPlayer(player);
            if (! (fields.get(X - i).get(Y + i).getPlayer().equals(player))) {
                fields.get(X).get(Y).setSurroundUpRightCount(i-1);

                break;
            }
            i++;

        }
        i = 1;
        while(Y - i >= 0 && X + i < BORDER_X) {
            fields.get(X + i).get(Y - i).setSurroundUpRightCount(i);
            fields.get(X + i).get(Y - i).setSurroundUpRightPlayer(player);
            if (! (fields.get(X + i).get(Y - i).getPlayer().equals(player))) {
                fields.get(X).get(Y).setSurroundDownLeftCount(i-1);

                break;
            }
            i++;

        }
        i = 1;
        while(Y + i < BORDER_Y && X + i < BORDER_X) {
            fields.get(X + i).get(Y + i).setSurroundUpLeftCount(i);
            fields.get(X + i).get(Y + i).setSurroundUpLeftPlayer(player);
            if (! (fields.get(X + i).get(Y + i).getPlayer().equals(player))) {
                fields.get(X).get(Y).setSurroundDownRightCount(i-1);

                break;
            }
            i++;

        }
        i = 1;*/




        int count = 0;

        if (fields.get(X).get(Y).getSurroundUpPlayer().equals(player))      count += fields.get(X).get(Y).getSurroundUpCount();
        if (fields.get(X).get(Y).getSurroundDownPlayer().equals(player))    count += fields.get(X).get(Y).getSurroundDownCount();
        if (((count + 1) >= WINNIG_COUNT) && player.equals("X"))
            return "Vyhral si!";
        else if (((count + 1) >= WINNIG_COUNT) && player.equals("O"))
            return "Prehral si!";
        else count = 0;

        if (fields.get(X).get(Y).getSurroundLeftPlayer().equals(player))      count += fields.get(X).get(Y).getSurroundLeftCount();
        if (fields.get(X).get(Y).getSurroundRightPlayer().equals(player))     count += fields.get(X).get(Y).getSurroundRightCount();
        if      (((count + 1) >= WINNIG_COUNT) && player.equals("X"))
            return "Vyhral si!";
        else if (((count + 1) >= WINNIG_COUNT) && player.equals("O"))
            return "Prehral si!";
        else count = 0;

        if (fields.get(X).get(Y).getSurroundUpLeftPlayer().equals(player))      count += fields.get(X).get(Y).getSurroundUpLeftCount();
        if (fields.get(X).get(Y).getSurroundDownRightPlayer().equals(player))     count += fields.get(X).get(Y).getSurroundDownRightCount();
        if      (((count + 1) >= WINNIG_COUNT) && player.equals("X"))
            return "Vyhral si!";
        else if (((count + 1) >= WINNIG_COUNT) && player.equals("O"))
            return "Prehral si!";
        else count = 0;

        if (fields.get(X).get(Y).getSurroundDownLeftPlayer().equals(player))      count += fields.get(X).get(Y).getSurroundDownLeftCount();
        if (fields.get(X).get(Y).getSurroundUpRightPlayer().equals(player))     count += fields.get(X).get(Y).getSurroundUpRightCount();
        if      (((count + 1) >= WINNIG_COUNT) && player.equals("X"))
            return "Vyhral si!";
        else if (((count + 1) >= WINNIG_COUNT) && player.equals("O"))
            return "Prehral si!";
        else count = 0;

        return "";

//        int count = 0;
//        String result = "";
//        // check lines
//        for (int i = 0; i < BORDER_X; i++) {
//            for (int j = 0; j < BORDER_Y; j++) {
//                if (fields.get(i).get(j).getPlayer().equals(player)) count++;
//                else count = 0;
//                if (count == WINNIG_COUNT && player.equals("X")) return "Vyhral si!";
//                if (count == WINNIG_COUNT && player.equals("O")) return "Prehral si!";
//            }
//            count = 0;
//        }
//
//        //check columns
//        for (int i = 0; i < BORDER_X; i++) {
//            for (int j = 0; j < BORDER_Y; j++) {
//                if (fields.get(j).get(i).getPlayer().equals(player)) count++;
//                else count = 0;
//                if (count == WINNIG_COUNT && player.equals("X")) return "Vyhral si!";
//                if (count == WINNIG_COUNT && player.equals("O")) return "Prehral si!";
//            }
//            count = 0;
//        }
//
//        //check diagonals from left down to right up
//        for (int k = 0; k < BORDER_X; k++) {
//            if (k < WINNIG_COUNT - 1) {
//                for (int i = WINNIG_COUNT - 1 + k, j = 0; (j <= WINNIG_COUNT - 1 + k) && (i >= 0); i--, j++) {
//                    if (fields.get(i).get(j).getPlayer().equals(player)) count++;
//                    else count = 0;
//                    if (count == WINNIG_COUNT && player.equals("X")) return "Vyhral si!";
//                    if (count == WINNIG_COUNT && player.equals("O")) return "Prehral si!";
//                }
//            } else {
//                for (int i = BORDER_Y - 1, j = k - (BORDER_Y - WINNIG_COUNT); (i > k - (WINNIG_COUNT)) && (j < (BORDER_Y)); i--, j++) {
//                    if (fields.get(i).get(j).getPlayer().equals(player)) count++;
//                    else count = 0;
//                    if (count == WINNIG_COUNT && player.equals("X")) return "Vyhral si!";
//                    if (count == WINNIG_COUNT && player.equals("O")) return "Prehral si!";
//                }
//                count = 0;
//            }
//
//        }
//
//            //check diagonals from right top to left down
//            for (int k = 0; k < BORDER_X; k++) {
//                if (k < WINNIG_COUNT - 1) {
//                    for (int i = (BORDER_Y / 2) - k - 1, j = 0; (j < (BORDER_Y / 2) + k + 1) && (i < BORDER_Y); i++, j++) {
//                        if (fields.get(i).get(j).getPlayer().equals(player)) count++;
//                        else count = 0;
//                        if (count == WINNIG_COUNT && player.equals("X")) return "Vyhral si!";
//                        if (count == WINNIG_COUNT && player.equals("O")) return "Prehral si!";
//                    }
//                } else {
//                    for (int i = 0, j = (k-3); (i < BORDER_Y-(k-3)) && (j < BORDER_Y ); i++, j++) {
//                        if (fields.get(i).get(j).getPlayer().equals(player)) count++;
//                        else count = 0;
//                        if (count == WINNIG_COUNT && player.equals("X")) return "Vyhral si!";
//                        if (count == WINNIG_COUNT && player.equals("O")) return "Prehral si!";
//                    }
//                    count = 0;
//                }
//            }
//        return result;

    }

    public static String getDebgStr(List<List<Field>> fields) {
        int l0 = fields.get(3).get(0).getSurroundLeftCount();
        int l1 = fields.get(3).get(1).getSurroundLeftCount();
        int l2 = fields.get(3).get(2).getSurroundLeftCount();
        int l3 = fields.get(3).get(3).getSurroundLeftCount();
        int l4 = fields.get(3).get(4).getSurroundLeftCount();
        int l5 = fields.get(3).get(5).getSurroundLeftCount();
        int l6 = fields.get(3).get(6).getSurroundLeftCount();
        int l7 = fields.get(3).get(7).getSurroundLeftCount();

        String p0 = fields.get(3).get(0).getPlayer();
        String p1 = fields.get(3).get(1).getPlayer();
        String p2 = fields.get(3).get(2).getPlayer();
        String p3 = fields.get(3).get(3).getPlayer();
        String p4 = fields.get(3).get(4).getPlayer();
        String p5 = fields.get(3).get(5).getPlayer();
        String p6 = fields.get(3).get(6).getPlayer();
        String p7 = fields.get(3).get(7).getPlayer();

        int r0 = fields.get(3).get(0).getSurroundRightCount();
        int r1 = fields.get(3).get(1).getSurroundRightCount();
        int r2 = fields.get(3).get(2).getSurroundRightCount();
        int r3 = fields.get(3).get(3).getSurroundRightCount();
        int r4 = fields.get(3).get(4).getSurroundRightCount();
        int r5 = fields.get(3).get(5).getSurroundRightCount();
        int r6 = fields.get(3).get(6).getSurroundRightCount();
        int r7 = fields.get(3).get(7).getSurroundRightCount();

        return (l0+p0+r0 +" "+ l1+p1+r1 +" "+ l2+p2+r2 +" "+ l3+p3+r3 +" "+ l4+p4+r4 +" "+ l5+p5+r5 +" "+ l6+p6+r6 +" "+ l7+p7+r7);
    }

}
