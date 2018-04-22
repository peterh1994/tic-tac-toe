package sk.peterh.bp.tictactoe;

import java.util.ArrayList;
import java.util.List;

import ConstatntPackage.Constant;

import static ConstatntPackage.Constant.BEST_SCORE_WIDTH;
import static ConstatntPackage.Constant.BORDER_X;
import static ConstatntPackage.Constant.BORDER_Y;
import static ConstatntPackage.Constant.WINNIG_COUNT;
import static ConstatntPackage.Constant.direction;
import static ConstatntPackage.Constant.direction.LEFT_RIGHT;
import static ConstatntPackage.Constant.direction.UP_DOWN;
import static ConstatntPackage.Constant.direction.UP_LEFT_DOWN_RIGHT;
import static ConstatntPackage.Constant.direction.UP_RIGHT_DOWN_LEFT;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */

public class MinMaxAI implements AI {

    @Override
    public void getMoveOfAI(List<List<Field>> fields, int lastPositionX, int lastPositionY, int[] data) {

    }

    @Override
    public void getMoveOfAI(List<List<Field>> fields, FieldAdapter.bestScore bestScoreX, FieldAdapter.bestScore bestScoreO, int[] data) {
        FieldAdapter.bestScore bestScoreTmp = new FieldAdapter.bestScore();
        List<finalScore> finScore = new ArrayList<finalScore>();
        int dirScore[] = new int[]{0, 0, 0, 0};
        int dirScoreTmp[] = new int[]{0, 0, 0 ,0};
        direction dirTmp[] = new direction[]{LEFT_RIGHT, UP_DOWN, UP_LEFT_DOWN_RIGHT, UP_RIGHT_DOWN_LEFT};
        direction dirOrigin[] = new direction[]{LEFT_RIGHT, UP_DOWN, UP_LEFT_DOWN_RIGHT, UP_RIGHT_DOWN_LEFT};
        int bestScoreIndex = 0;


        for (int i = 0; i < BEST_SCORE_WIDTH; i++) {
            int tests = 0;

                if (bestScoreO.score[i] == -1) {                                                                  //check for intit value
                    tests = 0;
                }
                else if (!fields.get(bestScoreO.X[i]).get(bestScoreO.Y[i]).isEmpty()) {                         //check if point is free
                    tests = 0;
                }
                else tests = 1;

                if (tests > 0) {
                    dirScore(fields, bestScoreO, "O", i, dirScore, dirOrigin);
                    for (int g = 0; g < dirScore.length; g++) {
                        if ((dirScore[0] == dirScore[g]) && dirScore[g] != 0) {
                            if (dirPossibility(fields,"O", bestScoreO.X[i], bestScoreO.Y[i], dirScore[g], dirOrigin[g])) {
                                finScore.add(new finalScore());
                                int lastIndex = finScore.size()-1;
                                finScore.get(lastIndex).X = bestScoreO.X[i];
                                finScore.get(lastIndex).Y = bestScoreO.Y[i];
                                finScore.get(lastIndex).score = dirScore[g];
                                finScore.get(lastIndex).dir = dirOrigin[g];
                                tests = 2;
                            }
                        } else if (dirScore[0] == 0){
                            tests = 0;
                            break;
                        }
                        else break;
                    }
                }

                if (tests > 1) {
                    List<List<Field>> items = new ArrayList<List<Field>>();                                         //create temp field
                    for (int j = 0; j < BORDER_X; j++) {
                        items.add(new ArrayList<Field>());
                        for (int k = 0; k < BORDER_Y; k++) {
                            items.get(j).add(new Field());
                        }
                    }
                    copyField(items, fields);                                                                       //copy field data

                    if (GameResult.checkWinner(items, "O", finScore.get(0).X, finScore.get(0).Y, FieldAdapter.playerCounter, bestScoreTmp, bestScoreTmp).equals("Prehral si!")) {        //check if it is final move
                        data[0] = finScore.get(0).X;
                        data[1] = finScore.get(0).Y;
                        return;
                    }

            }
        }

        List<finalScore> finScoreX = new ArrayList<finalScore>();

        for (int i = 0; i < BEST_SCORE_WIDTH; i++) {
            dirScore(fields, bestScoreX, "X", i, dirScore, dirOrigin);
            for (int g = 0; g < dirScore.length; g++) {
                if ((dirScore[0] == dirScore[g]) && dirScore[g] != 0) {
                    if (dirPossibility(fields,"X", bestScoreX.X[i], bestScoreX.Y[i], dirScore[g], dirOrigin[g])) {
                        finScoreX.add(new finalScore());
                        int lastIndex = finScoreX.size() - 1;
                        finScoreX.get(lastIndex).X = bestScoreX.X[i];
                        finScoreX.get(lastIndex).Y = bestScoreX.Y[i];
                        finScoreX.get(lastIndex).score = dirScore[g];
                        finScoreX.get(lastIndex).dir = dirOrigin[g];
                    }
                } else break;
            }
        }
        FieldScore.bubbleSort(finScoreX);

        if (finScore.size() == 0 && finScoreX.size() == 0){
            data[0] = bestScoreX.X[0];
            data[1] = bestScoreX.Y[0];
            return;
        }
        else if (finScore.size() == 0) {
            data[0] = bestScoreX.X[0];
            data[1] = bestScoreX.Y[0];
            return;
        }

        List<List<Field>> items = new ArrayList<List<Field>>();                                         //create temp field
        for (int j = 0; j < BORDER_X; j++) {
            items.add(new ArrayList<Field>());
            for (int k = 0; k < BORDER_Y; k++) {
                items.get(j).add(new Field());
            }
        }
        copyField(items, fields);
        items.get(finScore.get(0).X).get(finScore.get(0).Y).setPlayer("O");
        FieldScore.getFieldScore(items,"O", finScore.get(0).X, finScore.get(0).Y, bestScoreX, bestScoreTmp);

        FieldScore.bubbleSort(finScore);

        if (finScoreX.size() == 0) {
            data[0] = finScore.get(0).X;
            data[1] = finScore.get(0).Y;
        }
        else if ((bestScoreTmp.score[0]/10 > fields.get(finScoreX.get(0).X).get(finScoreX.get(0).Y).getScoreX()/10) && findScore(items,bestScoreTmp,finScore)) {
            data[0] = finScore.get(0).X;
            data[1] = finScore.get(0).Y;
        }
        else {
            data[0] = finScoreX.get(0).X;
            data[1] = finScoreX.get(0).Y;
        }

    }


    private void copyField(List<List<Field>> destField, List<List<Field>> sourceField) {
        for (int i = 0; i < BORDER_X; i++) {
            for (int j = 0; j < BORDER_Y; j++) {
                destField.get(i).get(j).setPlayer(sourceField.get(i).get(j).getPlayer());
                destField.get(i).get(j).setScoreX(sourceField.get(i).get(j).getScoreX());
                destField.get(i).get(j).setScoreO(sourceField.get(i).get(j).getScoreO());
                destField.get(i).get(j).setSurroundLeftCount(sourceField.get(i).get(j).getSurroundLeftCount());
                destField.get(i).get(j).setSurroundRightCount(sourceField.get(i).get(j).getSurroundRightCount());
                destField.get(i).get(j).setSurroundUpCount(sourceField.get(i).get(j).getSurroundUpCount());
                destField.get(i).get(j).setSurroundDownCount(sourceField.get(i).get(j).getSurroundDownCount());
                destField.get(i).get(j).setSurroundUpLeftCount(sourceField.get(i).get(j).getSurroundUpLeftCount());
                destField.get(i).get(j).setSurroundDownLeftCount(sourceField.get(i).get(j).getSurroundDownLeftCount());
                destField.get(i).get(j).setSurroundUpRightCount(sourceField.get(i).get(j).getSurroundUpRightCount());
                destField.get(i).get(j).setSurroundDownRightCount(sourceField.get(i).get(j).getSurroundDownRightCount());

                destField.get(i).get(j).setSurroundLeftPlayer(sourceField.get(i).get(j).getSurroundLeftPlayer());
                destField.get(i).get(j).setSurroundRightPlayer(sourceField.get(i).get(j).getSurroundRightPlayer());
                destField.get(i).get(j).setSurroundUpPlayer(sourceField.get(i).get(j).getSurroundUpPlayer());
                destField.get(i).get(j).setSurroundDownPlayer(sourceField.get(i).get(j).getSurroundDownPlayer());
                destField.get(i).get(j).setSurroundUpLeftPlayer(sourceField.get(i).get(j).getSurroundUpLeftPlayer());
                destField.get(i).get(j).setSurroundDownLeftPlayer(sourceField.get(i).get(j).getSurroundDownLeftPlayer());
                destField.get(i).get(j).setSurroundUpRightPlayer(sourceField.get(i).get(j).getSurroundUpRightPlayer());
                destField.get(i).get(j).setSurroundDownRightPlayer(sourceField.get(i).get(j).getSurroundDownRightPlayer());
            }
        }
    }

    private void dirScore(List<List<Field>> items, FieldAdapter.bestScore bestScoreTmp, String player, int index, int dirScore[], direction dir[]) {
        for (int i = 0; i < dir.length;i++)
            dirScore[i] = 0;

        dir[0] = LEFT_RIGHT;
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundLeftPlayer().equals(player))
            dirScore[0] += items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundLeftCount();
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundRightPlayer().equals(player))
            dirScore[0] += items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundRightCount();

        dir[1] = UP_DOWN;
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundUpPlayer().equals(player))
            dirScore[1] += items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundUpCount();
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundDownPlayer().equals(player))
            dirScore[1] += items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundDownCount();

        dir[2] = UP_LEFT_DOWN_RIGHT;
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundUpLeftPlayer().equals(player))
            dirScore[2] += items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundUpLeftCount();
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundDownRightPlayer().equals(player))
            dirScore[2] += items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundDownRightCount();

        dir[3] = UP_RIGHT_DOWN_LEFT;
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundUpRightPlayer().equals(player))
            dirScore[3] += items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundUpRightCount();
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundDownLeftPlayer().equals(player))
            dirScore[3] += items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundDownLeftCount();

        FieldScore.bubbleSort(dirScore, dir);
    }

    private boolean dirPossibility(List<List<Field>> fields,String player, int X, int Y, int dirScore, direction dirOrigin) {
        int emptyFields = 0, crrConst = 0, dirCount = 0;

        switch (dirOrigin) {

            case LEFT_RIGHT:

                if (fields.get(X).get(Y).getSurroundLeftPlayer().equals(player)) {
                    if ((dirCount = fields.get(X).get(Y).getSurroundLeftCount()) > 0)
                        crrConst = 1;
                }
                for (int j = (Y - dirCount) - crrConst; j >= 0; j--) {
                    if (fields.get(X).get(j).isEmpty() || fields.get(X).get(j).getPlayer().equals(player))
                        emptyFields += 1;
                    else break;
                }

                dirCount = 0;
                if (fields.get(X).get(Y).getSurroundRightPlayer().equals(player)) {
                    if ((dirCount = fields.get(X).get(Y).getSurroundRightCount()) > 0)
                        crrConst = 1;
                }
                for (int j = (Y + dirCount) + crrConst; j < BORDER_X; j++) {
                    if (fields.get(X).get(j).isEmpty() || fields.get(X).get(j).getPlayer().equals(player))
                        emptyFields += 1;
                    else break;
                }
                if (emptyFields + dirScore + 1 >= WINNIG_COUNT) return true;
                break;

            case UP_DOWN:
                if (fields.get(X).get(Y).getSurroundUpPlayer().equals(player)) {
                    if ((dirCount = fields.get(X).get(Y).getSurroundUpCount()) > 0)
                        crrConst = 1;
                }
                for (int j = (X - dirCount) - crrConst; j >= 0; j--) {
                    if (fields.get(j).get(Y).isEmpty() || fields.get(j).get(Y).getPlayer().equals(player))
                        emptyFields += 1;
                    else break;
                }

                dirCount = 0;
                if (fields.get(X).get(Y).getSurroundDownPlayer().equals(player)) {
                    if ((dirCount = fields.get(X).get(Y).getSurroundDownCount()) > 0)
                        crrConst = 1;
                }
                for (int j = (X + dirCount) + crrConst; j < BORDER_X; j++) {
                    if (fields.get(j).get(Y).isEmpty()  || fields.get(j).get(Y).getPlayer().equals(player))
                        emptyFields += 1;
                    else break;
                }
                if (emptyFields + dirScore + 1 >= WINNIG_COUNT) return true;
                break;

            case UP_LEFT_DOWN_RIGHT:
                if (fields.get(X).get(Y).getSurroundUpLeftPlayer().equals(player)) {
                    if ((dirCount = fields.get(X).get(Y).getSurroundUpLeftCount()) > 0)
                        crrConst = 1;
                }
                for (int j = (X - dirCount) - crrConst, k = (Y - dirCount) - crrConst; (j >= 0) && (k >= 0); j--, k--) {
                    if (fields.get(j).get(k).isEmpty() || fields.get(j).get(k).getPlayer().equals(player))
                        emptyFields += 1;
                    else break;
                }

                dirCount = 0;
                if (fields.get(X).get(Y).getSurroundDownRightPlayer().equals(player)) {
                    if ((dirCount = fields.get(X).get(Y).getSurroundDownRightCount()) > 0)
                        crrConst = 1;
                }
                for (int j = (X + dirCount) + crrConst, k = (Y + dirCount) + crrConst; ((j < BORDER_X) && (k < BORDER_Y)); j++, k++) {
                    if (fields.get(j).get(k).isEmpty() || fields.get(j).get(k).getPlayer().equals(player))
                        emptyFields += 1;
                    else break;
                }
                if (emptyFields + dirScore + 1>= WINNIG_COUNT) return true;
                break;

            case UP_RIGHT_DOWN_LEFT:

                if (fields.get(X).get(Y).getSurroundUpRightPlayer().equals(player)) {
                    if ((dirCount = fields.get(X).get(Y).getSurroundUpRightCount()) > 0)
                     crrConst = 1;
                }
                for (int j = (X - dirCount) - crrConst, k = (Y + dirCount) + crrConst; (j >= 0) && (k < BORDER_Y); j--, k++) {
                    if (fields.get(j).get(k).isEmpty() || fields.get(j).get(k).getPlayer().equals(player))
                    emptyFields += 1;
                    else break;
                }

                dirCount = 0;
                if (fields.get(X).get(Y).getSurroundDownLeftPlayer().equals(player)) {
                    if ((dirCount = fields.get(X).get(Y).getSurroundDownLeftCount()) > 0)
                        crrConst = 1;
                }
                for (int j = (X + dirCount) + crrConst, k = (Y - dirCount) - crrConst; ((j < BORDER_X) && (k >= 0)); j++, k--) {
                    if (fields.get(j).get(k).isEmpty()  || fields.get(j).get(k).getPlayer().equals(player))
                    emptyFields += 1;
                    else break;
                }
                if (emptyFields + dirScore + 1>= WINNIG_COUNT) return true;
                    break;

                    default:
                        return false;
        }
        return false;
    }

    private boolean findScore(List<List<Field>> fields, FieldAdapter.bestScore bestScore, List<finalScore> finScore) {
        switch (finScore.get(0).dir) {
            case LEFT_RIGHT:
                for (int i = 0; i < BEST_SCORE_WIDTH; i++) {
                    if (fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundLeftPlayer().equals("O")) {
                        if ((bestScore.Y[i] - fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundLeftCount() - 1 <= finScore.get(0).Y) && (finScore.get(0).X == bestScore.X[i]))
                            return true;
                    }
                    if (fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundRightPlayer().equals("O")) {
                        if ((bestScore.Y[i] + fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundRightCount() + 1 >= finScore.get(0).Y) && (finScore.get(0).X == bestScore.X[i]))
                            return true;
                    }
                }
                break;
            case UP_DOWN:
                for (int i = 0; i < BEST_SCORE_WIDTH; i++) {
                    if (fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundUpPlayer().equals("O")) {
                        if ((bestScore.X[i] - fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundUpCount() - 1 <= finScore.get(0).X) && (finScore.get(0).Y == bestScore.Y[i]))
                            return true;
                    }
                    if (fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundDownPlayer().equals("O")) {
                        if ((bestScore.X[i] + fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundDownCount() + 1 >= bestScore.X[i]) && (finScore.get(0).Y == bestScore.Y[i]))
                            return true;
                    }
                }
                break;
            case UP_LEFT_DOWN_RIGHT:
                for (int i = 0; i < BEST_SCORE_WIDTH; i++) {
                    if (fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundUpLeftPlayer().equals("O")) {
                        if ((bestScore.X[i]- fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundUpLeftCount() - 1 <= finScore.get(0).X)
                                && ((bestScore.Y[i] - fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundUpLeftCount() - 1 <= finScore.get(0).Y)))
                            return true;
                    }
                    if (fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundDownRightPlayer().equals("O")) {
                        if ((bestScore.X[i] + fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundDownRightCount() + 1 >= finScore.get(0).X)
                                && ((bestScore.Y[i] + fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundDownRightCount() + 1 >= finScore.get(0).Y)))
                            return true;
                    }
                }
                break;
            case UP_RIGHT_DOWN_LEFT:
                for (int i = 0; i < BEST_SCORE_WIDTH; i++) {
                    if (fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundDownLeftPlayer().equals("O")) {
                        if ((bestScore.X[i] + fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundDownLeftCount() + 1 >= finScore.get(0).X)
                                && ((bestScore.Y[i] - fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundDownLeftCount() - 1 <= finScore.get(0).Y)))
                            return true;
                    }
                    if (fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundUpRightPlayer().equals("O")) {
                        if ((bestScore.X[i] - fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundUpRightCount() - 1 <= finScore.get(0).X)
                                && ((bestScore.Y[i] + fields.get(bestScore.X[i]).get(bestScore.Y[i]).getSurroundUpRightCount() + 1 >= finScore.get(0).Y)))
                            return true;
                    }
                }
                break;
            default: return false;
        }
        return false;
    }

    public class finalScore {
        public int X = 0;
        public int Y = 0;
        public int score = 0;
        public direction dir = UP_DOWN;
    };
}







