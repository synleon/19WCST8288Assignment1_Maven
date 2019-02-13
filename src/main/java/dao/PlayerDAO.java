package dao;

import entity.Player;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerDAO extends GenericDAO<Player>{

    public PlayerDAO() {
        super(Player.class);
    }

    public List<Player> findAll() {
        return findResults( "Player.findAll", null);
    }

    public Player findById(int id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        return findResult("Player.findById", parameters);
    }

    public List<Player> findByFirstName(String firstname) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstName", firstname);
        return findResults("Player.findByFirstName", parameters);
    }

    public List<Player> findByLastName(String lastname) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("lastName", lastname);
        return findResults("Player.findByLastName", parameters);
    }

    public List<Player> findByJoined(Date date) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("joined", date);
        return findResults("Player.findByJoined", parameters);
    }

    public Player findByEmail(String email) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);
        return findResult("Player.findByEmail", parameters);
    }
}
