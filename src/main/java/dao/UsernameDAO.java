package dao;

import entity.Username;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsernameDAO extends GenericDAO<Username> {
    public UsernameDAO() {
        super(Username.class);
    }

    public List<Username> findAll(){
        return findResults( "Username.findAll", null);
    }

    public Username findByPlayerID( int playerID){
        Map<String, Object> map = new HashMap<>();
        map.put("playerId", playerID);
        return findResult( "Username.findByPlayerId", map);
    }

    public List<Username> findByUsername( String username){
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        return findResults( "Username.findByUsername", map);
    }
}
