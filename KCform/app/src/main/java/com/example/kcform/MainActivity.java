package com.example.kcform;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String[] items= {"Android development" , "IOS development" , "Games development" , "Web development"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    int select_photo=1;
    Uri uri;
    ImageView imageView;
    EditText name,age;
    AutoCompleteTextView path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText name = findViewById(R.id.name);
        EditText age = findViewById(R.id.age);
        AutoCompleteTextView path = findViewById(R.id.path);
        ImageView image = findViewById(R.id.photo);
        Button submit = findViewById(R.id.submit);
        Button uploadphoto = findViewById(R.id.upload_photo);
        imageView = findViewById(R.id.photo);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                if (TextUtils.isEmpty(name.getText().toString()))
                {
                    name.setError("This field is required");
                    return;
                }

                if (TextUtils.isEmpty(age.getText().toString()))
                {
                    age.setError("This field is required");
                    return;
                }

                
                String namedata = name.getText().toString();
                int agedata = Integer.parseInt(age.getText().toString());
                String pathdata = path.getText().toString();
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("nameintent", namedata);
                intent.putExtra("ageintent", agedata);
                intent.putExtra("pathintent",pathdata);
                startActivity(intent);


            }
        });



        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,select_photo);
            }
        });
        autoCompleteTxt=findViewById(R.id.path);
        adapterItems= new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id)
            {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==select_photo && resultCode==RESULT_OK && data!=null && data.getData() !=null)
        {
            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}