package com.example.user.demomultithread2;

import android.app.ActionBar;
import android.graphics.LinearGradient;
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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.FileHandler;

public class MainActivity extends AppCompatActivity {

    Handler handlerMain;
    AtomicBoolean atomic = null;
    LinearLayout layoutdevebutton;
    Button btnOk;
    EditText edtOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layoutdevebutton = (LinearLayout) findViewById(R.id.layout_draw_button);
        btnOk = (Button) findViewById(R.id.btnDrawButton);
        edtOk = (EditText) findViewById(R.id.edtNumber);
        handlerMain = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String nhan_button = msg.obj.toString();
                Button b = new Button(MainActivity.this);
                b.setText(nhan_button);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                b.setLayoutParams(params);
                layoutdevebutton.addView(b);
            }
        };
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStart();
            }
        });
    }

    private void doStart(){
        atomic = new AtomicBoolean(false);
        final int sobutton = Integer.parseInt(edtOk.getText() + "");
        Thread thCon = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<sobutton && atomic.get(); i++){
                    SystemClock.sleep(200);
                    Message msg = handlerMain.obtainMessage();
                    msg.obj = "Button thứ " + i;
                    handlerMain.sendMessage(msg);
                }
            }
        });
        atomic.set(true);
        thCon.start();
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
