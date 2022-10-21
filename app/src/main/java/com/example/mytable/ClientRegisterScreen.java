package com.example.mytable;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClientRegisterScreen extends AppCompatActivity {

    private Client client;
    private Address clientAddress;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = database.getReference("users");
    private List<Client> clients = new ArrayList<Client>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_register_screen);
        client = new Client();
        clientAddress = new Address();
    }

    public void onStart() {
        super.onStart();
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Clearing previously stored list of clients.
                clients.clear();
                for (DataSnapshot postSnapShot : snapshot.getChildren()){
                    //get each client
                    Client client = postSnapShot.getValue(Client.class);
                    //add updated client to list
                    clients.add(client);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                final String TAG = "Couldn't fetch list of clients";
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });

    }


    public void registerComplete(View v) {
        EditText clientFirstName = (EditText)findViewById(R.id.clientFirstName);
        TextView clientFirstNameInvalid = (TextView)findViewById(R.id.clientFirstNameInvalid);
        boolean firstNameValid = client.setFirstName(clientFirstName.getText().toString());

        if (!firstNameValid) {

            clientFirstNameInvalid.setVisibility(clientFirstNameInvalid.VISIBLE);

        } else {
            clientFirstNameInvalid.setVisibility(clientFirstNameInvalid.GONE);
        }

        EditText clientLastName = (EditText)findViewById(R.id.clientLastName);
        TextView clientLastNameInvalid = (TextView)findViewById(R.id.clientLastNameInvalid);
        boolean lastNameValid = client.setLastName(clientLastName.getText().toString());

        if (!lastNameValid) {

            clientLastNameInvalid.setVisibility(clientLastNameInvalid.VISIBLE);

        } else {
            clientLastNameInvalid.setVisibility(clientLastNameInvalid.GONE);
        }

        EditText clientEmail = (EditText)findViewById(R.id.clientEmail);
        TextView clientEmailInvalid = (TextView)findViewById(R.id.clientEmailInvalid);
        boolean emailValid = client.setEmail(clientEmail.getText().toString());

        if (!emailValid) {

            clientEmailInvalid.setVisibility(clientEmailInvalid.VISIBLE);

        } else {
            clientEmailInvalid.setVisibility(clientEmailInvalid.GONE);
        }

        EditText clientPassword1 = (EditText)findViewById(R.id.clientPassword1);
        EditText clientPassword2 = (EditText)findViewById(R.id.clientPassword2);
        LinearLayout clientPasswordInvalid = (LinearLayout)findViewById(R.id.clientPasswordInvalid);
        TextView clientPasswordNoMatch = (TextView)findViewById(R.id.clientPasswordNoMatch);

        boolean passwordValid = client.setPassword(clientPassword1.getText().toString());
        boolean passwordsMatch = clientPassword1.getText().toString().equals(clientPassword2.getText().toString());

        if (!passwordValid) {

            clientPasswordInvalid.setVisibility(clientPasswordInvalid.VISIBLE);

        } else {
            clientPasswordInvalid.setVisibility(clientPasswordInvalid.GONE);
        }
        if (!passwordsMatch) {

            clientPasswordNoMatch.setVisibility(clientPasswordNoMatch.VISIBLE);

        } else {
            clientPasswordNoMatch.setVisibility(clientPasswordNoMatch.GONE);
        }

        EditText clientCardNumber = (EditText)findViewById(R.id.clientCardNumber);
        TextView clientCardNumberInvalid = (TextView)findViewById(R.id.clientCardNumberInvalid);
        boolean cardNumberValid = client.setCardNumber(clientCardNumber.getText().toString());

        if (!cardNumberValid) {

            clientCardNumberInvalid.setVisibility(clientCardNumberInvalid.VISIBLE);

        } else {
            clientCardNumberInvalid.setVisibility(clientCardNumberInvalid.GONE);
        }

        EditText clientCardCVC = (EditText)findViewById(R.id.clientCardCVC);
        TextView clientCardCVCInvalid = (TextView)findViewById(R.id.clientCardCVCInvalid);
        boolean cardCVCValid = client.setCardCVC(clientCardCVC.getText().toString());

        if (!cardCVCValid) {

            clientCardCVCInvalid.setVisibility(clientCardCVCInvalid.VISIBLE);

        } else {
            clientCardCVCInvalid.setVisibility(clientCardCVCInvalid.GONE);
        }

        EditText clientCardHolderName = (EditText)findViewById(R.id.clientCardHolderName);
        TextView clientCardholderNameInvalid = (TextView)findViewById(R.id.clientCardholderNameInvalid);

        boolean cardHolderNameValid = client.setCardHolderName(clientCardHolderName.getText().toString());

        if (!cardHolderNameValid) {
            clientCardholderNameInvalid.setVisibility(clientCardholderNameInvalid.VISIBLE);
        } else {
            clientCardholderNameInvalid.setVisibility(clientCardholderNameInvalid.GONE);
        }

        EditText clientCardExpirationDateMonth = (EditText)findViewById(R.id.clientCardExpirationDateMonth);
        EditText clientCardExpirationDateYear = (EditText)findViewById(R.id.clientCardExpirationDateYear);
        TextView clientCardExpirationDateInvalid = (TextView)findViewById(R.id.clientCardExpirationDateInvalid);

        boolean cardExpirationDateMonthValid = client.setCardExpirationMonth(clientCardExpirationDateMonth.getText().toString());
        boolean cardExpirationDateYearValid = client.setCardExpirationYear(clientCardExpirationDateYear.getText().toString());

        if (!cardExpirationDateMonthValid | !cardExpirationDateYearValid) {
            clientCardExpirationDateInvalid.setVisibility(clientCardExpirationDateInvalid.VISIBLE);
        } else {
            clientCardExpirationDateInvalid.setVisibility(clientCardExpirationDateInvalid.GONE);
        }

        EditText clientCountry = (EditText)findViewById(R.id.clientCountry);
        TextView clientCountryInvalid = (TextView)findViewById(R.id.clientCountryInvalid);

        boolean countryValid = clientAddress.setCountry(clientCountry.getText().toString());

        if (!countryValid) {
            clientCountryInvalid.setVisibility(clientCountryInvalid.VISIBLE);
        } else {
            clientCountryInvalid.setVisibility(clientCountryInvalid.GONE);
        }

        EditText clientProvince = (EditText)findViewById(R.id.clientProvince);
        TextView clientProvinceInvalid = (TextView)findViewById(R.id.clientProvinceInvalid);

        boolean provinceValid = clientAddress.setProvince(clientProvince.getText().toString());

        if (!provinceValid) {
            clientProvinceInvalid.setVisibility(clientProvinceInvalid.VISIBLE);
        } else {
            clientProvinceInvalid.setVisibility(clientProvinceInvalid.GONE);
        }

        EditText clientCity = (EditText)findViewById(R.id.clientCity);
        TextView clientCityInvalid = (TextView)findViewById(R.id.clientCityInvalid);

        boolean cityValid = clientAddress.setCity(clientCity.getText().toString());

        if (!cityValid) {
            clientCityInvalid.setVisibility(clientCityInvalid.VISIBLE);
        } else {
            clientCityInvalid.setVisibility(clientCityInvalid.GONE);
        }

        EditText clientStreetName = (EditText)findViewById(R.id.clientStreetName);
        TextView clientStreetNameInvalid = (TextView)findViewById(R.id.clientStreetNameInvalid);

        boolean streetNameValid = clientAddress.setStreet(clientStreetName.getText().toString());

        if (!streetNameValid) {
            clientStreetNameInvalid.setVisibility(clientStreetNameInvalid.VISIBLE);
        } else {
            clientStreetNameInvalid.setVisibility(clientStreetNameInvalid.GONE);
        }

        EditText clientStreetNumber = (EditText)findViewById(R.id.clientStreetNumber);
        TextView clientStreetNumberInvalid = (TextView)findViewById(R.id.clientStreetNumberInvalid);

        boolean streetNumberValid = clientAddress.setNumber(clientStreetNumber.getText().toString());

        if (!streetNumberValid) {
            clientStreetNumberInvalid.setVisibility(clientStreetNumberInvalid.VISIBLE);
        } else {
            clientStreetNumberInvalid.setVisibility(clientStreetNumberInvalid.GONE);
        }

        EditText clientPostalCode = (EditText)findViewById(R.id.clientPostalCode);
        TextView clientPostalCodeInvalid = (TextView)findViewById(R.id.clientPostalCodeInvalid);

        boolean clientPostalCodeValid = clientAddress.setPostalCode(clientPostalCode.getText().toString());

        if (!clientPostalCodeValid) {
            clientStreetNumberInvalid.setVisibility(clientPostalCodeInvalid.VISIBLE);
        } else {
            clientStreetNumberInvalid.setVisibility(clientPostalCodeInvalid.GONE);
        }

        EditText clientUnitNumber = (EditText)findViewById(R.id.clientUnitNumber);

        clientAddress.setUnit(clientUnitNumber.getText().toString());
        clientAddress.setPostalCode("ABC 123");
        client.setClientAddress(clientAddress);
        if(firstNameValid && lastNameValid && emailValid && passwordValid && passwordsMatch && cardNumberValid && cardCVCValid && cardExpirationDateMonthValid && cardExpirationDateYearValid && cardHolderNameValid && countryValid && provinceValid && cityValid && streetNameValid && clientPostalCodeValid && streetNumberValid) {
            System.out.println("All fields valid");
            postNewClient(client);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

    }

    public void postNewClient(Client newClient){
        String userId = dbRef.push().getKey();
        dbRef.child(userId).setValue(newClient);
    }


}