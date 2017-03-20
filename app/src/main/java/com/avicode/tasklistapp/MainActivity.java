package com.avicode.tasklistapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class MainActivity extends ListActivity {

    public final static String NEW_TASK_ITEM_MESSAGE= "com.avicode.myapp.MESSAGE";
    private TextView textView;
    private ArrayList<String> arrayList;
    private Menu menu;

    public final static int port = 19090;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.taskListTitle);

        arrayList = new ArrayList<String>();
        arrayList.add("t1");
        arrayList.add("t2");
        arrayList.add("t3");

        ArrayAdapter<String> adapter = new ArrayAdapter <String>(this,R.layout.row_layout, R.id.listText, arrayList);



        setListAdapter(adapter);

    }
    // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);

        String selectedItem = (String) getListView().getItemAtPosition(position);
        //String selectedItem = (String) getListAdapter().getItem(position);
        String messageStr = "You clicked " + selectedItem + " at position " + position;
        textView.setText(messageStr);
        sendMessage(messageStr);
    }
    private void sendMessage(final String message){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramSocket socket = new DatagramSocket();

                    DatagramPacket packet = new DatagramPacket( message.getBytes(), message.length(), InetAddress.getByName("127.0.0.1"), port);
                    Log.d("YS, ",message);
                    socket.send(packet);


                }catch (Exception ex)
                {
                    ex.printStackTrace();
                    Log.e("YS","sendMessage" + ex.getMessage() );
                }

            }
        }).start();


    }
    //<editor-fold desc="Description">
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d("[YS]","onCreateOptionsMenu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu) {
            Intent intent = new Intent(this, TaskItemFormActivity.class);
            intent.putExtra(NEW_TASK_ITEM_MESSAGE, "New Item" );
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //</editor-fold>
}
