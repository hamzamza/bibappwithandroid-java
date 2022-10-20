package com.localapps.bookproject;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String TYPE= "type";
    public static final int ALL= 1 ;
    public static final int CURRENT = 2 ;
    public static final int ALREADY= 3 ;
    public static final int WISHLISt= 4 ;
    public static final int FAVORIt= 5 ;






    private Button seeAllbtn , currentlyBtn , alreadyBtn , wishBtn, favoritsBtn , aboutBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("desingned and developedd with love by hamza_dev at COEGO dev-team ");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
// automatic dissmiss if you didn't put any code inside this
                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent  = new Intent(MainActivity.this , webActivity.class);
            startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });
        seeAllbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this  , AllBooksActivity.class);
                intent.putExtra(TYPE,MainActivity.ALL);
startActivity(intent);
            }
        });

        currentlyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this  , AllBooksActivity.class);
                intent.putExtra(TYPE,MainActivity.CURRENT);
                startActivity(intent);
            }
        });

        alreadyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this  , AllBooksActivity.class);
                intent.putExtra(TYPE,ALREADY);
                startActivity(intent);
            }
        });

        wishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this  , AllBooksActivity.class);
                intent.putExtra(TYPE,MainActivity.WISHLISt);
                startActivity(intent);
            }
        });

        favoritsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this  , AllBooksActivity.class);
                intent.putExtra(TYPE,MainActivity.FAVORIt);
                startActivity(intent);
            }
        });
    }


    private void initializeViews(){
        seeAllbtn = findViewById(R.id.seeAllBtn);
        currentlyBtn = findViewById(R.id.currentReadingBtn);
        alreadyBtn = findViewById(R.id.alreadyReadBtn);
        wishBtn = findViewById(R.id.wishListBtn);
        favoritsBtn = findViewById(R.id.favoritsBtn);
        aboutBtn = findViewById(R.id.aboutBtn);
    }

}