package logic;

import dao.UsernameDAO;
import entity.Player;
import entity.Username;

import java.sql.Date;
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
        Username user = new Username();
        if(parameterMap.containsKey(PLAYER_ID)){
            user.setPlayerid(Integer.valueOf(parameterMap.get(PLAYER_ID)[0]));
        }
        user.setUsername(parameterMap.get(USERNAME)[0]);
        return user;
    }
}
