package com.example.omikujiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    public static String KEY_RESULT_NUMBER = "com.example.omikujiap.key_result_number";

    public static String[] OMIKUJI_PATTERN = {"大吉", "中吉", "小吉", "吉", "末吉", "凶", "大凶"};
    public static int[] OMIKUJI_IMG_RES_ID = {
            R.drawable.great_blessing, R.drawable.middle_blessing, R.drawable.small_blessing,
            R.drawable.blessing, R.drawable.uncertain_luck,
            R.drawable.curse, R.drawable.great_curse
    };

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

        if (resultNum == -1) {
            throw new RuntimeException("result number is not found.");
        }

        String resultTitle = "";
        int resultImResId = 0;

        //乱数によって結果を分岐させる
        if(resultNum<=5){
            //numberが5以下の場合はここに入る
            //大吉
            resultTitle = OMIKUJI_PATTERN[0];
            resultImResId = OMIKUJI_IMG_RES_ID[0];
        }else if(resultNum<=15){
            //numberが5以下でなく15以下の場合はここに入る。
            //中吉
            resultTitle = OMIKUJI_PATTERN[1];
            resultImResId = OMIKUJI_IMG_RES_ID[1];
        }else if(resultNum<=35){
            //numberが15以下でなく35以下の場合はここに入る。
            //小吉
            resultTitle = OMIKUJI_PATTERN[2];
            resultImResId = OMIKUJI_IMG_RES_ID[2];
        }else if(resultNum<=65){
            //numberが35以下でなく65以下の場合はここに入る。
            //吉
            resultTitle = OMIKUJI_PATTERN[3];
            resultImResId = OMIKUJI_IMG_RES_ID[3];
        }else if(resultNum<=85){
            //numberが65以下でなく85以下の場合はここに入る。
            //末吉
            resultTitle = OMIKUJI_PATTERN[4];
            resultImResId = OMIKUJI_IMG_RES_ID[4];
        }else if(resultNum<=96){
            //numberが85以下でなく96以下の場合はここに入る。
            //凶
            resultTitle = OMIKUJI_PATTERN[5];
            resultImResId = OMIKUJI_IMG_RES_ID[5];
        }else{
            //上記の条件以外のものが入る
            resultTitle = OMIKUJI_PATTERN[6];
            resultImResId = OMIKUJI_IMG_RES_ID[6];
        }

        //結果タイトルのTextViewを読み込む
        TextView resultTextView = findViewById(R.id.result_title);
        resultTextView.setText(resultTitle);

        //結果画像のImageViewを読み込む
        ImageView resultImageView = findViewById(R.id.resultImResId);
        resultImageView.setBackgroundResource(resultImResId);

        //トップ画面に戻る
        FrameLayout backButton = findViewById(R.id.returnTop_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }
}