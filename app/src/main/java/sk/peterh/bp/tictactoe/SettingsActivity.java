package sk.peterh.bp.tictactoe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnKeyListener;
import android.view.View;
import android.view.KeyEvent;

import static ConstatntPackage.Constant.BORDER_X;
import static ConstatntPackage.Constant.BORDER_Y;
import static ConstatntPackage.Constant.WINNIG_COUNT;

/**
 * Created by Peter.Hajlak on 28.4.2017.
 */

public class SettingsActivity extends AppCompatActivity {

    TextView tvBS,tvWC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Nastavenia");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvBS = (TextView)(findViewById(R.id.BorderSizeInput));
        tvWC = (TextView)(findViewById(R.id.WinningCountOutput));
        tvBS.setText(String.valueOf(BORDER_X));
        tvWC.setText(String.valueOf(WINNIG_COUNT));

        tvBS.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    int i = 0;
                    String text = tvBS.getText().toString();
                    if(!text.equals("")) {
                        i = Integer.parseInt(text);
                        if (i >= 3 && i < 15) {
                            BORDER_X = i;
                            BORDER_Y = i;
                            if (i == 3) WINNIG_COUNT = (int) (3);
                            else if (i == 4) WINNIG_COUNT = (int) (4);
                            else WINNIG_COUNT = (int) (5);
                            tvWC.setText(String.valueOf(WINNIG_COUNT));
                        }
                        else if (i < 3) {
                            tvBS.setText(String.valueOf(3));
                            tvWC.setText(String.valueOf(3));
                        }
                        else {
                            tvBS.setText(String.valueOf(15));
                            tvWC.setText(String.valueOf(5));
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        tvBS.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String text = tvBS.getText().toString();
                if(!text.equals("")) {
                    int i = Integer.parseInt(text);
                    if (i == 3) tvWC.setText(String.valueOf(3));
                    else if (i == 4) tvWC.setText(String.valueOf(4));
                    else tvWC.setText(String.valueOf(5));
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


        });

    }

}
