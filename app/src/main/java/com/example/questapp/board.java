package com.example.questapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class board extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        Button lgtbtn = (Button) findViewById(R.id.logout);
        lgtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutActivity();
            }
        });
    }
    public void logoutActivity(){
        Intent intent = new Intent(board.this,MainActivity.class);
        startActivity(intent);
    }

}