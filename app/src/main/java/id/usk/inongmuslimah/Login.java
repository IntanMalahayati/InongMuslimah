package id.usk.inongmuslimah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://inong-muslimah-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final Button loginBtn = findViewById(R.id.loginBtn);
        final TextView openRegBtn = findViewById(R.id.openRegBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final String emailTxt = email.getText().toString();
               final String passwordTxt = password.getText().toString();

               if(emailTxt.isEmpty() || passwordTxt.isEmpty()) {
                   Toast.makeText(Login.this, "Please enter your email or password", Toast.LENGTH_SHORT).show();
               }
               else{
                   databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if(!emailTxt.isEmpty()) {
                               final String getPassword = password.getText().toString();

                               if(getPassword.equals(passwordTxt)) {
                                   Toast.makeText(Login.this, "Login success", Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(Login.this, Dashboard.class));
                               }
                               else {
                                   Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();

                               }
                           }
                           else {
                               Toast.makeText(Login.this, "Wrong email", Toast.LENGTH_SHORT).show();
                           }
                       }
                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {
                           Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                       }
                   });
               }

           }
        });

        openRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //direct to register
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }

}
