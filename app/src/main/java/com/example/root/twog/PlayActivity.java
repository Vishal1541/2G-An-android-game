package com.example.root.twog;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    Integer selection[] = new Integer[3], insertion[] = new Integer[5], random[] = new Integer[3];
    TextView sel0, sel1, sel2, ins0, ins1, ins2, ins3, ins4;
    TextView selArr[] = new TextView[3], insArr[] = new TextView[5];
    int ran = new Random().nextInt(3), active = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        random[0] = 2;
        random[1] = 4;
        random[2] = 8;

        sel0 = (TextView)findViewById(R.id.show0);
        sel1 = (TextView)findViewById(R.id.show1);
        sel2 = (TextView)findViewById(R.id.show2);

        ins0 = (TextView)findViewById(R.id.zero);
        ins1 = (TextView)findViewById(R.id.one);
        ins2 = (TextView)findViewById(R.id.two);
        ins3 = (TextView)findViewById(R.id.three);
        ins4 = (TextView)findViewById(R.id.four);

        sel0.setText(Integer.toString(random[new Random().nextInt(3)]));
        sel1.setText(Integer.toString(random[new Random().nextInt(3)]));
        sel2.setText(Integer.toString(random[new Random().nextInt(3)]));

        selArr[0] = sel0;
        selArr[1] = sel1;
        selArr[2] = sel2;

        insArr[0] = ins0;
        insArr[1] = ins0;
        insArr[2] = ins0;
        insArr[3] = ins0;
        insArr[4] = ins0;

        for(int i=0; i<5; i++){
            insertion[i] = 0;
        }
        getSelectedValues();
    }

    public void resetAllSelColor(){
        for(int i=0; i<3; i++){
            selArr[i].setBackgroundColor(Color.parseColor("#efefef"));
        }
    }

    public void getSelectedValues(){
        for(int i=0; i<3; i++){
            selection[i] = Integer.parseInt(selArr[i].getText().toString());
        }
    }

    public void getInsertionValues(){
        for(int i=0; i<5; i++){
            if(insArr[i] == null){
                insertion[i] = 0;
            }
            else{
                insertion[i] = Integer.parseInt(insArr[i].getText().toString());
            }
        }
    }

    public void onClickSel0(View view){
        resetAllSelColor();
        if(active != 0) {
            selArr[0].setBackgroundColor(Color.parseColor("#67d83e"));
            active = 0;
        }
        else{
            selArr[0].setBackgroundColor(Color.parseColor("#efefef"));
            active = -1;
        }
    }

    public void onClickSel1(View view){
        resetAllSelColor();
        if(active != 1) {
            selArr[1].setBackgroundColor(Color.parseColor("#67d83e"));
            active = 1;
        }
        else{
            selArr[1].setBackgroundColor(Color.parseColor("#efefef"));
            active = -1;
        }
    }

    public void onClickSel2(View view){
        resetAllSelColor();
        if(active != 2) {
            selArr[2].setBackgroundColor(Color.parseColor("#67d83e"));
            active = 2;
        }
        else{
            selArr[2].setBackgroundColor(Color.parseColor("#efefef"));
            active = -1;
        }
    }

    public void onClickIns0(View view){
        if(insertion[0] == 0){
            insertion[0] = selection[0];
            insArr[0].setText(Integer.toString(insertion[0]));
        }
        else if(insertion[0] == selection[0]){
            insertion[0] *= 2;
            insArr[0].setText(Integer.toString(insertion[0]));
        }
        else{
            resetAllSelColor();
            Toast.makeText(getApplicationContext(),"Numbers do not match!",Toast.LENGTH_SHORT).show();
        }
    }
}
