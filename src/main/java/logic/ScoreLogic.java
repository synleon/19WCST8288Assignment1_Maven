package logic;

import dao.ScoreDAO;
import entity.Score;
import org.omg.CORBA.INTERNAL;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.util.Map;

public class ScoreLogic extends GenericLogic<Score, ScoreDAO> {

    public String ID = "scoreid";
    public String PLAYER_ID = "id";
    public String SCORE = "score";
    public String SUBMISSION = "submission";

    public ScoreLogic() {
        super(new ScoreDAO());
    }

    @Override
    protected Score createEntity(Map<String, String[]> parameterMap) {
        Score score = new Score();

        score.setScore(Integer.valueOf(parameterMap.get(SCORE)[0]));
        score.setSubmission(Date.from(Instant.now(Clock.systemDefaultZone())));

        return score;
    }
}
