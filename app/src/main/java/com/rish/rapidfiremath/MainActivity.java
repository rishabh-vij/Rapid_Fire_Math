package com.rish.rapidfiremath;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button gobtn,button0,button1,button2,button3,playAgainButton;
    TextView question,timerText,resultText,scoretext;
    int rightindex;
    int score,gamecount=0;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    ConstraintLayout gameLayout;
    boolean gamestate=true;


    public void setanswer(View view){

        if(gamestate==true) {
            String selected = view.getTag().toString();
            if (Integer.toString(rightindex).equals(selected)) {
                resultText.setText("Correct! +1");
                score++;
            } else {
                resultText.setText("Wrong");
            }
            gamecount++;
            scoretext.setText(Integer.toString(score) + "/" + Integer.toString(gamecount));
            newQuestion();
        }
    }

    public void gobutton(View view){
        gobtn.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timertext));
        gameLayout.setVisibility(View.VISIBLE);

    }

    public void newQuestion(){
        int a,b;
        Random rand=new Random();

        a=rand.nextInt(41);
        b=rand.nextInt(21);
        question.setText(Integer.toString(a)+"+"+Integer.toString(b));
        rightindex=rand.nextInt(4);
        answers.clear();

        for(int i=0;i<4;i++){
            if(rightindex==i){
                answers.add(a+b);
            }
            else{
                int wronganswer=rand.nextInt(61);
                while(wronganswer==a+b){
                    wronganswer=rand.nextInt(61);
                }
                answers.add(wronganswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

        }

        public void playAgain(View view){
        score=0;
        gamecount=0;
        timerText.setText("30s");
        scoretext.setText(Integer.toString(score)+"/"+Integer.toString(gamecount));
        resultText.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        gamestate=true;
        newQuestion();
            new CountDownTimer(30100, 1000) {
                @Override
                public void onTick(long l) {
                    timerText.setText(String.valueOf(l/1000)+"s");

                }

                @Override
                public void onFinish() {
                    resultText.setText("Done!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    timerText.setText("0s");
                    gamestate=false;


                }
            }.start();



        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gobtn=findViewById(R.id.gobutton);
        question=findViewById(R.id.question);
        button0=findViewById(R.id.answer0);
        button1=findViewById(R.id.answer1);
        button2=findViewById(R.id.answer2);
        button3=findViewById(R.id.answer3);
        timerText=findViewById(R.id.timertext);
        playAgainButton=findViewById(R.id.playAgainButton);
        scoretext=findViewById(R.id.scoretext);
        gameLayout=findViewById(R.id.gameLayout);
        resultText=findViewById(R.id.result);

        gameLayout.setVisibility(View.INVISIBLE);
        gobtn.setVisibility(View.VISIBLE);

        newQuestion();
    }
}
