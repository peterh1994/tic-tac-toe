package sk.peterh.bp.tictactoe;


import java.util.List;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */
public interface AI {

    public int getMoveOfAI(List<Field> fields, int lastPosition);
}
