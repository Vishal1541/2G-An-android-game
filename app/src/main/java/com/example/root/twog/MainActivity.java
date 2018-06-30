package com.example.root.twog;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickPlay(View view){
        Intent intent = new Intent(this.getApplicationContext(),PlayActivity.class);
        startActivity(intent);
    }

    public void onClickRules(View view){
        AlertDialog.Builder dia_rules = new AlertDialog.Builder(this);
        String rules = "You are given 5 contiguous blocks, initially one occupied by 2. There are three selection boxes at the bottom. " +
                "Numbers in these three blocks are random. You have to select any one of these blocks and place it at any empty position " +
                "among the five blocks. Just click to select, it'll turn green. And then click on any non empty block to place. " +
                "Any two consecutive same numbers are clubbed together (summed up) to form a larger number and is replaced by the left of the two blocks. " +
                "The right of the two blocks is then reset. " +
                "The total score at any point of time is the sum the numbers in all the blocks.";
        dia_rules.setMessage(rules);
        dia_rules.setTitle("Rules");
        dia_rules.setCancelable(true);
        dia_rules.create().show();
    }
}
