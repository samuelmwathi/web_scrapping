package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scrapper extends AsyncTask {
    Elements gallery__slide;
    TextView textView;
    ImageView imageView;
    Context context;
    Document document;
    List<String> tittle=new ArrayList<>();
    List<String> img=new ArrayList<>();
    List<String> description=new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    public Scrapper(   RecyclerView recyclerView, RecyclerView.Adapter adapter,Context context) {
        this.recyclerView=recyclerView;
        this.adapter=adapter;
        this.context=context;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
           document = Jsoup.connect("https://www.loveproperty.com/gallerylist/75710/13-common-garden-pests-and-how-to-treat-them").get();
             gallery__slide = document.getElementsByClass("gallery__slide");
        } catch (IOException e){
            e.printStackTrace();
        }
        return gallery__slide;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        gallery__slide=(Elements) o;
        if (o != null) {
         for(Element element:gallery__slide){

             String attr = element.getElementsByTag("img").attr("src");
             Elements byClass = element.getElementsByClass("gallery__caption-title u-mb-");
             Elements p = element.getElementsByTag("p");
             tittle.add(byClass.text());
             img.add(attr);
             description.add(p.text());
         }
         recyclerView.setAdapter(adapter);
//         textView.setText(description.get(0));
//         Glide.with(context).load(img.get(0)).into(imageView);
        }
    }

    public Scrapper() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }
}
