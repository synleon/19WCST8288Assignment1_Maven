package logic;

import dao.PlayerDAO;
import entity.Player;
import javafx.print.PageLayout;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PlayerLogic extends GenericLogic<Player, PlayerDAO> {

    public String FIRST_NAME = "firstName";
    public String LAST_NAME = "lastName";
    public String JOINED = "joined";
    public String EMAIL = "email";
    public String ID = "id";

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
    protected Player createEntity(Map<String, String[]> parameterMap ) {
        Player player = new Player();
        if(parameterMap.containsKey(ID)){
            player.setId(Integer.valueOf(parameterMap.get(ID)[0]));
        }
        player.setFirstName(parameterMap.get(FIRST_NAME)[0]);
        player.setLastName(parameterMap.get(LAST_NAME)[0]);
        player.setJoined(Date.valueOf(parameterMap.get(JOINED)[0]));
        player.setEmail(parameterMap.get(EMAIL)[0]);
        return player;
    }
}
