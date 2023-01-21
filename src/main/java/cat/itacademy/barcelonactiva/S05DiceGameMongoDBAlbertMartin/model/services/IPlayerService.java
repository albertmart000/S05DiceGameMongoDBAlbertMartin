package cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.services;

import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain.Game;
import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain.Player;

import java.util.List;
import java.util.Optional;

public interface IPlayerService {

    Player addPlayer(Player player);

    Player updatePlayer(Long id, Player playerToUpdate);

    void deletePlayer(Long id);

    Game addGame(Long Id, Game game);

    Game createGame(Long playerId);

    List<Game> getGameListByPlayer(Long playerId);

    void deleteGameList(Long playerId);

    Optional<Player> getPlayerById(Long id);

    List<Player> getAllPlayers();

    Optional<Player> getWinningPlayer();

    Optional<Player> getLosingPlayer();

    List<Player> getRankingPlayers();

}
