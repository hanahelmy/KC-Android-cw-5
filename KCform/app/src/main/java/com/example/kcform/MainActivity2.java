package com.example.kcform;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView studentname = findViewById(R.id.nametxt);
        TextView studentage = findViewById(R.id.agetxt);
        TextView studentpath = findViewById(R.id.pathtxt);


        Bundle bundle= getIntent().getExtras();

        String name = bundle.getString("nameintent");
        studentname.setText(name);

        int age = bundle.getInt("ageintent");
        studentage.setText(String.valueOf(age));

        String path = bundle.getString("pathintent");
        studentpath.setText(path);






    }
}