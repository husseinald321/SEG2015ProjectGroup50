package com.example.mytable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CookRegisterScreen extends AppCompatActivity {

    Cook cook;
    Address cookAddress;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = database.getReference("users");
    List<Cook> cooks = new ArrayList<Cook>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_register_screen);
        cook = new Cook();
        cookAddress = new Address();
    }

    public void onStart() {
        super.onStart();
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Clearing previously stored list of clients.
                cooks.clear();
                for (DataSnapshot postSnapShot : snapshot.getChildren()){
                    //get each client
                    Cook cook = postSnapShot.getValue(Cook.class);
                    //add updated client to list
                    cooks.add(cook);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                final String TAG = "Couldn't fetch list of cooks";
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });

    }

    public void registerComplete(View v) {
        EditText cookFirstName = (EditText)findViewById(R.id.cookFirstName);
        TextView cookFirstNameInvalid = (TextView)findViewById(R.id.cookFirstNameInvalid);
        boolean firstNameValid = cook.setFirstName(cookFirstName.getText().toString());

        if (!firstNameValid) {

            cookFirstNameInvalid.setVisibility(cookFirstNameInvalid.VISIBLE);

        } else {
            cookFirstNameInvalid.setVisibility(cookFirstNameInvalid.GONE);
        }

        EditText cookLastName = (EditText)findViewById(R.id.cookLastName);
        TextView cookLastNameInvalid = (TextView)findViewById(R.id.cookLastNameInvalid);
        boolean lastNameValid = cook.setLastName(cookLastName.getText().toString());

        if (!lastNameValid) {

            cookLastNameInvalid.setVisibility(cookLastNameInvalid.VISIBLE);

        } else {
            cookLastNameInvalid.setVisibility(cookLastNameInvalid.GONE);
        }

        EditText cookEmail = (EditText)findViewById(R.id.cookEmail);
        TextView cookEmailInvalid = (TextView)findViewById(R.id.cookEmailInvalid);
        boolean emailValid = cook.setEmail(cookEmail.getText().toString());

        if (!emailValid) {

            cookEmailInvalid.setVisibility(cookEmailInvalid.VISIBLE);

        } else {
            cookEmailInvalid.setVisibility(cookEmailInvalid.GONE);
        }

        EditText cookPassword1 = (EditText)findViewById(R.id.cookPassword1);
        EditText cookPassword2 = (EditText)findViewById(R.id.cookPassword2);
        LinearLayout cookPasswordInvalid = (LinearLayout)findViewById(R.id.cookPasswordInvalid);
        TextView cookPasswordNoMatch = (TextView)findViewById(R.id.cookPasswordNoMatch);

        boolean passwordValid = cook.setPassword(cookPassword1.getText().toString());
        boolean passwordsMatch = cookPassword1.getText().toString().equals(cookPassword2.getText().toString());

        if (!passwordValid) {

            cookPasswordInvalid.setVisibility(cookPasswordInvalid.VISIBLE);

        } else {
            cookPasswordInvalid.setVisibility(cookPasswordInvalid.GONE);
        }
        if (!passwordsMatch) {

            cookPasswordNoMatch.setVisibility(cookPasswordNoMatch.VISIBLE);

        } else {
            cookPasswordNoMatch.setVisibility(cookPasswordNoMatch.GONE);
        }

        EditText cookCountry = (EditText)findViewById(R.id.cookCountry);
        TextView cookCountryInvalid = (TextView)findViewById(R.id.cookCountryInvalid);

        boolean countryValid = cookAddress.setCountry(cookCountry.getText().toString());

        if (!countryValid) {
            cookCountryInvalid.setVisibility(cookCountryInvalid.VISIBLE);
        } else {
            cookCountryInvalid.setVisibility(cookCountryInvalid.GONE);
        }

        EditText cookProvince = (EditText)findViewById(R.id.cookProvince);
        TextView cookProvinceInvalid = (TextView)findViewById(R.id.cookProvinceInvalid);

        boolean provinceValid = cookAddress.setProvince(cookProvince.getText().toString());

        if (!provinceValid) {
            cookProvinceInvalid.setVisibility(cookProvinceInvalid.VISIBLE);
        } else {
            cookProvinceInvalid.setVisibility(cookProvinceInvalid.GONE);
        }

        EditText cookCity = (EditText)findViewById(R.id.cookCity);
        TextView cookCityInvalid = (TextView)findViewById(R.id.cookCityInvalid);

        boolean cityValid = cookAddress.setCity(cookCity.getText().toString());

        if (!cityValid) {
            cookCityInvalid.setVisibility(cookCityInvalid.VISIBLE);
        } else {
            cookCityInvalid.setVisibility(cookCityInvalid.GONE);
        }

        EditText cookStreetName = (EditText)findViewById(R.id.cookStreetName);
        TextView cookStreetNameInvalid = (TextView)findViewById(R.id.cookStreetNameInvalid);

        boolean streetNameValid = cookAddress.setStreet(cookStreetName.getText().toString());

        if (!streetNameValid) {
            cookStreetNameInvalid.setVisibility(cookStreetNameInvalid.VISIBLE);
        } else {
            cookStreetNameInvalid.setVisibility(cookStreetNameInvalid.GONE);
        }

        EditText cookStreetNumber = (EditText)findViewById(R.id.cookStreetNumber);
        TextView cookStreetNumberInvalid = (TextView)findViewById(R.id.cookStreetNumberInvalid);

        boolean streetNumberValid = cookAddress.setNumber(cookStreetNumber.getText().toString());

        if (!streetNumberValid) {
            cookStreetNumberInvalid.setVisibility(cookStreetNumberInvalid.VISIBLE);
        } else {
            cookStreetNumberInvalid.setVisibility(cookStreetNumberInvalid.GONE);
        }

        EditText cookUnitNumber = (EditText)findViewById(R.id.cookUnitNumber);

        cookAddress.setUnit(cookUnitNumber.getText().toString());
        cookAddress.setPostalCode("ABC 123");
        cook.setCookAddress(cookAddress);
        cook.setUserType("Cook");
        if(firstNameValid && lastNameValid && emailValid && passwordValid && passwordsMatch && countryValid && provinceValid && cityValid && streetNameValid && streetNumberValid) {
            System.out.println("All fields valid");
            postNewCook(cook);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

    }

    public void postNewCook(Cook newCook){
        String userId = dbRef.push().getKey();
        dbRef.child("cooks").child(userId).setValue(newCook);
    }


}

