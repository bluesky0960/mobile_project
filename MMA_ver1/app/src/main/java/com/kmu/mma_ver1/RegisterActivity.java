package com.kmu.mma_ver1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.kmu.mma_ver1.models.Member;


import java.util.ArrayList;
import java.util.Iterator;



public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseDatabase mDatabase;

    private DatabaseReference userRef;

    private int numText = 0;

    private final int CLASS_ID = 0;

    LinearLayout dynamicLayout;


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
        dynamicLayout = (LinearLayout) findViewById(R.id.dynamicArea);
        TextView ClassBtn = (TextView) findViewById(R.id.classBtn);
        ClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClass();
            }
        });
        Button MinusBtn = (Button) findViewById(R.id.minusBtn);
        MinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusClass();
            }
        });

    }

    private void minusClass(){
        if(numText <= 0){
            return;
        }
        EditText editText = (EditText)findViewById(CLASS_ID+numText);
        dynamicLayout.removeView(editText);
        numText--;
    }

    private void addClass(){
        numText++;

        EditText editText = new EditText(this);
        editText.setId(CLASS_ID + numText);
        editText.setHint(numText+". 수업 내용");

        dynamicLayout.addView(editText, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }


    private void addMember(){

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userRef = mDatabase.getReference("users").child(user.getUid()).child("member");

        final ArrayList<String> classList = new ArrayList<>();

        final EditText edtName = (EditText) findViewById(R.id.idName);
        final EditText edtPhone = (EditText) findViewById(R.id.idPhone);

        final String inputName = edtName.getText().toString();
        final String inputPhone = edtPhone.getText().toString();

        for(int i =1; i<=numText; i++){
            EditText edtClass = (EditText) findViewById(CLASS_ID+i);
            String inputClass = edtClass.getText().toString();
            classList.add(inputClass);
        }

        if(inputName.isEmpty()){
            Toast.makeText(this, "성명을 입력해주세요", Toast.LENGTH_SHORT).show();
        }else if(inputPhone.isEmpty()){
            Toast.makeText(this, "전화번호을 입력해주세요", Toast.LENGTH_SHORT).show();
        }

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> memberIterator = dataSnapshot.getChildren().iterator();
                while(memberIterator.hasNext()){
                    Member member1 = memberIterator.next().getValue(Member.class);
                    if(member1.getPhone().equals(inputPhone)){
                        return;
                    }
                }
                if (inputName.isEmpty()==false&inputPhone.isEmpty()==false) {

                    userRef.push().setValue(new Member(inputName, inputPhone, classList));
                    edtName.setText("");
                    edtPhone.setText("");
                    Toast.makeText(RegisterActivity.this, "회원등록이 완료되었습니다", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}
