package sk.peterh.bp.tictactoe;


import java.util.List;
import java.util.Random;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */
public class RandomAI implements AI {

    @Override
    public int getMoveOfAI(List<Field> fields, int lastPosition) {
        int position = -1;
        while(position < 0 || position > 64 || !fields.get(position).isEmpty()) {
            Random rand = new Random();
            int x = rand.nextInt(3) - 1;
            int y = (rand.nextInt(3) - 1) * 8;
            position = lastPosition + x + y;
        }
        return position;
    }
}
