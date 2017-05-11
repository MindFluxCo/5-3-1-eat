package com.example.bootycall20.a5_3_1_dinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.venueOption1)
    EditText venueOption1;
    @BindView(R.id.venueOption2)
    EditText venueOption2;
    @BindView(R.id.venueOption3)
    EditText venueOption3;
    @BindView(R.id.venueOption4)
    EditText venueOption4;
    @BindView(R.id.venueOption5)
    EditText venueOption5;

    Button UpdateVenueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        UpdateVenueButton = (Button) findViewById(R.id.button);

        UpdateVenueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String choice1 = venueOption1.getText().toString();
                String choice2 = venueOption2.getText().toString();
                String choice3 = venueOption3.getText().toString();
                String choice4 = venueOption4.getText().toString();
                String choice5 = venueOption5.getText().toString();

                FirebaseUtility.updateChoices(choice1, choice2, choice3, choice4, choice5);

                Intent intent = new Intent(v.getContext(), VenueOptions.class);
                startActivity(intent);
            }
        });


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
}
