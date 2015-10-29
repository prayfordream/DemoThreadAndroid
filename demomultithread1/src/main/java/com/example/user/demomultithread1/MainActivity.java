package com.example.user.demomultithread1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    private static int STOP = 0;
    private static int RUNNING = 1;
    private static int RESET = 2;
    ProgressBar bar;
    Handler handler;
    Button btnStart, btnStop, btnReset;
    TextView lblmsg;
    private int numberLoading = 0;
    Thread th;
    private int statusThread = STOP;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bar = (ProgressBar) findViewById(R.id.progressBar1);
        btnStart = (Button) findViewById(R.id.btnstart);
        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                doStart();
            }
        });

        btnReset = (Button) findViewById(R.id.btnreset);
        btnReset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                doReset();
            }
        });

        handler = new Handler() {
            public void handleMessage(Message msg){
                if(msg.arg1 == 100 || statusThread == RESET){
                    setDefautProgress();
                }else{
                    bar.setProgress(msg.arg1);
                    lblmsg.setText(msg.arg1 + "%");
                }
            }
        };

        lblmsg = (TextView) findViewById(R.id.textView1);
    }

    public void doStart(){
        final int DONE = 0;
        if(numberLoading == 100)
            numberLoading = 0;
        bar.setProgress(numberLoading);
        if(statusThread == RUNNING){
            statusThread = STOP;
            btnStart.setText("continue");
        }

        else if(statusThread == STOP) {
            th = new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int i = numberLoading; i <= 100 && (statusThread == RUNNING); i++) {
                        SystemClock.sleep(50);
                        if(statusThread == STOP) break;
                        Message msg = handler.obtainMessage();
                        msg.arg1 = i;
                        numberLoading = i;
                        handler.sendMessage(msg);
                    }
                }
            });
            statusThread = RUNNING;
            btnStart.setText("stop");
            th.start();
        }
    }

    public void doReset() {
        if(statusThread == STOP)
            setDefautProgress();
        else statusThread = RESET;
    }

    public void setDefautProgress(){
        numberLoading = 0;
        bar.setProgress(0);
        btnStart.setText("start");
        lblmsg.setText("0%");
        statusThread = STOP;
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
