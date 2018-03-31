package sk.peterh.bp.tictactoe;

import java.util.List;
import static ConstatntPackage.Constant.BORDER_X;
import static ConstatntPackage.Constant.BORDER_Y;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */

public class FieldScore {


    public static void getFiledScore(List<List<Field>> fields, String player, int X, int Y) {

        int i = 1;
        String res = "";

//-------------------------From right to left------------------------------------//
        i = 0;
        while ((Y - i) >= 0) {                                                                                    //search from left to right
            if (Y == 0) {                                                                                       //if point is at left border
                fields.get(X).get(Y + 1).setSurroundLeftCount(1);                                               //
                fields.get(X).get(Y + 1).setSurroundLeftPlayer(player);
                break;
            } else if (!(fields.get(X).get(Y - i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X).get(Y - i).getSurroundRightCount() < i) {                                     //count cannot be decreased
                    fields.get(X).get(Y - i).setSurroundRightCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X).get(Y - i).setSurroundRightPlayer(player);
                }
                if ((Y + 1) < BORDER_Y) {                                                                        //check right border
                    fields.get(X).get(Y + 1).setSurroundLeftCount(i);
                    fields.get(X).get(Y + 1).setSurroundLeftPlayer(player);
                }
                break;
            } else if ((Y - i) == 0) {                                                                            //check left border
                if ((Y + 1) < BORDER_Y) {
                    fields.get(X).get(Y + 1).setSurroundLeftCount(i + 1);
                    fields.get(X).get(Y + 1).setSurroundLeftPlayer(player);
                }
                else{   fields.get(X).get(Y).setSurroundLeftCount(i);
                        fields.get(X).get(Y).setSurroundLeftPlayer(player);
                    }
                break;
            } else i++;
        }


//-------------------------From left to right------------------------------------//
        i = 0;
        while ((Y + i) < BORDER_Y) {
            if (Y == (BORDER_Y - 1)) {
                fields.get(X).get(Y - 1).setSurroundRightCount(1);
                fields.get(X).get(Y - 1).setSurroundRightPlayer(player);
                break;
            } else if (!(fields.get(X).get(Y + i).getPlayer().equals(player))) {
                if (fields.get(X).get(Y + i).getSurroundLeftCount() < i) {
                    fields.get(X).get(Y + i).setSurroundLeftCount(i);
                    fields.get(X).get(Y + i).setSurroundLeftPlayer(player);
                }
                if ((Y - 1) >= 0) {
                    fields.get(X).get(Y - 1).setSurroundRightCount(i);
                    fields.get(X).get(Y - 1).setSurroundRightPlayer(player);
                }
                break;
            } else if ((Y + i) == (BORDER_Y - 1)) {
                if ((Y - 1) >= 0) {
                    fields.get(X).get(Y - 1).setSurroundRightCount(i + 1);
                    fields.get(X).get(Y - 1).setSurroundRightPlayer(player);
                }
                else {
                    fields.get(X).get(Y).setSurroundRightCount(i);
                    fields.get(X).get(Y).setSurroundRightPlayer(player);
                }
                break;
            } else i++;
        }


//-------------------------From Down to up------------------------------------//
        i = 0;
        while ((X - i) >= 0) {                                                                                    //search from left to right
            if (X == 0) {                                                                                       //if point is at left border
                fields.get(X + 1).get(Y).setSurroundUpCount(1);                                               //
                fields.get(X + 1).get(Y).setSurroundUpPlayer(player);
                break;
            } else if (!(fields.get(X - i).get(Y).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X - i).get(Y).getSurroundDownCount() < i) {                                     //count cannot be decreased
                    fields.get(X - i).get(Y).setSurroundDownCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X - i).get(Y).setSurroundDownPlayer(player);
                }
                if ((X + 1) < BORDER_X) {                                                                        //check right border
                    fields.get(X + 1).get(Y).setSurroundUpCount(i);
                    fields.get(X + 1).get(Y).setSurroundUpPlayer(player);
                }
                break;
            } else if ((X - i) == 0) {                                                                            //check left border
                if ((X + 1) < BORDER_X) {
                    fields.get(X + 1).get(Y).setSurroundUpCount(i + 1);
                    fields.get(X + 1).get(Y).setSurroundUpPlayer(player);
                }
                else{   fields.get(X).get(Y).setSurroundUpCount(i);
                        fields.get(X).get(Y).setSurroundUpPlayer(player);
                    }
                break;
            } else i++;
        }

        //-------------------------From up to down------------------------------------//
        i = 0;
        while ((X + i) < BORDER_X) {
            if (X == (BORDER_X - 1)) {
                fields.get(X - 1).get(Y).setSurroundDownCount(1);
                fields.get(X - 1).get(Y).setSurroundDownPlayer(player);
                break;
            } else if (!(fields.get(X + i).get(Y).getPlayer().equals(player))) {
                if (fields.get(X + 1).get(Y).getSurroundUpCount() < i) {
                    fields.get(X + 1).get(Y).setSurroundUpCount(i);
                    fields.get(X + 1).get(Y).setSurroundUpPlayer(player);
                }
                if ((X - 1) >= 0) {
                    fields.get(X - 1).get(Y).setSurroundDownCount(i);
                    fields.get(X - 1).get(Y).setSurroundDownPlayer(player);
                }
                break;
            } else if ((X + i) == (BORDER_X - 1)) {
                if ((X - 1) >= 0) {
                    fields.get(X - 1).get(Y).setSurroundDownCount(i + 1);
                    fields.get(X - 1).get(Y).setSurroundDownPlayer(player);
                }
                else {
                        fields.get(X).get(Y).setSurroundDownCount(i);
                        fields.get(X).get(Y).setSurroundDownPlayer(player);
                    }
                break;
            } else i++;
        }


//-------------------------From Down right to up left------------------------------------//
        i = 0;
        while (((X - i) >= 0) && ((Y - i) >= 0)) {                                                                                    //search from left to right
            if (X == 0 && Y == 0) {                                                                                       //if point is at left border
                fields.get(X + 1).get(Y + 1).setSurroundUpLeftCount(1);                                               //
                fields.get(X + 1).get(Y + 1).setSurroundUpLeftPlayer(player);
                break;
            } else if (!(fields.get(X - i).get(Y - i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X - i).get(Y - i).getSurroundDownRightCount() < i) {                                     //count cannot be decreased
                    fields.get(X - i).get(Y - i).setSurroundDownRightCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X - i).get(Y - i).setSurroundDownRightPlayer(player);
                }
                if (((Y + 1) < BORDER_Y) && ((X + 1) < BORDER_X)) {                                                                        //check right border
                    fields.get(X + 1).get(Y + 1).setSurroundUpLeftCount(i);
                    fields.get(X + 1).get(Y + 1).setSurroundUpLeftPlayer(player);
                }
                break;
            } else if (((X - i) == 0) && ((Y - i) == 0)) {                                                                            //check left border
                if (((X + 1) < BORDER_X) && ((Y + 1) < BORDER_Y)) {
                    fields.get(X + 1).get(Y + 1).setSurroundUpLeftCount(i + 1);
                    fields.get(X + 1).get(Y + 1).setSurroundUpLeftPlayer(player);
                }
                else {
                    fields.get(X).get(Y).setSurroundUpLeftCount(i);
                    fields.get(X).get(Y).setSurroundUpLeftPlayer(player);
                }
                break;
            } else i++;
        }


        //-------------------------From up left to down right------------------------------------//
        i = 0;
        while (((X + i) < BORDER_X) && ((Y + i) < BORDER_Y)) {                                                                                    //search from left to right
            if (X == (BORDER_X - 1) && Y == (BORDER_Y - 1)) {                                                                                       //if point is at left border
                fields.get(X - 1).get(Y - 1).setSurroundDownRightCount(1);                                               //
                fields.get(X - 1).get(Y - 1).setSurroundDownRightPlayer(player);
                break;
            } else if (!(fields.get(X + i).get(Y + i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X + i).get(Y + i).getSurroundUpLeftCount() < i) {                                     //count cannot be decreased
                    fields.get(X + i).get(Y + i).setSurroundUpLeftCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X + i).get(Y + i).setSurroundUpLeftPlayer(player);
                }
                if (((Y - 1) >= 0) && ((X - 1) >= 0)) {                                                                        //check right border
                    fields.get(X - 1).get(Y - 1).setSurroundDownRightCount(i);
                    fields.get(X - 1).get(Y - 1).setSurroundDownRightPlayer(player);
                }
                break;
            } else if (((X + i) == (BORDER_X - 1) && ((Y + i) == (BORDER_Y - 1)))) {
                if (((X - 1) >= 0) && ((Y - 1) >= 0)) {
                    fields.get(X - 1).get(Y - 1).setSurroundDownRightCount(i + 1);
                    fields.get(X - 1).get(Y - 1).setSurroundDownRightPlayer(player);
                }
                else {
                    fields.get(X).get(Y).setSurroundDownRightCount(i);
                    fields.get(X).get(Y).setSurroundDownRightPlayer(player);
                }
                break;
            } else i++;
        }

        //-------------------------From up left to down right------------------------------------//
        i = 0;
        while (((X - i) >= 0) && ((Y + i) < BORDER_Y)) {                                                                                    //search from left to right
            if ((X == 0) && Y == (BORDER_Y - 1)) {                                                                                       //if point is at left border
                fields.get(X + 1).get(Y - 1).setSurroundUpRightCount(1);                                               //
                fields.get(X + 1).get(Y - 1).setSurroundUpRightPlayer(player);
                break;
            } else if (!(fields.get(X - i).get(Y + i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X - i).get(Y + i).getSurroundDownLeftCount() < i) {                                     //count cannot be decreased
                    fields.get(X - i).get(Y + i).setSurroundDownLeftCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X - i).get(Y + i).setSurroundDownLeftPlayer(player);
                }
                if (((Y - 1) >= 0) && ((X + 1) < BORDER_X)) {                                                                        //check right border
                    fields.get(X + 1).get(Y - 1).setSurroundUpRightCount(i);
                    fields.get(X + 1).get(Y - 1).setSurroundUpRightPlayer(player);
                }
                break;
            } else if (((X - i) == 0) && ((Y + i) == (BORDER_Y - 1))) {
                if (((X + 1) < BORDER_X) && ((Y - 1) >= 0)) {
                    fields.get(X + 1).get(Y - 1).setSurroundUpRightCount(i + 1);
                    fields.get(X + 1).get(Y - 1).setSurroundUpRightPlayer(player);
                }
                else {
                    fields.get(X).get(Y).setSurroundUpRightCount(i);
                    fields.get(X).get(Y).setSurroundUpRightPlayer(player);
                }
                break;
            } else i++;
        }

        //-------------------------From up left to down right------------------------------------//
        i = 0;
        while (((X + i) < BORDER_X) && ((Y - i) >= 0)) {                                                                                    //search from left to right
            if ((X == BORDER_X - 1) && Y == (0)) {                                                                                       //if point is at left border
                fields.get(X - 1).get(Y + 1).setSurroundDownLeftCount(1);                                               //
                fields.get(X - 1).get(Y + 1).setSurroundDownLeftPlayer(player);
                break;
            } else if (!(fields.get(X + i).get(Y - i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X + i).get(Y - i).getSurroundUpRightCount() < i) {                                     //count cannot be decreased
                    fields.get(X + i).get(Y - i).setSurroundUpRightCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X + i).get(Y - i).setSurroundUpRightPlayer(player);
                }
                if (((Y + 1) < BORDER_Y) && ((X - 1) >= 0)) {                                                                        //check right border
                    fields.get(X - 1).get(Y + 1).setSurroundDownLeftCount(i);
                    fields.get(X - 1).get(Y + 1).setSurroundDownLeftPlayer(player);
                }
                break;
            } else if (((X + i) == (BORDER_X - 1) && ((Y - i) == 0))) {
                if (((X - 1) >= 0) && ((Y + 1) < BORDER_Y)) {
                    fields.get(X - 1).get(Y + 1).setSurroundDownLeftCount(i + 1);
                    fields.get(X - 1).get(Y + 1).setSurroundDownLeftPlayer(player);
                }
                else {
                    fields.get(X).get(Y).setSurroundDownLeftCount(i);
                    fields.get(X).get(Y).setSurroundDownLeftPlayer(player);
                }
                break;
            } else i++;
        }

    }

    public static String getDebgStr(List<List<Field>> fields) {
        int l0 = fields.get(3).get(0).getSurroundLeftCount();
        int l1 = fields.get(4).get(1).getSurroundLeftCount();
        int l2 = fields.get(5).get(2).getSurroundLeftCount();
        int l3 = fields.get(6).get(3).getSurroundLeftCount();
        int l4 = fields.get(7).get(4).getSurroundLeftCount();
        int l5 = fields.get(2).get(5).getSurroundLeftCount();
        int l6 = fields.get(1).get(6).getSurroundLeftCount();
        int l7 = fields.get(0).get(7).getSurroundLeftCount();

        String p0 = fields.get(3).get(0).getPlayer();
        String p1 = fields.get(4).get(1).getPlayer();
        String p2 = fields.get(5).get(2).getPlayer();
        String p3 = fields.get(6).get(3).getPlayer();
        String p4 = fields.get(7).get(4).getPlayer();
        String p5 = fields.get(2).get(5).getPlayer();
        String p6 = fields.get(1).get(6).getPlayer();
        String p7 = fields.get(0).get(7).getPlayer();

        int r0 = fields.get(3).get(0).getSurroundRightCount();
        int r1 = fields.get(4).get(1).getSurroundRightCount();
        int r2 = fields.get(5).get(2).getSurroundRightCount();
        int r3 = fields.get(6).get(3).getSurroundRightCount();
        int r4 = fields.get(7).get(4).getSurroundRightCount();
        int r5 = fields.get(2).get(5).getSurroundRightCount();
        int r6 = fields.get(1).get(6).getSurroundRightCount();
        int r7 = fields.get(0).get(7).getSurroundRightCount();

        return (l0+p0+r0 +" "+ l1+p1+r1 +" "+ l2+p2+r2 +" "+ l3+p3+r3 +" "+ l4+p4+r4 +" "+ l5+p5+r5 +" "+ l6+p6+r6 +" "+ l7+p7+r7);
    }

}
