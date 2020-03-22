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

public class RegisterActivity extends AppCompatActivity {

    EditText emailRegister,passwordRegister;
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