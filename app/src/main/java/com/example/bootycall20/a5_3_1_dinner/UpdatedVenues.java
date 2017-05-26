package com.example.bootycall20.a5_3_1_dinner;

import android.content.Intent;
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


public class UpdatedVenues extends AppCompatActivity implements View.OnClickListener {

    public static Boolean isFinalChoice1 = false;
    public static Boolean isFinalChoice2 = false;
    public static Boolean isFinalChoice3 = false;
    public static Boolean isFinalChoice4 = false;
    public static Boolean isFinalChoice5 = false;


    public Boolean isChoice1Clicked;
    public Boolean isChoice2Clicked;
    public Boolean isChoice3Clicked;
    public Boolean isChoice4Clicked;
    public Boolean isChoice5Clicked;

    public String userKey;
    public int itemsTouched;

    @BindView(R.id.choice1) TextView tvChoice1;
    @BindView(R.id.choice2) TextView tvChoice2;
    @BindView(R.id.choice3) TextView tvChoice3;
    @BindView(R.id.choice4) TextView tvChoice4;
    @BindView(R.id.choice5) TextView tvChoice5;
    @BindView(R.id.button2)
    Button button2;

    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_options_display);
        ButterKnife.bind(this);

        setDefaultStates();

        button2.setVisibility(View.VISIBLE);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        userKey = mChoicesId;

        itemsTouched = 0;



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

                isChoice1Clicked = VenueOptions.isChoice1Clicked;
                isChoice2Clicked = VenueOptions.isChoice2Clicked;
                isChoice3Clicked = VenueOptions.isChoice3Clicked;
                isChoice4Clicked = VenueOptions.isChoice4Clicked;
                isChoice5Clicked = VenueOptions.isChoice5Clicked;

                tvChoice1.setClickable(false);
                tvChoice2.setClickable(false);
                tvChoice3.setClickable(false);
                tvChoice4.setClickable(false);
                tvChoice5.setClickable(false);

                prepareChoice(tvChoice1, isChoice1Clicked, choice1Name);
                prepareChoice(tvChoice2, isChoice2Clicked, choice2Name);
                prepareChoice(tvChoice3, isChoice3Clicked, choice3Name);
                prepareChoice(tvChoice4, isChoice4Clicked, choice4Name);
                prepareChoice(tvChoice5, isChoice5Clicked, choice5Name);


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

    public void venueOptions5(View view) {
        if (itemsTouched == 1) {
            Intent intent = new Intent(this, FinalVenue.class);
            startActivity(intent);
        } else {
            Toast.makeText(UpdatedVenues.this, R.string.updated_venues_toast, Toast.LENGTH_SHORT).show();
        }
    }

    private void buttonsArePressed(View view) {

        view.setBackgroundColor(getResources().getColor(R.color.primary));
        itemsTouched++;
    }

    @Override
    public void onClick(View v) {

        if (itemsTouched != 1) {

            if (v == tvChoice1) {
                buttonsArePressed(tvChoice1);
                isChoice1Clicked = true;
                isFinalChoice1 = true;
            }
            if (v == tvChoice2) {
                buttonsArePressed(tvChoice2);
                isChoice2Clicked = true;
                isFinalChoice2 = true;

            }
            if (v == tvChoice3) {
                buttonsArePressed(tvChoice3);
                isFinalChoice3 = true;
                isChoice3Clicked = true;
            }
            if (v == tvChoice4) {
                buttonsArePressed(tvChoice4);
                isFinalChoice4 = true;
                isChoice4Clicked = true;
            }
            if (v == tvChoice5) {
                buttonsArePressed(tvChoice5);
                isFinalChoice5 = true;
                isChoice5Clicked = true;

            }

        } else if (itemsTouched == 1) {

            Toast.makeText(UpdatedVenues.this, R.string.venue_options_button_toast, Toast.LENGTH_SHORT).show();
        }

    }

    //Method to set Text, Content Description, cickability, and visibility
    private void prepareChoice (TextView choiceView, boolean choiceClicked, String choiceName){
        //Selects which view is being filled
        if(choiceClicked){
            //sets view content
            choiceView.setText(choiceName);
            choiceView.setContentDescription(choiceName);
            choiceView.setClickable(true);
            choiceView.setVisibility(View.VISIBLE);

        }
    }

    //Sets the views to their default states
    private void setDefaultStates(){

        tvChoice1.setVisibility(View.INVISIBLE);
        tvChoice2.setVisibility(View.INVISIBLE);
        tvChoice3.setVisibility(View.INVISIBLE);
        tvChoice4.setVisibility(View.INVISIBLE);
        tvChoice5.setVisibility(View.INVISIBLE);

        tvChoice1.setOnClickListener(this);
        tvChoice2.setOnClickListener(this);
        tvChoice3.setOnClickListener(this);
        tvChoice4.setOnClickListener(this);
        tvChoice5.setOnClickListener(this);

    }

}


