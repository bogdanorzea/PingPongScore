package com.bogdanorzea.pingpongscore;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String ENABLED_ADD_POINTS = "ENABLED_ADD_POINTS";
    private static final String PLAYER_1_SCORE = "PLAYER_1_SCORE";
    private static final String PLAYER_2_SCORE = "PLAYER_2_SCORE";
    private static final String PLAYER_1_SETS = "PLAYER_1_SETS";
    private static final String PLAYER_2_SETS = "PLAYER_2_SETS";
    private static final String PLAYER_1_NAME = "PLAYER_1_NAME";
    private static final String PLAYER_2_NAME = "PLAYER_2_NAME";

    private Button addPointPlayer1;
    private Button addPointPlayer2;
    private TextView scorePlayer1, scorePlayer2;
    private TextView setsPlayer1, setsPlayer2;
    private EditText namePlayer1, namePlayer2;
    private PingPongGame myGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPointPlayer1 = (Button) findViewById(R.id.player1_addPoint);
        addPointPlayer2 = (Button) findViewById(R.id.player2_addPoint);
        Button reset = (Button) findViewById(R.id.resetScore);

        scorePlayer1 = (TextView) findViewById(R.id.player1_points);
        setsPlayer1 = (TextView) findViewById(R.id.player1_sets);
        scorePlayer2 = (TextView) findViewById(R.id.player2_points);
        setsPlayer2 = (TextView) findViewById(R.id.player2_sets);

        namePlayer1 = (EditText) findViewById(R.id.player1_name);
        namePlayer2 = (EditText) findViewById(R.id.player2_name);

        namePlayer1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                myGame.setPlayer1Name(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        namePlayer2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                myGame.setPlayer2Name(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        addPointPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myGame.addPointPlayer1();
                updateScore();
                if (myGame.hasGameEnded()) {
                    String name = myGame.getPlayerNames()[0];
                    if (name == null || name.isEmpty()) {
                        name = getString(R.string.player_one_name);
                    }
                    endGame(v, name);
                }
            }
        });
        addPointPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myGame.addPointPlayer2();
                updateScore();
                if (myGame.hasGameEnded()) {
                    String name = myGame.getPlayerNames()[1];
                    if (name == null || name.isEmpty()) {
                        name = getString(R.string.player_two_name);
                    }
                    endGame(v, name);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle(R.string.confirm_dialog_title);
                builder.setMessage(R.string.confirm_dialog_message);

                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        myGame.resetGame();
                        updateScore();
                        updateName();

                        dialog.dismiss();

                        addPointPlayer1.setEnabled(true);
                        addPointPlayer2.setEnabled(true);
                    }
                });

                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        if (savedInstanceState != null) {
            int p1Score = savedInstanceState.getInt(PLAYER_1_SCORE);
            int p2Score = savedInstanceState.getInt(PLAYER_2_SCORE);
            int p1Sets = savedInstanceState.getInt(PLAYER_1_SETS);
            int p2Sets = savedInstanceState.getInt(PLAYER_2_SETS);
            String p1Name = savedInstanceState.getString(PLAYER_1_NAME);
            String p2Name = savedInstanceState.getString(PLAYER_2_NAME);
            boolean enabledAddPoints = savedInstanceState.getBoolean(ENABLED_ADD_POINTS);

            myGame = new PingPongGame(p1Sets, p2Sets, p1Score, p2Score);
            myGame.setPlayer1Name(p1Name);
            myGame.setPlayer2Name(p2Name);
            addPointPlayer1.setEnabled(enabledAddPoints);
            addPointPlayer2.setEnabled(enabledAddPoints);

        } else {
            myGame = new PingPongGame();
        }
        updateScore();
        updateName();
    }

    /**
     * Updates the on-screen player names
     */
    private void updateName() {
        namePlayer1.setText(myGame.getPlayerNames()[0]);
        namePlayer2.setText(myGame.getPlayerNames()[1]);
    }

    private void endGame(View v, String playerName) {
        // Display Congratulation window
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        builder.setMessage(getString(R.string.congratulate_dialog_message, playerName));

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

        addPointPlayer1.setEnabled(false);
        addPointPlayer2.setEnabled(false);
    }

    /**
     * Updates the on-screen score for the game
     */
    private void updateScore() {
        scorePlayer1.setText(Integer.toString(myGame.getScore()[0]));
        scorePlayer2.setText(Integer.toString(myGame.getScore()[1]));

        setsPlayer1.setText(Integer.toString(myGame.getSets()[0]));
        setsPlayer2.setText(Integer.toString(myGame.getSets()[1]));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(PLAYER_1_SCORE, myGame.getScore()[0]);
        outState.putInt(PLAYER_2_SCORE, myGame.getScore()[1]);
        outState.putInt(PLAYER_1_SETS, myGame.getSets()[0]);
        outState.putInt(PLAYER_2_SETS, myGame.getSets()[1]);
        outState.putString(PLAYER_1_NAME, myGame.getPlayerNames()[0]);
        outState.putString(PLAYER_2_NAME, myGame.getPlayerNames()[1]);
        outState.putBoolean(ENABLED_ADD_POINTS, addPointPlayer1.isEnabled() || addPointPlayer2.isEnabled());

        super.onSaveInstanceState(outState);
    }
}
