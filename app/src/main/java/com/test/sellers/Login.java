package com.test.sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    EditText text1,text2;
    Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        text1=(EditText)findViewById(R.id.t1);
        text2=(EditText)findViewById(R.id.t2);
        log=(Button)findViewById(R.id.log);
       log.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(TextUtils.isEmpty(text1.getText().toString())){
                   Toast.makeText(Login.this,"Please Enter E-mail",Toast.LENGTH_SHORT).show();
               }
               else if(Patterns.EMAIL_ADDRESS.matcher(text2.toString()).matches()){
                   Toast.makeText(Login.this,"Please Enter valid  E-mail",Toast.LENGTH_SHORT).show();
               }
               if(TextUtils.isEmpty(text2.getText().toString())){
                   Toast.makeText(Login.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
               }

               else {
                   loginn();
               }
           }
       });

    }
    private void loginn(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(text1.getText().toString(),text2.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(Login.this,"Login onSuccess",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,dashboard.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this,"onFailure"+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

    }
}
