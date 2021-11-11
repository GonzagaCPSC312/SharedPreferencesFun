package com.sprint.gina.sharedpreferencesfun;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.EditText;

// a simple app to persistently save and load a user's name

public class MainActivity extends AppCompatActivity {
    static final String SHARED_PREFERENCES_FNAME = "sharedpreffname";
    static final String NAME_KEY = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get a SharedPreferences reference
        // get the string extra using the key
        // update the edittext
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_FNAME, 0);
        String name = sharedPreferences.getString(NAME_KEY, ""); // second arg is default value
        EditText nameEditText = findViewById(R.id.nameEditText);
        nameEditText.setText(name);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EditText nameEditText = findViewById(R.id.nameEditText);
        String name = nameEditText.getText().toString();

        // persistent data storage: save data between executions of the app
        // few approaches to do this
        // 1. shared preferences: can store int, double, string, stringset, etc.
        // 2. read/write to a file
        // 3. database like SQLite
        // 4. Room persistent library (abstraction layer on top of SQLite)

        // for 1. we store key value pairs
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_FNAME, 0);
        // 0 Context.MODE_PRIVATE
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME_KEY, name);
        editor.apply(); // or commit()
        // save the edits!! very important!!
    }
}