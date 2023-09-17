package game;

import domain.Player;
import exiption.NotRegisteredException;
import manadger.PlayerManager;
import org.junit.jupiter.api.Test;
import repository.PlayerRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Player player1 = new Player(1, "Ant", 2, false);
    Player player2 = new Player(2, "A", 3, false);
    Player player3 = new Player(3, "n", 1, false);
    Player player4 = new Player(4, "K", 3, false);

    PlayerRepository playerRepository = new PlayerRepository();
    PlayerManager playerManager = new PlayerManager(playerRepository);
    Game game = new Game();

    @Test
    public void playersStatusShouldChangeToRegistered() {
        playerManager.addNewPlayer(player1);
        playerManager.addNewPlayer(player2);
        playerManager.addNewPlayer(player3);

        playerManager.changeStatusRegistered(player1);
        playerManager.changeStatusRegistered(player2);

        List<Player> expected = Arrays.asList(player1, player2);
        List<Player> actual = playerManager.findIsRegistered();

        assertIterableEquals(actual, expected);
    }

    @Test
    public void playerWithMorePowerMustWin() {
        playerManager.addNewPlayer(player1);
        playerManager.addNewPlayer(player2);
        playerManager.addNewPlayer(player3);

        playerManager.changeStatusRegistered(player1);
        playerManager.changeStatusRegistered(player2);

        int expected = 2;
        int actual = game.round(player1, player2);
        assertEquals(expected, actual);
    }

    @Test
    public void firstPlayerMustWin() {
        playerManager.addNewPlayer(player1);
        playerManager.addNewPlayer(player2);
        playerManager.addNewPlayer(player3);

        playerManager.changeStatusRegistered(player2);
        playerManager.changeStatusRegistered(player3);

        int expected = 1;
        int actual = game.round(player2, player3);
        assertEquals(expected, actual);
    }

    @Test
    public void draw() {
        playerManager.addNewPlayer(player1);
        playerManager.addNewPlayer(player2);
        playerManager.addNewPlayer(player3);
        playerManager.addNewPlayer(player4);

        playerManager.changeStatusRegistered(player2);
        playerManager.changeStatusRegistered(player4);

        int expected = 0;
        int actual = game.round(player2, player4);
        assertEquals(expected, actual);
    }

    @Test
    public void mustNotParticipateInTheTournamentIfTheStatusIsUnregistered() {
        playerManager.addNewPlayer(player1);
        playerManager.addNewPlayer(player2);
        playerManager.addNewPlayer(player3);

        playerManager.changeStatusRegistered(player1);
        playerManager.changeStatusRegistered(player3);

        assertThrows(NotRegisteredException.class, () -> game.round(player1, player2), "Игрок не зарегистрирован!");
    }

    @Test
    public void mustThrowAnExceptionIfOneOfThePlayersIsNotRegistered() {
        playerManager.addNewPlayer(player1);
        playerManager.addNewPlayer(player2);
        playerManager.addNewPlayer(player3);

        playerManager.changeStatusRegistered(player1);
        playerManager.changeStatusRegistered(player3);

        assertThrows(NotRegisteredException.class, () -> game.round(player2, player1), "Игрок не зарегистрирован!");
    }
}
