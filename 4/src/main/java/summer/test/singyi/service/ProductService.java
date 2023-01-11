package summer.test.singyi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import summer.test.singyi.repository.ProductRepository;
import summer.test.singyi.entity.Sight;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Sight> getSight(String zone){
        return repository.findByZone(zone);
    }

    public List<Sight> getAllSight(){
        return repository.findAll();
    }

    public Sight createSight(Sight s){
        System.out.print(s);
        Sight sight = new Sight();
        sight.setSightName(s.getSightName());
        sight.setAddress(s.getAddress());
        sight.setCatagory(s.getCatagory());
        sight.setDescription(s.getDescription());
        sight.setZone(s.getZone());
        sight.setPhotoURL(s.getPhotoURL());

        return repository.save(sight);
    }

}
