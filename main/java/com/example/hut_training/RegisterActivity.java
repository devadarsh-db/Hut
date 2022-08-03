package com.example.hut_training;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    Button b2,b3;
    EditText t1,t2;
    RegisterDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerlayout);
        b2=(Button) findViewById(R.id.b2);
        b3=(Button) findViewById(R.id.b3);
        t1=(EditText) findViewById(R.id.t1);
        t2=(EditText) findViewById(R.id.t2);
        db=new RegisterDB(this);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=t1.getText().toString();
                String password=t2.getText().toString();
                if (username.equals("")||password.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Username or Password is Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean status=db.insertdata(username,password);
                    if (status==true)
                    {
                        Toast.makeText(RegisterActivity.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Registration Unsuccessfull", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("USERNAME :" + res.getString(0) + "\n");
                    buffer.append("PASSWORD :" + res.getString(1) + "\n");

                }
                showMessage("Data", buffer.toString());
            }
        });

    }

    public void showMessage(String title, String Message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();
        }

}
