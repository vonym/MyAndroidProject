package com.example.radiobutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
    private RadioGroup radioGroup;
    private String sex;
    private CheckBox game, basketball, music;
    private Button submit;
    private String hobby_game = "";
    private String hobby_basketball = "";
    private String hobby_music = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        game = (CheckBox) findViewById(R.id.game);
        basketball = (CheckBox) findViewById(R.id.basketball);
        music = (CheckBox) findViewById(R.id.music);
        submit = (Button) findViewById(R.id.submit);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.male:
                        sex = "男";
                        Toast.makeText(MainActivity.this, sex, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.female:
                        sex = "女";
                        Toast.makeText(MainActivity.this, sex, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        game.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    hobby_game = "游戏";
                } else {
                    hobby_game = "";
                }
            }
        });
        basketball.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    hobby_basketball = "篮球";
                } else {
                    hobby_basketball = "";
                }
            }
        });
        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    hobby_music = "音乐";
                } else {
                    hobby_music = "";
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((hobby_game + hobby_basketball + hobby_music).equals("")) {
                    Toast.makeText(MainActivity.this, "没有爱好", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "爱好有：" + hobby_game + hobby_basketball + hobby_music, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
