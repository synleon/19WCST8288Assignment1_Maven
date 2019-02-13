package dao;

import entity.Score;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
@NamedQueries({
        @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s")
        , @NamedQuery(name = "Score.findById", query = "SELECT s FROM Score s WHERE s.id = :id")
        , @NamedQuery(name = "Score.findByScore", query = "SELECT s FROM Score s WHERE s.score = :score")
        , @NamedQuery(name = "Score.findBySubmission", query = "SELECT s FROM Score s WHERE s.submission = :submission")
        , @NamedQuery(name = "Score.findByPlayerID", query = "SELECT s FROM Score s WHERE s.playerid.id = :lpayerid")})
        */
public class ScoreDAO extends GenericDAO<Score> {
    public ScoreDAO() {
        super(Score.class);
    }

    public List<Score> findAll() {
        return findResults( "Score.findAll", null);
    }

    public Score findById(int id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        return findResult("Score.findById", parameters);
    }

    public List<Score> findByScore(int score) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("score", score);
        return findResults("Score.findByScore", parameters);
    }

    public List<Score> findBySubmission(Date date) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("submission", date);
        return findResults("Score.findBySubmission", parameters);
    }

    public List<Score> findByPlayerID(int playerid) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("lpayerid", playerid);
        return findResults("Score.findByPlayerID", parameters);
    }
}
