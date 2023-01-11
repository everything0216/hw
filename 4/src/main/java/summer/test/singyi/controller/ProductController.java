package summer.test.singyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import summer.test.singyi.repository.ProductRepository;
import summer.test.singyi.service.ProductService;
import summer.test.singyi.entity.Sight;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Service
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductService productService;

    @GetMapping("/SightAPI/{Zone}")
    public ResponseEntity <List<Sight>> getSight(@PathVariable("Zone") String zone){
        List<Sight> sightItem = productService.getSight(zone);
        return ResponseEntity.ok(sightItem);
    }

    //全部景點
    @GetMapping("/SightAPI")
    public List<Sight> getSights(){
        return productService.getAllSight();
    }

    @PostMapping("/add")
    public Sight saveSight (@RequestBody Sight s){
        return productService.createSight(s);
    }

/*
    //http://127.0.0.1:8080/index.html
    //http://127.0.0.1:8080/SightAPI?zone=中山
    @GetMapping("/SightAPI")
            //接收查詢字串
            @RequestParam(value = "zone",defaultValue = "") String zone){
        List<Sight> item = sightItem.stream()
                .filter(i -> i.getZone().contains(zone))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(item);
    }
*/
}
