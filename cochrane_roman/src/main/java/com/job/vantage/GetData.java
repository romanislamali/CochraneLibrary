package com.job.vantage;


import java.io.BufferedWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetData {
    public static boolean Collection() {
        try {
            
            Urls_and_components urls_and_components = new Urls_and_components();
            GetElements getElements = new GetElements();
            Elements topics = getElements.
                                get_list_Elements(
                                    urls_and_components.GetBaseUrl(), 
                                    urls_and_components.GetTopics_list()
                                    );
            
            GetFile getFile = new GetFile(urls_and_components.GetFileName());
            BufferedWriter bw = getFile.create_file();
            for (Element topic : topics) {

                String topic_url = topic.select("a").attr("href");
                Elements pages = getElements.
                                    get_list_Elements(
                                        topic_url, 
                                        urls_and_components.GetPageList()
                                    ).select("a");

                for(Element page : pages){
                    String page_url_comp = page.attr("href");
                    String page_url_slice = page_url_comp.substring(page_url_comp.indexOf("&p_p_id="));
                    
                    Document curr_page_review = Jsoup.connect(topic_url + page_url_slice).get();
                    Elements articles = curr_page_review.select("div[class=search-results-item-body]");
                    
                    for(Element article : articles){
                        
                        String title = urls_and_components.GetTitle(article);
                        String author = urls_and_components.
                                            GetResults(
                                                article,
                                                urls_and_components.author_component()
                                             );
                        String date = urls_and_components.MapDate(
                                            urls_and_components.GetResults(article, 
                                                urls_and_components.Date()
                                            )
                        );

                        Write_to_File(urls_and_components.GetUrl(article), urls_and_components.GetTitle(topic), title, author, date , bw);
                        
                    }
            }
        }
            System.out.println("Done writing all Data.");
            bw.close();

        } catch (IOException e) {
            System.out.println("There some issues with URLs");
        }

        return true;
    }

    private static void Write_to_File(String topic_url, String topic, String title, String author, String date ,BufferedWriter bw) throws IOException {
        bw.write(topic_url + "|" + topic + "|" + title +"|" +author+ "|" + date);
        bw.newLine();
    }
}