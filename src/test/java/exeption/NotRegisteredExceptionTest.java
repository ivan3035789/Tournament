package exeption;

import domain.Player;
import exiption.NotRegisteredException;
import manadger.PlayerManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.PlayerRepository;

import static org.junit.jupiter.api.Assertions.*;

public class NotRegisteredExceptionTest {
    Player player1 = new Player(1, "Ant", 2, false);
    Player player2 = new Player(2, "A", 3, false);
    Player player3 = new Player(3, "n", 1, false);

    PlayerRepository playerRepository = new PlayerRepository();
    PlayerManager playerManager = new PlayerManager(playerRepository);

    @BeforeEach
    public void setUp() {
        playerManager.addNewPlayer(player1);
        playerManager.addNewPlayer(player2);
        playerManager.addNewPlayer(player3);
    }

    @Test
    public void mustNotThrowAnException() {

        Player actual = playerManager.findPlayerById(1);
        assertEquals(player1, actual);
    }

    @Test
    public void mustThrowAnExceptionInAbsenceOfPlayer() {
        int id = 4;
        assertThrows(NotRegisteredException.class, () -> playerManager.findPlayerById(4), "Игрок с id = " + id + " не найден!");
    }

    @Test
    public void shouldNotGenerateAnExceptionIfThePlayerIsRegistered() {
        playerManager.changeStatusRegistered(player1);
        assertTrue(player1.isRegistered(), String.valueOf(playerManager.getStatusRegistered(player1)));
    }

    @Test
    public void mustThrowAnExceptionIfThereIsNoPlayerRegistration() {
        assertThrows(NotRegisteredException.class, () -> playerManager.getStatusRegistered(player1), "Игрок " + player1 + " не зарегистрирован!");
    }
}
