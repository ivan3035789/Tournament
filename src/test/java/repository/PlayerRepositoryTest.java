package repository;

import domain.Player;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class PlayerRepositoryTest {

    Player player1 = new Player(1, "Ant", 2, false);
    Player player2 = new Player(2, "A", 3, false);
    Player player3 = new Player(3, "n", 1, false);

    PlayerRepository playerRepository = new PlayerRepository();


    @Test
    public void mustAddOnePlayerToTheRepository() {
        playerRepository.addPlayer(player1);
        List<Player> expected = List.of(player1);
        List<Player> actual = playerRepository.findAll();
        assertIterableEquals(actual, expected);
    }

    @Test
    public void mustAddThreePlayerToTheRepository() {
        playerRepository.addPlayer(player1);
        playerRepository.addPlayer(player2);
        playerRepository.addPlayer(player3);

        List<Player> expected = Arrays.asList(player1, player2, player3);
        List<Player> actual = playerRepository.findAll();
        assertIterableEquals(actual, expected);
    }

    @Test
    public void mustDeleteAnPlayerFromTheRepositoryById() {
        playerRepository.addPlayer(player1);
        playerRepository.addPlayer(player2);
        playerRepository.addPlayer(player3);
        playerRepository.remove(1);

        List<Player> expected = Arrays.asList(player1, player3);
        List<Player> actual = playerRepository.findAll();
        assertIterableEquals(actual, expected);
    }
}
