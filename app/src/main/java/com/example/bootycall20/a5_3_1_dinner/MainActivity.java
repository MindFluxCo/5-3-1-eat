package com.example.bootycall20.a5_3_1_dinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class  MainActivity extends AppCompatActivity {

    public int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    //Places API Variables
    public String mPlaceName;
    public String mPlaceAdress;

    // Objects for Firebase Database
    public String name1;
    public String adress1;
    public String name2;
    public String adress2;
    public String name3;
    public String adress3;
    public String name4;
    public String adress4;
    public String name5;
    public String adress5;

    public ChoicesDetail choice1;
    public ChoicesDetail choice2;
    public ChoicesDetail choice3;
    public ChoicesDetail choice4;
    public ChoicesDetail choice5;


    //When buttons are clicked, mark true/flase for text filling
    public Boolean isChoice1Clicked = false;
    public Boolean isChoice2Clicked = false;
    public Boolean isChoice3Clicked = false;
    public Boolean isChoice4Clicked = false;
    public Boolean isChoice5Clicked = false;



    //View Bindings
    @BindView(R.id.editChoice1) TextView editChoice1;
    @BindView(R.id.editChoice2) TextView editChoice2;
    @BindView(R.id.editChoice3) TextView editChoice3;
    @BindView(R.id.editChoice4) TextView editChoice4;
    @BindView(R.id.editChoice5) TextView editChoice5;
    @BindView(R.id.button) Button updateVenueButton;


    //Variables for checking if all views are clicked to move on
    private Boolean isChoice1Filled = false;
    private Boolean isChoice2Filled = false;
    private Boolean isChoice3Filled = false;
    private Boolean isChoice4Filled = false;
    private Boolean isChoice5Filled = false;

    private AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Loads Ads(test) Into AdView
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        //Moves the activity on to Venue Options
        updateVenueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isChoice1Filled && isChoice2Filled &&
                        isChoice3Filled && isChoice4Filled && isChoice5Filled) {

                    FirebaseUtility.updateChoice(choice1, choice2, choice3, choice4, choice5);
                    Intent intent = new Intent(v.getContext(), FiveOptions.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(MainActivity.this, R.string.main_activity_button_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @OnClick({R.id.editChoice1, R.id.editChoice2, R.id.editChoice3, R.id.editChoice4,
            R.id.editChoice5,})
    public void setChoices(View v) {

        if(v == editChoice1){
            isChoice1Clicked = true;
            startPlacesUi();
        }
        if(v == editChoice2){
            isChoice2Clicked = true;
            startPlacesUi();
        }
        if(v == editChoice3){
            isChoice3Clicked = true;
            startPlacesUi();
        }
        if(v == editChoice4){
            isChoice4Clicked = true;
            startPlacesUi();
        }
        if(v == editChoice5){
            isChoice5Clicked = true;
            startPlacesUi();
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
            Intent intent = new Intent(this, HowToActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // If Google Place API can connect
                // Get the name and address
                Place place = PlaceAutocomplete.getPlace(this, data);
                mPlaceName = place.getName().toString();
                mPlaceAdress = place.getAddress().toString();

                // When a certain view is clicked, set Name, Address, and mark as unclicked
                // and filled when finished

                if (isChoice1Clicked) {
                    editChoice1.setText(mPlaceName);
                    name1 = mPlaceName;
                    adress1 = mPlaceAdress;
                    choice1 = new ChoicesDetail(name1, adress1);
                    Log.v("Places", "Place: " + mPlaceName);
                    Log.v("Places", "Place: " + mPlaceAdress);
                    isChoice1Clicked = false;
                    isChoice1Filled = true;
                }
                if (isChoice2Clicked) {
                    editChoice2.setText(mPlaceName);
                    name2 = mPlaceName;
                    adress2 = mPlaceAdress;
                    choice2 = new ChoicesDetail(name2, adress2);
                    Log.v("Places", "Place: " + mPlaceName);
                    Log.v("Places", "Place: " + mPlaceAdress);
                    isChoice2Clicked = false;
                    isChoice2Filled = true;
                }
                if (isChoice3Clicked) {
                    editChoice3.setText(mPlaceName);
                    name3 = mPlaceName;
                    adress3 = mPlaceAdress;
                    choice3 = new ChoicesDetail(name3, adress3);
                    Log.v("Places", "Place: " + mPlaceName);
                    Log.v("Places", "Place: " + mPlaceAdress);
                    isChoice3Clicked = false;
                    isChoice3Filled = true;
                }
                if (isChoice4Clicked) {
                    editChoice4.setText(mPlaceName);
                    name4 = mPlaceName;
                    adress4 = mPlaceAdress;
                    choice4 = new ChoicesDetail(name4, adress4);
                    Log.v("Places", "Place: " + mPlaceName);
                    Log.v("Places", "Place: " + mPlaceAdress);
                    isChoice4Clicked = false;
                    isChoice4Filled = true;
                }
                if (isChoice5Clicked) {
                    editChoice5.setText(mPlaceName);
                    name5 = mPlaceName;
                    adress5 = mPlaceAdress;
                    choice5 = new ChoicesDetail(name5, adress5);
                    Log.v("Places", "Place: " + mPlaceName);
                    Log.v("Places", "Place: " + mPlaceAdress);
                    isChoice5Clicked = false;
                    isChoice5Filled = true;
                }


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                isChoice1Clicked = false;
                isChoice2Clicked = false;
                isChoice3Clicked = false;
                isChoice4Clicked = false;
                isChoice5Clicked = false;
                Log.v("Places", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
                Log.v("Places", "user cancelled");

                // Mark as not clicked and not filled so user cannot move on to next screen
                // until text is inputted

                isChoice1Clicked = false;
                isChoice2Clicked = false;
                isChoice3Clicked = false;
                isChoice4Clicked = false;
                isChoice5Clicked = false;


            }
        }
    }

    private void startPlacesUi(){
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(MainActivity.this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);

        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }
}
