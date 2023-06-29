package com.example.gamexo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultDialog extends Dialog {
    private final String message;
    private final MainActivity mainActivity;

    TextView dialogMessage;
    Button dialogBtnStartAgain;

    public ResultDialog(Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_dialog);

        mapping();

        dialogMessage.setText(message);

        clickBtnStartAgain();
    }

    private void clickBtnStartAgain() {
        dialogBtnStartAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.recreate();
                dismiss();
            }
        });
    }

    private void mapping() {
        dialogMessage = findViewById(R.id.dialog_message);
        dialogBtnStartAgain = findViewById(R.id.dialog_btn_start_again);
    }
}