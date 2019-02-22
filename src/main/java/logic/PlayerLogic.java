package logic;

import dao.PlayerDAO;
import entity.Player;
import javafx.print.PageLayout;

import javax.xml.bind.annotation.XmlType;
import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PlayerLogic extends GenericLogic<Player, PlayerDAO> {

    public static String FIRST_NAME = "firstName";
    public static String LAST_NAME = "lastName";
    public static String JOINED = "joined";
    public static String EMAIL = "email";
    public static String ID = "id";

    public PlayerLogic() {
        super(new PlayerDAO());
    }

    public List<Player> getAllPlayers() {
        // anonymous object
//        return get(new Supplier<List<Player>>() {
//            @Override
//            public List<Player> get() {
//                return dao().findAll();
//            }
//        });

        return get(() -> dao().findAll());
    }

    public Player getPlayerWithId(int id) {
        return get(() -> dao().findById(id));
    }

    public void deletePlayerWithID(int id) {
        Player player = getPlayerWithId(id);
        delete(player);
    }

    /**
     * update player identified by id with new information
     * @param parameterMap the id of the player entity needs to be updated
     */
    public void updatePlayerWithID(Map<String, String[]> parameterMap) {
        Player player = getPlayerWithId(Integer.valueOf(parameterMap.get(ID)[0]));
        player.setFirstName(parameterMap.get(FIRST_NAME)[0]);
        player.setLastName(parameterMap.get(LAST_NAME)[0]);
        player.setJoined(Date.valueOf(parameterMap.get(JOINED)[0]));
        player.setEmail(parameterMap.get(EMAIL)[0]);
        update(player);
    }

    public List<Player> getPlayerWithFirstName(String firstName) {
        return get(() -> dao().findByFirstName(firstName));
    }

    public List<Player> getPlayerWithLastName(String lastName) {
        return get(() -> dao().findByLastName(lastName));
    }

    public List<Player> getPlayerJoinedOn(Date date) {
        return get(() -> dao().findByJoined(date));
    }

    public Player getPLayerWithEmail(String email) {
        return get(() -> dao().findByEmail(email));
    }

    @Override
    public Player createEntity(Map<String, String[]> parameterMap) {
        Player player = new Player();
        if (parameterMap.containsKey(JOINED)) {
            player.setJoined(Date.valueOf(parameterMap.get(JOINED)[0]));
        }
        else {
            player.setJoined(Date.from(Instant.now(Clock.systemDefaultZone())));
        }
        player.setId(Integer.valueOf(parameterMap.get(ID)[0]));
        player.setFirstName(parameterMap.get(FIRST_NAME)[0]);
        player.setLastName(parameterMap.get(LAST_NAME)[0]);
        if (parameterMap.containsKey(EMAIL)) {
            player.setEmail(parameterMap.get(EMAIL)[0]);
        }
        return player;
    }
}
