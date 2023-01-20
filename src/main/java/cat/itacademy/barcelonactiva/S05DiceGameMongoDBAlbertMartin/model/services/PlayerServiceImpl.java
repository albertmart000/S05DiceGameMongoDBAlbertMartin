package cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.services;

import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain.Game;
import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain.Player;
import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.repository.IGameRepository;
import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements IPlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

    @Autowired
    private IGameRepository gameRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Player addPlayer(Player player) {
        player.setPlayerId(sequenceGeneratorService.generateSequence(Player.SEQUENCE_NAME));
        if (player.getName() == null || player.getName().isEmpty()) {
            player = playerRepository.save(player);
            player.setName("Anonymous");
        }
        if (playerRepository.existsByName(player.getName())) {
            player = playerRepository.save(player);
            player.setName(player.getName() + player.getPlayerId());
        }
        return playerRepository.save(player);
    }

    @Override
    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public Player updatePlayer(Long id, Player playerToUpdate) {
        Optional<Player> optionalPlayer = this.playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            Player playerUpdated = optionalPlayer.get();
            playerUpdated.setName(playerToUpdate.getName());
            playerUpdated.setRegistrationDate(playerToUpdate.getRegistrationDate());
            return playerRepository.save(playerUpdated);
        }

        return null;
    }

//    Post post = postRepository.findAll().get(1);
//
//    Comment comment = new Comment();
//    comment.setComment("comment");
//    comment.setRating(5);
//
//    post.addComment(comment);
//    postRepository.save(post);

    //   this.patients.add(patient);
//}


    @Override
    public Game addGame(Long playerId, Game game) {
        Optional<Player> optionalPlayer = getPlayerById(playerId);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            // Game game = new Game();
            game.setPlayer(player);
            //player.getGameList().add(game);
            //player.addGame(gameRepository);
            //playerRepository.save(player);
            return gameRepository.save(game);
        }
        return null;
    }

    @Override
    public Boolean deletePlayer(Long id) {
        playerRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> getWinningPlayer() {
        List<Player> playerList = getAllPlayers();
        if (playerList == null || playerList.size() == 0) {
            return Optional.empty();
        }
        Comparator<Player> comparator = Comparator.comparing(Player::getRateGamesWon);
        return Optional.of(playerList.stream()
                .sorted(comparator).toList()
                .get(playerList.size() - 1));
    }

    @Override
    public Optional<Player> getLosingPlayer() {
        List<Player> playerList = getAllPlayers();
        if (playerList == null || playerList.size() == 0) {
            return Optional.empty();
        }
        Comparator<Player> comparator = Comparator.comparing(Player::getRateGamesWon);
        return Optional.of(playerList.stream()
                .sorted(comparator).toList()
                .get(0));
    }

    @Override
    public List<Player> getRankingPlayers() {
        List<Player> playerList = getAllPlayers();
        Comparator<Player> comparator = Comparator.comparing(Player::getRateGamesWon);
        return playerList.stream()
                .sorted(comparator.reversed()).toList();
    }

}
