package sk.peterh.bp.tictactoe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static ConstatntPackage.Constant.BORDER_X;
import static ConstatntPackage.Constant.BORDER_Y;
import static ConstatntPackage.Constant.WINNIG_COUNT;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */

public class GameActivity extends AppCompatActivity {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        List<List<Field>> items = new ArrayList<List<Field>>();
        for(int i = 0; i < BORDER_X; i++){
            items.add(new ArrayList<Field>());
            for(int j = 0; j < BORDER_Y; j++) {
                items.get(i).add(new Field());
                }
            }


        getSupportActionBar().setTitle("Game");
        TextView tvGH = (TextView)(findViewById(R.id.gameHint));
        tvGH.setText("Získaj " + String.valueOf(WINNIG_COUNT) + "-krát 'X' veďla seba v rade, stĺpci alebo diagonále.");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(mContext, (BORDER_X+BORDER_Y)/2 ); //Utility.calculateNoOfColumns(context)); //Size of grid X*Y
        mRecyclerView.setLayoutManager(mLayoutManager);


        //mAdapter = new FieldAdapter(mContext, items, new RandomAI());
        mAdapter = new FieldAdapter(mContext, items, new MinMaxAI());
        mRecyclerView.setAdapter(mAdapter);

    }

}
