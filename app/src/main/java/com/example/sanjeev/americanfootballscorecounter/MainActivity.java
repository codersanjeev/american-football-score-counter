package com.example.sanjeev.americanfootballscorecounter;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements MyDialog.Communicator{

    int scoreA = 0;                 // Team A Score Counter
    int scoreB = 0;                 // Team B Score Counter
    public boolean flag = true;     // To Check which Team Hit a Touchdown

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("TeamAscore", scoreA);
        outState.putInt("TeamBscore", scoreB);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView v = findViewById(R.id.teamA_score_view);
        TextView v1 = findViewById(R.id.teamB_score_view);
        if(savedInstanceState != null){
            scoreA = savedInstanceState.getInt("TeamAscore");
            scoreB = savedInstanceState.getInt("TeamBscore");
            v.setText(String.valueOf(scoreA));
            v1.setText(String.valueOf(scoreB));
        }
        else{
            scoreA = 0;
            scoreB = 0;
            v.setText(String.valueOf(scoreA));
            v1.setText(String.valueOf(scoreB));
        }
    }

    // increments the score of Team A by 6.
    // also, shows a Dialog Box for Points after Touchdown.
    public void TeamAtouchdown(View view) {
        scoreA += 6;
        UpdateTeamAscore(scoreA);
        flag = true;
        FragmentManager fragmentManager = getFragmentManager();
        MyDialog myDialog = new MyDialog();
        myDialog.show(fragmentManager, "MyDialog");
    }

    // Updates Team A score.
    private void UpdateTeamAscore(int val) {
        TextView v = findViewById(R.id.teamA_score_view);
        v.setText(String.valueOf(val));
    }

    // increments the score of Team A by 3.
    public void TeamAfieldgoal(View view) {
        scoreA += 3;
        UpdateTeamAscore(scoreA);
    }

    // increments the score of Team B by 2.
    public void TeamAsafety(View view) {
        scoreB += 2;
        UpdateTeamBscore(scoreB);
    }

    // Updates Team B score.
    private void UpdateTeamBscore(int val) {
        TextView v = findViewById(R.id.teamB_score_view);
        v.setText(String.valueOf(val));
    }

    // increments the score of Team B by 6.
    // also, shows a Dialog Box for Points after Touchdown.
    public void TeamBtouchdown(View view) {
        scoreB += 6;
        UpdateTeamBscore(scoreB);
        flag = false;
        FragmentManager fragmentManager = getFragmentManager();
        MyDialog myDialog = new MyDialog();
        myDialog.show(fragmentManager, "MyDialog");
    }

    // increments the score of Team B by 3.
    public void TeamBfieldgoal(View view) {
        scoreB += 3;
        UpdateTeamBscore(scoreB);
    }

    // increments the score of team A by 2.
    public void TeamBsafety(View view) {
        scoreA += 2;
        UpdateTeamAscore(scoreA);
    }

    // Updates the scores of Team A and Team B to zero.
    public void Reset(View view) {
        scoreB = 0;
        scoreA = 0;
        UpdateTeamAscore(scoreA);
        UpdateTeamBscore(scoreB);
    }


    // Updates the Score Accordingly
    // from flag, it is known which team hit a Touchdown.
    // from message received in string, it is known whether it is +1 or +2.
    @Override
    public void onDialogMessage(String message) {
        if(message.equals("plus one") && flag){
            // Increase Score of Team A by One
            scoreA += 1;
            UpdateTeamAscore(scoreA);
        }

        else if(message.equals("plus one") && !flag){
            // Increase Score of Team B by One
            scoreB += 1;
            UpdateTeamBscore(scoreB);
        }

        else if(message.equals("plus two") && flag){
            // Increase Score of Team A by Two
            scoreA += 2;
            UpdateTeamAscore(scoreA);
        }
        else{
            // Increase Score of Team B by Two
            scoreB += 2;
            UpdateTeamBscore(scoreB);
        }
    }
}
