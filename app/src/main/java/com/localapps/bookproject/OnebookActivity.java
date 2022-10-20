package com.localapps.bookproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;

public class OnebookActivity extends AppCompatActivity {
    private Button btnWantToRad , btnAlreadyRead, btnCurrentlyRead, btnRavorits;
    private TextView bookName, author, txtPage, desc;
    private ImageView BookImage;
    private void initView(){
                 btnWantToRad = findViewById(R.id.wanttoread);
                btnAlreadyRead= findViewById(R.id.alreadyread);
                 btnCurrentlyRead=findViewById(R.id.currentlyread);
                btnRavorits=findViewById(R.id.favorite);
                 bookName=findViewById(R.id.onebookname);
                author=findViewById(R.id.onbookauthor);
                  txtPage=findViewById(R.id.pages);
                desc=findViewById(R.id.descreption);
                  BookImage=findViewById(R.id.onebookimage);
    }

    /*

          currentlyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this  , AllBooksActivity.class);
                intent.putExtra(TYPE,CURRENT);
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
                intent.putExtra(TYPE,WISHLISt);
                startActivity(intent);
            }
        });

        favoritsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this  , AllBooksActivity.class);
                intent.putExtra(TYPE,FAVORIt);
                startActivity(intent);
            }
        });

     */
private void setData(Book book){
    bookName.setText(book.getName());
    author.setText(book.getAuthor());
    txtPage.setText(String.valueOf(book.getPage())+ "  pages ");
    desc.setText(book.getLongDesc());
    Glide.with(this).asBitmap().load(book.getImageUrl()).into(BookImage);
    btnWantToRad.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
               if(!Utils.getInstance(OnebookActivity.this).getWishlist().contains(book)){
                   if(Utils.getInstance(OnebookActivity.this).setbooks(Utils.WISHLIST,book)){
                   Utils.getInstance(OnebookActivity.this).getWishlist().add(book);
               Toast.makeText(OnebookActivity.this, "added to want to read", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent( OnebookActivity.this  , AllBooksActivity.class);
                   intent.putExtra(MainActivity.TYPE,MainActivity.WISHLISt);
                   startActivity(intent);
           }}
               else {
                   Toast.makeText(OnebookActivity.this, "already added", Toast.LENGTH_SHORT).show();
               }
        }
    });
    btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!Utils.getInstance(OnebookActivity.this).getReadedBooks().contains(book)){
               if( Utils.getInstance(OnebookActivity.this).setbooks(Utils.ALREADYREAD,book)) {
                   Utils.getInstance(OnebookActivity.this).getReadedBooks().add(book);
                   Toast.makeText(OnebookActivity.this, "added  to want to already readed list", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(OnebookActivity.this, AllBooksActivity.class);
                   intent.putExtra(MainActivity.TYPE, MainActivity.ALREADY);
                   startActivity(intent);
               }
            }
            else {
                Toast.makeText(OnebookActivity.this, "already added", Toast.LENGTH_SHORT).show();
            }
        }



    });
    btnCurrentlyRead.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!Utils.getInstance(OnebookActivity.this).getCurrentlyread().contains(book)){
              if(  Utils.getInstance(OnebookActivity.this).setbooks(Utils.CURRENT,book)) {
                  Utils.getInstance(OnebookActivity.this).getCurrentlyread().add(book);
                  Toast.makeText(OnebookActivity.this, "added  to cuurently read list", Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(OnebookActivity.this, AllBooksActivity.class);
                  intent.putExtra(MainActivity.TYPE, MainActivity.CURRENT);
                  startActivity(intent);
              }
            }
            else {
                Toast.makeText(OnebookActivity.this, "already added", Toast.LENGTH_SHORT).show();
            }
        }
    });
    btnRavorits.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!Utils.getInstance(OnebookActivity.this).getFavorits().contains(book)){
              if(  Utils.getInstance(OnebookActivity.this).setbooks(Utils.FAVORITS,book)) {
                  Utils.getInstance(OnebookActivity.this).getFavorits().add(book);
                  Toast.makeText(OnebookActivity.this, "added  to favoritlist", Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(OnebookActivity.this, AllBooksActivity.class);
                  intent.putExtra(MainActivity.TYPE, MainActivity.FAVORIt);
                  startActivity(intent);
              }
            }
            else {
                Toast.makeText(OnebookActivity.this, "already added", Toast.LENGTH_SHORT).show();
            }
        }
    });

}



@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onebook);
        //Todo : get the date from recycler view in here
        Intent intent = getIntent();
   if(null != intent){
     int BookId =   intent.getIntExtra(CustomAdapter.BOOKID,-1);
       Book book = Utils.getInstance(OnebookActivity.this).getbookbyId(BookId);
       initView();
       setData(book);
   }
    }
}