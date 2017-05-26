package com.example.bootycall20.a5_3_1_dinner;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.bootycall20.a5_3_1_dinner.FirebaseUtility.mChoicesId;


public class VenueOptions extends AppCompatActivity implements View.OnClickListener {


    //When views are created, mark true/flase for text filling
    public static Boolean isChoice1Clicked;
    public static Boolean isChoice2Clicked;
    public static Boolean isChoice3Clicked;
    public static Boolean isChoice4Clicked;
    public static Boolean isChoice5Clicked;

    //Unique ID for Firebase Database Post
    public String userKey;

    //Counts how many items are selected to restrict moving on if requirements aren't met/
    public int itemsTouched;


    //View Bindings
    @BindView(R.id.choice1) TextView tvChoice1;
    @BindView(R.id.choice2) TextView tvChoice2;
    @BindView(R.id.choice3) TextView tvChoice3;
    @BindView(R.id.choice4) TextView tvChoice4;
    @BindView(R.id.choice5) TextView tvChoice5;

    @BindView(R.id.button2)
    Button button2;

    //Firebase database reference
    private DatabaseReference mFirebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_options_display);
        ButterKnife.bind(this);

        button2.setVisibility(View.VISIBLE);

        isChoice1Clicked = false;
        isChoice2Clicked = false;
        isChoice3Clicked = false;
        isChoice4Clicked = false;
        isChoice5Clicked = false;

        tvChoice1.setVisibility(View.VISIBLE);
        tvChoice2.setVisibility(View.VISIBLE);
        tvChoice3.setVisibility(View.VISIBLE);
        tvChoice4.setVisibility(View.VISIBLE);
        tvChoice5.setVisibility(View.VISIBLE);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        userKey = mChoicesId;

        itemsTouched = 0;


        tvChoice1.setOnClickListener(this);
        tvChoice2.setOnClickListener(this);
        tvChoice3.setOnClickListener(this);
        tvChoice4.setOnClickListener(this);
        tvChoice5.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();

        mFirebaseDatabase.child(userKey).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ChoicesDetail choice1 = dataSnapshot.child("choice1").getValue(ChoicesDetail.class);
                ChoicesDetail choice2 = dataSnapshot.child("choice2").getValue(ChoicesDetail.class);
                ChoicesDetail choice3 = dataSnapshot.child("choice3").getValue(ChoicesDetail.class);
                ChoicesDetail choice4 = dataSnapshot.child("choice4").getValue(ChoicesDetail.class);
                ChoicesDetail choice5 = dataSnapshot.child("choice5").getValue(ChoicesDetail.class);

                String choice1Name = choice1.name;
                String choice2Name = choice2.name;
                String choice3Name = choice3.name;
                String choice4Name = choice4.name;
                String choice5Name = choice5.name;


                tvChoice1.setText(choice1Name);
                tvChoice1.setContentDescription(choice1Name);
                tvChoice2.setText(choice2Name);
                tvChoice2.setContentDescription(choice2Name);
                tvChoice3.setText(choice3Name);
                tvChoice3.setContentDescription(choice3Name);
                tvChoice4.setText(choice4Name);
                tvChoice4.setContentDescription(choice4Name);
                tvChoice5.setText(choice5Name);
                tvChoice5.setContentDescription(choice5Name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //If 3 views are not selected you cannot move on to the next page
    @Override
    public void onClick(View v) {

        if (itemsTouched != 3) {

            if (v == tvChoice1){
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

        } else if (itemsTouched == 3) {

            Toast.makeText(VenueOptions.this, R.string.venue_options_button_toast, Toast.LENGTH_SHORT).show();
        }

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
        if (itemsTouched == 3) {
            Intent intent = new Intent(this, UpdatedVenues.class);
            startActivity(intent);
        } else {
            Toast.makeText(VenueOptions.this, R.string.venue_options_toast, Toast.LENGTH_SHORT).show();
        }

    }

    //When a view is pressed ad a background color to indicate selections
    private void buttonsArePressed(View view) {

        view.setBackgroundColor(getResources().getColor(R.color.primary));
        itemsTouched++;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}
