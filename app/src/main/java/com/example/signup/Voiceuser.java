package com.example.signup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Locale;

public class Voiceuser extends AppCompatActivity {




    private static final int REQUEST_CODE_SPEECH_INPUT=1000;
    TextView mTextTv,phNo1,phNo2,phNo3,Detail,mess,word;
    ImageButton mVoiceBtn;
    Button cli;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_voiceuser);


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);

        mTextTv=findViewById(R.id.textTv);
        Detail=findViewById(R.id.textdet);
        mess=findViewById(R.id.textmess);
        word=findViewById(R.id.textword);
        //cli=findViewById(R.id.click);
        phNo1=findViewById(R.id.phN1);
        phNo2=findViewById(R.id.phN2);
        phNo3=findViewById(R.id.phN3);



      //  mVoiceBtn=findViewById(R.id.voiceBtn);

       speak();


    }
    private void speak(){
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi say key word");

        try {
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
        }
        catch (Exception e){
            Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if(resultCode==RESULT_OK && null!=data){
                    ArrayList<String> result =data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    word.setText("Key word");
                    mTextTv.setText(result.get(0));
                    //sendAlert();
                    final String text= mTextTv.getText().toString();
                    DatabaseReference reff =FirebaseDatabase.getInstance().getReference().child("users").child(text);
                    reff.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String name=snapshot.child("name").getValue().toString();
                            String ph1=snapshot.child("mobileno1").getValue().toString();
                            String ph2=snapshot.child("mobileno2").getValue().toString();
                            String ph3=snapshot.child("mobileno3").getValue().toString();


                            phNo1.setText(ph1);
                            mess.setText("Don't worry "+name+"! our goal is to protect you..");
                            Detail.setText("Mobile numbers you entered..");
                            phNo1.setText(ph1);
                            phNo2.setText(ph2);
                            phNo3.setText(ph3);


                            String message = "It seems "+name+" is feeling unsafe.. Please make sure she/he is safe!"  ;
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(ph1,null,message,null,null);
                            smsManager.sendTextMessage(ph2,null,message,null,null);
                            smsManager.sendTextMessage(ph3,null,message,null,null);
                            Toast.makeText(getApplicationContext(),"Alert send successfully!",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                          //  Toast.makeText(getApplicationContext(),"Word you said is incorrect!",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                break;

            }
        }
    }




}


































