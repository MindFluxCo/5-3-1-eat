package com.example.bootycall20.a5_3_1_dinner;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



/**
 * Created by LucasVasquez on 5/8/17.
 */

public class FirebaseUtility extends MainActivity {

    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mChoicesRef;
    public static String mChoicesId;




    public static void updateChoices(String userChoice1, String userChoice2, String userChoice3, String userChoice4,
                                     String userChoice5) {


        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mChoicesRef = mFirebaseDatabase.getReference().child("choices");
        mChoicesId = mChoicesRef.push().getKey();

        ChoicesObject choices = new ChoicesObject(userChoice1, userChoice2, userChoice3,
                userChoice4, userChoice5);

        Log.v("List Pushed First: ", userChoice1);
        Log.v("           : ", userChoice2);
        Log.v("           : ", userChoice3);
        Log.v("           : ", userChoice4);
        Log.v("           : ", userChoice5);

        mChoicesRef.child(mChoicesId).setValue(choices);

        Log.v("List Pushed Second: ", userChoice1);
        Log.v("           : ", userChoice2);
        Log.v("           : ", userChoice3);
        Log.v("           : ", userChoice4);
        Log.v("           : ", userChoice5);
    }

}
