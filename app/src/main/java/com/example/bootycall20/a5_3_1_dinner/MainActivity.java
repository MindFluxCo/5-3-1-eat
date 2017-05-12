package com.example.bootycall20.a5_3_1_dinner;

import android.content.Context;
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



public class MainActivity extends AppCompatActivity {

    public int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    public String mPlaceName;
    public String mPlaceAdress;
    public String choice1;
    public String choice2;
    public String choice3;
    public String choice4;
    public String choice5;
    public Boolean isChoice2Clicked = false;
    public Boolean isChoice3Clicked = false;
    public Boolean isChoice4Clicked = false;
    public Boolean isChoice5Clicked = false;
    public Boolean isChoice1Clicked = false;

    private AdView mAdView;

    private Context mContext;

    @BindView(R.id.editChoice1)
    TextView editChoice1;
    @BindView(R.id.editChoice2)
    TextView editChoice2;
    @BindView(R.id.editChoice3)
    TextView editChoice3;
    @BindView(R.id.editChoice4)
    TextView editChoice4;
    @BindView(R.id.editChoice5)
    TextView editChoice5;
    Button UpdateVenueButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        editChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChoice1Clicked = true;
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
        });

        editChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChoice2Clicked = true;

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
        });

        editChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChoice3Clicked = true;

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
        });

        editChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChoice4Clicked = true;

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
        });

        editChoice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChoice5Clicked = true;

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
        });

//        loads test adds into AdView
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

//        additional code to attatch listener to AdView
        //        mAdView.setAdListener(new ToastAdListener(this));

        UpdateVenueButton = (Button) findViewById(R.id.button);

        UpdateVenueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isChoice1Clicked = true){
                    FirebaseUtility.updateChoices(choice1, choice2, choice3, choice4, choice5);

                    Intent intent = new Intent(v.getContext(), VenueOptions.class);
                    startActivity(intent);

                }

                if(isChoice1Clicked = false){
                    Toast.makeText(mContext, "No venues entered. Please enter a Venue", Toast.LENGTH_SHORT).show();
                }

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                mPlaceName = place.getName().toString();
                mPlaceAdress = place.getAddress().toString();

                if (isChoice1Clicked) {
                    editChoice1.setText(mPlaceName);
                    choice1 = mPlaceName;
                    Log.v("Places", "Place: " + mPlaceName);
                    Log.v("Places", "Place: " + mPlaceAdress);
                    isChoice1Clicked = false;
                }
                if (isChoice2Clicked) {
                    editChoice2.setText(mPlaceName);
                    choice2 = mPlaceName;
                    Log.v("Places", "Place: " + mPlaceName);
                    Log.v("Places", "Place: " + mPlaceAdress);
                    isChoice2Clicked = false;
                }
                if (isChoice3Clicked) {
                    editChoice3.setText(mPlaceName);
                    choice3 = mPlaceName;
                    Log.v("Places", "Place: " + mPlaceName);
                    Log.v("Places", "Place: " + mPlaceAdress);
                    isChoice3Clicked = false;
                }
                if (isChoice4Clicked) {
                    editChoice4.setText(mPlaceName);
                    choice4 = mPlaceName;
                    Log.v("Places", "Place: " + mPlaceName);
                    Log.v("Places", "Place: " + mPlaceAdress);
                    isChoice4Clicked = false;
                }
                if (isChoice5Clicked) {
                    editChoice5.setText(mPlaceName);
                    choice5 = mPlaceName;
                    Log.v("Places", "Place: " + mPlaceName);
                    Log.v("Places", "Place: " + mPlaceAdress);
                    isChoice5Clicked = false;
                }


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.v("Places", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
}
