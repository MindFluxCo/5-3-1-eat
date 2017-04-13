package com.example.bootycall20.a5_3_1_dinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BootyCall2.0 on 3/14/2017.
 */

public class VenueOptions extends AppCompatActivity {

    public static List<String> userInput;
    String venueText;
    EditText venueOption1;
    EditText venueOption2;
    EditText venueOption3;
    EditText venueOption4;
    EditText venueOption5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_options_display);

        userInput = new ArrayList(4);
        venueText = new String();

        venueOption1 = (EditText) findViewById(R.id.venueOption1);
        venueOption2 = (EditText) findViewById(R.id.venueOption2);
        venueOption3 = (EditText) findViewById(R.id.venueOption3);
        venueOption4 = (EditText) findViewById(R.id.venueOption4);
        venueOption5 = (EditText) findViewById(R.id.venueOption5);

        ToggleButton venueButton1 = (ToggleButton) findViewById(R.id.venueButton1);
        ToggleButton venueButton2 = (ToggleButton) findViewById(R.id.venueButton2);
        ToggleButton venueButton3 = (ToggleButton) findViewById(R.id.venueButton3);
        ToggleButton venueButton4 = (ToggleButton) findViewById(R.id.venueButton4);
        ToggleButton venueButton5 = (ToggleButton) findViewById(R.id.venueButton5);

        venueText = userInput.get(0);
        venueButton1.setTextOn(venueText);
        venueButton2.setTextOn(venueText);
        venueButton3.setTextOn(venueText);
        venueButton4.setTextOn(venueText);
        venueButton5.setTextOn(venueText);

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
        // automatically handle clicks on the Home/Up UpdateVenueButton, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void venueOptions5(View view) {
        Intent intent = new Intent(this, UpdatedVenues.class);
        startActivity(intent);
    }

}
