package summer.test.singyi;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    List<Sight> sightItem =new ArrayList<>();
    Sight s;
    @PostConstruct
    private void initDB(){
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

                s = new Sight();
                s.setZone(zone);
                s.setSightName(name);
                s.setCatagory(catagory);
                s.setPhotoURL(photo);
                s.setAddress(address);
                s.setDescription(dec);
                sightItem.add(s);

            }

        }catch(Exception e){
            System.out.println("wrong");
        }
    }

    //http://127.0.0.1:8080/SightAPI?zone=中山
    @GetMapping("/SightAPI")
    public ResponseEntity<List<Sight>> getSight(
            //接收查詢字串
            @RequestParam(value = "zone",defaultValue = "") String zone){
        List<Sight> item = sightItem.stream()
                .filter(i -> i.getZone().contains(zone))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(item);
    }
}
