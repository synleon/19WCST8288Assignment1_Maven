package logic;

import dao.PlayerDAO;
import entity.Player;
import java.sql.Date;
import java.time.Clock;
import java.time.Instant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class PlayerLogic extends GenericLogic<Player, PlayerDAO> {

    public final static String FIRST_NAME = "firstName";
    public final static String LAST_NAME = "lastName";
    public final static String JOINED = "joined";
    public final static String EMAIL = "email";
    public final static String ID = "id";

    public final static int MAXLEN_ID = 16;
    public final static int MAXLEN_FIRSTNAME = 32;
    public final static int MAXLEN_LASTNAME = 32;


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
    public Player createEntity(Map<String, String[]> parameterMap) throws IllegalFormParameterException {
        Map<String, String> errorMessages = new HashMap<>();
        String playerid = parameterMap.get(ID)[0];
        String firstName = parameterMap.get(FIRST_NAME)[0];
        String lastName = parameterMap.get(LAST_NAME)[0];
        String email = parameterMap.get(EMAIL)[0];


        if (playerid == null || playerid.length() == 0) {
            errorMessages.put("idError", "* id can not be empty!");
        }
        else if (playerid.length() > MAXLEN_ID) {
            errorMessages.put("idError", "* length of id can not exceed " + MAXLEN_ID);
        }

        if (firstName == null || firstName.trim().length() == 0) {
            errorMessages.put("firstNameError", "* firstName can not be empty!");
        }
        else if (firstName.trim().length() > MAXLEN_FIRSTNAME) {
            errorMessages.put("firstNameError", "* length of firstName can not exceed " + MAXLEN_FIRSTNAME);
        }

        if (lastName == null || lastName.trim().length() == 0) {
            errorMessages.put("lastNameError", "* lastName can not be empty!");
        }
        else if (lastName.trim().length() > MAXLEN_LASTNAME) {
            errorMessages.put("lastNameError", "* length of lastName can not exceed " + MAXLEN_LASTNAME);
        }

        // valid email format x@x.xx "[a-zA-Z]\w*@\w+.[a-zA-Z]{2,4}"
        if (email != null && !email.trim().isEmpty() && !email.matches("[a-zA-Z]\\w*@\\w+.[a-zA-Z]{2,4}")) {
            errorMessages.put("emailError", "* email must be in x@x.xx format");
        }

        if (errorMessages.isEmpty()) {
            // no error parameter found, let's create the entity
            Player player = new Player();

            // set date of joined, use default if not specified
            if (parameterMap.containsKey(JOINED)) {
                player.setJoined(Date.valueOf(parameterMap.get(JOINED)[0]));
            }
            else {
                player.setJoined(Date.from(Instant.now(Clock.systemDefaultZone())));
            }

            // set player id
            player.setId(Integer.valueOf(playerid));

            // set first name
            player.setFirstName(firstName.trim());

            // set last name
            player.setLastName(lastName.trim());

            // set email
            if (email != null) {
                player.setEmail(email);
            }
            return player;
        } else {
            throw new IllegalFormParameterException(errorMessages);
        }
    }
}
