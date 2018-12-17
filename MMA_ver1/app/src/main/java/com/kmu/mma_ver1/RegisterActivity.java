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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.kmu.mma_ver1.models.Member;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseDatabase mDatabase;

    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button RegBtn= (Button) findViewById(R.id.registerBtn);
        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMember();
            }
        });


    }

    private void addMember(){

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userRef = mDatabase.getReference("users").child(user.getUid()).child("member");

        EditText edtId = (EditText) findViewById(R.id.idId);
        EditText edtName = (EditText) findViewById(R.id.idName);
        EditText edtEmail = (EditText) findViewById(R.id.idEmail);

        String inputId = edtId.getText().toString();
        String inputName = edtName.getText().toString();
        String inputEmail = edtEmail.getText().toString();

        if(inputId.isEmpty()){
            Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
        }else if(inputName.isEmpty()){
            Toast.makeText(this, "성명을 입력해주세요", Toast.LENGTH_SHORT).show();
        }else if(inputEmail.isEmpty()){
            Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
        }

        userRef.push().setValue(new Member(inputId,inputName,inputEmail));
        edtId.setText("");
        edtName.setText("");
        edtEmail.setText("");
        Toast.makeText(this, "회원등록이 완료되었습니다", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));

    }
}
