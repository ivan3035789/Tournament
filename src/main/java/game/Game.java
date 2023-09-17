package game;

import domain.Player;
import exiption.NotRegisteredException;

public class Game {

    public int round(Player player1, Player player2) {
        int result = 0;
        if (player1.isRegistered() && player2.isRegistered()) {
            if (player1.getStrength() > player2.getStrength()) {
                result = 1;
            } else if (player1.getStrength() < player2.getStrength()) {
                result = 2;
            } else {
                result = 0;
            }
        } else {
            throw new NotRegisteredException("Игрок не зарегистрирован!");
        }
        return result;
    }
}
