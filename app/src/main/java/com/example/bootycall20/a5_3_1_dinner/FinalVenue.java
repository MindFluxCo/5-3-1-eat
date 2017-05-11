package com.example.bootycall20.a5_3_1_dinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.bootycall20.a5_3_1_dinner.FirebaseUtility.mChoicesId;

/**
 * Created by BootyCall2.0 on 3/14/2017.
 */

public class FinalVenue extends AppCompatActivity {

    public String userKey;

    public Boolean isFinalChoice1;
    public Boolean isFinalChoice2;
    public Boolean isFinalChoice3;
    public Boolean isFinalChoice4;
    public Boolean isFinalChoice5;

    @BindView(R.id.choice1)
    TextView tvChoice1;
    @BindView(R.id.choice2)
    TextView tvChoice2;
    @BindView(R.id.choice3)
    TextView tvChoice3;
    @BindView(R.id.choice4)
    TextView tvChoice4;
    @BindView(R.id.choice5)
    TextView tvChoice5;
    @BindView(R.id.button2)
    Button button2;


    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_options_display);
        ButterKnife.bind(this);

        tvChoice3.setVisibility(View.VISIBLE);


        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        userKey = mChoicesId;

    }

    @Override
    public void onStart() {
        super.onStart();

        mFirebaseDatabase.child("choices").child(userKey).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ChoicesObject choices = dataSnapshot.getValue(ChoicesObject.class);

                isFinalChoice1 = UpdatedVenues.isFinalChoice1;
                isFinalChoice2 = UpdatedVenues.isFinalChoice2;
                isFinalChoice3 = UpdatedVenues.isFinalChoice3;
                isFinalChoice4 = UpdatedVenues.isFinalChoice4;
                isFinalChoice5 = UpdatedVenues.isFinalChoice5;


                tvChoice1.setClickable(false);
                tvChoice2.setClickable(false);
                tvChoice3.setClickable(false);
                tvChoice4.setClickable(false);
                tvChoice5.setClickable(false);


                if (isFinalChoice1) {
                    tvChoice3.setText(choices.choice1);
                    tvChoice3.setTextSize(100);
                }
                if (isFinalChoice2) {
                    tvChoice3.setText(choices.choice2);
                    tvChoice3.setTextSize(100);
                }
                if (isFinalChoice3) {
                    tvChoice3.setText(choices.choice3);
                    tvChoice3.setTextSize(100);
                }
                if (isFinalChoice4) {
                    tvChoice3.setText(choices.choice4);
                    tvChoice3.setTextSize(100);
                }
                if (isFinalChoice5) {
                    tvChoice3.setText(choices.choice5);
                    tvChoice3.setTextSize(50);
                }

                button2.setVisibility(View.INVISIBLE);
                button2.setClickable(false);

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

}