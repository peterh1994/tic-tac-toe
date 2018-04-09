package sk.peterh.bp.tictactoe;

import java.util.ArrayList;
import java.util.List;

import static ConstatntPackage.Constant.BORDER_X;
import static ConstatntPackage.Constant.BORDER_Y;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */

public class MinMaxAI implements AI {
    @Override
    public void getMoveOfAI(List<List<Field>> fields, int lastPositionX, int lastPositionY, int[] data) {

    }

    @Override
    public  void getMoveOfAI(List<List<Field>> fields, FieldAdapter.bestScore bestScoreX, FieldAdapter.bestScore bestScoreO, int[] data) {

        List<List<Field>> items = new ArrayList<List<Field>>();                                                     //copy array
        for(int i = 0; i < BORDER_X; i++){
            items.add(new ArrayList<Field>());
            for(int j = 0; j < BORDER_Y; j++) {
                items.get(i).add(new Field());
                items.get(i).get(j).setPlayer(fields.get(i).get(j).getPlayer());
                items.get(i).get(j).setScoreX(fields.get(i).get(j).getScoreX());
                items.get(i).get(j).setScoreO(fields.get(i).get(j).getScoreO());
                items.get(i).get(j).setSurroundLeftCount(fields.get(i).get(j).getSurroundLeftCount());
                items.get(i).get(j).setSurroundLeftPlayer(fields.get(i).get(j).getSurroundLeftPlayer());
                items.get(i).get(j).setSurroundRightCount(fields.get(i).get(j).getSurroundRightCount());
                items.get(i).get(j).setSurroundRightPlayer(fields.get(i).get(j).getSurroundRightPlayer());
                items.get(i).get(j).setSurroundUpCount(fields.get(i).get(j).getSurroundUpCount());
                items.get(i).get(j).setSurroundUpPlayer(fields.get(i).get(j).getSurroundUpPlayer());
                items.get(i).get(j).setSurroundDownCount(fields.get(i).get(j).getSurroundDownCount());
                items.get(i).get(j).setSurroundDownPlayer(fields.get(i).get(j).getSurroundDownPlayer());
                items.get(i).get(j).setSurroundUpLeftCount(fields.get(i).get(j).getSurroundUpLeftCount());
                items.get(i).get(j).setSurroundUpLeftPlayer(fields.get(i).get(j).getSurroundUpLeftPlayer());
                items.get(i).get(j).setSurroundUpRightCount(fields.get(i).get(j).getSurroundUpRightCount());
                items.get(i).get(j).setSurroundUpRightPlayer(fields.get(i).get(j).getSurroundUpRightPlayer());
                items.get(i).get(j).setSurroundDownCount(fields.get(i).get(j).getSurroundDownLeftCount());
                items.get(i).get(j).setSurroundDownLeftPlayer(fields.get(i).get(j).getSurroundDownLeftPlayer());
                items.get(i).get(j).setSurroundDownRightCount(fields.get(i).get(j).getSurroundDownRightCount());
                items.get(i).get(j).setSurroundDownRightPlayer(fields.get(i).get(j).getSurroundDownRightPlayer());
            }
        }


        if (bestScoreO.score[0] > bestScoreX.score[0]) {
            data[0] = bestScoreO.X[0];
            data[1] = bestScoreO.Y[0];
        }
        else {
            data[0] = bestScoreX.X[0];
            data[1] = bestScoreX.Y[0];
        }
    }
}
