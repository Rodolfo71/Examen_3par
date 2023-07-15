package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private String[] words;
    private Random random;
    private String currWord;
    private TextView[] charViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //https://github.com/codigofacilito/hangman
        words=getResources().getStringArray(R.array.words);

    }

    private void playGame(){
        String newWord=words[random.nextInt(words.length)];

        while(newWord.equals(currWord))newWord=words[random.nextInt(words.length)];

        currWord=newWord;

        charViews=new TextView[currWord.length()];

        for(int i=0; i<currWord.length(); i++){
            charViews[i]=new TextView( context: this);

        }
    }
}