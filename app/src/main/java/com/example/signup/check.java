package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Key;

public class check extends AppCompatActivity {
    private EditText rgname,rgmob1,rgmob2,rgmob3,rginputText;
    private Button rsubmit;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    users u;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_check);
        rgname=(EditText)findViewById(R.id.name1);
        rgmob1=(EditText)findViewById(R.id.mobile1);
        rgmob2=(EditText)findViewById(R.id.mobile2);
        rgmob3=(EditText)findViewById(R.id.mobile3);
        rginputText=(EditText)findViewById(R.id.keyword);
        rsubmit=(Button) findViewById(R.id.submit);


        reference= FirebaseDatabase.getInstance().getReference().child("users");
        u=new users();
        rsubmit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                  register();
                }
            });


    }

    public void register()
    {
        u.setName(rgname.getText().toString().trim());
        Long phn1=Long.parseLong((rgmob1.getText().toString().trim()));
        u.setMobileno1(phn1);
        Long phn2=Long.parseLong((rgmob2.getText().toString().trim()));
        u.setMobileno2(phn2);
        Long phn3=Long.parseLong((rgmob3.getText().toString().trim()));
        u.setMobileno3(phn3);
        u.setInputText(rginputText.getText().toString().trim());

        u.setName(rgname.getText().toString().trim());
        reference.setValue(u);



        Toast.makeText(getApplicationContext(),"input successfully",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,MainActivity.class);


        startActivity(i);
    }


}