package cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain;

public class Dice {

    public static int throwDice() {
        return (int) Math.floor(Math.random()*6+1);
    }
}

