package sk.peterh.bp.tictactoe;

import java.util.List;
import static ConstatntPackage.Constant.BORDER_X;
import static ConstatntPackage.Constant.BORDER_Y;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */

public class FieldScore {


    public static void getFiledScore(List<List<Field>> fields, String player, int X, int Y) {
        String res = "";

        fields.get(X).get(Y).resetSurroundCount();
        fields.get(X).get(Y).setScoreX(0);
        fields.get(X).get(Y).setScoreO(0);

        //-------------------------horizontal------------------------------------//
        //Left direction
        int count1 = 0, count2=0, i = 0;                                                                //Count of same char like player on left and right without X,Y point
        int X_1 = -1, Y_1 = -1;                                                                         //Tmp var, -1 means that there is no player's char around
        int X_2 = -1, Y_2 = -1;                                                                         //Tmp var, -1 means that there is no player's char around

        if (Y != 0) i = 1;                                                                              //Check if point is on border of matrix
        while(i > 0){                                                                                   //if i = 1, point is not at border and program can continue
            if (Y - i >= 0) {                                                                           //check if index with position is not out of border
                if (fields.get(X).get(Y - i).getPlayer().equals(player)) {                              //if index with position is equal to player's char
                    fields.get(X).get(Y - i).resetSurroundCount();                                      //clean up used positions
                    fields.get(X).get(Y - i).setScoreX(0);                                              //clean up used positions
                    fields.get(X).get(Y - i).setScoreO(0);                                              //clean up used positions
                    i++;                                                                                //increment index
                }
                else if (fields.get(X).get(Y - i).isEmpty()) {                                          //if there is not player's char, check if point is empty
                    X_1 = X;                                                                            //if point is empty save axis X
                    Y_1 = Y - i;                                                                        //also axis Y
                    break;                                                                              //run out of infinite while
                }
                else break;                                                                             //run out of infinite while
            }
            else break;                                                                                 //index is out of matrix border, it means last player's char is on border
        }
        count1 = i - 1;                                                                                 //count of things in direction si depend on counted index
        //Right direction
        if (Y != (BORDER_Y - 1)) i = 1;                                                                 //Check if point is on border of matrix
        while(i > 0){                                                                                   //if i = 1, point is not at border and program can continue
            if (Y + i < BORDER_Y) {                                                                     //check if index with position is not out of border
                if (fields.get(X).get(Y + i).getPlayer().equals(player)){                               //if index with position is equal to player's char
                    fields.get(X).get(Y + i).resetSurroundCount();                                      //clean up used positions
                    fields.get(X).get(Y + i).setScoreX(0);                                              //clean up used positions
                    fields.get(X).get(Y + i).setScoreO(0);                                              //clean up used positions
                    i++;                                                                                //increment index
                }
                else if (fields.get(X).get(Y + i).isEmpty()) {                                          //if there is not player's char, check if point is empty
                    X_2 = X;                                                                            //if point is empty save axis X
                    Y_2 = Y + i;                                                                        //also axis Y
                    break;                                                                              //run out of infinite while
                }
                else break;                                                                             //run out of infinite while
            }
            else break;                                                                                 //run out of infinite while
        }
        count2 = i - 1;                                                                                 //count of things in direction si depend on counted index

        if ((X_1 != -1) && (Y_1!= -1)) {                                                                //axis must be other than -1, -1 is default value
            fields.get(X_1).get(Y_1).setSurroundRightCount(count1 + count2 + 1);                        //count of things on one side + count of thing on oposite side + actual position (1)
            fields.get(X_1).get(Y_1).setSurroundRightPlayer(player);                                    //set for what which count is it
            getPlayerScore(fields,X_1,Y_1);                                                             //compute score on empty position
        }
        if ((X_2 != -1) && (Y_2 != -1)) {                                                               //axis must be other than -1, -1 is default value
            fields.get(X_2).get(Y_2).setSurroundLeftCount(count1 + count2 + 1);                         //count of things on one side + count of thing on oposite side + actual position (1)
            fields.get(X_2).get(Y_2).setSurroundLeftPlayer(player);                                     //set for what which count is it
            getPlayerScore(fields,X_2,Y_2);                                                             //compute score on empty position
        }
        count1 = 0; count2 = 0; i = 0;                                                                  //Count of same char like player on left and right without X,Y point
        X_1 = -1; Y_1 = -1;                                                                             //Tmp var, -1 means that there is no player's char around
        X_2 = -1; Y_2 = -1;                                                                             //Tmp var, -1 means that there is no player's char around

        //-------------------------vertical------------------------------------//
        //Up direction
        if (X != 0) i = 1;                                                                              //Check if point is on border of matrix
        while(i > 0){                                                                                   //if i = 1, point is not at border and program can continue
            if (X - i >= 0) {                                                                           //check if index with position is not out of border
                if (fields.get(X - i).get(Y).getPlayer().equals(player)) {                              //if index with position is equal to player's char
                    fields.get(X - i).get(Y).resetSurroundCount();                                      //clean up used positions
                    fields.get(X - i).get(Y).setScoreX(0);                                              //clean up used positions
                    fields.get(X - i).get(Y).setScoreO(0);                                              //clean up used positions
                    i++;                                                                                //increment index
                }
                else if (fields.get(X - i).get(Y).isEmpty()) {                                          //if there is not player's char, check if point is empty
                    X_1 = X - i;                                                                        //if point is empty save axis X
                    Y_1 = Y;                                                                            //also axis Y
                    break;                                                                              //run out of infinite while
                }
                else break;                                                                             //run out of infinite while
            }
            else break;                                                                                 //index is out of matrix border, it means last player's char is on border
        }
        count1 = i - 1;                                                                                 //count of things in direction si depend on counted index
        //Down direction
        if (X != (BORDER_X - 1)) i = 1;                                                                 //Check if point is on border of matrix
        while(i > 0){                                                                                   //if i = 1, point is not at border and program can continue
            if (X + i < BORDER_X) {                                                                     //check if index with position is not out of border
                if (fields.get(X + i).get(Y).getPlayer().equals(player)){                               //if index with position is equal to player's char
                    fields.get(X + i).get(Y).resetSurroundCount();                                      //clean up used positions
                    fields.get(X + i).get(Y).setScoreX(0);                                              //clean up used positions
                    fields.get(X + i).get(Y).setScoreO(0);                                              //clean up used positions
                    i++;                                                                                //increment index
                }
                else if (fields.get(X + i).get(Y).isEmpty()) {                                          //if there is not player's char, check if point is empty
                    X_2 = X + i;                                                                        //if point is empty save axis X
                    Y_2 = Y;                                                                            //also axis Y
                    break;                                                                              //run out of infinite while
                }
                else break;                                                                             //run out of infinite while
            }
            else break;                                                                                 //run out of infinite while
        }
        count2 = i - 1;                                                                                 //count of things in direction si depend on counted index

        if ((X_1 != -1) && (Y_1!= -1)) {                                                                //axis must be other than -1, -1 is default value
            fields.get(X_1).get(Y_1).setSurroundDownCount(count1 + count2 + 1);                         //count of things on one side + count of thing on oposite side + actual position (1)
            fields.get(X_1).get(Y_1).setSurroundDownPlayer(player);                                     //set for what which count is it
            getPlayerScore(fields,X_1,Y_1);                                                             //compute score on empty position
        }
        if ((X_2 != -1) && (Y_2 != -1)) {                                                               //axis must be other than -1, -1 is default value
            fields.get(X_2).get(Y_2).setSurroundUpCount(count1 + count2 + 1);                           //count of things on one side + count of thing on oposite side + actual position (1)
            fields.get(X_2).get(Y_2).setSurroundUpPlayer(player);                                       //set for what which count is it
            getPlayerScore(fields,X_2,Y_2);                                                             //compute score on empty position
        }
        count1 = 0; count2 = 0; i = 0;                                                                  //Count of same char like player on left and right without X,Y point
        X_1 = -1; Y_1 = -1;                                                                             //Tmp var, -1 means that there is no player's char around
        X_2 = -1; Y_2 = -1;                                                                             //Tmp var, -1 means that there is no player's char around


        //-------------------------diagonal +------------------------------------//
        //Up left direction
        if ((X != 0) || (Y != 0)) i = 1;                                                                //Check if point is on border of matrix
        while(i > 0){                                                                                   //if i = 1, point is not at border and program can continue
            if ((X - i >= 0) && (Y - i >= 0)) {                                                         //check if index with position is not out of border
                if (fields.get(X - i).get(Y - i).getPlayer().equals(player)) {                          //if index with position is equal to player's char
                    fields.get(X - i).get(Y - i).resetSurroundCount();                                  //clean up used positions
                    fields.get(X - i).get(Y - i).setScoreX(0);                                          //clean up used positions
                    fields.get(X - i).get(Y - i).setScoreO(0);                                          //clean up used positions
                    i++;                                                                                //increment index
                }
                else if (fields.get(X - i).get(Y - i).isEmpty()) {                                      //if there is not player's char, check if point is empty
                    X_1 = X - i;                                                                        //if point is empty save axis X
                    Y_1 = Y - i;                                                                        //also axis Y
                    break;                                                                              //run out of infinite while
                }
                else break;                                                                             //run out of infinite while
            }
            else break;                                                                                 //index is out of matrix border, it means last player's char is on border
        }
        count1 = i - 1;                                                                                 //count of things in direction si depend on counted index
        //Down Right
        if ((X != (BORDER_X - 1)) || (Y != (BORDER_Y - 1))) i = 1;                                      //Check if point is on border of matrix
        while(i > 0){                                                                                   //if i = 1, point is not at border and program can continue
            if ((X + i < BORDER_X) && (Y + i < BORDER_Y)) {                                             //check if index with position is not out of border
                if (fields.get(X + i).get(Y + i).getPlayer().equals(player)){                           //if index with position is equal to player's char
                    fields.get(X + i).get(Y + i).resetSurroundCount();                                  //clean up used positions
                    fields.get(X + i).get(Y + i).setScoreX(0);                                          //clean up used positions
                    fields.get(X + i).get(Y + i).setScoreO(0);                                          //clean up used positions
                    i++;                                                                                //increment index
                }
                else if (fields.get(X + i).get(Y + i).isEmpty()) {                                      //if there is not player's char, check if point is empty
                    X_2 = X + i;                                                                        //if point is empty save axis X
                    Y_2 = Y + i;                                                                        //also axis Y
                    break;                                                                              //run out of infinite while
                }
                else break;                                                                             //run out of infinite while
            }
            else break;                                                                                 //run out of infinite while
        }
        count2 = i - 1;                                                                                 //count of things in direction si depend on counted index

        if ((X_1 != -1) && (Y_1!= -1)) {                                                                //axis must be other than -1, -1 is default value
            fields.get(X_1).get(Y_1).setSurroundDownRightCount(count1 + count2 + 1);                         //count of things on one side + count of thing on oposite side + actual position (1)
            fields.get(X_1).get(Y_1).setSurroundDownRightPlayer(player);                                //set for what which count is it
            getPlayerScore(fields,X_1,Y_1);                                                             //compute score on empty position
        }
        if ((X_2 != -1) && (Y_2 != -1)) {                                                               //axis must be other than -1, -1 is default value
            fields.get(X_2).get(Y_2).setSurroundUpLeftCount(count1 + count2 + 1);                         //count of things on one side + count of thing on oposite side + actual position (1)
            fields.get(X_2).get(Y_2).setSurroundUpLeftPlayer(player);                                     //set for what which count is it
            getPlayerScore(fields,X_2,Y_2);                                                             //compute score on empty position
        }
        count1 = 0; count2 = 0; i = 0;                                                                  //Count of same char like player on left and right without X,Y point
        X_1 = -1; Y_1 = -1;                                                                             //Tmp var, -1 means that there is no player's char around
        X_2 = -1; Y_2 = -1;                                                                             //Tmp var, -1 means that there is no player's char around


        //-------------------------diagonal -------------------------------------//
        //Down left direction
        if ((X != (BORDER_X - 1)) || (Y != 0)) i = 1;                                                                //Check if point is on border of matrix
        while(i > 0){                                                                                   //if i = 1, point is not at border and program can continue
            if ((X + i < BORDER_X) && (Y - i >= 0)) {                                                         //check if index with position is not out of border
                if (fields.get(X + i).get(Y - i).getPlayer().equals(player)) {                          //if index with position is equal to player's char
                    fields.get(X + i).get(Y - i).resetSurroundCount();                                  //clean up used positions
                    fields.get(X + i).get(Y - i).setScoreX(0);                                          //clean up used positions
                    fields.get(X + i).get(Y - i).setScoreO(0);                                          //clean up used positions
                    i++;                                                                                //increment index
                }
                else if (fields.get(X + i).get(Y - i).isEmpty()) {                                      //if there is not player's char, check if point is empty
                    X_1 = X + i;                                                                        //if point is empty save axis X
                    Y_1 = Y - i;                                                                        //also axis Y
                    break;                                                                              //run out of infinite while
                }
                else break;                                                                             //run out of infinite while
            }
            else break;                                                                                 //index is out of matrix border, it means last player's char is on border
        }
        count1 = i - 1;                                                                                 //count of things in direction si depend on counted index
        //Up Right
        if ((X != 0) || (Y != (BORDER_Y - 1))) i = 1;                                      //Check if point is on border of matrix
        while(i > 0){                                                                                   //if i = 1, point is not at border and program can continue
            if ((X - i >= 0) && (Y + i < BORDER_Y)) {                                             //check if index with position is not out of border
                if (fields.get(X - i).get(Y + i).getPlayer().equals(player)){                           //if index with position is equal to player's char
                    fields.get(X - i).get(Y + i).resetSurroundCount();                                  //clean up used positions
                    fields.get(X - i).get(Y + i).setScoreX(0);                                          //clean up used positions
                    fields.get(X - i).get(Y + i).setScoreO(0);                                          //clean up used positions
                    i++;                                                                                //increment index
                }
                else if (fields.get(X - i).get(Y + i).isEmpty()) {                                      //if there is not player's char, check if point is empty
                    X_2 = X - i;                                                                        //if point is empty save axis X
                    Y_2 = Y + i;                                                                            //also axis Y
                    break;                                                                              //run out of infinite while
                }
                else break;                                                                             //run out of infinite while
            }
            else break;                                                                                 //run out of infinite while
        }
        count2 = i - 1;                                                                                 //count of things in direction si depend on counted index

        if ((X_1 != -1) && (Y_1!= -1)) {                                                                //axis must be other than -1, -1 is default value
            fields.get(X_1).get(Y_1).setSurroundUpRightCount(count1 + count2 + 1);                        //count of things on one side + count of thing on oposite side + actual position (1)
            fields.get(X_1).get(Y_1).setSurroundUpRightPlayer(player);                                    //set for what which count is it
            getPlayerScore(fields,X_1,Y_1);                                                             //compute score on empty position
        }
        if ((X_2 != -1) && (Y_2 != -1)) {                                                               //axis must be other than -1, -1 is default value
            fields.get(X_2).get(Y_2).setSurroundDownLeftCount(count1 + count2 + 1);                         //count of things on one side + count of thing on oposite side + actual position (1)
            fields.get(X_2).get(Y_2).setSurroundDownLeftPlayer(player);                                     //set for what which count is it
            getPlayerScore(fields,X_2,Y_2);                                                             //compute score on empty position
        }


        if (fields.get(X).get(Y).getPlayer().equals("X")) {
            res = getDebgStr(fields);
            fields.get(X).get(Y).getScoreX();
        }


    }

    private static void getPlayerScore(List<List<Field>> fields, int X, int Y) {
        long scoreX = 0, scoreO = 0;
        fields.get(X).get(Y).setScoreX(0);
        fields.get(X).get(Y).setScoreO(0);
        //Left
        if (fields.get(X).get(Y).getSurroundLeftPlayer().equals("X"))
            scoreX += getDirPririty(fields.get(X).get(Y).getSurroundLeftCount());
        else if (fields.get(X).get(Y).getSurroundLeftPlayer().equals("O"))
            scoreO += getDirPririty(fields.get(X).get(Y).getSurroundLeftCount());

        //Right
        if (fields.get(X).get(Y).getSurroundRightPlayer().equals("X"))
            scoreX += getDirPririty(fields.get(X).get(Y).getSurroundRightCount());
        else if (fields.get(X).get(Y).getSurroundRightPlayer().equals("O"))
            scoreO += getDirPririty(fields.get(X).get(Y).getSurroundRightCount());

        //Up
        if (fields.get(X).get(Y).getSurroundUpPlayer().equals("X"))
            scoreX += getDirPririty(fields.get(X).get(Y).getSurroundUpCount());
        else if (fields.get(X).get(Y).getSurroundUpPlayer().equals("O"))
            scoreO += getDirPririty(fields.get(X).get(Y).getSurroundUpCount());

        //Down
        if (fields.get(X).get(Y).getSurroundDownPlayer().equals("X"))
            scoreX += getDirPririty(fields.get(X).get(Y).getSurroundDownCount());
        else if (fields.get(X).get(Y).getSurroundDownPlayer().equals("O"))
            scoreO += getDirPririty(fields.get(X).get(Y).getSurroundDownCount());

        //Up Left
        if (fields.get(X).get(Y).getSurroundUpLeftPlayer().equals("X"))
            scoreX += getDirPririty(fields.get(X).get(Y).getSurroundUpLeftCount());
        else if (fields.get(X).get(Y).getSurroundUpLeftPlayer().equals("O"))
            scoreO += getDirPririty(fields.get(X).get(Y).getSurroundUpLeftCount());

        //Up Right
        if (fields.get(X).get(Y).getSurroundUpRightPlayer().equals("X"))
            scoreX += getDirPririty(fields.get(X).get(Y).getSurroundUpRightCount());
        else if (fields.get(X).get(Y).getSurroundUpRightPlayer().equals("O"))
            scoreO += getDirPririty(fields.get(X).get(Y).getSurroundUpRightCount());

        //Down Left
        if (fields.get(X).get(Y).getSurroundDownLeftPlayer().equals("X"))
            scoreX += getDirPririty(fields.get(X).get(Y).getSurroundDownLeftCount());
        else if (fields.get(X).get(Y).getSurroundDownLeftPlayer().equals("O"))
            scoreO += getDirPririty(fields.get(X).get(Y).getSurroundDownLeftCount());

        //Down Left
        if (fields.get(X).get(Y).getSurroundDownRightPlayer().equals("X"))
            scoreX += getDirPririty(fields.get(X).get(Y).getSurroundDownRightCount());
        else if (fields.get(X).get(Y).getSurroundDownRightPlayer().equals("O"))
            scoreO += getDirPririty(fields.get(X).get(Y).getSurroundDownRightCount());

        if (scoreX > fields.get(X).get(Y).getScoreX())
            fields.get(X).get(Y).setScoreX(scoreX);
        if (scoreO > fields.get(X).get(Y).getScoreO())
            fields.get(X).get(Y).setScoreO(scoreO);
    }

    private static long getDirPririty(int num) {
        switch (num) {
            case 0:     return 0;
            case 1:     return 1;
            case 2:     return 10;
            case 3:     return 100;
            case 4:     return 1000;
            case 5:     return 10000;
            case 6:     return 100000;
            case 7:     return 1000000;
            case 8:     return 10000000;
            case 9:     return 100000000;
            case 10:    return 1000000000;
            default:    return -1;
        }
    }
    public static String getDebgStr(List<List<Field>> fields) {
        String scoreOfX;
        String res;

        long X0 = fields.get(0).get(0).getScoreX();
        long X1 = fields.get(0).get(1).getScoreX();
        long X2 = fields.get(0).get(2).getScoreX();
        long X3 = fields.get(0).get(3).getScoreX();
        long X4 = fields.get(0).get(4).getScoreX();
        long X5 = fields.get(0).get(5).getScoreX();
        long X6 = fields.get(0).get(6).getScoreX();
        long X7 = fields.get(0).get(7).getScoreX();

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

        res = ("1|\t" +X0 +"\t\t|"+ X1 +"\t\t|"+ X2 +"\t\t|"+ X3 +"\t\t|"+ X4 +"\t\t|"+ X5 +"\t\t|"+ X6 +"\t\t|"+ X7 +"\t|\n");
        scoreOfX += (res);

        X0 = fields.get(2).get(0).getScoreX();
        X1 = fields.get(2).get(1).getScoreX();
        X2 = fields.get(2).get(2).getScoreX();
        X3 = fields.get(2).get(3).getScoreX();
        X4 = fields.get(2).get(4).getScoreX();
        X5 = fields.get(2).get(5).getScoreX();
        X6 = fields.get(2).get(6).getScoreX();
        X7 = fields.get(2).get(7).getScoreX();

        res = ("2|\t" +X0 +"\t\t|"+ X1 +"\t\t|"+ X2 +"\t\t|"+ X3 +"\t\t|"+ X4 +"\t\t|"+ X5 +"\t\t|"+ X6 +"\t\t|"+ X7 +"\t|\n");
        scoreOfX += (res);

        X0 = fields.get(3).get(0).getScoreX();
        X1 = fields.get(3).get(1).getScoreX();
        X2 = fields.get(3).get(2).getScoreX();
        X3 = fields.get(3).get(3).getScoreX();
        X4 = fields.get(3).get(4).getScoreX();
        X5 = fields.get(3).get(5).getScoreX();
        X6 = fields.get(3).get(6).getScoreX();
        X7 = fields.get(3).get(7).getScoreX();

        res = ("3|\t" +X0 +"\t\t|"+ X1 +"\t\t|"+ X2 +"\t\t|"+ X3 +"\t\t|"+ X4 +"\t\t|"+ X5 +"\t\t|"+ X6 +"\t\t|"+ X7 +"\t|\n");
        scoreOfX += (res);

        X0 = fields.get(4).get(0).getScoreX();
        X1 = fields.get(4).get(1).getScoreX();
        X2 = fields.get(4).get(2).getScoreX();
        X3 = fields.get(4).get(3).getScoreX();
        X4 = fields.get(4).get(4).getScoreX();
        X5 = fields.get(4).get(5).getScoreX();
        X6 = fields.get(4).get(6).getScoreX();
        X7 = fields.get(4).get(7).getScoreX();

        res = ("4|\t" +X0 +"\t\t|"+ X1 +"\t\t|"+ X2 +"\t\t|"+ X3 +"\t\t|"+ X4 +"\t\t|"+ X5 +"\t\t|"+ X6 +"\t\t|"+ X7 +"\t|\n");
        scoreOfX += (res);

        X0 = fields.get(5).get(0).getScoreX();
        X1 = fields.get(5).get(1).getScoreX();
        X2 = fields.get(5).get(2).getScoreX();
        X3 = fields.get(5).get(3).getScoreX();
        X4 = fields.get(5).get(4).getScoreX();
        X5 = fields.get(5).get(5).getScoreX();
        X6 = fields.get(5).get(6).getScoreX();
        X7 = fields.get(5).get(7).getScoreX();

        res = ("5|\t" +X0 +"\t\t|"+ X1 +"\t\t|"+ X2 +"\t\t|"+ X3 +"\t\t|"+ X4 +"\t\t|"+ X5 +"\t\t|"+ X6 +"\t\t|"+ X7 +"\t|\n");
        scoreOfX += (res);

        X0 = fields.get(6).get(0).getScoreX();
        X1 = fields.get(6).get(1).getScoreX();
        X2 = fields.get(6).get(2).getScoreX();
        X3 = fields.get(6).get(3).getScoreX();
        X4 = fields.get(6).get(4).getScoreX();
        X5 = fields.get(6).get(5).getScoreX();
        X6 = fields.get(6).get(6).getScoreX();
        X7 = fields.get(6).get(7).getScoreX();

        res = ("6|\t" +X0 +"\t\t|"+ X1 +"\t\t|"+ X2 +"\t\t|"+ X3 +"\t\t|"+ X4 +"\t\t|"+ X5 +"\t\t|"+ X6 +"\t\t|"+ X7 +"\t|\n");
        scoreOfX += (res);

        X0 = fields.get(7).get(0).getScoreX();
        X1 = fields.get(7).get(1).getScoreX();
        X2 = fields.get(7).get(2).getScoreX();
        X3 = fields.get(7).get(3).getScoreX();
        X4 = fields.get(7).get(4).getScoreX();
        X5 = fields.get(7).get(5).getScoreX();
        X6 = fields.get(7).get(6).getScoreX();
        X7 = fields.get(7).get(7).getScoreX();

        res = ("7|\t" +X0 +"\t\t|"+ X1 +"\t\t|"+ X2 +"\t\t|"+ X3 +"\t\t|"+ X4 +"\t\t|"+ X5 +"\t\t|"+ X6 +"\t\t|"+ X7 +"\t|\n");
        scoreOfX += (res);

        return scoreOfX;
    }

}
