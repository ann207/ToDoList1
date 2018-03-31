package com.rsc.annaparrish.todolist;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.awt.font.TextAttribute;

public class MainActivity extends Activity {

    EditText userInput;
    TextView recordsTextView;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText) findViewById(R.id.userInput);
        recordsTextView = (TextView) findViewById(R.id.recordsTextView);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();

    }

    public void printDatabase() {
        String dbString = dbHandler.databaseToString();
        recordsTextView.setText(dbString);
        userInput.setText("");
    }

    public void addButtonClicked(View view) {
        Item item = new Item(userInput.getText().toString());
        dbHandler.addItem(item);
        printDatabase();
        Toast.makeText(getApplicationContext(), "Added item on to do list", Toast.LENGTH_SHORT).show();
    }

    public void deleteButtonClicked(View view) {
        String inputText = userInput.getText().toString();
        dbHandler.deleteItem(inputText);
        printDatabase();

        Toast.makeText(getApplicationContext(), "Deleted item on to do list", Toast.LENGTH_SHORT).show();
    }
    //delete table
    public void dropButtonClicked (View view){
        /*String inputText = userInput.getText().toString();
        dbHandler.deleteAll(inputText);
        printDatabase();*/



        /*dbHandler.deleteAll(MyDBHandler.TABLE_ITEM);
        printDatabase();
        Toast.makeText(getApplicationContext(), "Deleted Table", Toast.LENGTH_SHORT).show();*/
    }

}