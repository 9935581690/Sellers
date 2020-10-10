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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    private EditText text2, text3, text4, text5, text6, text7;
    private Button save;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text2 = (EditText) findViewById(R.id.text2);
        text3 = (EditText) findViewById(R.id.text3);
        text4 = (EditText) findViewById(R.id.text4);
        text5 = (EditText) findViewById(R.id.text5);
        text6 = (EditText) findViewById(R.id.text6);
        text7 =  (EditText)findViewById(R.id.text7);
        save = (Button) findViewById(R.id.Save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                registerSeller();
                if(TextUtils.isEmpty(text2.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Enter Name",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(text3.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Enter Address",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(text4.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Enter E-mail",Toast.LENGTH_SHORT).show();
                }
                else if(Patterns.EMAIL_ADDRESS.matcher(text4.toString()).matches()){
                    Toast.makeText(MainActivity.this,"Please Enter valid  E-mail",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(text5.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(text6.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Enter Business Name",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(text7.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Enter Phone",Toast.LENGTH_SHORT).show();
                }
                else {
                    registration();
                }


            }
        });



    }
    private void registration(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(text4.getText().toString(),text5.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        HashMap<String,Object>map=new HashMap<>();
                        map.put("Name", text2.getText().toString().trim());
        map.put("Address",text3.getText().toString().trim() );
        map.put("E-mail", text4.getText().toString());
        map.put("Password", text5.getText().toString());
        map.put("Business_Name", text6.getText().toString());
        map.put("Phone", text7.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Sellers")
                .child(FirebaseAuth.getInstance().getUid())
                .setValue(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this,"Registration onSuccess",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();
            }
        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"onFailure"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

    }
//    private void  registerSeller(){
//        String Name = text2.getText().toString();
//        String  Address = text3.getText().toString();
//        String  Email = text4.getText().toString();
//        String  Password = text5.getText().toString();
//        String  Business_Name = text6.getText().toString();
//        String Phone = text7.getText().toString();
//         DatabaseReference rootRef;
//        rootRef = FirebaseDatabase.getInstance().getReference();
//
//        String sid=mAuth.getCurrentUser().getUid();
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("Sid", sid);
//        map.put("Name", Name);
//        map.put("Address", Address);
//        map.put("E-mail", Email);
//        map.put("Password", Password);
//        map.put("Business_Name", Business_Name);
//        map.put("Phone", Phone);
//        rootRef.child("Sellers").child(sid).updateChildren(map)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Log.i("xyz","onComplete");
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//                Log.i("xyz","onFailure");
//            }
//        });

    }

