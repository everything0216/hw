package summer.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;


public class KeelungSightsCrawler{
        
    Sight[] s = new Sight[60];
    Sight[] ans = new Sight[40];
    int num = 0;
    int n = 0;
    public KeelungSightsCrawler(){

        try{
            String headHref = "https://www.travelking.com.tw";
            Document document = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/").get();
            Element b = document.getElementById("guide-point");
            Elements items = b.getElementsByTag("li");
            for(Element item : items){
                String href = item.getElementsByTag("a").attr("href");

                Document sight = Jsoup.connect(headHref+href).get();
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

                s[num] = new Sight();
                s[num].setZone(zone);
                s[num].setSightName(name);
                s[num].setCatagory(catagory);
                s[num].setPhotoURL(photo);
                s[num].setAddress(address);
                s[num].setDes(dec);
                num++;
            }

        }catch(Exception e){
            System.out.println("wrong");
        }
    }

    public Sight[] getItems(String z){
        for(int i = 0;i<num;i++){
            if(s[i].getZone().equals(z+"區")){
                ans[n] = new Sight();
                ans[n] = s[i];
                n++;
            }
        }
        return ans;
    }
}