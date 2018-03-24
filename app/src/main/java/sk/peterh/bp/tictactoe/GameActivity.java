package sk.peterh.bp.tictactoe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static ConstatntPackage.Constant.BORDER_X;
import static ConstatntPackage.Constant.BORDER_Y;

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
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(mContext, 8 ); //Utility.calculateNoOfColumns(context));
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new FieldAdapter(mContext, items, new RandomAI());
        mRecyclerView.setAdapter(mAdapter);

    }

}
