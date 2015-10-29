package com.example.user.demomultithread5;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 10/28/2015.
 */
public class MyAsyncTask extends AsyncTask<Integer, Integer, ArrayList<Integer>> {
    Activity contextMain;
    ListView lv = null;
    ArrayList<Integer>arrDataListView = new ArrayList<Integer>();
    ArrayAdapter<Integer>adapterListView = null;

    public MyAsyncTask(Activity act){
        //lấy MainActivity
        contextMain = act;
        //lấy ListView của MainActivity
        lv = (ListView)contextMain.findViewById(R.id.listView1);
        //Adapter cho listview
        adapterListView = new ArrayAdapter<Integer>(contextMain, android.R.layout.simple_list_item_1, arrDataListView);
        //gán adapter cho listview
        lv.setAdapter(adapterListView);
    }

    /**
     * Số fibonacci thứ n trong chuỗi Fibonacci
     * @param n
     * @return
     */
    public int fib(int n){
        if(n < 2) return 1;
        return fib(n-1)+fib(n-2);
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        Toast.makeText(contextMain, "Bắt đầu chạy tiến trình", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected ArrayList<Integer> doInBackground(Integer... arg0) {
        //vậy thì khi bắt đầu thực thi đối số 1 sẽ ở đây: arg0
        int so = arg0[0];
        //khai báo ArrayList lưu trữ tập các số nguyên
        ArrayList<Integer> arrTongCacSoFib = new ArrayList<Integer>();
        for(int i = 1;i<=so;i++){
            SystemClock.sleep(150);
            //lấy số fibonacci tại vị trí thứ i
            int f = fib(i);
            //lưu vào danh sách
            arrTongCacSoFib.add(f);
            //cập nhật số fibonaci lên listview
            publishProgress(f);
        }
        //trả về danh sách nó được lưu trữ trong hàm onPostExecute
        return arrTongCacSoFib;
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        //lấy giá trị truyền từ publishProgress
        arrDataListView.add(values[0]);
        //cập nhật lại giao diện
        adapterListView.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(ArrayList<Integer> result){
        super.onPostExecute(result);
        //result chính là arraylist lưu trữ của chuỗi Fibonacci
        int tong = 0;
        //vòng lặp để cộng dồn lại
        for(int v: result){
            tong += v;
        }
        Toast.makeText(contextMain, "Tong= " + tong, Toast.LENGTH_SHORT).show();
        //cập nhật lên TestView
        TextView txt = (TextView)contextMain.findViewById(R.id.textView1);
        txt.setText(tong+"");
    }
}
