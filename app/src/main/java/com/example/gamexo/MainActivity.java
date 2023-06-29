package com.example.gamexo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gamexo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<int[]> combinationList = new ArrayList<>();
    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int playerTurn = 1;
    private int totalSelectedBoxes = 1;

    LinearLayout mainPlayerOneLayout, mainPlayerTwoLayout;
    ImageView mainImg1, mainImg2, mainImg3, mainImg4, mainImg5, mainImg6, mainImg7, mainImg8, mainImg9;
    TextView mainTxtPlayerOneName, mainTxtPlayerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();
        victoryConditions();
        changePlayerTurn(1);

        String getPlayerOneName = getIntent().getStringExtra("playerOne");
        String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        mainTxtPlayerOneName.setText(getPlayerOneName);
        mainTxtPlayerTwoName.setText(getPlayerTwoName);

        mainImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(0)) {
                    performAction((ImageView) view, 0);
                }
            }
        });

        mainImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(1)) {
                    performAction((ImageView) view, 1);
                }
            }
        });
        mainImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(2)) {
                    performAction((ImageView) view, 2);
                }
            }
        });
        mainImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(3)) {
                    performAction((ImageView) view, 3);
                }
            }
        });
        mainImg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(4)) {
                    performAction((ImageView) view, 4);
                }
            }
        });
        mainImg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(5)) {
                    performAction((ImageView) view, 5);
                }
            }
        });
        mainImg7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(6)) {
                    performAction((ImageView) view, 6);
                }
            }
        });
        mainImg8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(7)) {
                    performAction((ImageView) view, 7);
                }
            }
        });
        mainImg9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(8)) {
                    performAction((ImageView) view, 8);
                }
            }
        });
    }

    private void performAction(ImageView imgView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;

        if (playerTurn == 1) {
            imgView.setImageResource(R.drawable.ximage);

            if (checkResult()) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, mainTxtPlayerOneName.getText().toString()
                        + " is a Winner!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if (totalSelectedBoxes == 9) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Match Draw", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        } else {
            imgView.setImageResource(R.drawable.oimage);

            if (checkResult()) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, mainTxtPlayerTwoName.getText().toString()
                        + " is a Winner!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if (totalSelectedBoxes == 9) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Match Draw", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1) {
            mainPlayerOneLayout.setBackgroundResource(R.drawable.black_border);
            mainPlayerTwoLayout.setBackgroundResource(R.drawable.white_box);
        } else {
            mainPlayerTwoLayout.setBackgroundResource(R.drawable.black_border);
            mainPlayerOneLayout.setBackgroundResource(R.drawable.white_box);
        }
    }

    private boolean checkResult() {
        boolean response = false;
        for (int i = 0; i < combinationList.size(); i++) {
            final int[] combination = combinationList.get(i);

            if (
                    boxPositions[combination[0]] == playerTurn
                            && boxPositions[combination[1]] == playerTurn
                            && boxPositions[combination[2]] == playerTurn) {
                response = true;
            }
        }
        return response;
    }

    private boolean isBoxSelectable(int boxPosition) {
        boolean response = false;
        if (boxPositions[boxPosition] == 0) {
            response = true;
        }
        return response;
    }

    private void mapping() {
        mainImg1 = findViewById(R.id.mainImg1);
        mainImg2 = findViewById(R.id.mainImg2);
        mainImg3 = findViewById(R.id.mainImg3);
        mainImg4 = findViewById(R.id.mainImg4);
        mainImg5 = findViewById(R.id.mainImg5);
        mainImg6 = findViewById(R.id.mainImg6);
        mainImg7 = findViewById(R.id.mainImg7);
        mainImg8 = findViewById(R.id.mainImg8);
        mainImg9 = findViewById(R.id.mainImg9);

        mainPlayerOneLayout = findViewById(R.id.main_player_one_layout);
        mainPlayerTwoLayout = findViewById(R.id.main_player_two_layout);

        mainTxtPlayerOneName = findViewById(R.id.main_player_one_name);
        mainTxtPlayerTwoName = findViewById(R.id.main_player_two_name);
    }

    private void victoryConditions() {
        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{0, 4, 8});
        combinationList.add(new int[]{2, 4, 6});
    }
}