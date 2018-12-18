package com.kmu.mma_ver1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kmu.mma_ver1.models.LoginUser;
import com.kmu.mma_ver1.models.Member;

import java.util.Iterator;

public class RegLogActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private FirebaseDatabase mDatabase;

    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_log);


        Button regiBtn = (Button) findViewById(R.id.registerBtn);
        regiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regisMember();
            }
        });
    }
    private void regisMember(){
        mDatabase = FirebaseDatabase.getInstance();
        userRef = mDatabase.getReference("users");

        EditText edtName = (EditText) findViewById(R.id.idName);
        EditText edtID = (EditText) findViewById(R.id.logId);
        EditText edtPassword = (EditText) findViewById(R.id.logpassword);

        String inputName = edtName.getText().toString();
        String inputID = edtID.getText().toString();
        String inputPassword = edtPassword.getText().toString();
        String key = userRef.push().getKey();
        userRef.push().setValue(new LoginUser(inputName, inputID, inputPassword,key));
        startActivity(new Intent(RegLogActivity.this, LoginActivity.class));
        finish();

    }
}
