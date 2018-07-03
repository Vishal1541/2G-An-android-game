package com.example.root.twog;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.sql.Time;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    Integer selection[] = new Integer[3], insertion[] = new Integer[5], random[] = new Integer[3], score = 0, selChange[] = new Integer[3];
    TextView sel0, sel1, sel2, ins0, ins1, ins2, ins3, ins4, tvscore;
    TextView selArr[] = new TextView[3], insArr[] = new TextView[5];
    int active = -1;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        random[0] = 2;
        random[1] = 4;
        random[2] = 8;

        tvscore = findViewById(R.id.tvScore);
        score = 2;

        sel0 = findViewById(R.id.show0);
        sel1 = findViewById(R.id.show1);
        sel2 = findViewById(R.id.show2);

        ins0 = findViewById(R.id.zero);
        ins1 = findViewById(R.id.one);
        ins2 = findViewById(R.id.two);
        ins3 = findViewById(R.id.three);
        ins4 = findViewById(R.id.four);

        int idx;
        idx = rand.nextInt(3);
        sel0.setText(Integer.toString(random[idx]));
        idx = rand.nextInt(3);
        sel1.setText(Integer.toString(random[idx]));
        idx = rand.nextInt(3);
        sel2.setText(Integer.toString(random[idx]));

        selArr[0] = sel0;
        selArr[1] = sel1;
        selArr[2] = sel2;

        insArr[0] = ins0;
        insArr[1] = ins1;
        insArr[2] = ins2;
        insArr[3] = ins3;
        insArr[4] = ins4;

        idx = rand.nextInt(5);
        for(int i=0; i<5; i++){
            insertion[i] = 0;
        }
        insertion[idx] = 2;
        insArr[idx].setText(Integer.toString(insertion[idx]));
        tvscore.setText(Integer.toString(insertion[idx]));
        for(int i=0; i<3; i++){
            selection[i] = Integer.parseInt(selArr[i].getText().toString());
        }

        Toast.makeText(getApplicationContext(),"Start",Toast.LENGTH_SHORT).show();
    }

    public String trueInsColor(int i){
        String color = null;
        switch (i){
            case 0: color = "#c9e8ff"; break;
            case 1: color = "#fff7c9"; break;
            case 2: color = "#caf7db"; break;
            case 3: color = "#e2caf7"; break;
            case 4: color = "#f2cbd9"; break;
        }
        return color;
    }

    public void changeSelBlockActive(){
        int sum = 0, distinct = 0, avg, temp, bits = 0;
        for(int i=0; i<5; i++){
            if(insertion[i] != 0){
                sum += insertion[i];
                distinct++;
            }
        }
        for(int i=0; i<3; i++){
            if(i != active){
                sum += selection[i];
                distinct++;
            }
        }
        avg = sum / distinct;
        temp = avg;
        while(temp != 0){
            temp >>= 1;
            bits++;
        }
        avg = 1 << bits;
        selChange[0] = avg;
        avg <<= 1;
        selChange[1] = avg;
        avg >>= 2;
        selChange[2] = avg;
        int idx = rand.nextInt(3);
        selection[active] = selChange[idx];
        selArr[active].setText(Integer.toString(selection[active]));
        return;
    }

    public void changeColorsSel(final int i, String rgb){
        Handler handler = new Handler();
        final String color = rgb;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                insArr[i].setBackgroundColor(Color.parseColor(color));
            }
        },800);
    }

    public void updateInsBlocks(){
        for(int i=0; i<5; i++){
            if(insertion[i] == 0){
                insArr[i].setText("");
            }
            else{
                insArr[i].setText(Integer.toString(insertion[i]));
            }
        }
        for(int i=0; i<4; i++){
            if(insertion[i] == insertion[i+1] && insertion[i] != 0){
                changeColorsSel(i, "#67d83e");
                changeColorsSel(i, "#67d83e");
                insertion[i] *= 2;
                insertion[i+1] = 0;
                insArr[i].setText(Integer.toString(insertion[i]));
                insArr[i+1].setText("");
                i = -1;
            }
        }
        return;
    }

    public void updateScore(){
        score = 0;
        for(int i=0; i<5; i++){
            score += insertion[i];
        }
        return;
    }

    public void pickUpSel(int i){
        if(active != i) {
            selArr[i].setBackgroundColor(Color.parseColor("#67d83e"));
            active = i;
        }
        else{
            selArr[i].setBackgroundColor(Color.parseColor("#efefef"));
            active = -1;
        }
        return;
    }

    public void resetAllSelColor(){
        for(int i=0; i<3; i++){
            selArr[i].setBackgroundColor(Color.parseColor("#efefef"));
        }
        return;
    }

    public void getSelectedValues(){
        for(int i=0; i<3; i++){
            selection[i] = Integer.parseInt(selArr[i].getText().toString());
        }
        return;
    }

    public boolean gameOver(){
        for(int i=0; i<5; i++){
            if(insertion[i] == 0){
                return false;
            }
        }
        return true;
    }

    public void onClickInsUtil(int i){
        if(active == -1){
            Toast.makeText(getApplicationContext(),"Please select any one amonog the three selection blocks",Toast.LENGTH_SHORT).show();
            return;
        }
        boolean changed = false;
        if(insertion[i] == 0){
            insertion[i] = selection[active];
            insArr[i].setText(Integer.toString(insertion[i]));
            changed = true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Block already filled!",Toast.LENGTH_SHORT).show();
        }
        if(changed == true){
            updateInsBlocks();
            updateScore();
            tvscore.setText(Integer.toString(score));
            changeSelBlockActive();
        }
        active = -1;
        resetAllSelColor();
        for(int idx=0; idx<5; idx++){
            String color = trueInsColor(idx);
            changeColorsSel(idx,color);
        }
        if(gameOver()){
//            Toast.makeText(getApplicationContext(),"GAME OVER",Toast.LENGTH_LONG).show();
            AlertDialog.Builder dia_rules = new AlertDialog.Builder(this);
            String rules = "Bingo! You just scored" + Integer.toString(score);
            dia_rules.setMessage(rules);
            dia_rules.setTitle("Your Score");
            dia_rules.setCancelable(true);
            dia_rules.create().show();
        }
        return;
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
        return;
    }

    public void onClickSel0(View view){
        resetAllSelColor();
        pickUpSel(0);
        return;
    }

    public void onClickSel1(View view){
        resetAllSelColor();
        pickUpSel(1);
        return;
    }

    public void onClickSel2(View view){
        resetAllSelColor();
        pickUpSel(2);
        return;
    }

    public void onClickIns0(View view){
        onClickInsUtil(0);
        return;
    }
    public void onClickIns1(View view){
        onClickInsUtil(1);
        return;
    }
    public void onClickIns2(View view){
        onClickInsUtil(2);
        return;
    }
    public void onClickIns3(View view){
        onClickInsUtil(3);
        return;
    }
    public void onClickIns4(View view){
        onClickInsUtil(4);
        return;
    }
}
