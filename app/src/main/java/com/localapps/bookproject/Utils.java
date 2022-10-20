package com.localapps.bookproject;

import android.content.Context;
import android.content.SharedPreferences;

import com.bumptech.glide.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
 // used to stor the data into the  andoird machin folders
public class Utils {
    private static Gson JSON ;
    private static     SharedPreferences sharedPref;
    public static final String ALL = "allbooks", FAVORITS = "favorits" , ALREADYREAD = "alreadyread" , WISHLIST = "wishlist" , CURRENT = "currentlist" ;
    private static final String preference_file_key = "filekey";
    private static Context utilContext ;
    private static Utils instatce ;




    public  Book getbookbyId(int id ){
        for (int i = 0 ; i < getAllBooks().size();i++){
            Book book = getAllBooks().get(i);
            if(book.getId() == id){
                return  book;
            }
        }
        return null ;
    }
    private static void initData(){
        System.out.println("--------data initialed even the app already have data on it ");
 sharedPref = utilContext.getSharedPreferences( preference_file_key, Context.MODE_PRIVATE);
        ArrayList<Book>  books = new ArrayList<>();
        books.add(new Book(12, 1,"purple", "mohamed","https://cdn.lifehack.org/wp-content/uploads/2015/03/purple.jpg", "a very good book for studying perposses", " a very long desc but it's shorter than the first one you already know"));
        books.add(new Book(12, 2,"poor dad and rich dad", "mohamed","https://www.richdad.com/MediaLibrary/RichDad/Images/books/rich-dad-poor-dad/rdpd-front-cover-20th(882x1332-144dpi).jpg", "a very good book for studying perposses", " a very long desc but it's shorter than the first one you already know"));
        books.add(new Book(12, 3,"without merite", "abdelouahed fanan","https://images.gr-assets.com/misc/1513113288-1513113288_goodreads_misc.jpg", "a very good book for studying perposses", " a very long desc but it's shorter than the first one you already know"));
        books.add(new Book(12, 4,"quotes about books & reading", "abdelouahed fanan","https://m.media-amazon.com/images/I/41XI94pqPNS.jpg", "a very good book for studying perposses", " a very long desc but it's shorter than the first one you already know"));
        books.add(new Book(12, 5,"quotes about books & reading", "abdelouahed fanan","https://m.media-amazon.com/images/I/41XI94pqPNS.jpg", "a very good book for studying perposses", " a very long desc but it's shorter than the first one you already know"));

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Utils.ALL, JSON.toJson(books));
        editor.putString(Utils.FAVORITS, JSON.toJson(books));
        editor.commit();
    }

    private  ArrayList<Book> getbooks(String key){
        String allbooks = sharedPref.getString(key, null);


        System.out.println( "all books ayad" +allbooks);
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book>  listofbooks  =  JSON.fromJson(allbooks,type);
        return listofbooks;
    }
    public  Boolean setbooks(String key,Book newbook){
        String allbooks = sharedPref.getString(key, null);
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book>  listofbooks  ;
        if(allbooks == null){
            listofbooks = new ArrayList<Book>();
        }
        else{
           listofbooks =  JSON.fromJson(allbooks,type);
        }
        listofbooks.add(newbook);
        sharedPref = utilContext.getSharedPreferences( preference_file_key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key,JSON.toJson(listofbooks) );
        editor.commit();
        return true;
    }
               public  ArrayList<Book> getAllBooks() {return getbooks(Utils.ALL);}
                public  ArrayList<Book> getFavorits() {
 System.out.println("should ruturn " + getbooks(Utils.FAVORITS));
            return getbooks(Utils.FAVORITS);}


                public  ArrayList<Book> getReadedBooks() {return getbooks(Utils.ALREADYREAD);}

                public  ArrayList<Book> getWishlist() {
                    return getbooks(Utils.WISHLIST);
                }

                public  ArrayList<Book> getCurrentlyread() {return getbooks(Utils.CURRENT);}

    private  Utils(Context context){
        JSON = new Gson();
        this.utilContext = context;

        sharedPref = utilContext.getSharedPreferences( preference_file_key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if(null == getFavorits()){
           editor.putString(Utils.FAVORITS,JSON.toJson(new Book(12, 1,"purple", "mohamed","https://cdn.lifehack.org/wp-content/uploads/2015/03/purple.jpg", "a very good book for studying perposses", " a very long desc but it's shorter than the first one you already know")) );


        }
        if(null == getAllBooks()){
            initData();
            }
        if(null == getReadedBooks()){
            System.out.println("------------readed book is empt-y--");
             ArrayList<Book> bokk = new ArrayList<>();
             bokk.add(new Book(12, 1,"purple", "mohamed","https://cdn.lifehack.org/wp-content/uploads/2015/03/purple.jpg", "a very good book for studying perposses", " a very long desc but it's shorter than the first one you already know"));
            editor.putString(Utils.ALREADYREAD,JSON.toJson( bokk) );
            System.out.println( " awdi  " +sharedPref.getString(Utils.ALREADYREAD, null) );

        }
        if(null == getWishlist()){
            editor.putString(Utils.WISHLIST,JSON.toJson( new ArrayList<Book>() ) );


        }
        if(null == getCurrentlyread()){
            editor.putString(Utils.CURRENT,JSON.toJson( new ArrayList<Book>() ) );


        }
        editor.commit();
    }
    public static Utils getInstance(Context context){
        if(null != instatce ){
            return instatce;
        }
        else{
            return  new Utils(context);
        }
    }
}
