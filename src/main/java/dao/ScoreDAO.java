package dao;

import entity.Score;

import java.util.List;

public class ScoreDAO extends GenericDAO<Score> {
    public ScoreDAO() {
        super(Score.class);
    }

    public List<Score> findAll() {
        return findResults( "Score.findAll", null);
    }
}
