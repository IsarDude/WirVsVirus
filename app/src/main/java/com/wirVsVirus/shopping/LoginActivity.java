package com.wirVsVirus.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

        EditText emailLogin,passwordLogin;
        Button logIn;
        TextView viewRegister;
        FirebaseAuth.AuthStateListener firebaseAuthListener;
        FirebaseAuth firebaseAuth;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            firebaseAuth = FirebaseAuth.getInstance();

            firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                }
            };


            emailLogin = findViewById(R.id.email);
            passwordLogin = findViewById(R.id.password);
            viewRegister = findViewById(R.id.viewRegister);
            logIn = findViewById(R.id.btnSignIn);
        }

        public void goToRegister(View view){
            startActivity(new Intent(this,RegisterActivity.class));


        }

        public void login(View view){
            String email = emailLogin.getText().toString();
            String password = passwordLogin.getText().toString();
            if(email.isEmpty()){
                emailLogin.setError("Geben Sie bitte eine Email an");
                emailLogin.requestFocus();
            }
            if(password.isEmpty()){
                passwordLogin.setError("Geben Sie bitte ein Passwort an");
                passwordLogin.requestFocus();
            }
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Login erfolgreich", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(),StaffActivity.class));

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Login fehlgeschlagen", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

