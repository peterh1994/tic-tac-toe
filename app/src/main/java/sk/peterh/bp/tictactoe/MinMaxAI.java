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
        int dirScore[] = new int[] {0,0,0,0};
        direction dir[] = new direction[] {LEFT_RIGHT, UP_DOWN, UP_LEFT_DOWN_RIGHT, UP_RIGHT_DOWN_LEFT};
        int besScoreIndex = 0;


        for (int i = 0; i < BEST_SCORE_WIDTH; i++) {                                                        //check for init value
            if (bestScoreO.score[i] == -1) {
                data[0] = bestScoreX.X[i];
                data[1] = bestScoreX.Y[i];
                return;
                //TODO: use random generator
            }
        }

        //TODO: Check every best score
        if (!fields.get(bestScoreO.X[0]).get(bestScoreO.Y[0]).isEmpty()) besScoreIndex += 1;
            bestScoreTmp.X[0] = bestScoreO.X[besScoreIndex];
            bestScoreTmp.Y[0] = bestScoreO.Y[besScoreIndex];
            bestScoreTmp.score[0] = bestScoreO.score[besScoreIndex];

        List<List<Field>> items = new ArrayList<List<Field>>();
        for (int i = 0; i < BORDER_X; i++) {
            items.add(new ArrayList<Field>());
            for (int j = 0; j < BORDER_Y; j++) {
                items.get(i).add(new Field());
            }
        }
        copyField(items, fields);

/*        items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).setPlayer("O");
        if (GameResult.checkWinner(items,"O",bestScoreTmp.X[0],bestScoreTmp.Y[0],FieldAdapter.playerCounter,bestScoreTmp, bestScoreTmp).equals("Vyhral si!")) {
            data[0] = bestScoreO.X[besScoreIndex];
            data[1] = bestScoreO.Y[besScoreIndex];
            return;
        }*/
        if (!items.get(bestScoreX.X[0]).get(bestScoreX.Y[0]).isEmpty()) {
            bestScoreX.score[0] = bestScoreX.score[1];
            bestScoreX.X[0] = bestScoreX.X[1];
            bestScoreX.Y[0] = bestScoreX.Y[1];
        }
        if (bestScoreTmp.score[0] > bestScoreX.score[0]) {
            dirScore(items, bestScoreTmp, dirScore,"O",0, dir);
            for (int i = 0; i < dirScore.length; i++) {
                if (dirScore[0] == dirScore[i]) {
                    int emptyFields = 0, crrConst = 0, dirCount = 0;
                    switch(dir[i]) {
                        case LEFT_RIGHT:

                            if (items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundLeftPlayer().equals("O")) {
                                if ((dirCount = items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundLeftCount()) > 0)
                                    crrConst = 1;
                            }
                            for (int j = (bestScoreTmp.Y[0] - dirCount) - crrConst; j >= 0; j--) {
                                if (items.get(bestScoreTmp.X[0]).get(j).isEmpty()) emptyFields +=1;
                                else break;
                            }

                            if (items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundRightPlayer().equals("O")) {
                                if ((dirCount = items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundRightCount()) > 0)
                                    crrConst = 1;
                            }
                            for (int j = (bestScoreTmp.Y[0] + dirCount) + crrConst; j < BORDER_X; j++) {
                                if (items.get(bestScoreTmp.X[0]).get(j).isEmpty()) emptyFields +=1;
                                else break;
                            }
                            if (emptyFields + dirScore[i] >= WINNIG_COUNT) {
                                data[0] = bestScoreO.X[besScoreIndex];
                                data[1] = bestScoreO.Y[besScoreIndex];
                                break;
                            }
                            break;

                        case UP_DOWN:
                            if (items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundUpPlayer().equals("O")) {
                                if ((dirCount = items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundUpCount()) > 0)
                                    crrConst = 1;
                            }
                            for (int j = (bestScoreTmp.X[0] - dirCount) - crrConst; j >= 0; j--) {
                                if (items.get(j).get(bestScoreTmp.Y[0]).isEmpty()) emptyFields +=1;
                                else break;
                            }

                            if (items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundDownPlayer().equals("O")) {
                                if ((dirCount = items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundDownCount()) > 0)
                                    crrConst = 1;
                            }
                            for (int j = (bestScoreTmp.X[0] + dirCount) + crrConst; j < BORDER_X; j++) {
                                if (items.get(j).get(bestScoreTmp.Y[0]).isEmpty()) emptyFields +=1;
                                else break;
                            }
                            if (emptyFields + dirScore[i] >= WINNIG_COUNT) {
                                data[0] = bestScoreO.X[besScoreIndex];
                                data[1] = bestScoreO.Y[besScoreIndex];
                                break;
                            }
                            break;

                        case UP_LEFT_DOWN_RIGHT:
                            if (items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundUpLeftPlayer().equals("O")) {
                                if ((dirCount = items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundUpLeftCount()) > 0)
                                    crrConst = 1;
                            }
                            for (int j = (bestScoreTmp.X[0] - dirCount)- crrConst, k = (bestScoreTmp.Y[0] - dirCount) - crrConst; (j >= 0) && (k >= 0); j--, k--) {
                                if (items.get(j).get(k).isEmpty()) emptyFields +=1;
                                else break;
                            }

                            if (items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundDownRightPlayer().equals("O")) {
                                if ((dirCount = items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundDownRightCount()) > 0)
                                    crrConst = 1;
                            }
                            for (int j = (bestScoreTmp.X[0] + dirCount) + crrConst, k = (bestScoreTmp.Y[0] + dirCount) + crrConst; ((j < BORDER_X) && (k < BORDER_Y)); j++, k++) {
                                if (items.get(j).get(k).isEmpty()) emptyFields +=1;
                                else break;
                            }
                            if (emptyFields + dirScore[i] >= WINNIG_COUNT) {
                                data[0] = bestScoreO.X[besScoreIndex];
                                data[1] = bestScoreO.Y[besScoreIndex];
                                break;
                            }
                            break;


                        case UP_RIGHT_DOWN_LEFT:

                            if (items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundUpRightPlayer().equals("O")) {
                                if ((dirCount = items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundUpRightCount()) > 0)
                                    crrConst = 1;
                            }
                            for (int j = (bestScoreTmp.X[0] + dirCount) + crrConst, k = (bestScoreTmp.Y[0] - dirCount) - crrConst; (j >= 0) && (k >= 0); j++, k--) {
                                if (items.get(j).get(k).isEmpty()) emptyFields +=1;
                                else break;
                            }

                            if (items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundDownLeftPlayer().equals("O")) {
                                if ((dirCount = items.get(bestScoreTmp.X[0]).get(bestScoreTmp.Y[0]).getSurroundDownLeftCount()) > 0)
                                    crrConst = 1;
                            }
                            for (int j = (bestScoreTmp.X[0] - dirCount)- crrConst, k = (bestScoreTmp.Y[0] + dirCount) + crrConst; ((j < BORDER_X) && (k < BORDER_Y)); j--, k++) {
                                if (items.get(j).get(k).isEmpty()) emptyFields +=1;
                                else break;
                            }
                            if (emptyFields + dirScore[i] >= WINNIG_COUNT) {
                                data[0] = bestScoreO.X[besScoreIndex];
                                data[1] = bestScoreO.Y[besScoreIndex];
                                break;
                            }
                            break;
                    }
                }
            }
        }
        else {
            data[0] = bestScoreX.X[0];
            data[1] = bestScoreX.Y[0];
            return;
        }
    }


    private void copyField(List<List<Field>> destField, List<List<Field>> sourceField) {
        for(int i = 0; i < BORDER_X; i++){
            for(int j = 0; j < BORDER_Y; j++) {
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

    private void dirScore (List<List<Field>> items,FieldAdapter.bestScore bestScoreTmp, int dirScore[],String player, int index, direction dir[]) {
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundLeftPlayer().equals(player))
            dirScore[0] += items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundLeftCount();
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundRightPlayer().equals(player))
            dirScore[0] = items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundRightCount();

        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundUpPlayer().equals(player))
            dirScore[1] += items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundUpCount();
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundDownPlayer().equals(player))
            dirScore[1] = items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundDownCount();

        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundUpLeftPlayer().equals(player))
            dirScore[2] += items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundUpLeftCount();
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundDownRightPlayer().equals(player))
            dirScore[2] = items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundDownRightCount();

        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundUpRightPlayer().equals(player))
            dirScore[3] += items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundUpRightCount();
        if (items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundDownLeftPlayer().equals(player))
            dirScore[3] = items.get(bestScoreTmp.X[index]).get(bestScoreTmp.Y[index]).getSurroundDownLeftCount();

        FieldScore.bubbleSort(dirScore, dir);
    }
}




