package com.example.android.mytennisapp;
//Racket Icon made by Freepik from www.flaticon.com

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.mytennisapp.R;

import static android.R.attr.id;
import static android.R.attr.visibility;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.view.View.VISIBLE;
import static com.example.android.mytennisapp.R.id.btnFaultA;
import static com.example.android.mytennisapp.R.id.btnFaultB;
import static com.example.android.mytennisapp.R.id.gameName;
import static com.example.android.mytennisapp.R.id.playerA;
import static com.example.android.mytennisapp.R.id.playerB;
import static com.example.android.mytennisapp.R.id.serveA;
import static com.example.android.mytennisapp.R.id.serveB;

public class MainActivity extends AppCompatActivity {

    int player_a_score = 0;
    int player_b_score = 0;
    int gameA = 0;
    int gameB = 0;
    int advA = 0;
    int advB = 0;
    int faultCheckA = 0;
    int faultCheckB = 0;

    //declare views here
    TextView scoreViewA = (TextView) findViewById(R.id.player_a_score);
    TextView scoreViewB = (TextView) findViewById(R.id.player_b_score);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * DUCE dialogue alert
     */

    public void Duce(View w) {
        AlertDialog ad = new AlertDialog.Builder(this)
                .setMessage("Deuce!")
                .setCancelable(true)
                .create();
        ad.show();
    }

    /**
     * Double Fault dialogue alert
     */
    public void doubleFault(View w) {
        AlertDialog ad = new AlertDialog.Builder(this)
                .setMessage("Double Fault!")
                .setCancelable(true)
                .create();
        ad.show();
    }

    /**
     * Method for adding a point to player A
     */
    public void addPointOne(){
        player_a_score = addPoint(player_a_score,player_b_score);
    }

    /**
     *
     * @param scoreOne denotes the scorer of the point
     * @param scoreTwo is used for checking
     */
    public int addPoint(int scoreOne, int scoreTwo) {
        //cancel penging faults
        //make faults invisible

        if (scoreOne == 0) {
            scoreOne = 15;
            displayScores(scoreOne,scoreTwo);
            return scoreOne;

        } else if (scoreOne == 15) {
            scoreOne = 30;
            displayScores(scoreOne,scoreTwo);

        } else if (scoreOne == 30) {
            if (scoreTwo == 40) {
                Duce(null);
            }
            scoreOne = 40;
            displayScores(scoreOne,scoreTwo);

        } else if (scoreOne == 40) {
            if (scoreTwo < 40) {
                gameA = gameA + 1;
                player_a_score = 0;
                player_b_score = 0;
                advA = 0;
                advB = 0;
                TextView advView = (TextView) findViewById(R.id.player_a_advantage);
                advView.setVisibility(View.INVISIBLE);
                TextView advView2 = (TextView) findViewById(R.id.player_b_advantage);
                advView2.setVisibility(View.INVISIBLE);
                changeServe(null);

            } else if (advB == 1) {
                advB = 0;
                TextView advView2 = (TextView) findViewById(R.id.player_b_advantage);
                advView2.setVisibility(View.INVISIBLE);
            } else if (advA == 1) {
                gameA = gameA + 1;
                player_a_score = 0;
                player_b_score = 0;
                advA = 0;
                advB = 0;
                TextView advView = (TextView) findViewById(R.id.player_a_advantage);
                advView.setVisibility(View.INVISIBLE);
                TextView advView2 = (TextView) findViewById(R.id.player_b_advantage);
                advView2.setVisibility(View.INVISIBLE);
                changeServe(null);

            } else if (advA == 0) {
                advA = 1;
                advB = 0;
                TextView advView = (TextView) findViewById(R.id.player_a_advantage);
                advView.setVisibility(VISIBLE);
                TextView advView2 = (TextView) findViewById(R.id.player_b_advantage);
                advView2.setVisibility(View.INVISIBLE);
                displayForTeamA(player_a_score);
                displayForTeamB(player_b_score);
            }

            displayForTeamA(player_a_score);
            displayForTeamB(player_b_score);
            String games = "Game: " + (gameA + gameB + 1);
            displayGame(games);
            String gamesA = "Player A - " + gameA;
            displayGameA(gamesA);
            String gamesB = "Player B - " + gameB;
            displayGameB(gamesB);

        }

    }


    /**
     * Increase the score for Player A.
     */
    public void addPointA(View v) {
        faultCheckA = 0;
        faultCheckB = 0;
        TextView faultView = (TextView) findViewById(R.id.player_a_fault);
        faultView.setVisibility(View.INVISIBLE);
        TextView faultViewB = (TextView) findViewById(R.id.player_b_fault);
        faultViewB.setVisibility(View.INVISIBLE);

        if (player_a_score == 0) {
            player_a_score = 15;
            displayForTeamA(player_a_score);
            displayForTeamB(player_b_score);
        } else if (player_a_score == 15) {
            player_a_score = 30;
            displayForTeamA(player_a_score);
            displayForTeamB(player_b_score);
        } else if (player_a_score == 30) {
            if (player_b_score == 40) {
                Duce(null);
            }
            player_a_score = 40;
            displayForTeamA(player_a_score);
            displayForTeamB(player_b_score);
        } else if (player_a_score == 40) {
            if (player_b_score < 40) {
                gameA = gameA + 1;
                player_a_score = 0;
                player_b_score = 0;
                advA = 0;
                advB = 0;
                TextView advView = (TextView) findViewById(R.id.player_a_advantage);
                advView.setVisibility(View.INVISIBLE);
                TextView advView2 = (TextView) findViewById(R.id.player_b_advantage);
                advView2.setVisibility(View.INVISIBLE);
                changeServe(null);

            } else if (advB == 1) {
                advB = 0;
                TextView advView2 = (TextView) findViewById(R.id.player_b_advantage);
                advView2.setVisibility(View.INVISIBLE);
            } else if (advA == 1) {
                gameA = gameA + 1;
                player_a_score = 0;
                player_b_score = 0;
                advA = 0;
                advB = 0;
                TextView advView = (TextView) findViewById(R.id.player_a_advantage);
                advView.setVisibility(View.INVISIBLE);
                TextView advView2 = (TextView) findViewById(R.id.player_b_advantage);
                advView2.setVisibility(View.INVISIBLE);
                changeServe(null);

            } else if (advA == 0) {
                advA = 1;
                advB = 0;
                TextView advView = (TextView) findViewById(R.id.player_a_advantage);
                advView.setVisibility(VISIBLE);
                TextView advView2 = (TextView) findViewById(R.id.player_b_advantage);
                advView2.setVisibility(View.INVISIBLE);
                displayForTeamA(player_a_score);
                displayForTeamB(player_b_score);
            }

            displayForTeamA(player_a_score);
            displayForTeamB(player_b_score);
            String games = "Game: " + (gameA + gameB + 1);
            displayGame(games);
            String gamesA = "Player A - " + gameA;
            displayGameA(gamesA);
            String gamesB = "Player B - " + gameB;
            displayGameB(gamesB);

        }

    }

    /**
     * Change the serve
     */

    public void changeServe(View v) {
        ImageView racketA = (ImageView) findViewById(R.id.serveA);
        ImageView racketB = (ImageView) findViewById(R.id.serveB);
        Button buttnFaultA = (Button) findViewById(btnFaultA);
        Button buttnFaultB = (Button) findViewById(btnFaultB);

        faultCheckA = 0;
        faultCheckB = 0;
        TextView faultView = (TextView) findViewById(R.id.player_a_fault);
        faultView.setVisibility(View.INVISIBLE);
        TextView faultViewB = (TextView) findViewById(R.id.player_b_fault);
        faultViewB.setVisibility(View.INVISIBLE);


        if (racketA.getVisibility() == View.VISIBLE) {
            racketA.setVisibility(View.INVISIBLE);
            racketB.setVisibility(View.VISIBLE);
            buttnFaultA.setEnabled(false);
            buttnFaultB.setEnabled(true);
        } else {
            racketB.setVisibility(View.INVISIBLE);
            racketA.setVisibility(View.VISIBLE);
            buttnFaultA.setEnabled(true);
            buttnFaultB.setEnabled(false);
        }
    }

    /**
     * Increase the score for Player B.
     */
    public void addPointB(View v) {
        faultCheckA = 0;
        faultCheckB = 0;
        TextView faultView = (TextView) findViewById(R.id.player_a_fault);
        faultView.setVisibility(View.INVISIBLE);
        TextView faultViewB = (TextView) findViewById(R.id.player_b_fault);
        faultViewB.setVisibility(View.INVISIBLE);

        if (player_b_score == 0) {
            player_b_score = 15;
            displayForTeamA(player_a_score);
            displayForTeamB(player_b_score);
        } else if (player_b_score == 15) {
            player_b_score = 30;
            displayForTeamA(player_a_score);
            displayForTeamB(player_b_score);
        } else if (player_b_score == 30) {
            if (player_a_score == 40) {
                Duce(null);
            }
            player_b_score = 40;
            displayForTeamA(player_a_score);
            displayForTeamB(player_b_score);
        } else if (player_b_score == 40) {
            if (player_a_score < 40) {
                gameB = gameB + 1;
                player_a_score = 0;
                player_b_score = 0;
                advA = 0;
                advB = 0;
                TextView advView = (TextView) findViewById(R.id.player_a_advantage);
                advView.setVisibility(View.INVISIBLE);
                TextView advView2 = (TextView) findViewById(R.id.player_b_advantage);
                advView2.setVisibility(View.INVISIBLE);

                changeServe(null);
            } else if (advA == 1) {
                advA = 0;
                TextView advView = (TextView) findViewById(R.id.player_a_advantage);
                advView.setVisibility(View.INVISIBLE);
            } else if (advB == 1) {
                gameB = gameB + 1;
                player_a_score = 0;
                player_b_score = 0;
                advA = 0;
                advB = 0;
                TextView advView = (TextView) findViewById(R.id.player_a_advantage);
                advView.setVisibility(View.INVISIBLE);
                TextView advView2 = (TextView) findViewById(R.id.player_b_advantage);
                advView2.setVisibility(View.INVISIBLE);
                changeServe(null);


            } else if (advB == 0) {
                advA = 0;
                advB = 1;
                TextView advView = (TextView) findViewById(R.id.player_b_advantage);
                advView.setVisibility(View.VISIBLE);
                TextView advView2 = (TextView) findViewById(R.id.player_a_advantage);
                advView2.setVisibility(View.INVISIBLE);
                displayForTeamA(player_a_score);
                displayForTeamB(player_b_score);
            }

            displayForTeamA(player_a_score);
            displayForTeamB(player_b_score);
            String games = "Game: " + (gameA + gameB + 1);
            displayGame(games);
            String gamesA = "Player A - " + gameA;
            displayGameA(gamesA);
            String gamesB = "Player B - " + gameB;
            displayGameB(gamesB);
        }
    }


    /**
     * Fault for Player A.
     */
    public void faultA(View v) {
        if (faultCheckA == 0) {
            TextView faultView = (TextView) findViewById(R.id.player_a_fault);
            faultView.setVisibility(VISIBLE);
            faultCheckA = 1;
        } else {
            TextView faultView = (TextView) findViewById(R.id.player_a_fault);
            faultView.setVisibility(View.INVISIBLE);
            doubleFault(null);
            addPointB(null);
            faultCheckA = 0;
        }

    }


    /**
     * Fault for Player B.
     */
    public void faultB(View v) {
        if (faultCheckB == 0) {
            TextView faultView = (TextView) findViewById(R.id.player_b_fault);
            faultView.setVisibility(VISIBLE);
            faultCheckB = 1;
        } else {
            TextView faultView = (TextView) findViewById(R.id.player_b_fault);
            faultView.setVisibility(View.INVISIBLE);
            doubleFault(null);
            addPointA(null);
            faultCheckB = 0;
        }
    }

    /**
     * Displays the game number
     */
    public void displayGame(String game) {
        TextView gameView = (TextView) findViewById(gameName);
        gameView.setText(String.valueOf(game));
    }

    /**
     * a
     * Displays the Player A games tally
     */
    public void displayGameA(String game) {
        TextView gameView = (TextView) findViewById(playerA);
        gameView.setText(String.valueOf(game));
    }

    /**
     * Displays the Player B games tally
     */
    public void displayGameB(String game) {
        TextView gameView = (TextView) findViewById(playerB);
        gameView.setText(String.valueOf(game));
    }

    public void displayScores(int scoreOne, int scoreTwo){
        scoreViewA.setText(String.valueOf(scoreOne));
        scoreViewB.setText(String.valueOf(scoreTwo));
    }

    /**
     * Displays the given score for Player A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Player B.
     */

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void resetScore(View v) {
        player_a_score = 0;
        displayForTeamA(player_a_score);
        player_b_score = 0;
        displayForTeamB(player_b_score);
        gameA = 0;
        gameB = 0;
        String games = "Game: " + (gameA + gameB + 1);
        displayGame(games);
        String gamesA = "Player A - " + gameA;
        displayGameA(gamesA);
        String gamesB = "Player B - " + gameB;
        displayGameB(gamesB);

        //Set Advantages to zero and invisible
        advA = 0;
        advB = 0;
        TextView advView = (TextView) findViewById(R.id.player_a_advantage);
        advView.setVisibility(View.INVISIBLE);
        TextView advView2 = (TextView) findViewById(R.id.player_b_advantage);
        advView2.setVisibility(View.INVISIBLE);

        //set faults to invisible
        faultCheckA = 0;
        faultCheckB = 0;
        TextView faultView = (TextView) findViewById(R.id.player_a_fault);
        faultView.setVisibility(View.INVISIBLE);
        TextView faultViewB = (TextView) findViewById(R.id.player_b_fault);
        faultViewB.setVisibility(View.INVISIBLE);

        /*
        * Reset serve to A
        */
        ImageView racketA = (ImageView) findViewById(R.id.serveA);
        ImageView racketB = (ImageView) findViewById(R.id.serveB);
        Button buttnFaultA = (Button) findViewById(btnFaultA);
        Button buttnFaultB = (Button) findViewById(btnFaultB);
        racketB.setVisibility(View.INVISIBLE);
        racketA.setVisibility(View.VISIBLE);
        buttnFaultB.setEnabled(false);
        buttnFaultA.setEnabled(true);

    }
}