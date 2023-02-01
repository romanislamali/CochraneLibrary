package com.job.vantage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;

public class Urls_and_components {
	private String library_url;
    private String authors;
    private String date;
    private List<String> months;
    private Map<String, String> map;
    private String baseUrl ;
    private String topics_list;
    private String reviews_list;
    private String pageList;
    private String fileName;
    private String url_slicer;
    

    public Urls_and_components(){
        baseUrl = "https://www.cochranelibrary.com/cdsr/reviews/topics"; 
        topics_list = "li[class=browse-by-list-item]";
        fileName = "cochrane-reviews.txt";
        pageList = "ul[class=pagination-page-list]";
        url_slicer = "&p_p_id=";
        reviews_list = "div[class=search-results-item-body]";
        library_url = "http://onlinelibrary.wiley.com";
        authors = "div[class=search-result-authors]";
        date = "div[class=search-result-date]";
        
        
        months = Arrays.asList("january","february","march","april","may","june","july","august","september","october","november","december");
        map = new HashMap<>();

        for(int i = 0 ;i < months.size(); i++){
            map.put(months.get(i) , ( (i+1)+"") );
        }


    }

    public String GetBaseUrl(){
        return baseUrl;
    }
    public String GetTopics_list(){
        return topics_list;
    }

    public String GetFileName(){
        return fileName;
    }

    public String GetPageList(){
        return pageList;
    }

    public String GetTitle(Element topic){
        return topic.select("a").first().text();
    }

    public String GetPageUrl(Element page){
        String page_url_comp = page.attr("href");
        String page_url_slice = page_url_comp.substring(page_url_comp.indexOf(url_slicer));
        return page_url_slice;
    }


    public String GetReviews(){
        return reviews_list;
    }

    public String GetUrl(Element element){
        return library_url + element.select("a").attr("href").substring(5);
    }

    public String GetResults(Element element ,String component){
        return element.select(component).first().text();
    }
    public String author_component(){
        return authors;
    }

    public String Date(){
        return date;
    }
    public String MapDate(String component){
        String[] temp_date = component.split(" ");
        temp_date[1] = map.get(temp_date[1].toLowerCase());
        return temp_date[2] + "-" + temp_date[1] + "-" + temp_date[0];
    }
    

}