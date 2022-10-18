package com.example.mytable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

public class ClientRegisterScreen extends AppCompatActivity {

    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_register_screen);
        client = new Client();
    }

    public void registerComplete(View v) {
        EditText clientName = (EditText)findViewById(R.id.clientName);

        boolean nameValid = client.setName(clientName.getText().toString());

        if (!nameValid) {
            System.out.println("name not valid");
        } else {
            System.out.println("name valid");
        }

        EditText clientEmail = (EditText)findViewById(R.id.clientEmail);

        boolean emailValid = client.setEmail(clientEmail.getText().toString());

        if (!emailValid) {
            System.out.println("email not valid");
        } else {
            System.out.println("email valid");
        }

        EditText clientPassword1 = (EditText)findViewById(R.id.clientPassword1);
        EditText clientPassword2 = (EditText)findViewById(R.id.clientPassword2);

        if (clientPassword1.toString().equals(clientPassword2.toString())) {
            boolean passwordValid = client.setPassword(clientPassword1.toString());

            if (!passwordValid) {
                System.out.println("password not valid");
            } else {
                System.out.println("password valid");
            }

        } else {
            System.out.println("passwords do not match");
        }






    }


}