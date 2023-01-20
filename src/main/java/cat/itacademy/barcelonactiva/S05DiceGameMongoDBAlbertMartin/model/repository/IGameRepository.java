package cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.repository;

import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IGameRepository extends MongoRepository<Game, Long> {

    @Transactional
    void deleteByPlayerPlayerIdIs(Long playerId);

    List<Game> findAllByPlayerPlayerId(Long playerId);

}