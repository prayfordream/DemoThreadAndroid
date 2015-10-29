package com.example.user.demomultithread4;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 10/28/2015.
 */
public class MyAsyncTask extends AsyncTask<Void, Integer, Void>{
    //khai báo Activity để lưu trữ địa chỉ của MainActivity
    Activity contextMain;

    //constructor này được truyền vào là MainActivity
    public MyAsyncTask(Activity ctx){
        contextMain = ctx;
    }

    //hàm này sẽ được thực hiện đầu tiên
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        Toast.makeText(contextMain, "onPreExecute!", Toast.LENGTH_SHORT).show();
    }

    //sau đó tới hàm doInBackground
    //tuyệt đối không được cập nhật giao diện trong hàm này
    @Override
    protected Void doInBackground(Void... params) {
        for(int i = 0;i<=100;i++) {
            SystemClock.sleep(100);
            publishProgress(i);
        }
        return null;
    }

    /**
     * ta cập nhập giao diện trong hàm này
     */
    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        //thông qua contextCha để lấy được control trong MainActivity
        ProgressBar progressBar = (ProgressBar) contextMain.findViewById(R.id.progressBar1);
        //vì publishProgress chỉ truyền 1 đối số
        //nên mảng values chỉ có 1 phần tử
        int giatri = values[0];
        //tăng giá trị của Progressbar lên
        progressBar.setProgress(giatri);
        //đồng thời hiển thị giá trị phần trăm lên textview
        TextView txtmsg = (TextView)contextMain.findViewById(R.id.textView1);
        txtmsg.setText(giatri + "%");
    }

    /**
     * sau khi tiến trình thực hiện xong thì hàm này sảy ra
     */
    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        Toast.makeText(contextMain, "Update xong roi do!",
                Toast.LENGTH_LONG).show();
    }
}
