package cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.repository;

import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerRepository extends MongoRepository<Player, Long> {

    boolean existsByName (String name);



}

