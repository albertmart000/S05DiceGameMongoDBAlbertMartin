package cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.services;

import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain.Dice;
import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain.Game;
import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.domain.Player;
import cat.itacademy.barcelonactiva.S05DiceGameMongoDBAlbertMartin.model.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements IPlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

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
        Optional<Player> optionalPlayer = this.playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            return playerRepository.findById(id);
        }
        return Optional.empty();
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

    @Override
    public Game addGame(Long playerId, Game game) {
        Game newGame = createGame(playerId);
        Optional<Player> optionalPlayer = getPlayerById(playerId);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            List<Game> listGamesPlayer = player.getGameList();
            listGamesPlayer.add(newGame);
            player.setGameList(listGamesPlayer);
            double rateGamesWon;
            int gamesWon = 0;
            for (Game gamePlayed : listGamesPlayer) {
                if (gamePlayed.getDice1() + gamePlayed.getDice2() == 7)
                    gamesWon++;
            }
            rateGamesWon = (double) gamesWon / listGamesPlayer.size() * 100;
            player.setRateGamesWon(rateGamesWon);
            playerRepository.save(player);
        }
        return newGame;
    }

    @Override
    public Game createGame(Long playerId) {
        int dice1 = Dice.throwDice();
        int dice2 = Dice.throwDice();
        return new Game(playerId, dice1, dice2);
    }

    @Override
    public List<Game> getGameListByPlayer(Long playerId) {
        Optional<Player> optionalPlayer = getPlayerById(playerId);
        if (optionalPlayer.isPresent()) {
            return optionalPlayer.get().getGameList();
        }
        return null;
    }

    @Override
    public void deletePlayer(Long playerId) {
        Optional<Player> optionalPlayer = getPlayerById(playerId);
        if (optionalPlayer.isPresent()) {
            playerRepository.deleteById(playerId);
        }
    }

    @Override
    public void deleteGameList(Long playerId) {
        Optional<Player> optionalPlayer = getPlayerById(playerId);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.setGameList(new ArrayList<>());
            player.setRateGamesWon(0.0);
            playerRepository.save(player);
        }
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
