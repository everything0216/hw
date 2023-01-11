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

import java.awt.*;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin(value = "http://localhost:8080")
public class ProductController {

    List<Sight> sightItem =new ArrayList<>();
    Sight s;
    @PostConstruct
    private void initDB(){
        try{
            KeelungSightsCrawler crawler = new KeelungSightsCrawler();
            sightItem = crawler.getItem();

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
