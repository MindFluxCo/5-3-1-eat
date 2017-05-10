package com.example.bootycall20.a5_3_1_dinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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

public class VenueOptions extends AppCompatActivity implements View.OnTouchListener{

    public String userKey;
    TextView tvChoice1;
    TextView tvChoice2;
    TextView tvChoice3;
    TextView tvChoice4;
    TextView tvChoice5;
    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_options_display);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        userKey = mChoicesId;


        tvChoice1 = (TextView) findViewById(R.id.choice1);
        tvChoice2 = (TextView) findViewById(R.id.choice2);
        tvChoice3 = (TextView) findViewById(R.id.choice3);
        tvChoice4 = (TextView) findViewById(R.id.choice4);
        tvChoice5 = (TextView) findViewById(R.id.choice5);

        tvChoice1.setOnTouchListener(this);
        tvChoice2.setOnTouchListener(this);
        tvChoice3.setOnTouchListener(this);
        tvChoice4.setOnTouchListener(this);
        tvChoice5.setOnTouchListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();

        mFirebaseDatabase.child("choices").child(userKey).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ChoicesObject choices = dataSnapshot.getValue(ChoicesObject.class);

                tvChoice1.setText(choices.choice1);
                tvChoice2.setText(choices.choice2);
                tvChoice3.setText(choices.choice3);
                tvChoice4.setText(choices.choice4);
                tvChoice5.setText(choices.choice5);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        tvChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(VenueOptions.this, "Clicked 1", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int itemsTouched = 0;

        while (itemsTouched != 3){
            if (v == tvChoice1){

                Toast.makeText(VenueOptions.this, "Clicked 1", Toast.LENGTH_SHORT).show();
                v.setBackgroundColor(getResources().getColor(R.color.primary));
                itemsTouched++;

            }

            if (v == tvChoice2){

                Toast.makeText(VenueOptions.this, "Clicked 2", Toast.LENGTH_SHORT).show();
                itemsTouched++;

            }

            if (v == tvChoice3){

                Toast.makeText(VenueOptions.this, "Clicked 3", Toast.LENGTH_SHORT).show();
                itemsTouched++;

            }

            if (v == tvChoice4){

                Toast.makeText(VenueOptions.this, "Clicked 4", Toast.LENGTH_SHORT).show();
                itemsTouched++;

            }

            if (v == tvChoice5){

                Toast.makeText(VenueOptions.this, "Clicked 5", Toast.LENGTH_SHORT).show();
                itemsTouched++;

            }

        }

        Toast.makeText(VenueOptions.this, "loop ended", Toast.LENGTH_SHORT).show();

        return true;
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
