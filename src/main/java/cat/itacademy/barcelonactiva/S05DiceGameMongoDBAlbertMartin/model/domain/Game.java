package cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Document
public class Game implements Serializable {

    //    private Long gameId;
    int idPlayer;
    String playerName;
    int dice1 = Dice.throwDice();
    int dice2 = Dice.throwDice();
    boolean playerHasWon = playerHasWon(dice1, dice2);

    //@DBRef
    //@DocumentReference
    //@JsonBackReference

     @Transient
    private Player player;



    public Game() {
    }

//    public Long getGameId() {
//        return gameId;
//    }


    public Game(int idPlayer, String playerName, int dice1, int dice2,
                boolean playerHasWon, Player player) {
        this.idPlayer = idPlayer;
        this.playerName = playerName;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.playerHasWon = playerHasWon;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

    public boolean isPlayerHasWon() {
        return playerHasWon;
    }

    public boolean playerHasWon(int dice1, int dice2) {
        return dice1 + dice2 == 7;
    }

}



