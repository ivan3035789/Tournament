package repository;

import domain.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
    private final List<Player> playerList = new ArrayList<>();

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public List<Player> findAll() {
        return playerList;
    }

    public void remove(int id) {
        playerList.remove(id);
    }
}
