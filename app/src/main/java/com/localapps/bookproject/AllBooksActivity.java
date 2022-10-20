package com.localapps.bookproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;





import java.util.ArrayList;
import com.localapps.bookproject.CustomAdapter.*;
public class AllBooksActivity extends AppCompatActivity {
    private RecyclerView allBooks ;
private ArrayList<Book> Books ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_books);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        allBooks = findViewById(R.id.allbooks);
        Intent intent = getIntent();
        Books   =  Utils.getInstance(this).getAllBooks();
        if(null!= intent){
           int type =   intent.getIntExtra( MainActivity.TYPE ,-1);
           switch (type)
           {
               case MainActivity.ALL:
                   Books =    Utils.getInstance(this).getAllBooks();
                   break;
               case MainActivity.ALREADY:
                   Books =    Utils.getInstance(this).getReadedBooks();
                   break;
               case MainActivity.CURRENT:
                   Books =     Utils.getInstance(this).getCurrentlyread();
                   break;
               case MainActivity.WISHLISt:
                   Books =     Utils.getInstance(this).getWishlist();
                   break;
               case MainActivity.FAVORIt:
                   Books =     Utils.getInstance(this).getFavorits();
                   break;
               default:
                   Books =     Utils.getInstance(this).getAllBooks();
           }

            CustomAdapter adapter = new CustomAdapter(this,type);

            adapter.setBooklist(Books);
            allBooks.setAdapter(adapter);
            allBooks.setLayoutManager(new LinearLayoutManager(this));
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case  android.R.id.home:
                onBackPressed( );
                break ;
            default:
                break;



        }
        return super.onOptionsItemSelected(item);

    }
}