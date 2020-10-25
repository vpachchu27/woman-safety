package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
TextInputEditText regname,regmob1,regmob2,regmob3,reginputText;
private Button submit;

FirebaseDatabase rootNode;
DatabaseReference reference;
    users u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

       regname=findViewById(R.id.Editname);
       regmob1=findViewById(R.id.mob01);
        regmob2=findViewById(R.id.mob02);
        regmob3=findViewById(R.id.mob03);
       reginputText=findViewById(R.id.keyword);
        submit=(Button) findViewById(R.id.submit);


        reference=FirebaseDatabase.getInstance().getReference().child("users");
        u=new users();


    }
    public void signin(View v)
    {
        u.setName(regname.getText().toString().trim());
       Long phn1=Long.parseLong((regmob1.getText().toString().trim()));
       u.setMobileno1(phn1);
        Long phn2=Long.parseLong((regmob2.getText().toString().trim()));
        u.setMobileno2(phn2);
        Long phn3=Long.parseLong((regmob3.getText().toString().trim()));
        u.setMobileno3(phn3);
        u.setInputText(reginputText.getText().toString().trim());

        u.setName(regname.getText().toString().trim());
        reference.setValue(u);
        Toast.makeText(getApplicationContext(),"input successfully",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}