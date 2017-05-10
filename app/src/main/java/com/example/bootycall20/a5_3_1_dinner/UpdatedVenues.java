package com.example.bootycall20.a5_3_1_dinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.bootycall20.a5_3_1_dinner.FirebaseUtility.mChoicesId;

/**
 * Created by BootyCall2.0 on 3/14/2017.
 */

public class UpdatedVenues extends AppCompatActivity implements View.OnClickListener {

    public String userKey;
    public int itemsTouched;
    public Boolean isChoice1Clicked;
    public Boolean isChoice2Clicked;
    public Boolean isChoice3Clicked;
    public Boolean isChoice4Clicked;
    public Boolean isChoice5Clicked;
    TextView tvChoice1;
    TextView tvChoice2;
    TextView tvChoice3;
    TextView tvChoice4;
    TextView tvChoice5;
    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_options_5);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        userKey = mChoicesId;

        itemsTouched = 0;


        tvChoice1 = (TextView) findViewById(R.id.choice1);
        tvChoice2 = (TextView) findViewById(R.id.choice2);
        tvChoice3 = (TextView) findViewById(R.id.choice3);
        tvChoice4 = (TextView) findViewById(R.id.choice4);
        tvChoice5 = (TextView) findViewById(R.id.choice5);

        tvChoice1.setOnClickListener(this);
        tvChoice2.setOnClickListener(this);
        tvChoice3.setOnClickListener(this);
        tvChoice4.setOnClickListener(this);
        tvChoice5.setOnClickListener(this);


    }

    @Override
    public void onStart() {
        super.onStart();

        mFirebaseDatabase.child("choices").child(userKey).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ChoicesObject choices = dataSnapshot.getValue(ChoicesObject.class);

                isChoice1Clicked = VenueOptions.isChoice1Clicked;
                isChoice2Clicked = VenueOptions.isChoice2Clicked;
                isChoice3Clicked = VenueOptions.isChoice3Clicked;
                isChoice4Clicked = VenueOptions.isChoice4Clicked;
                isChoice5Clicked = VenueOptions.isChoice5Clicked;


                if (isChoice1Clicked) {
                    tvChoice1.setText(choices.choice1);
                    tvChoice1.setClickable(true);
                }
                if (isChoice2Clicked) {
                    tvChoice2.setText(choices.choice2);
                    tvChoice1.setClickable(true);
                }
                if (isChoice3Clicked) {
                    tvChoice3.setText(choices.choice3);
                    tvChoice1.setClickable(true);
                }
                if (isChoice4Clicked) {
                    tvChoice4.setText(choices.choice4);
                    tvChoice1.setClickable(true);
                }
                if (isChoice5Clicked) {
                    tvChoice5.setText(choices.choice5);
                    tvChoice1.setClickable(true);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

    public void finalVenue(View view) {
        Intent intent = new Intent(this, FinalVenue.class);
        startActivity(intent);
    }

    private void buttonsArePressed(View view) {

        String logMessage = "Clicked: " + view.getId();

        view.setBackgroundColor(getResources().getColor(R.color.primary));
        Toast.makeText(UpdatedVenues.this, logMessage, Toast.LENGTH_SHORT).show();
        itemsTouched++;
    }

    @Override
    public void onClick(View v) {

        if (itemsTouched != 1) {

            if (v == tvChoice1) {
                buttonsArePressed(tvChoice1);
                isChoice1Clicked = true;
            }
            if (v == tvChoice2) {
                buttonsArePressed(tvChoice2);
                isChoice2Clicked = true;
            }
            if (v == tvChoice3) {
                buttonsArePressed(tvChoice3);
                isChoice3Clicked = true;
            }
            if (v == tvChoice4) {
                buttonsArePressed(tvChoice4);
                isChoice4Clicked = true;
            }
            if (v == tvChoice5) {
                buttonsArePressed(tvChoice5);
                isChoice5Clicked = true;

            }

        } else if (itemsTouched == 1) {

            Toast.makeText(UpdatedVenues.this, "Hit the button to move on!", Toast.LENGTH_SHORT).show();
        }

    }


}


