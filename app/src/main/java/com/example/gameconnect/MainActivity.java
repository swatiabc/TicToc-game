package com.example.gameconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0: red , 1:yellow, 2:empty

    int[][] winArr = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[] position ={2,2,2,2,2,2,2,2,2};
    int action=0,count=0;
    boolean play=true;

    public void game(View view){

        ImageView counter = (ImageView) view;
        int i=Integer.parseInt(counter.getTag().toString());
        //int i= Integer.parseInt(counter.getTag().toString());

        if(play&&position[i]==2&&count!=9) {

            counter.setTranslationY(-1500);
            if (action == 0) {
                counter.setImageResource(R.drawable.red);
                action = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                action = 0;
            }
            position[i]=action;
            count++;
            counter.animate().translationYBy(1500).setDuration(300);


            for (int[] arr : winArr) {

                if (position[arr[0]] == position[arr[1]] && position[arr[1]] == position[arr[2]] && position[arr[0]]!=2) {
                    play = false;
                    String msg = "";

                    if (action == 1) {
                        msg = "red is winner";
                    } else {
                        msg = "yellow is winner";
                    }

                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                    Button button = (Button)findViewById(R.id.button);
                    button.setVisibility(View.VISIBLE);
                }
             else if(count==9 && play){
                    Toast.makeText(this, "this is a tie", Toast.LENGTH_LONG).show();
                    Button button = (Button)findViewById(R.id.button);
                    button.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){

        Button button = (Button)findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.GridLayout1);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }

        for(int i=0;i<9;i++)
            position[i]=2;
         action=0;
         play=true;
         count=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
