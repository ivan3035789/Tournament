package manadger;

import domain.Player;
import exiption.NotRegisteredException;
import repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private final PlayerRepository playerRepository;

    public PlayerManager(PlayerRepository repository) {
        this.playerRepository = repository;
    }

    public void addNewPlayer(Player player) {
        playerRepository.addPlayer(player);
    }

    public List<Player> findAllPlayer() {
        return playerRepository.findAll();
    }

    public List<Player> findIsRegistered() {
        List<Player> tmp = new ArrayList<>();
        for (Player players : playerRepository.findAll()) {
            if (players.isRegistered()) {
                tmp.add(players);
            }
        }
        return tmp;
    }

    public List<Player> findIsNotRegistered() {
        List<Player> tmp = new ArrayList<>();
        for (Player player : playerRepository.findAll()) {
            if (!player.isRegistered()) {
                tmp.add(player);
            }
        }
        return tmp;
    }

    public List<Player> sortPlayer() {
        playerRepository.findAll().sort((player1, player2) -> player2.getId() - player1.getId());
        return playerRepository.findAll();
    }

    public Player findPlayerById(int id) {
        for (Player player : playerRepository.findAll()) {
            if (player.getId() == id) {
                return player;
            }
        }
        throw new NotRegisteredException("игрок с id = " + id + " не найден!");
    }

    public void changeStatusRegistered(Player player) {
        boolean flag = false;
        for (Player players : playerRepository.findAll()) {
            if (players.getId() == player.getId()) {
                players.setRegistered(true);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Игрок " + player + " уже зарегестрирован!");
        }
    }

    public boolean getStatusRegistered(Player players) {
        for (Player player : playerRepository.findAll()) {
            if (player.getId() == players.getId() && player.isRegistered()) {
                return player.isRegistered();
            }
        }
        throw new NotRegisteredException("Игрок " + players + " не зарегистрирован!");
    }
}
