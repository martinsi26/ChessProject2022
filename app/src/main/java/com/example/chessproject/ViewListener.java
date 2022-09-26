package com.example.chessproject;

import android.view.View;
import android.widget.Button;

public class ViewListener implements View.OnClickListener {

    private Button surrenderButton;

    public ViewListener(Button _surrenderButton) {
        surrenderButton = _surrenderButton;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.surrenderButton) {

        }
    }
}
