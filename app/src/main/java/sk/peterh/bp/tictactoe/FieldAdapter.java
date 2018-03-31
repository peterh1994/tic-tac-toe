package sk.peterh.bp.tictactoe;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static ConstatntPackage.Constant.BORDER_X;
import static ConstatntPackage.Constant.BORDER_Y;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */
public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.ViewHolder>  {

    public Context context;
    public List<List<Field>> fields;
    public AI computer;

    private int mCounter = 1;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;

        public ViewHolder(View v){
            super(v);
            mCardView = (CardView) v.findViewById(R.id.item_card_view);
            mTextView = (TextView) v.findViewById(R.id.text_view);
        }
    }

    public FieldAdapter(Context context, List<List<Field>> fields, AI computer) {
        this.context = context;
        this.fields = fields;
        this.computer = computer;
    }


    @Override
    public FieldAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false);
        context = parent.getContext();
        // Get the TextView reference from RecyclerView current item
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final int squareSize = (int)(displayMetrics.widthPixels / 10);
        final TextView textView = (TextView) v.findViewById(R.id.text_view);
        textView.setWidth(squareSize);
        textView.setHeight(squareSize);
        // Set a click listener for the current item of RecyclerView
        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the RecyclerView current item serial and text
                int X = vh.getAdapterPosition()/BORDER_X;
                int Y = vh.getAdapterPosition()%BORDER_Y;
                if (fields.get(X).get(Y).isEmpty()) {
                    fields.get(X).get(Y).setPlayer("X");
                    notifyDataSetChanged();
                    String result = GameResult.checkWinner(fields, "X", X, Y);
                    if (!result.isEmpty()) {
                        resultMessage(result);
                    } else {
                        int[] data = new int[2];
                        computer.getMoveOfAI(fields, X, Y, data);
                        fields.get(data[0]).get(data[1]).setPlayer("O");
                        notifyDataSetChanged();
                       String result2 = GameResult.checkWinner(fields, "O", data[0], data[1]);
                        if (!result2.isEmpty()) {
                            resultMessage(result2);
                        }
                    }
                }
            }
        });
        // Return the ViewHolder
        return vh;
    }

    @Override
    public void onBindViewHolder(FieldAdapter.ViewHolder holder, int position) {
        // Get the current item from the data set
        int X = position/BORDER_X;
        int Y = position%BORDER_Y;
        String string = fields.get(X).get(Y).getPlayer();

        // Set the TextView widgets text
        holder.mTextView.setText(string);
        holder.mTextView.setTextColor(fields.get(X).get(Y).getColor());
        // Increase the counter
        mCounter +=1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
       // return fields.size();
       // int fieldSize = 0;
        //for(int i=0; i< 8; i++)
      //      fieldSize += fields.get(i).size();
        //return fieldSize;
        return BORDER_X*BORDER_Y;
    }

    public void setItem(int positionX,int positionY, String player) {
        fields.get(positionX).get(positionY).setPlayer(player);
    }

    private void resultMessage(String result){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        // set title
        alertDialogBuilder.setTitle(result);

        // set dialog message
        alertDialogBuilder
//                .setMessage(result)
                .setCancelable(false)
                .setPositiveButton("Návrat do menu",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close current activity
                        if(context instanceof Activity){
                            ((Activity)context).finish();
                        }
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
}
