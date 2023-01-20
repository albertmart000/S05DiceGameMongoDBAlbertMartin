package cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "players")
public class Player implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "players_sequence";

    @Id
    private long playerId;
    private String name;
    private LocalDate registrationDate = LocalDate.now();
    private double rateGamesWon;

    //@DocumentReference
    //@JsonManagedReference


    public Player(long playerId, String name, LocalDate registrationDate, double rateGamesWon, List<Game> gameList) {
        this.playerId = playerId;
        this.name = name;
        this.registrationDate = registrationDate;
        this.rateGamesWon = rateGamesWon;
        this.gameList = gameList;
    }

    //@DBRef
    List<Game> gameList = new ArrayList<>();

    public Player() {
    }



    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
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
        if (gameList.size() == 0) {
            this.rateGamesWon = 0.0;
        } else {
            for (Game game : gameList) {
                int gamesWon = 0;
                if (game.isPlayerHasWon()) {
                    gamesWon++;
                    this.rateGamesWon = (double) gamesWon / gameList.size() * 100;
                }
            }
        }
        return rateGamesWon;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }


}

