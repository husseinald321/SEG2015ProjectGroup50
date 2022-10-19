package com.example.mytable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginScreen extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = database.getReference("users");
    List<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    public void onStart() {
        super.onStart();
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Clearing previously stored list of users.
                users.clear();
                for (DataSnapshot postSnapShot : snapshot.getChildren()){
                    //get each user
                    User user = postSnapShot.getValue(User.class);
                    //add updated user to list
                    users.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                final String TAG = "Couldn't fetch users";
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });

    }

    public void attemptLogin(View v){
        EditText userEmail = (EditText)findViewById(R.id.userEmail);
        EditText userPassword = (EditText)findViewById(R.id.userPassword);
        TextView successfulLogin = (TextView)findViewById(R.id.successLogin);
        TextView failedLogin = (TextView)findViewById(R.id.failedLogin);

        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        User foundUser = new User();
        for (User user: users) {
            System.out.println(user.getEmail());
        }
    }
}