package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    Player player1 = new Player(1, "Ant", 2, true);

    @Test
    public void idMustBeEstablished() {
        int expectedId = 1;
        int actualId = player1.getId();
        assertEquals(expectedId, actualId);
    }
    @Test
    public void nameMustBeEstablished() {
        String expectedName = "Ant";
        String actualName = player1.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void powerLevelMustBeEstablished() {
        int expectedStrength = 2;
        int actualStrength = player1.getStrength();
        assertEquals(expectedStrength, actualStrength);
    }

    @Test
    public void statusMustBeEstablished() {
        boolean expectedIsRegistered = true;
        boolean actualIsRegistered = player1.isRegistered();
        assertTrue(expectedIsRegistered, String.valueOf(actualIsRegistered));
    }
}
