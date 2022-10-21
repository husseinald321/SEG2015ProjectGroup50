package com.example.mytable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CookRegisterScreen extends AppCompatActivity {

    private Cook cook;
    private Address cookAddress;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = database.getReference("users");
    private DatabaseReference images = database.getReference("images");
    ImageView voidChequeImage;

    private List<Cook> cooks = new ArrayList<Cook>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_register_screen);
        cook = new Cook();
        cookAddress = new Address();
        voidChequeImage = (ImageView) findViewById(R.id.voidChequeImage);
    }

    public void onStart() {
        super.onStart();
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Clearing previously stored list of cooks.
                cooks.clear();
                for (DataSnapshot postSnapShot : snapshot.getChildren()){
                    //get each cook
                    Cook cook = postSnapShot.getValue(Cook.class);
                    //add updated cook to list
                    cooks.add(cook);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                final String TAG = "Error:fetchlistofcooks";
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            voidChequeImage.setImageBitmap(captureImage);

        }

        if (requestCode == 200 && data != null) {
            Uri selectedImage = data.getData();
            voidChequeImage.setImageURI(selectedImage);
        }
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

        EditText cookPostalCode = (EditText)findViewById(R.id.cookPostalCode);
        TextView cookPostalCodeInvalid = (TextView)findViewById(R.id.cookPostalCodeInvalid);

        boolean cookPostalCodeValid = cookAddress.setPostalCode(cookPostalCode.getText().toString());

        if (!cookPostalCodeValid) {
            cookStreetNumberInvalid.setVisibility(cookPostalCodeInvalid.VISIBLE);
        } else {
            cookStreetNumberInvalid.setVisibility(cookPostalCodeInvalid.GONE);
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
        dbRef.child(userId).setValue(newCook);
//        voidChequeImage.setDrawingCacheEnabled(true);
//        voidChequeImage.buildDrawingCache();
//        Bitmap bitmap = ((BitmapDrawable) voidChequeImage.getDrawable()).getBitmap();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] data = baos.toByteArray();
//
//        Task uploadTask = images.;
    }

    public void takePhoto(View v) {
        if (ContextCompat.checkSelfPermission(CookRegisterScreen.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CookRegisterScreen.this, new String[]{
                    Manifest.permission.CAMERA
            },
                    100);
        }
        
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }

    public void importPhoto(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 200);



    }



}

