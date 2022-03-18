package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Myview>{
    List<Pest_model> tittles;
    Context context;


    public Adapter(  Context context,List<Pest_model> tittles) {
        this.tittles = tittles;
        this.context=context;
    }

    @NonNull
    @Override
    public Adapter.Myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view   =LayoutInflater.from(context).inflate(R.layout.one_line_view,parent,false);
        return new Myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Myview holder, int position) {
        holder.tittle.setText(tittles.get(position).getName());
        holder.descrption.setText(tittles.get(position).getDescription());
        Glide.with(context).load(tittles.get(position).getImg()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return tittles.size();
    }

    public class Myview extends RecyclerView.ViewHolder{
        TextView tittle,descrption;
        ImageView imageView;
        public Myview(@NonNull View view) {
            super(view);
            tittle=view.findViewById(R.id.title);
            descrption=view.findViewById(R.id.description);
            imageView=view.findViewById(R.id.imageView);

        }
    }

    Intent intent=new Intent(Intent.ACTION_SEND);

}
