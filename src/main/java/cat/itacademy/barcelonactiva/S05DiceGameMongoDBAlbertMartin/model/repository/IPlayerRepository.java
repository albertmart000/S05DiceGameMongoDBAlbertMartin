package cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.repository;

import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface IPlayerRepository extends MongoRepository<Player, Long> {

    boolean existsByName (String name);

}

