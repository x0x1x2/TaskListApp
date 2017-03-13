package com.avicode.tasklistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TaskItemFormActivity extends AppCompatActivity {
    private final static String tag = "TaskItemFormActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_item_form);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.NEW_TASK_ITEM_MESSAGE);

    }



    public void okButton_onClick(View view) {
        try {
            EditText editText = (EditText) findViewById(R.id.titleEditBox);
            String title = editText.getText().toString();
            editText = (EditText) findViewById(R.id.dateEditText);
            String date = editText.getText().toString();
            editText = (EditText) findViewById(R.id.detailsEditText);
            String detailes = editText.getText().toString();


            TaskItem taskItem = new TaskItem(title,date,detailes);
            Toast.makeText(this, taskItem.toString(), Toast.LENGTH_SHORT).show();

        }
        catch ( Exception ex)
        {
            Log.e(tag,ex.getMessage());
        }
    }
    public void cancelButton_onClick(View view) {
        //Intent callIntent = new Intent(Intent.ACTION_, number);

    }
}