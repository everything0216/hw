package summer.test.singyi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import summer.test.singyi.entity.Sight;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Sight, String> {
    List<Sight> findByZone(String zone);

}
