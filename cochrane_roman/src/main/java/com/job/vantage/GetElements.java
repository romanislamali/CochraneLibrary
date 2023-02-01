package com.job.vantage;



import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;

public class GetElements {

    public Document GetDocument_page(String BaseUrl) throws IOException {
        return Jsoup.connect(BaseUrl).get();
    }

    public Elements get_list_Elements(String BaseUrl, String element) throws IOException {
        Document document = GetDocument_page(BaseUrl);
        return document.select(element);
    }


}