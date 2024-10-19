package com.example.omikujiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    public static String KEY_RESULT_NUMBER = "com.example.omikujiap.key_result_number";

    public static String[] OMIKUJI_PATTERN = {"大吉", "中吉", "小吉", "吉", "末吉", "凶", "大凶"};
    public static int[] OMIKUJI_IMG_RES_ID = {
            R.drawable.great_blessing, R.drawable.middle_blessing, R.drawable.small_blessing,
            R.drawable.blessing, R.drawable.blessing, R.drawable.uncertain_luck,
            R.drawable.curse, R.drawable.great_curse
    };
    public static int OMIKUJI_MAX_NUMBER= OMIKUJI_PATTERN.length; //7

    public static Intent newIntent(Context context, int resultNum) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(KEY_RESULT_NUMBER, resultNum);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int resultNum =
                getIntent().getIntExtra(KEY_RESULT_NUMBER, -1);

        if(resultNum == -1){
            throw new RuntimeException("result number is not found.");
        }

        //TODO：データを受け取る（結果の番号）
        //TODO：結果に応じて表示を変える

        String resultTitle = OMIKUJI_PATTERN[resultNum];
        int resultImResId= OMIKUJI_IMG_RES_ID[resultNum];

       Log.d("ResultActivity", "resultTitle" + resultTitle);

    }
}