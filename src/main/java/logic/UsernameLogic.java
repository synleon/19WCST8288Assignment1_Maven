package logic;

import dao.UsernameDAO;
import entity.Player;
import entity.Username;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shariar
 */
public class UsernameLogic extends GenericLogic<Username,UsernameDAO>{

    public static final String PLAYER_ID = "id";
    public static final String USERNAME = "username";

    public UsernameLogic() {
        super( new UsernameDAO());
    }

    public List<Username> getAll(){
        return get(()->dao().findAll());
    }

    public Username getByID( int playerID){
        return get(()->dao().findByPlayerID(playerID));
    }

    public List<Username> getByUserName( String username){
        return get(()->dao().findByUsername(username));
    }

    /**
     * delete username by playerid
     * @param playerId identify the username entity needs to be deleted
     */
    public void deleteUsernameWithPlayerId(int playerId) {
        Username username = getByID(playerId);
        delete(username);
    }

    /**
     * update username identified by id with new information
     * @param parameterMap the id of the username entity needs to be updated
     */
    public void updateUsernameWithPlayerId(Map<String, String[]> parameterMap) {
        Username username = getByID(Integer.valueOf(parameterMap.get(PLAYER_ID)[0]));
        username.setUsername(parameterMap.get(USERNAME)[0]);
        update(username);
    }

    @Override
    public Username createEntity(Map<String, String[]> parameterMap) {
        Map<String, String> errorMessages = new HashMap<>();
        String playerid = parameterMap.get(PlayerLogic.ID)[0];
        String username = parameterMap.get(USERNAME)[0];

        if (playerid == null || playerid.length() == 0) {
            errorMessages.put("idError", "* id can not be empty!");
        }

        if (username == null || username.trim().length() == 0) {
            errorMessages.put("usernameError", "* username can not be empty!");
        }

        if (errorMessages.isEmpty()) {
            // no error parameter found, let's create the entity
            Username userName = new Username();
            userName.setPlayerid(Integer.valueOf(playerid));
            userName.setUsername(username);
            return userName;
        }
        else {
            throw new IllegalFormParameterException(errorMessages);
        }
    }
}
