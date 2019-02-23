package logic;

import dao.ScoreDAO;
import entity.Score;

import java.util.Date;
import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ScoreLogic extends GenericLogic<Score, ScoreDAO> {

    public final static String ID = "scoreid";
    public final static String PLAYER_ID = "id";
    public final static String SCORE = "score";
    public final static String SUBMISSION = "submission";

    public ScoreLogic() {
        super(new ScoreDAO());
    }

    public List<Score> getAll() {
        // anonymous class object
        return get(new Supplier<List<Score>>() {
            @Override
            public List<Score> get() {
                return dao().findAll();
            }
        });
        // lambda
        // return get(() -> dao().findAll());

        // method reference
        // return get(dao()::findAll);
    }

    public Score getById(int id) {
        return get(() -> dao().findById(id));
    }

    public List<Score> getScoresWith(int score) {
        return get(() -> dao().findByScore(score));
    }

    public List<Score> getScoreOnDate(Date submission) {
        return get(() -> dao().findBySubmission(submission));
    }

    public List<Score> getScoresForPlayerID(int playerid) {
        return get(() -> dao().findByPlayerID(playerid));
    }

    public void deleteScoreWithId(int id) {
        Score score = getById(id);
        delete(score);
    }

    public void updateScoreWithID(Map<String, String[]> parameterMap) {
        Score score = getById(Integer.valueOf(parameterMap.get(ID)[0]));
        score.setScore(Integer.valueOf(parameterMap.get(SCORE)[0]));
        score.setSubmission(Date.from(Instant.now(Clock.systemDefaultZone())));

        update(score);
    }

    @Override
    public Score createEntity(Map<String, String[]> parameterMap) {
        Score score = new Score();

        score.setScore(Integer.valueOf(parameterMap.get(SCORE)[0]));
        score.setSubmission(Date.from(Instant.now(Clock.systemDefaultZone())));

        return score;
    }
}
