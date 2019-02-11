package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Shariar
 */
@Entity
@Table(name = "score", catalog = "ScoreDB", schema = "")
@NamedQueries({
        @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s")
        , @NamedQuery(name = "Score.findById", query = "SELECT s FROM Score s WHERE s.id = :id")
        , @NamedQuery(name = "Score.findByScore", query = "SELECT s FROM Score s WHERE s.score = :score")
        , @NamedQuery(name = "Score.findBySubmission", query = "SELECT s FROM Score s WHERE s.submission = :submission")
        , @NamedQuery(name = "Score.findByPlayerID", query = "SELECT s FROM Score s WHERE s.playerid.id = :playerid")})
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "score")
    private int score;
    @Basic(optional = false)
    @Column(name = "submission")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submission;
    @JoinColumn(name = "Player_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Player playerid;

    public Score() {
    }

    public Score(Integer id) {
        this.id = id;
    }

    public Score(Integer id, int score, Date submission) {
        this.id = id;
        this.score = score;
        this.submission = submission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getSubmission() {
        return submission;
    }

    public void setSubmission(Date submission) {
        this.submission = submission;
    }

    public Player getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Player playerid) {
        this.playerid = playerid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Score)) {
            return false;
        }
        Score other = (Score) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Score[ id=" + id + " ]";
    }

}
