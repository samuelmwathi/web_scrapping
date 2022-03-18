package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.PrecomputedTextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ProgressBar progressBar;
    RecyclerView.LayoutManager manager;
    List<Pest_model> pestModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        progressBar=findViewById(R.id.progressBar2);
        manager = new LinearLayoutManager(this);
        adapter = new Adapter(MainActivity.this, pestModelList);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        textView = findViewById(R.id.view_result);
        imageView = findViewById(R.id.img);
        get getting = (get) new get().execute();

    }

    public class get extends AsyncTask {
        Document document;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                document = Jsoup.connect("https://www.loveproperty.com/gallerylist/" +
                        "75710/13-common-garden-pests-and-how-to-treat-them").get();
                Elements gallery__slide = document.getElementsByClass("gallery__slide");


                int size = gallery__slide.size();


                for (Element element:gallery__slide) {
                    String attr = element.getElementsByTag("img").attr("src");
                    String byClass = element.getElementsByClass("gallery__caption-title u-mb-").text();
                    String p = element.getElementsByClass("u-mb+").text();
                    Pest_model pest_model = new Pest_model(byClass, attr, p);
                    pestModelList.add(pest_model);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return pestModelList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (o != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(MainActivity.this, pestModelList.get(6).getImg(), Toast.LENGTH_SHORT).show();
                       adapter.notifyDataSetChanged();
                       progressBar.setVisibility(View.GONE);
                        progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));
                    }
                });

            }

           // Toast.makeText(MainActivity.this, pestModelList.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }
    }
}
