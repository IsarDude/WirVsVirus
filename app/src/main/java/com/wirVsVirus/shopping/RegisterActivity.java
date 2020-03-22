package com.wirVsVirus.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static com.wirVsVirus.shopping.Client.IP;
import static com.wirVsVirus.shopping.Client.PORT;

public class RegisterActivity extends AppCompatActivity {

    EditText emailRegister,passwordRegister,storeStreet,storeNumber,storePlz;

    Button registrieren;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        emailRegister = findViewById(R.id.emailRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        registrieren = findViewById(R.id.btnSignUp);
        storeNumber= findViewById(R.id.nr);
        storePlz=findViewById(R.id.plz);
        storeStreet=findViewById(R.id.street);


    }

    public void register(View view){
        String email = emailRegister.getText().toString();
        String password = passwordRegister.getText().toString();

        if(email.isEmpty()){
            emailRegister.setError("Geben Sie bitte eine Email an");
            emailRegister.requestFocus();
        }
        if(password.isEmpty()){
            passwordRegister.setError("Geben Sie bitte ein Passwort an");
            passwordRegister.requestFocus();
        }

        else{
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registrierung erfolgreich", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),StaffActivity.class));
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            String street = storeStreet.getText().toString();
                            String nr = storeNumber.getText().toString();
                            String plz = storePlz.getText().toString();
                            String id = user.getUid();
                            Socket sock = null;
                            try {
                                sock = new Socket(IP, PORT);


                            PrintWriter socketOut = new PrintWriter(sock.getOutputStream(), true);
                            socketOut.println(0);
                            socketOut.println(street);
                            socketOut.println(nr);
                            socketOut.println(plz);
                            socketOut.println(id);
                            socketOut.close();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        }
                    } else{
                        Toast.makeText(RegisterActivity.this, "Registrierung fehlgeschlagen", Toast.LENGTH_SHORT).show();
                    }

                }


            });


        }


    }



    public void backToLogin(View view){
        finish();
    }
}