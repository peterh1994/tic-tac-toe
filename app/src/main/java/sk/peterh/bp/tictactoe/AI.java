package sk.peterh.bp.tictactoe;


import java.util.List;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */
public interface AI {

    public void getMoveOfAI(List<List<Field>> fields, int lastPositionX, int lastPositionY, int[] data);
    public void getMoveOfAI(List<List<Field>> fields, FieldAdapter.bestScore bestScoreX, FieldAdapter.bestScore bestScoreO, int[] data);


}

