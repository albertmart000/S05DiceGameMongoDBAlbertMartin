package cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "players")
public class Player implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "players_sequence";

    @Id
    private Long playerId;
    private String name;
    private LocalDate registrationDate = LocalDate.now();
    private double rateGamesWon;
    List<Game> gameList = new ArrayList<>();

    public Player() {
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public double getRateGamesWon() {
        return rateGamesWon;
    }

    public void setRateGamesWon(double rateGamesWon) {
        this.rateGamesWon = rateGamesWon;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }


}

