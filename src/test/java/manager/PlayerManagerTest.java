package manager;

import domain.Player;
import exiption.NotRegisteredException;
import manadger.PlayerManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.PlayerRepository;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerManagerTest {
    PlayerRepository playerRepository = new PlayerRepository();
    PlayerManager playerManager = new PlayerManager(playerRepository);

    Player player1 = new Player(1, "Ant", 2, false);
    Player player2 = new Player(2, "A", 3, false);
    Player player3 = new Player(3, "n", 1, false);
    Player player10 = new Player(10, "Anton", 2, true);

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void onePlayerShouldBeAddedToTheList() {
        playerManager.addNewPlayer(player1);

        List <Player> expected = Arrays.asList(player1);
        List <Player> actual = playerManager.findAllPlayer();
        assertIterableEquals(actual, expected);
    }

    @Test
    public void threePlayersShouldBeAddedToTheList() {
        playerManager.addNewPlayer(player1);
        playerManager.addNewPlayer(player2);
        playerManager.addNewPlayer(player3);

        List <Player> expected = Arrays.asList(player1, player2, player3);
        List <Player> actual = playerManager.findAllPlayer();
        assertIterableEquals(actual, expected);
    }

    @Test
    public void onePlayersStatusShouldChange() {
        playerManager.addNewPlayer(player1);
        playerManager.addNewPlayer(player2);
        playerManager.addNewPlayer(player3);

        playerManager.changeStatusRegistered(player2);
        assertTrue(player2.isRegistered(), String.valueOf(playerManager.getStatusRegistered(player2)));
        assertThrows(NotRegisteredException.class, () -> playerManager.getStatusRegistered(player1), "Игрок " + player1 + " не зарегистрирован!");
        assertThrows(NotRegisteredException.class, () -> playerManager.getStatusRegistered(player1), "Игрок " + player3 + " не зарегистрирован!");
    }

    @Test
    public void thereMustBeTwoUnregisteredPlayersOnTheList() {
        playerManager.addNewPlayer(player1);
        playerManager.addNewPlayer(player2);
        playerManager.addNewPlayer(player3);
        playerManager.changeStatusRegistered(player2);

        List <Player> expected = Arrays.asList(player1, player3);
        List <Player> actual = playerManager.findIsNotRegistered();
        assertIterableEquals(actual, expected);
    }

    @Test
    public void playersShouldBeSortedInDescendingOrder() {
        playerManager.addNewPlayer(player3);
        playerManager.addNewPlayer(player1);
        playerManager.addNewPlayer(player2);

        List <Player> expected = Arrays.asList(player3, player2, player1);
        List <Player> actual = playerManager.sortPlayer();
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldDisplayMessageThatThePlayerIsAlreadyRegistered() {
        playerManager.changeStatusRegistered(player10);
        assertEquals("Игрок " + player10 + " уже зарегестрирован!", outContent.toString().trim());
    }
}
