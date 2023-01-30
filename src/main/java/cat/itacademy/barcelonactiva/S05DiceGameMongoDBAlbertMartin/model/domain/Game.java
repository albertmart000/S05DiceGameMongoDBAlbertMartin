package cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

@Document
public class Game implements Serializable {

    Long playerId;
    Integer dice1;
    Integer dice2;

    public Game() {
    }
    public Game(Integer dice1, Integer dice2) {
        super();
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    public Game(Long playerId, Integer dice1, Integer dice2) {
        super();
        this.playerId = playerId;
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Integer getDice1() {
        return dice1;
    }

    public Integer getDice2() {
        return dice2;
    }

}



