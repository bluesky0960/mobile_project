package com.kmu.mma_ver1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.kmu.mma_ver1.models.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseDatabase mDatabase;

    private DatabaseReference userDBRef;

    private DatabaseReference userRef;

    private int numText = 0;

    private final int CLASS_ID = 0;

    LinearLayout dynamicLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userRef = mDatabase.getReference("users");



        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        userDBRef = mDatabase.getReference("users").child(user.getUid()).child("member");

        TextView userName = (TextView) findViewById(R.id.membername);
        TextView userPhone = (TextView) findViewById(R.id.memberPhone);
        userName.setText(name);


        Button remove = (Button)findViewById(R.id.deletebtn);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
            }
        });
    }

    public void remove(){
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userRef = mDatabase.getReference("users").child(user.getUid()).child("member");
        userDBRef.removeValue();
    }
}
