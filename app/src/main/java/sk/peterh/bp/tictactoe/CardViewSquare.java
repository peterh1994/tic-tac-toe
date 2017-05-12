package sk.peterh.bp.tictactoe;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */
public class CardViewSquare extends CardView {
    public CardViewSquare(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CardViewSquare(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public CardViewSquare(Context context) {

        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
