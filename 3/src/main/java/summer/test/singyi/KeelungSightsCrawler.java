package summer.test.singyi;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class KeelungSightsCrawler{


    Sight[] ans = new Sight[40];
    Sight s;
    int n = 0;
    ArrayList<Sight> sightItem =new ArrayList<>();
    public KeelungSightsCrawler(){

        try{
            String headHref = "https://www.travelking.com.tw";
            org.jsoup.nodes.Document document = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/").get();
            Element b = document.getElementById("guide-point");
            Elements items = b.getElementsByTag("li");

            for(Element item : items){
                String href = item.getElementsByTag("a").attr("href");

                org.jsoup.nodes.Document sight = Jsoup.connect(headHref+href).get();
                Element content = sight.getElementById("content");
                content.select("[class=author]").remove();
                content.select("[class=othermsg2]").remove();

                String zone = content.getElementsByClass("bc_last").text();
                String name = content.getElementsByClass("h1").text();
                String catagory = content.getElementsByClass("point_type").get(0).getElementsByTag("strong").get(0).text();

                Element p = content.getElementById("point_area");

                String photo = null;
                photo = p.getElementsByTag("meta").get(2).attr("content");

                String dec = content.getElementsByClass("text").first().text();
                String address = content.getElementsByClass("address").get(0).getElementsByTag("p").get(0).text();

                s = new Sight();
                s.setZone(zone);
                s.setSightName(name);
                s.setCatagory(catagory);
                s.setPhotoURL(photo);
                s.setAddress(address);
                s.setDescription(dec);
                sightItem.add(s);
                //System.out.println(s);
            }

        }catch(NullPointerException e){
            System.out.println(e.getMessage());

        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());

        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("wrong");
        }
    }

    public List<Sight> getItem(){
        return sightItem;
    }
}