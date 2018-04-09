package sk.peterh.bp.tictactoe;


import java.util.List;
import java.util.Random;

import static ConstatntPackage.Constant.BORDER_X;
import static ConstatntPackage.Constant.BORDER_Y;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */
public class RandomAI implements AI {

    @Override
    public void getMoveOfAI(List<List<Field>> fields, int lastPositionX, int lastPositionY, int[] data) {
        int x = lastPositionX,y = lastPositionY;
        int counter = 0;
        do {
            Random rand = new Random();
            int nextPoint = rand.nextInt(7);
            if (counter < 20) {

                switch (nextPoint) {
                    case 0:
                        x = lastPositionX;
                        y = lastPositionY + 1;
                        break;
                    case 1:
                        x = lastPositionX;
                        y = lastPositionY - 1;
                        break;
                    case 2:
                        x = lastPositionX + 1;
                        y = lastPositionY;
                        break;
                    case 3:
                        x = lastPositionX - 1;
                        y = lastPositionY;
                        break;
                    case 4:
                        x = lastPositionX + 1;
                        y = lastPositionY + 1;
                        break;
                    case 5:
                        x = lastPositionX - 1;
                        y = lastPositionY - 1;
                        break;
                    case 6:
                        x = lastPositionX + 1;
                        y = lastPositionY - 1;
                        break;
                    case 7:
                        x = lastPositionX - 1;
                        y = lastPositionY + 1;
                        break;
                }
            }
            else {
                x = rand.nextInt(7);
                y = rand.nextInt(7);
            }
            counter += 1;
            if (!this.isInBorder(x,y)) {
                x = lastPositionX;
                y = lastPositionY;
            }

        } while (!fields.get(x).get(y).isEmpty());

        data[0] = x;
        data[1] = y;
    }

    @Override
    public void getMoveOfAI(List<List<Field>> fields, FieldAdapter.bestScore bestScoreX, FieldAdapter.bestScore bestScoreO, int[] data) {

    }

    private boolean isInBorder(int X, int Y) {
        if ((X < 0 || X > BORDER_X - 1) || (Y < 0 || Y > BORDER_Y - 1)) return false;
        else return true;
    }
}