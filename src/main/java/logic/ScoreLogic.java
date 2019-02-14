package logic;

import dao.ScoreDAO;
import entity.Score;
import org.omg.CORBA.INTERNAL;

import java.sql.Date;
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
        if (parameterMap.containsKey(ID)) {
            score.setId(Integer.valueOf(parameterMap.get(ID)[0]));
        }
        // score.setPlayerid(parameterMap.get(PLAYER_ID)[0]);
        score.setScore(Integer.valueOf(parameterMap.get(SCORE)[0]));
        score.setSubmission(Date.valueOf(parameterMap.get(SUBMISSION)[0]));

        return score;
    }
}
