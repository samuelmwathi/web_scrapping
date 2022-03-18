package com.example.myapplication;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

public interface Result_listener {
    public void ongetResults(List<String> document);
}
