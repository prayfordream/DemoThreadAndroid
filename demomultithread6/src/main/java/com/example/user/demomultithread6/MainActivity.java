package com.example.user.demomultithread6;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnStart = (Button)findViewById(R.id.btnstart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStart();
            }
        });

    }

    private void doStart(){
        String s = ((EditText) this.findViewById(R.id.editnumber)).getText().toString();
        //lấy số lượng từ EditText
        int n = Integer.parseInt(s);
        myAsyncTask = new MyAsyncTask(this);
        myAsyncTask.execute();
    }

}
