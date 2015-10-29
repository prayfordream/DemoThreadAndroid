package com.example.user.demomultithread5;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStart();
            }
        });

    }

    private void doStart(){
        //tạo 1 tiến trình
        MyAsyncTask myAsyncTask = new MyAsyncTask(this);
        EditText edt = (EditText)findViewById(R.id.editText1);
        //lấy giá trị nhập từ EditText
        int so = Integer.parseInt(edt.getText().toString());
        //thực thi tiến trình đối với đối số truyền vào là số
        //nó được dùng trong đối số của doInBackground
        myAsyncTask.execute(so);
    }

}
