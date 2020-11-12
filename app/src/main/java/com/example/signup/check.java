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
    private EditText rgname,rgpass,rgmob1,rgmob2,rgmob3,rginputText;
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
        rgpass=(EditText)findViewById(R.id.Password);
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
                    rootNode=FirebaseDatabase.getInstance();
                    reference=rootNode.getReference("users");

                    //reference.setValue("First data storage");

                 register();
                }
            });



    }
    private Boolean validateName(){
        String val=rgname.getEditableText().toString();

        if(val.isEmpty())
        {
            rgname.setError("Field cannot be empty");
            return false;
        }
        else
        {
            rgname.setError(null);
            return true;
        }

    }

    private Boolean validatePassword(){
        String val=rgpass.getEditableText().toString();
        String passwordVal="^"+
                ".{5,}"+
                "$";


        if(val.isEmpty())
        {
            rgpass.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(passwordVal))
        {
            rgpass.setError("Password is too weak. atleast 5 characters.");
            return false;
        }
        else
        {
            rgpass.setError(null);
            return true;
        }

    }

    private Boolean validateM1(){
        String val=rgmob1.getEditableText().toString();

        if(val.isEmpty())
        {
            rgmob1.setError("Field cannot be empty");
            return false;
        }
        else
        {
            rgmob1.setError(null);
            return true;
        }

    }

    private Boolean validateM2(){
        String val=rgmob2.getEditableText().toString();

        if(val.isEmpty())
        {
            rgmob2.setError("Field cannot be empty");
            return false;
        }
        else
        {
            rgmob2.setError(null);
            return true;
        }

    }

    private Boolean validateM3(){
        String val=rgmob3.getEditableText().toString();

        if(val.isEmpty())
        {
            rgmob3.setError("Field cannot be empty");
            return false;
        }
        else
        {
            rgmob3.setError(null);
            return true;
        }

    }

    private Boolean validateKW(){
        String val=rginputText.getEditableText().toString();

        if(val.isEmpty())
        {
            rginputText.setError("Field cannot be empty");
            return false;
        }
        else
        {
            rginputText.setError(null);
            return true;
        }

    }


    public void register()
    {
        if(!validateName()||!validateM1()||!validateM2()||!validateM3()||!validateKW())
        {
            return;
        }
        String name=rgname.getEditableText().toString();
        long ph1=Long.parseLong((rgmob1.getText().toString().trim()));
        long ph2=Long.parseLong((rgmob2.getText().toString().trim()));
        long ph3=Long.parseLong((rgmob3.getText().toString().trim()));
        String kw=rginputText.getEditableText().toString();
        String pass=rgpass.getEditableText().toString();
        users helperclass=new users(name,ph1,ph2,ph3,pass,kw);
        reference.child(kw).setValue(helperclass);


        Toast.makeText(getApplicationContext(),"input successfully",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,MainActivity.class);


        startActivity(i);
    }


}