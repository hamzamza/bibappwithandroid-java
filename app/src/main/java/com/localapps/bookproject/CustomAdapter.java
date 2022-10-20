package com.localapps.bookproject;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private int type ;
    public static final String BOOKID = "bookId";
    private Context listContext ;
    private ArrayList<Book> Booklist ;



    public void setBooklist(ArrayList<Book> listbooks){
        this.Booklist = listbooks ;
        notifyDataSetChanged();
    }
    public void deletebook(Book book){
        Booklist.remove(book);
        notifyDataSetChanged();
    }


    public CustomAdapter(Context constext,int type ){
        this.listContext = constext;
        this.type = type ;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookcard ,parent, false) ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(Booklist.get(position).getName());
        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(listContext);
                builder.setMessage("are you sure you want to delete " + Booklist.get(position).getName());
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deletebook(Booklist.get(position));
                        Toast.makeText(listContext, "deleted succesffuly", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(listContext, "deleting canceled", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.create().show();
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(listContext, holder.name.getText()+" clicked ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(listContext,OnebookActivity.class);
                intent.putExtra(BOOKID,Booklist.get(position).getId() );
                listContext.startActivity(intent);
            }
        });
        Glide
                .with(listContext)
                .load(Booklist.get(position).getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.image);
        if(Booklist.get(position).getIsexpended()){
            TransitionManager.beginDelayedTransition((holder.parent));
            holder.expandlayout.setVisibility(View.VISIBLE);
            holder.downbtn.setVisibility(View.GONE);
        }
        else{
            TransitionManager.beginDelayedTransition((holder.parent));

            holder.expandlayout.setVisibility(View.GONE);
            holder.downbtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return  Booklist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,txtAuth,txtDesc;
        private Button deletebtn ;
        private ImageView image,upbtn, downbtn;
        private RelativeLayout expandlayout;
        private MaterialCardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deletebtn = itemView.findViewById(R.id.delete);
            parent = itemView.findViewById(R.id.parent2);
            name  = itemView.findViewById(R.id.name);
            image  = itemView.findViewById(R.id.imagebook);
            txtAuth = itemView.findViewById(R.id.second);
            txtDesc = itemView.findViewById(R.id.third);
            upbtn = itemView.findViewById(R.id.upbtn);
            downbtn = itemView.findViewById(R.id.downbtn);
            expandlayout = itemView.findViewById(R.id.expendeddetails);
            if(type == MainActivity.ALL){
                deletebtn.setVisibility(View.GONE);
            }
            downbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i  = getAdapterPosition();
                    Book book = Booklist.get(i);
                    book.setIsexpended(!book.getIsexpended());
                    notifyItemChanged(i);
                }
            });
            upbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i  = getAdapterPosition();
                    Book book = Booklist.get(i);
                    book.setIsexpended(!book.getIsexpended());
                    notifyItemChanged(i);
                }
            });
        }
    }
}
