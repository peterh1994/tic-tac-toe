package sk.peterh.bp.tictactoe;

import java.util.List;
import static ConstatntPackage.Constant.BORDER_X;
import static ConstatntPackage.Constant.BORDER_Y;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */

public class FieldScore {


    private List<List<Field>> fields;
    private List<List<Field>> fields1;

    public static void getFiledScore(List<List<Field>> fields, String player, int X, int Y) {

        int i = 1;
        String res = "";

//-------------------------From right to left------------------------------------//
        i = 0;
        while ((Y - i) >= 0) {                                                                                    //search from left to right
            if (Y == 0) {                                                                                       //if point is at left border
                fields.get(X).get(Y + 1).setSurroundLeftCount(1);                                               //
                fields.get(X).get(Y + 1).setSurroundLeftPlayer(player);
                getPlayerScore(fields,X,Y + 1);
                break;
            } else if (!(fields.get(X).get(Y - i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X).get(Y - i).getSurroundRightCount() < i) {                                     //count cannot be decreased
                    fields.get(X).get(Y - i).setSurroundRightCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X).get(Y - i).setSurroundRightPlayer(player);
                    getPlayerScore(fields,X,Y - i);
                }
                if ((Y + 1) < BORDER_Y) {                                                                        //check right border
                    fields.get(X).get(Y + 1).setSurroundLeftCount(i);
                    fields.get(X).get(Y + 1).setSurroundLeftPlayer(player);
                    getPlayerScore(fields,X,Y + 1);
                }
                break;
            } else if ((Y - i) == 0) {                                                                            //check left border
                if ((Y + 1) < BORDER_Y) {
                    fields.get(X).get(Y + 1).setSurroundLeftCount(i + 1);
                    fields.get(X).get(Y + 1).setSurroundLeftPlayer(player);
                    getPlayerScore(fields,X,Y + i);
                } else {
                    fields.get(X).get(Y).setSurroundLeftCount(i);
                    fields.get(X).get(Y).setSurroundLeftPlayer(player);
                    getPlayerScore(fields,X,Y);
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
                getPlayerScore(fields,X,Y - 1);
                break;
            } else if (!(fields.get(X).get(Y + i).getPlayer().equals(player))) {
                if (fields.get(X).get(Y + i).getSurroundLeftCount() < i) {
                    fields.get(X).get(Y + i).setSurroundLeftCount(i);
                    fields.get(X).get(Y + i).setSurroundLeftPlayer(player);
                    getPlayerScore(fields,X,Y + i);
                }
                if ((Y - 1) >= 0) {
                    fields.get(X).get(Y - 1).setSurroundRightCount(i);
                    fields.get(X).get(Y - 1).setSurroundRightPlayer(player);
                    getPlayerScore(fields,X,Y - 1);
                }
                break;
            } else if ((Y + i) == (BORDER_Y - 1)) {
                if ((Y - 1) >= 0) {
                    fields.get(X).get(Y - 1).setSurroundRightCount(i + 1);
                    fields.get(X).get(Y - 1).setSurroundRightPlayer(player);
                    getPlayerScore(fields,X,Y - 1);
                } else {
                    fields.get(X).get(Y).setSurroundRightCount(i);
                    fields.get(X).get(Y).setSurroundRightPlayer(player);
                    getPlayerScore(fields,X,Y);
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
                getPlayerScore(fields,X + 1,Y);
                break;
            } else if (!(fields.get(X - i).get(Y).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X - i).get(Y).getSurroundDownCount() < i) {                                     //count cannot be decreased
                    fields.get(X - i).get(Y).setSurroundDownCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X - i).get(Y).setSurroundDownPlayer(player);
                    getPlayerScore(fields,X - i,Y);
                }
                if ((X + 1) < BORDER_X) {                                                                        //check right border
                    fields.get(X + 1).get(Y).setSurroundUpCount(i);
                    fields.get(X + 1).get(Y).setSurroundUpPlayer(player);
                    getPlayerScore(fields,X + 1,Y);
                }
                break;
            } else if ((X - i) == 0) {                                                                            //check left border
                if ((X + 1) < BORDER_X) {
                    fields.get(X + 1).get(Y).setSurroundUpCount(i + 1);
                    fields.get(X + 1).get(Y).setSurroundUpPlayer(player);
                    getPlayerScore(fields,X + 1,Y);
                } else {
                    fields.get(X).get(Y).setSurroundUpCount(i);
                    fields.get(X).get(Y).setSurroundUpPlayer(player);
                    getPlayerScore(fields,X,Y);
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
                getPlayerScore(fields,X - 1,Y);
                break;
            } else if (!(fields.get(X + i).get(Y).getPlayer().equals(player))) {
                if (fields.get(X + i).get(Y).getSurroundUpCount() < i) {
                    fields.get(X + i).get(Y).setSurroundUpCount(i);
                    fields.get(X + i).get(Y).setSurroundUpPlayer(player);
                    getPlayerScore(fields,X + i,Y);
                }
                if ((X - 1) >= 0) {
                    fields.get(X - 1).get(Y).setSurroundDownCount(i);
                    fields.get(X - 1).get(Y).setSurroundDownPlayer(player);
                    getPlayerScore(fields,X - 1,Y);
                }
                break;
            } else if ((X + i) == (BORDER_X - 1)) {
                if ((X - 1) >= 0) {
                    fields.get(X - 1).get(Y).setSurroundDownCount(i + 1);
                    fields.get(X - 1).get(Y).setSurroundDownPlayer(player);
                    getPlayerScore(fields,X - 1,Y);
                } else {
                    fields.get(X).get(Y).setSurroundDownCount(i);
                    fields.get(X).get(Y).setSurroundDownPlayer(player);
                    getPlayerScore(fields,X,Y);
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
                getPlayerScore(fields,X + 1,Y + 1);
                break;
            } else if (!(fields.get(X - i).get(Y - i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X - i).get(Y - i).getSurroundDownRightCount() < i) {                                     //count cannot be decreased
                    fields.get(X - i).get(Y - i).setSurroundDownRightCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X - i).get(Y - i).setSurroundDownRightPlayer(player);
                    getPlayerScore(fields,X - i,Y - i);
                }
                if (((Y + 1) < BORDER_Y) && ((X + 1) < BORDER_X)) {                                                                        //check right border
                    fields.get(X + 1).get(Y + 1).setSurroundUpLeftCount(i);
                    fields.get(X + 1).get(Y + 1).setSurroundUpLeftPlayer(player);
                    getPlayerScore(fields,X + 1,Y + 1);
                }
                break;
            } else if (((X - i) == 0) && ((Y - i) == 0)) {                                                                            //check left border
                if (((X + 1) < BORDER_X) && ((Y + 1) < BORDER_Y)) {
                    fields.get(X + 1).get(Y + 1).setSurroundUpLeftCount(i + 1);
                    fields.get(X + 1).get(Y + 1).setSurroundUpLeftPlayer(player);
                    getPlayerScore(fields,X + 1,Y + 1);
                } else {
                    fields.get(X).get(Y).setSurroundUpLeftCount(i);
                    fields.get(X).get(Y).setSurroundUpLeftPlayer(player);
                    getPlayerScore(fields,X,Y);
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
                getPlayerScore(fields,X - 1,Y - 1);
                break;
            } else if (!(fields.get(X + i).get(Y + i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X + i).get(Y + i).getSurroundUpLeftCount() < i) {                                     //count cannot be decreased
                    fields.get(X + i).get(Y + i).setSurroundUpLeftCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X + i).get(Y + i).setSurroundUpLeftPlayer(player);
                    getPlayerScore(fields,X + i,Y + i);
                }
                if (((Y - 1) >= 0) && ((X - 1) >= 0)) {                                                                        //check right border
                    fields.get(X - 1).get(Y - 1).setSurroundDownRightCount(i);
                    fields.get(X - 1).get(Y - 1).setSurroundDownRightPlayer(player);
                    getPlayerScore(fields,X - 1,Y - 1);
                }
                break;
            } else if (((X + i) == (BORDER_X - 1) && ((Y + i) == (BORDER_Y - 1)))) {
                if (((X - 1) >= 0) && ((Y - 1) >= 0)) {
                    fields.get(X - 1).get(Y - 1).setSurroundDownRightCount(i + 1);
                    fields.get(X - 1).get(Y - 1).setSurroundDownRightPlayer(player);
                    getPlayerScore(fields,X - 1,Y - 1);
                } else {
                    fields.get(X).get(Y).setSurroundDownRightCount(i);
                    fields.get(X).get(Y).setSurroundDownRightPlayer(player);
                    getPlayerScore(fields,X,Y);
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
                getPlayerScore(fields,X + 1,Y - 1);
                break;
            } else if (!(fields.get(X - i).get(Y + i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X - i).get(Y + i).getSurroundDownLeftCount() < i) {                                     //count cannot be decreased
                    fields.get(X - i).get(Y + i).setSurroundDownLeftCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X - i).get(Y + i).setSurroundDownLeftPlayer(player);
                    getPlayerScore(fields,X - i,Y + i);
                }
                if (((Y - 1) >= 0) && ((X + 1) < BORDER_X)) {                                                                        //check right border
                    fields.get(X + 1).get(Y - 1).setSurroundUpRightCount(i);
                    fields.get(X + 1).get(Y - 1).setSurroundUpRightPlayer(player);
                    getPlayerScore(fields,X + 1,Y - 1);
                }
                break;
            } else if (((X - i) == 0) && ((Y + i) == (BORDER_Y - 1))) {
                if (((X + 1) < BORDER_X) && ((Y - 1) >= 0)) {
                    fields.get(X + 1).get(Y - 1).setSurroundUpRightCount(i + 1);
                    fields.get(X + 1).get(Y - 1).setSurroundUpRightPlayer(player);
                    getPlayerScore(fields,X + 1,Y - 1);
                } else {
                    fields.get(X).get(Y).setSurroundUpRightCount(i);
                    fields.get(X).get(Y).setSurroundUpRightPlayer(player);
                    getPlayerScore(fields,X,Y);
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
                getPlayerScore(fields,X - 1,Y + 1);
                break;
            } else if (!(fields.get(X + i).get(Y - i).getPlayer().equals(player))) {                                  //on index i is not actual player symbol
                if (fields.get(X + i).get(Y - i).getSurroundUpRightCount() < i) {                                     //count cannot be decreased
                    fields.get(X + i).get(Y - i).setSurroundUpRightCount(i);                                          //set new count |2->|<-0 X 1->|<-1 X 0->|
                    fields.get(X + i).get(Y - i).setSurroundUpRightPlayer(player);
                    getPlayerScore(fields,X + i,Y - i);
                }
                if (((Y + 1) < BORDER_Y) && ((X - 1) >= 0)) {                                                                        //check right border
                    fields.get(X - 1).get(Y + 1).setSurroundDownLeftCount(i);
                    fields.get(X - 1).get(Y + 1).setSurroundDownLeftPlayer(player);
                    getPlayerScore(fields,X - 1,Y + 1);
                }
                break;
            } else if (((X + i) == (BORDER_X - 1) && ((Y - i) == 0))) {
                if (((X - 1) >= 0) && ((Y + 1) < BORDER_Y)) {
                    fields.get(X - 1).get(Y + 1).setSurroundDownLeftCount(i + 1);
                    fields.get(X - 1).get(Y + 1).setSurroundDownLeftPlayer(player);
                    getPlayerScore(fields,X - 1,Y + 1);
                } else {
                    fields.get(X).get(Y).setSurroundDownLeftCount(i);
                    fields.get(X).get(Y).setSurroundDownLeftPlayer(player);
                    getPlayerScore(fields,X,Y);
                }
                break;
            } else i++;
        }
        res = getDebgStr(fields);
    }

    private static void getPlayerScore(List<List<Field>> fields, int X, int Y) {
        int scoreX = 0, scoreO = 0;
        fields.get(X).get(Y).setScoreX(0);
        fields.get(X).get(Y).setScoreO(0);
        //Left
        if (fields.get(X).get(Y).getSurroundLeftPlayer().equals("X"))
            scoreX = fields.get(X).get(Y).getScoreX() + getDirPririty(fields.get(X).get(Y).getSurroundLeftCount());
        else if (fields.get(X).get(Y).getSurroundLeftPlayer().equals("O"))
            scoreO = fields.get(X).get(Y).getScoreO() + getDirPririty(fields.get(X).get(Y).getSurroundLeftCount());

        //Right
        if (fields.get(X).get(Y).getSurroundRightPlayer().equals("X"))
            scoreX = fields.get(X).get(Y).getScoreX() + getDirPririty(fields.get(X).get(Y).getSurroundRightCount());
        else if (fields.get(X).get(Y).getSurroundRightPlayer().equals("O"))
            scoreO = fields.get(X).get(Y).getScoreO() + getDirPririty(fields.get(X).get(Y).getSurroundRightCount());

        //Up
        if (fields.get(X).get(Y).getSurroundUpPlayer().equals("X"))
            scoreX = fields.get(X).get(Y).getScoreX() + getDirPririty(fields.get(X).get(Y).getSurroundUpCount());
        else if (fields.get(X).get(Y).getSurroundUpPlayer().equals("O"))
            scoreO = fields.get(X).get(Y).getScoreO() + getDirPririty(fields.get(X).get(Y).getSurroundUpCount());

        //Down
        if (fields.get(X).get(Y).getSurroundDownPlayer().equals("X"))
            scoreX = fields.get(X).get(Y).getScoreX() + getDirPririty(fields.get(X).get(Y).getSurroundDownCount());
        else if (fields.get(X).get(Y).getSurroundDownPlayer().equals("O"))
            scoreO = fields.get(X).get(Y).getScoreO() + getDirPririty(fields.get(X).get(Y).getSurroundDownCount());

        //Up Left
        if (fields.get(X).get(Y).getSurroundUpLeftPlayer().equals("X"))
            scoreX = fields.get(X).get(Y).getScoreX() + getDirPririty(fields.get(X).get(Y).getSurroundUpLeftCount());
        else if (fields.get(X).get(Y).getSurroundUpLeftPlayer().equals("O"))
            scoreO = fields.get(X).get(Y).getScoreO() + getDirPririty(fields.get(X).get(Y).getSurroundUpLeftCount());

        //Up Right
        if (fields.get(X).get(Y).getSurroundUpRightPlayer().equals("X"))
            scoreX = fields.get(X).get(Y).getScoreX() + getDirPririty(fields.get(X).get(Y).getSurroundUpRightCount());
        else if (fields.get(X).get(Y).getSurroundUpRightPlayer().equals("O"))
            scoreO = fields.get(X).get(Y).getScoreO() + getDirPririty(fields.get(X).get(Y).getSurroundUpRightCount());

        //Down Left
        if (fields.get(X).get(Y).getSurroundDownLeftPlayer().equals("X"))
            scoreX = fields.get(X).get(Y).getScoreX() + getDirPririty(fields.get(X).get(Y).getSurroundDownLeftCount());
        else if (fields.get(X).get(Y).getSurroundDownLeftPlayer().equals("O"))
            scoreO = fields.get(X).get(Y).getScoreO() + getDirPririty(fields.get(X).get(Y).getSurroundDownLeftCount());

        //Down Left
        if (fields.get(X).get(Y).getSurroundDownRightPlayer().equals("X"))
            scoreX = fields.get(X).get(Y).getScoreX() + getDirPririty(fields.get(X).get(Y).getSurroundDownRightCount());
        else if (fields.get(X).get(Y).getSurroundDownRightPlayer().equals("O"))
            scoreO = fields.get(X).get(Y).getScoreO() + getDirPririty(fields.get(X).get(Y).getSurroundDownRightCount());

        if (scoreX > fields.get(X).get(Y).getScoreX())
            fields.get(X).get(Y).setScoreX(scoreX);
        if (scoreO > fields.get(X).get(Y).getScoreO())
            fields.get(X).get(Y).setScoreO(scoreO);
    }

    private static int getDirPririty(int num) {
        switch (num) {
            case 0:     return 0;
            case 1:     return 1;
            case 2:     return 10;
            case 3:     return 100;
            case 4:     return 1000;
            case 5:     return 10000;
            default:    return -1;
        }
    }
    public static String getDebgStr(List<List<Field>> fields) {
        String scoreOfX;
        String res;

        int X0 = fields.get(0).get(0).getScoreX();
        int X1 = fields.get(0).get(1).getScoreX();
        int X2 = fields.get(0).get(2).getScoreX();
        int X3 = fields.get(0).get(3).getScoreX();
        int X4 = fields.get(0).get(4).getScoreX();
        int X5 = fields.get(0).get(5).getScoreX();
        int X6 = fields.get(0).get(6).getScoreX();
        int X7 = fields.get(0).get(7).getScoreX();

        res = ("0|\t" +X0 +"\t|\t"+ X1 +"\t|\t"+ X2 +"\t|\t"+ X3 +"\t|\t"+ X4 +"\t|\t"+ X5 +"\t|\t"+ X6 +"\t|\t"+ X7 +"\t|\n");

        scoreOfX = (" |\t0 \t|\t 1 \t|\t 2 \t|\t 3 \t|\t 4 \t|\t 5 \t|\t 6 \t|\t 7 \t|\n");

        scoreOfX += "----------------------------------------------------------------\n";
        scoreOfX += (res);

         X0 = fields.get(1).get(0).getScoreX();
         X1 = fields.get(1).get(1).getScoreX();
         X2 = fields.get(1).get(2).getScoreX();
         X3 = fields.get(1).get(3).getScoreX();
         X4 = fields.get(1).get(4).getScoreX();
         X5 = fields.get(1).get(5).getScoreX();
         X6 = fields.get(1).get(6).getScoreX();
         X7 = fields.get(1).get(7).getScoreX();

        res = ("1|\t" +X0 +"\t|\t"+ X1 +"\t|\t"+ X2 +"\t|\t"+ X3 +"\t|\t"+ X4 +"\t|\t"+ X5 +"\t|\t"+ X6 +"\t|\t"+ X7 +"\t|\n");
        scoreOfX += (res);

        X0 = fields.get(2).get(0).getScoreX();
        X1 = fields.get(2).get(1).getScoreX();
        X2 = fields.get(2).get(2).getScoreX();
        X3 = fields.get(2).get(3).getScoreX();
        X4 = fields.get(2).get(4).getScoreX();
        X5 = fields.get(2).get(5).getScoreX();
        X6 = fields.get(2).get(6).getScoreX();
        X7 = fields.get(2).get(7).getScoreX();

        res = ("2|\t" +X0 +"\t|\t"+ X1 +"\t|\t"+ X2 +"\t|\t"+ X3 +"\t|\t"+ X4 +"\t|\t"+ X5 +"\t|\t"+ X6 +"\t|\t"+ X7 +"\t|\n");
        scoreOfX += (res);

        X0 = fields.get(3).get(0).getScoreX();
        X1 = fields.get(3).get(1).getScoreX();
        X2 = fields.get(3).get(2).getScoreX();
        X3 = fields.get(3).get(3).getScoreX();
        X4 = fields.get(3).get(4).getScoreX();
        X5 = fields.get(3).get(5).getScoreX();
        X6 = fields.get(3).get(6).getScoreX();
        X7 = fields.get(3).get(7).getScoreX();

        res = ("3|\t" +X0 +"\t|\t"+ X1 +"\t|\t"+ X2 +"\t|\t"+ X3 +"\t|\t"+ X4 +"\t|\t"+ X5 +"\t|\t"+ X6 +"\t|\t"+ X7 +"\t|\n");
        scoreOfX += (res);

        X0 = fields.get(4).get(0).getScoreX();
        X1 = fields.get(4).get(1).getScoreX();
        X2 = fields.get(4).get(2).getScoreX();
        X3 = fields.get(4).get(3).getScoreX();
        X4 = fields.get(4).get(4).getScoreX();
        X5 = fields.get(4).get(5).getScoreX();
        X6 = fields.get(4).get(6).getScoreX();
        X7 = fields.get(4).get(7).getScoreX();

        res = ("4|\t" +X0 +"\t|\t"+ X1 +"\t|\t"+ X2 +"\t|\t"+ X3 +"\t|\t"+ X4 +"\t|\t"+ X5 +"\t|\t"+ X6 +"\t|\t"+ X7 +"\t|\n");
        scoreOfX += (res);

        X0 = fields.get(5).get(0).getScoreX();
        X1 = fields.get(5).get(1).getScoreX();
        X2 = fields.get(5).get(2).getScoreX();
        X3 = fields.get(5).get(3).getScoreX();
        X4 = fields.get(5).get(4).getScoreX();
        X5 = fields.get(5).get(5).getScoreX();
        X6 = fields.get(5).get(6).getScoreX();
        X7 = fields.get(5).get(7).getScoreX();

        res = ("5|\t" +X0 +"\t|\t"+ X1 +"\t|\t"+ X2 +"\t|\t"+ X3 +"\t|\t"+ X4 +"\t|\t"+ X5 +"\t|\t"+ X6 +"\t|\t"+ X7 +"\t|\n");
        scoreOfX += (res);

        X0 = fields.get(6).get(0).getScoreX();
        X1 = fields.get(6).get(1).getScoreX();
        X2 = fields.get(6).get(2).getScoreX();
        X3 = fields.get(6).get(3).getScoreX();
        X4 = fields.get(6).get(4).getScoreX();
        X5 = fields.get(6).get(5).getScoreX();
        X6 = fields.get(6).get(6).getScoreX();
        X7 = fields.get(6).get(7).getScoreX();

        res = ("6|\t" +X0 +"\t|\t"+ X1 +"\t|\t"+ X2 +"\t|\t"+ X3 +"\t|\t"+ X4 +"\t|\t"+ X5 +"\t|\t"+ X6 +"\t|\t"+ X7 +"\t|\n");
        scoreOfX += (res);

        X0 = fields.get(7).get(0).getScoreX();
        X1 = fields.get(7).get(1).getScoreX();
        X2 = fields.get(7).get(2).getScoreX();
        X3 = fields.get(7).get(3).getScoreX();
        X4 = fields.get(7).get(4).getScoreX();
        X5 = fields.get(7).get(5).getScoreX();
        X6 = fields.get(7).get(6).getScoreX();
        X7 = fields.get(7).get(7).getScoreX();

        res = ("7|\t" +X0 +"\t|\t"+ X1 +"\t|\t"+ X2 +"\t|\t"+ X3 +"\t|\t"+ X4 +"\t|\t"+ X5 +"\t|\t"+ X6 +"\t|\t"+ X7 +"\t|");
        scoreOfX += (res);

        return scoreOfX;
    }

}
