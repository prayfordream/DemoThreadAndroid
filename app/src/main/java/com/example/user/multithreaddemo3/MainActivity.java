package com.example.user.multithreaddemo3;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    ListView lvTest;
    ArrayList<Integer>arr = new ArrayList<Integer>();
    ArrayAdapter<Integer> adapter = null;
    AtomicBoolean ab = new AtomicBoolean(false);

    Button btnTest;
    EditText edtNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        lvTest = (ListView)findViewById(R.id.lvTest);
        btnTest = (Button)findViewById(R.id.btnTest);
        edtNumber = (EditText)findViewById(R.id.edtNumber);
        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, arr);
        lvTest.setAdapter(adapter);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStart();
            }
        });
    }

    private void doStart(){
        final int so = Integer.parseInt(edtNumber.getText() + "");
        final Random rd = new Random();
        ab.set(false);
        Thread thCon = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<so && ab.get(); i++){
                    SystemClock.sleep(150);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            capnhatgiaodien(rd.nextInt(100));
                        }
                    });
                }
                //thongbaoketthuccapnhat();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        thongbaoketthuccapnhat();
                    }
                });
            }
        });
        ab.set(true);
        thCon.start();
    }

    private void capnhatgiaodien(int so){
        arr.add(so);
        adapter.notifyDataSetChanged();
    }

    private void thongbaoketthuccapnhat(){
        Toast.makeText(MainActivity.this, "Finish", Toast.LENGTH_SHORT).show();
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
}
