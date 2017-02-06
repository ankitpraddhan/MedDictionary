package com.example.ankit.meddictionary;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    DatabaseAdapter secondHelper;
    DatabaseAdapter.FirstHelper firstHelper;
    Button search_button;
    EditText edit_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secondHelper = new DatabaseAdapter(this);
        firstHelper  = new DatabaseAdapter(this).new FirstHelper(this);

        try {

            firstHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to Create Database");

        }

        try {

            firstHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }
        search_button= (Button) findViewById(R.id.search_button);
        edit_search=(EditText) findViewById(R.id.edit_search);



        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = edit_search.getText().toString().toUpperCase();
                String data = secondHelper.getData(search);
                TextView t = (TextView) findViewById(R.id.titleInfo);
                t.setText(search);
                TextView tt = (TextView) findViewById(R.id.gotit2);
                tt.setText(data);
                //Message.message(MainActivity.this,data);
            }
        });
    }
}
