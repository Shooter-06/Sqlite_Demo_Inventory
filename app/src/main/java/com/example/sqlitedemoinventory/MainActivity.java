package com.example.sqlitedemoinventory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText e1, e2, e3, e4, e5;
    Button btnadd, btnview, btnupdate, btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        e1 = findViewById(R.id.editTextTextPersonName);
        e2 = findViewById(R.id.editTextTextPersonName2);
        e3 = findViewById(R.id.editTextTextPersonName3);
        e4 = findViewById(R.id.editTextTextPersonName4);
        e5 = findViewById(R.id.editTextTextPersonName5);

        btnadd = findViewById(R.id.button);
        btnview = findViewById(R.id.button2);
        btnupdate = findViewById(R.id.button3);
        btndelete = findViewById(R.id.button4);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = mydb.addItems(e2.getText().toString(),e3.getText().toString(),
                        e4.getText().toString(),e5.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not Inserted ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isupdated = mydb.updateItems(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),
                        e4.getText().toString(),e5.getText().toString() );

                if( isupdated == true) {
                    Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer isdeleted = mydb.deleteproduct(e1.getText().toString());
                if (isdeleted > 0) {
                    Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.viewallProduct();
                if (res.getCount() == 0) {
                    ShowMessage ("Error", "No products found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("id " + res.getString(0) + " \n ");
                    buffer.append("Name " + res.getString(1) + " \n ");
                    buffer.append("Brand " + res.getString(2) + " \n ");
                    buffer.append("Cost " + res.getString(3) + " \n ");
                    buffer.append("Qty " + res.getString(4) + " \n ");
                }
                ShowMessage ("Data", buffer.toString());
            }
        });


    }

    public  void ShowMessage(String error, String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(error);
        builder.setCancelable(true);
        builder.setMessage(s);
        builder.show();

    }

}